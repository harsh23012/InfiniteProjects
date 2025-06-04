package com.java.ejb.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.ejb.model.GroupMembers;
import com.java.ejb.model.TripGroup;
import com.java.ejb.model.User;

public interface GroupDao {
	String createGroupDao(TripGroup tripGroup) throws SQLException, ClassNotFoundException;
	List<TripGroup> showGroupsByAdminIdDao(int id) throws ClassNotFoundException, SQLException;
	List<TripGroup> showGroupsByUserIdDao(int id) throws ClassNotFoundException, SQLException;
	void addUserToGroupDao(int groupId, int userId) throws SQLException, ClassNotFoundException;
	TripGroup searchGroupByIdDao(int id)throws SQLException, ClassNotFoundException;
	void addUserToGroupDao(GroupMembers member)throws SQLException, ClassNotFoundException;
	List<User> getUsersNotInGroupDao(int id)throws SQLException, ClassNotFoundException;
	List<GroupMembers> getMembersByGroupIdDao(int id)throws SQLException, ClassNotFoundException;
}
