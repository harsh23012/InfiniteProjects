package com.java.ejb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.ejb.model.User;
import com.java.ejb.model.UserType;
import com.java.ejb.util.ConnectionHelper;

public class UserDaoImpl implements UserDao {
	PreparedStatement psmt;
	@Override
	public String addUserDao(User user) throws ClassNotFoundException, SQLException {
		
		Connection connection = ConnectionHelper.getConnection();
		String sql = "INSERT INTO users (name, email, user_name, phone, balance, user_type, password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
		 psmt = connection.prepareStatement(sql);

		 psmt.setString(1, user.getName());
		 psmt.setString(2, user.getEmail());
		 psmt.setString(3, user.getUser_name());
		 psmt.setString(4, user.getPhone());
		 psmt.setDouble(5, user.getBalance());
		 psmt.setString(6, user.getUser_type().name());  // Assuming UserType is an enum
		 psmt.setString(7, user.getPassword());

	     psmt.executeUpdate();
	     return "Home.jsp?faces-redirect=true";
	}
	@Override
	public User findUserByUsernameDao(String user_name) throws SQLException, ClassNotFoundException {
		Connection connection = ConnectionHelper.getConnection();
		String sql = "SELECT * FROM users WHERE user_name = ?";
		psmt = connection.prepareStatement(sql);
		psmt.setString(1, user_name);
		ResultSet rs = psmt.executeQuery();
		if (rs.next()) {
            return extractUserFromResultSet(rs);
        }
		return null;

	}
	@Override
	public User findUserByEmailDao(String email) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionHelper.getConnection();
		String sql = "SELECT * FROM users WHERE email = ?";
		psmt = connection.prepareStatement(sql);
		psmt.setString(1, email);
		ResultSet rs = psmt.executeQuery();
		if (rs.next()) {
            return extractUserFromResultSet(rs);
        }
		return null;
	}
	@Override
	public User findUserByPhoneDao(String phone) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionHelper.getConnection();
		String sql = "SELECT * FROM users WHERE phone = ?";
		psmt = connection.prepareStatement(sql);
		psmt.setString(1, phone);
		ResultSet rs = psmt.executeQuery();
		if (rs.next()) {
            return extractUserFromResultSet(rs);
        }
		return null;
	}
	@Override
	public User findUserByIdDao(int id) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionHelper.getConnection();
		String sql = "SELECT * FROM users WHERE id = ?";
		psmt = connection.prepareStatement(sql);
		psmt.setInt(1, id);
		ResultSet rs = psmt.executeQuery();
		if (rs.next()) {
			return extractUserFromResultSet(rs);
		}
		return null;
	}
	
	private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setUser_name(rs.getString("user_name"));
        user.setPhone(rs.getString("phone"));
        user.setBalance(rs.getDouble("balance"));
        user.setUser_type(UserType.valueOf(rs.getString("user_type")));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
