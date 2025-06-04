package com.java.ejb.bean;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.java.ejb.dao.ExpenseDao;
import com.java.ejb.dao.ExpenseDaoImpl;
import com.java.ejb.model.Expense;
import com.java.ejb.model.ExpenseSplits;

/**
 * Session Bean implementation class ExpenseBean
 */
@Stateless
@Remote(ExpenseBeanRemote.class)
public class ExpenseBean implements ExpenseBeanRemote {
	
	static ExpenseDao daoImpl;
	static {
		daoImpl = new ExpenseDaoImpl();
	}

    /**
     * Default constructor. 
     */
    public ExpenseBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String addExpense(Expense expense) throws ClassNotFoundException, SQLException {
		return daoImpl.addExpense(expense);
	}

	@Override
	public void settleExpensesForGroup(int groupId) throws SQLException, ClassNotFoundException {
		daoImpl.settleExpensesForGroupDao(groupId);	
	}

	@Override
	public List<ExpenseSplits> getUnsettledSplitsForUser(int userId, int groupId)
			throws SQLException, ClassNotFoundException {
		return daoImpl.getUnsettledSplitsForUserDao(userId, groupId);
	}

}
