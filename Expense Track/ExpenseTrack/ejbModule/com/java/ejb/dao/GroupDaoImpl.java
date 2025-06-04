package com.java.ejb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import com.java.ejb.model.GroupMembers;
import com.java.ejb.model.TripGroup;
import com.java.ejb.model.User;
import com.java.ejb.model.UserType;
import com.java.ejb.util.ConnectionHelper;

public class GroupDaoImpl implements GroupDao{
	
	PreparedStatement psmt;
	@Override
	public String createGroupDao(TripGroup tripGroup) throws SQLException, ClassNotFoundException {
	    Connection connection = ConnectionHelper.getConnection();
	    
	    String sql = "INSERT INTO trip_group (name, description, created_by) VALUES (?, ?, ?)";
	    // Prepare statement with RETURN_GENERATED_KEYS to retrieve auto-generated ID
	    psmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    
	    psmt.setString(1, tripGroup.getName());
	    psmt.setString(2, tripGroup.getDescription());
	    psmt.setInt(3, tripGroup.getUser().getId());
	    psmt.executeUpdate();

	    ResultSet rs = psmt.getGeneratedKeys();
	    int groupId = 0;
	    if (rs.next()) {
	        groupId = rs.getInt(1);
	    }
	    rs.close();  // Always good to close ResultSet

	    // Insert initial group member as MEMBER role
	    String sql2 = "INSERT INTO group_members (group_id, user_id, role, collectedAmount) VALUES (?, ?, 'ADMIN',0)";
	    psmt = connection.prepareStatement(sql2);
	    psmt.setInt(1, groupId);
	    psmt.setInt(2, tripGroup.getUser().getId());
	    psmt.executeUpdate();

	    psmt.close(); // Close PreparedStatement
	    connection.close(); // Close connection

	    return "AdminHome.jsp?faces-redirect=true";
	}
	@Override
	public List<TripGroup> showGroupsByAdminIdDao(int id) throws ClassNotFoundException, SQLException {
		List<TripGroup> groups = new ArrayList<>();
		Connection connection = ConnectionHelper.getConnection();
	    String sql = "SELECT * FROM trip_group WHERE created_by = ?";
	    psmt = connection.prepareStatement(sql);
	    psmt.setInt(1, id);
        ResultSet rs = psmt.executeQuery();
        while (rs.next()) {
            TripGroup group = new TripGroup();
            group.setId(rs.getInt("id"));
            group.setName(rs.getString("name"));
            group.setDescription(rs.getString("description"));  
            group.setCreated_at(rs.getTimestamp("created_at"));
            User user = new User();
            user.setId(id);
            group.setUser(user);
            groups.add(group);
        }
        return groups;

	}
	
	public void addUserToGroupDao(int groupId, int userId) throws SQLException, ClassNotFoundException {
	    Connection connection = ConnectionHelper.getConnection();
	    String sql = "INSERT INTO group_members (group_id, user_id, role) VALUES (?, ?, 'MEMBER')";
	    psmt = connection.prepareStatement(sql);
	    psmt.setInt(1, groupId);
	    psmt.setInt(2, userId);
	    psmt.executeUpdate();
	}
	@Override
	public TripGroup searchGroupByIdDao(int id) throws SQLException, ClassNotFoundException {
		TripGroup group = new TripGroup();
		Connection connection = ConnectionHelper.getConnection();
	    String sql = "SELECT * FROM trip_group WHERE id = ?";
	    psmt = connection.prepareStatement(sql);
	    psmt.setInt(1, id);
        ResultSet rs = psmt.executeQuery();
        if (rs.next()) {
            group.setId(rs.getInt("id"));
            group.setName(rs.getString("name"));
            group.setDescription(rs.getString("description"));  
            group.setCreated_at(rs.getTimestamp("created_at"));
            User user = new User();
            user.setId(id);
            group.setUser(user);
        }
        return group;

	}
	@Override
	public void addUserToGroupDao(GroupMembers member) throws SQLException, ClassNotFoundException {
		Connection connection = ConnectionHelper.getConnection();
	    String sql = "INSERT INTO group_members (group_id, user_id, collectedAmount, role) VALUES (?, ?, ?, 'MEMBER')";
	    psmt = connection.prepareStatement(sql);
        psmt.setInt(1, member.getTripGroup().getId());
        psmt.setInt(2, member.getUser().getId());
        psmt.setDouble(3, member.getCollectedAmount());
        psmt.executeUpdate();	
        String updateBalanceSql = "UPDATE users SET balance = balance - ? WHERE id = ?";
        psmt = connection.prepareStatement(updateBalanceSql);
        psmt.setDouble(1, member.getCollectedAmount());
        psmt.setInt(2, member.getUser().getId());
        psmt.executeUpdate();
	}
	@Override
	public List<User> getUsersNotInGroupDao(int id) throws SQLException, ClassNotFoundException {
		List<User> users = new ArrayList<>();
	    Connection connection = ConnectionHelper.getConnection();
	    String sql = "SELECT * FROM users u WHERE u.id NOT IN (" +
	                 "SELECT gm.user_id FROM group_members gm WHERE gm.group_id = ?)";
	    psmt = connection.prepareStatement(sql);
        psmt.setInt(1, id);
        ResultSet rs = psmt.executeQuery();

        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("phone"));
            // Add other fields if needed

