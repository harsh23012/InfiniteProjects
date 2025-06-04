package com.java.ejb.bean;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.java.ejb.model.Expense;
import com.java.ejb.model.ExpenseSplits;

@Remote
public interface ExpenseBeanRemote {
	String addExpense(Expense expense) throws ClassNotFoundException, SQLException;
	void settleExpensesForGroup(int groupId) throws SQLException, ClassNotFoundException;
	List<ExpenseSplits> getUnsettledSplitsForUser(int userId, int groupId)throws SQLException, ClassNotFoundException;
}
