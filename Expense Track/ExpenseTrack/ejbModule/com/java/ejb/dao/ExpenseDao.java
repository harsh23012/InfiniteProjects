package com.java.ejb.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.ejb.model.Expense;
import com.java.ejb.model.ExpenseSplits;

public interface ExpenseDao {
	String addExpense(Expense expense) throws ClassNotFoundException, SQLException;
	void settleExpensesForGroupDao(int groupId) throws SQLException, ClassNotFoundException;
	List<ExpenseSplits> getUnsettledSplitsForUserDao(int userId, int groupId)throws SQLException, ClassNotFoundException;

}