            users.add(user);
        }
        
        return users;
	}
	@Override
	public List<GroupMembers> getMembersByGroupIdDao(int id) throws SQLException, ClassNotFoundException {
	    List<GroupMembers> members = new ArrayList<>();
	    Connection connection = ConnectionHelper.getConnection();

	    String sql = "SELECT gm.id AS group_member_id, gm.collectedAmount, gm.role, " +
	                 "u.id AS user_id, u.name, u.email, u.phone, u.user_name, u.balance " +
	                 "FROM group_members gm " +
	                 "JOIN users u ON gm.user_id = u.id " +
	                 "WHERE gm.group_id = ?";

	    PreparedStatement psmt = connection.prepareStatement(sql);
	    psmt.setInt(1, id);
	    ResultSet rs = psmt.executeQuery();

	    while (rs.next()) {
	        // Group member data
	        GroupMembers member = new GroupMembers();
	        member.setId(rs.getInt("group_member_id"));
	        member.setCollectedAmount(rs.getDouble("collectedAmount"));
	        member.setRole(com.java.ejb.model.Role.valueOf(rs.getString("role")));
	        // User data
	        User user = new User();
	        user.setId(rs.getInt("user_id"));
	        user.setName(rs.getString("name"));
	        user.setEmail(rs.getString("email"));
	        user.setPhone(rs.getString("phone"));
	        user.setUser_name(rs.getString("user_name"));
	        user.setBalance(rs.getDouble("balance"));

	        // Link user to member
	        member.setUser(user);

	        members.add(member);
	    }

	    rs.close();
	    psmt.close();
	    connection.close();

	    return members;
	}
	@Override
	public List<TripGroup> showGroupsByUserIdDao(int id) throws ClassNotFoundException, SQLException {
		List<TripGroup> groupList = new ArrayList<>();
	    
	    Connection connection = ConnectionHelper.getConnection();
	    
	    String sql = "SELECT tg.id, tg.name, tg.description, tg.created_by " +
	                 "FROM trip_group tg " +
	                 "JOIN group_members gm ON tg.id = gm.group_id " +
	                 "WHERE gm.user_id = ?";
	    
	    PreparedStatement psmt = connection.prepareStatement(sql);
	    psmt.setInt(1, id);
	    
	    ResultSet rs = psmt.executeQuery();
	    
	    while (rs.next()) {
	        TripGroup group = new TripGroup();
	        group.setId(rs.getInt("id"));
	        group.setName(rs.getString("name"));
	        group.setDescription(rs.getString("description"));
	        
	        // Optional: If you want to set the 'created_by' user object
	        User createdBy = new User();
	        createdBy.setId(rs.getInt("created_by"));
	        group.setUser(createdBy);
	        
	        groupList.add(group);
	    }
	    
	    rs.close();
	    psmt.close();
	    connection.close();
	    
	    return groupList;
	}

}
