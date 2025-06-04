package com.java.ejb.bean;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.java.ejb.model.GroupMembers;
import com.java.ejb.model.TripGroup;
import com.java.ejb.model.User;

@Remote
public interface GroupBeanRemote {
	String createGroup(TripGroup tripGroup) throws ClassNotFoundException, SQLException;
	List<TripGroup> showGroupsByAdminId(int id) throws ClassNotFoundException, SQLException;
	List<TripGroup> showGroupsByUserId(int id) throws ClassNotFoundException, SQLException;
	void addUserToGroup(int groupId, int userId) throws SQLException, ClassNotFoundException;
	TripGroup searchGroupById(int id)throws SQLException, ClassNotFoundException;
	void addUserToGroup(GroupMembers member)throws SQLException, ClassNotFoundException;
	List<User> getUsersNotInGroup(int id)throws SQLException, ClassNotFoundException;
	List<GroupMembers> getMembersByGroupId(int id)throws SQLException, ClassNotFoundException;
}
