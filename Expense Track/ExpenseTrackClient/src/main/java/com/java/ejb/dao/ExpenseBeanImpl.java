package com.java.ejb.dao;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import com.java.ejb.bean.ExpenseBeanRemote;
import com.java.ejb.bean.ExpenseRemoteHelper;
import com.java.ejb.model.Expense;
import com.java.ejb.model.ExpenseSplits;
import com.java.ejb.model.TripGroup;
import com.java.ejb.model.User;

public class ExpenseBeanImpl {
	 static ExpenseBeanRemote remote;
	 static {
	      try {
	    	  remote = ExpenseRemoteHelper.lookupRemoteExpense();
	      } catch (NamingException e) {
	          e.printStackTrace();
	      }
	 }
	 
	 public String addExpenseEjb(Expense expense) throws ClassNotFoundException, SQLException {
		 TripGroup group = (TripGroup) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("groupDetails");
		 User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
		 expense.setTripGroup(group);
		 expense.setUser(user);
		 
		 return remote.addExpense(expense);
	 }
	  
	 public void settleExpensesForGroupEjb() throws ClassNotFoundException, SQLException {
		 TripGroup group = (TripGroup) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("groupDetails");
	    remote.settleExpensesForGroup(group.getId());
	 } 
	 public List<ExpenseSplits> loadOwedAmountsEjb(int groupId) throws ClassNotFoundException, SQLException {
		 User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
		 System.out.println("Input parameters: groupId=" + groupId + ", userId=" + user.getId());
		 return remote.getUnsettledSplitsForUser(user.getId(), groupId);
		    
		}
}
