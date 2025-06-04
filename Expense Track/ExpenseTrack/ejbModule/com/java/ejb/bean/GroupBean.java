package com.java.ejb.bean;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.java.ejb.dao.GroupDao;
import com.java.ejb.dao.GroupDaoImpl;
import com.java.ejb.model.GroupMembers;
import com.java.ejb.model.TripGroup;
import com.java.ejb.model.User;

/**
 * Session Bean implementation class GroupBean
 */
@Stateless
@Remote(GroupBeanRemote.class)
public class GroupBean implements GroupBeanRemote {
	static GroupDao daoImpl;
	static {
		daoImpl = new GroupDaoImpl();
	}

    /**
     * Default constructor. 
     */
    public GroupBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String createGroup(TripGroup tripGroup) throws ClassNotFoundException, SQLException {
		return daoImpl.createGroupDao(tripGroup);
	}

	@Override
	public List<TripGroup> showGroupsByAdminId(int id) throws ClassNotFoundException, SQLException {
		return daoImpl.showGroupsByAdminIdDao(id);
	}

	@Override
	public void addUserToGroup(int groupId, int userId) throws SQLException, ClassNotFoundException {
		daoImpl.addUserToGroupDao(groupId, userId);
	}

	@Override
	public TripGroup searchGroupById(int id) throws SQLException, ClassNotFoundException {
		return daoImpl.searchGroupByIdDao(id);
	}

	@Override
	public void addUserToGroup(GroupMembers members)
			throws SQLException, ClassNotFoundException {
		daoImpl.addUserToGroupDao(members);
		
	}

	@Override
	public List<User> getUsersNotInGroup(int id) throws SQLException, ClassNotFoundException {
		return daoImpl.getUsersNotInGroupDao(id);
	}

	@Override
	public List<GroupMembers> getMembersByGroupId(int id) throws SQLException, ClassNotFoundException {
		return daoImpl.getMembersByGroupIdDao(id);
	}

	@Override
	public List<TripGroup> showGroupsByUserId(int id) throws ClassNotFoundException, SQLException {
		return daoImpl.showGroupsByUserIdDao(id);
	}

}
