package com.java.ejb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import com.java.ejb.bean.GroupBeanRemote;
import com.java.ejb.bean.GroupRemoteHelper;
import com.java.ejb.bean.UserRemoteHelper;
import com.java.ejb.bean.UserStatelessBeanRemote;
import com.java.ejb.model.ExpenseSplits;
import com.java.ejb.model.GroupMembers;
import com.java.ejb.model.Role;
import com.java.ejb.model.TripGroup;
import com.java.ejb.model.User;

public class GroupBeanImpl {

    static GroupBeanRemote remote;
    static UserStatelessBeanRemote remoteUser;
    static ExpenseBeanImpl expenseImpl;

    static {
        try {
            remote = GroupRemoteHelper.lookupRemoteGroup();
            remoteUser = UserRemoteHelper.lookupRemoteStatelessUser();
            expenseImpl = new ExpenseBeanImpl();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private List<User> availableUsersForGroup;
    private Map<Integer, Boolean> selectedUserMap = new HashMap<>();
    private double amountCollected;
    
    public Map<Integer, Boolean> getSelectedUserMap() {
        return selectedUserMap;
    }

    public void setSelectedUserMap(Map<Integer, Boolean> selectedUserMap) {
        this.selectedUserMap = selectedUserMap;
    }

    public double getAmountCollected() {
        return amountCollected;
    }

    public void setAmountCollected(double amountCollected) {
        this.amountCollected = amountCollected;
    }


    public String createGroupEjb(TripGroup tripGroup) throws ClassNotFoundException, SQLException {
        return remote.createGroup(tripGroup);
    }

    public List<TripGroup> showGroupsByAdminIdEjb() throws ClassNotFoundException, SQLException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        return remote.showGroupsByAdminId(loggedInUser.getId());
    }
    public List<TripGroup> showGroupsByUserIdEjb() throws ClassNotFoundException, SQLException {
    	FacesContext facesContext = FacesContext.getCurrentInstance();
    	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
    	User loggedInUser = (User) session.getAttribute("loggedInUser");
    	return remote.showGroupsByUserId(loggedInUser.getId());
    }

    public String searchGroupByIdEjb(int id) throws ClassNotFoundException, SQLException {
    	TripGroup tripGroup = remote.searchGroupById(id);
        List<GroupMembers> members = remote.getMembersByGroupId(id); 

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        System.out.println("Members : " + members);
        sessionMap.put("groupDetails", tripGroup);
        sessionMap.put("groupMembers", members); 
        return "GroupAdmin?faces-redirect=true";
    }
    public String searchUserGroupByIdEjb(int id) throws ClassNotFoundException, SQLException {
    	TripGroup tripGroup = remote.searchGroupById(id);
    	List<GroupMembers> members = remote.getMembersByGroupId(id); 
    	List<ExpenseSplits> expenseSplits = expenseImpl.loadOwedAmountsEjb(id);
    	FacesContext context = FacesContext.getCurrentInstance();
    	Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
    	System.out.println("Members : " + members);
    	sessionMap.put("groupDetails", tripGroup);
    	sessionMap.put("groupMembers", members); 
    	sessionMap.put("splits", expenseSplits);
    	return "GroupUser?faces-redirect=true";
    }

    // Get list of users not in group
    public List<User> getAvailableUsersForGroup() throws ClassNotFoundException, SQLException {
        if (availableUsersForGroup == null) {
            TripGroup group = (TripGroup) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("groupDetails");
            availableUsersForGroup = remote.getUsersNotInGroup(group.getId());
        }
        System.out.println("available groups : " + availableUsersForGroup);
        return availableUsersForGroup;
    }

    // Add selected users
    public String addSelectedUsersToGroup() throws ClassNotFoundException, SQLException {
        TripGroup group = (TripGroup) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("groupDetails");
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");

        for (Map.Entry<Integer, Boolean> entry : selectedUserMap.entrySet()) {
            if (entry.getValue()) {
            	GroupMembers member = new GroupMembers();
            	member.setCollectedAmount(amountCollected);
            	member.setRole(Role.MEMBER);
            	member.setUser(remoteUser.findUserById(entry.getKey()));
            	member.setTripGroup(group);
                remote.addUserToGroup(member);
            }
        }

        return "GroupAdmin?faces-redirect=true";
    }
    
    public String getUserNameById(int id) throws ClassNotFoundException, SQLException {
    	User user = remoteUser.findUserById(id);
    	System.out.println("name : " + user.getName());
    	return user.getName();
    	
    }
    
}
