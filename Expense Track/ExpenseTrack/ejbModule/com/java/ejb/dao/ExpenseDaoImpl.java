package com.java.ejb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.ejb.model.Expense;
import com.java.ejb.model.ExpenseSplits;
import com.java.ejb.model.User;
import com.java.ejb.util.ConnectionHelper;

public class ExpenseDaoImpl implements ExpenseDao{
	PreparedStatement psmt;
	@Override
	public String addExpense(Expense expense) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionHelper.getConnection();
		String sql = "INSERT INTO expenses (group_id, paid_by_user_id, description, category, total_amount, expense_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
		psmt = connection.prepareStatement(sql);
        psmt.setInt(1, expense.getTripGroup().getId());
        psmt.setInt(2, expense.getUser().getId());
        psmt.setString(3, expense.getDescription());
        psmt.setString(4, expense.getCategory());
        psmt.setDouble(5, expense.getTotal_amount());
        psmt.setDate(6, new java.sql.Date(expense.getExpense_date().getTime()));
        psmt.executeUpdate();
        
        return "GroupUser.jsp?faces-redirect=true";
	}
	public void settleExpensesForGroupDao(int groupId) throws SQLException, ClassNotFoundException {

	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = ConnectionHelper.getConnection();
	        conn.setAutoCommit(false); // start transaction

	        // 1) Get all group members with their collectedAmount (advances)
	        ps = conn.prepareStatement("SELECT user_id, collectedAmount FROM group_members WHERE group_id = ?");
	        ps.setInt(1, groupId);
	        rs = ps.executeQuery();

	        Map<Integer, Double> memberCollectedMap = new HashMap<>();
	        while (rs.next()) {
	            memberCollectedMap.put(rs.getInt("user_id"), rs.getDouble("collectedAmount"));
	        }
	        rs.close();
	        ps.close();

	        int memberCount = memberCollectedMap.size();
	        if (memberCount == 0) {
	            System.out.println("No members found in group " + groupId);
	            return;
	        }

	        // 2) Get total expenses of the group
	        ps = conn.prepareStatement("SELECT COALESCE(SUM(total_amount), 0) as totalExpense FROM expenses WHERE group_id = ?");
	        ps.setInt(1, groupId);
	        rs = ps.executeQuery();

	        double totalExpense = 0;
	        if (rs.next()) {
	            totalExpense = rs.getDouble("totalExpense");
	        }
	        rs.close();
	        ps.close();

	        double fairShare = totalExpense / memberCount;

	        // 3) Get amount paid by each user
	        ps = conn.prepareStatement("SELECT paid_by_user_id, SUM(total_amount) as paidSum FROM expenses WHERE group_id = ? GROUP BY paid_by_user_id");
	        ps.setInt(1, groupId);
	        rs = ps.executeQuery();

	        Map<Integer, Double> paidMap = new HashMap<>();
	        while (rs.next()) {
	            paidMap.put(rs.getInt("paid_by_user_id"), rs.getDouble("paidSum"));
	        }
	        rs.close();
	        ps.close();

	        // 4) Compute net balances per user = (paid + advance) - fairShare
	        Map<Integer, Double> netBalances = new HashMap<>();
	        for (Integer userId : memberCollectedMap.keySet()) {
	            double paid = paidMap.getOrDefault(userId, 0.0);
	            double advance = memberCollectedMap.get(userId);
	            double net = (paid + advance) - fairShare;
	            netBalances.put(userId, net);
	        }

	        // Separate creditors and debtors
	        List<Map.Entry<Integer, Double>> creditors = new ArrayList<>();
	        List<Map.Entry<Integer, Double>> debtors = new ArrayList<>();

	        for (Map.Entry<Integer, Double> entry : netBalances.entrySet()) {
	            if (entry.getValue() > 0.01) creditors.add(entry);
	            else if (entry.getValue() < -0.01) debtors.add(entry);
	        }

	        // 5) Clear existing splits for this group (optional)
	        ps = conn.prepareStatement("DELETE FROM expense_splits WHERE group_id = ?");
	        ps.setInt(1, groupId);
	        ps.executeUpdate();
	        ps.close();

	        // 6) Insert new expense splits based on net balances
	        ps = conn.prepareStatement(
	            "INSERT INTO expense_splits ( user_id, group_id, amount_owed, owed_to, is_settled, settled_at) " +
	            "VALUES ( ?, ?, ?, ?, false, NULL)");


	        for (Map.Entry<Integer, Double> debtorEntry : debtors) {
	            int debtorId = debtorEntry.getKey();
	            double debt = -debtorEntry.getValue();

	            for (Map.Entry<Integer, Double> creditorEntry : creditors) {
	                int creditorId = creditorEntry.getKey();
	                double credit = creditorEntry.getValue();

	                if (credit <= 0) continue;

	                double payment = Math.min(debt, credit);

	                if (payment > 0.01) {
	                    ps.setInt(1, debtorId);
	                    ps.setInt(2, groupId);
	                    ps.setDouble(3, payment);
	                    ps.setInt(4, creditorId);
	                    ps.executeUpdate();

	                    debt -= payment;
	                    credit -= payment;

	                    creditorEntry.setValue(credit);

	                    if (debt <= 0) break;
	                }
	            }
	        }

	        conn.commit();
	        System.out.println("Settlement done for group " + groupId);

	    } catch (SQLException e) {
	        if (conn != null) conn.rollback();
	        throw e;
	    } finally {
	        if (rs != null) rs.close();
	        if (ps != null) ps.close();
	        if (conn != null) conn.close();
	    }
	}
	
	public List<ExpenseSplits> getUnsettledSplitsForUserDao(int userId, int groupId) throws ClassNotFoundException, SQLException {
	    List<ExpenseSplits> splits = new ArrayList<>();

	    String sql = "SELECT es.id, es.amount_owed, es.owed_to, es.user_id, u1.name AS userName, " +
	                 "u2.id AS owedToId, u2.name AS owedToName " +
	                 "FROM expense_splits es " +
	                 "JOIN users u1 ON es.user_id = u1.id " +
	                 "JOIN users u2 ON es.owed_to = u2.id " +
	                 "WHERE es.user_id = ? AND es.group_id = ? AND es.is_settled = 0";

	    Connection conn = ConnectionHelper.getConnection();
	    psmt = conn.prepareStatement(sql);

	    psmt.setInt(1, userId);
	    psmt.setInt(2, groupId);

	    ResultSet rs = psmt.executeQuery();
	    while (rs.next()) {
	            ExpenseSplits split = new ExpenseSplits();
	            split.setId(rs.getInt("id"));
	            split.setAmount_owed(rs.getDouble("amount_owed"));
	            split.setOwedto(rs.getInt("owedToId"));

	            User u = new User();
	            u.setId(rs.getInt("owedToId"));
	            u.setName(rs.getString("owedToName"));
	            split.setUser(u);  // setting owedTo user here

	            splits.add(split);
	     }
	    return splits;
	}


}
