package com.java.ejb.dao;

import java.sql.SQLException;

import com.java.ejb.model.User;

public interface UserDao {
	
	String addUserDao(User user) throws ClassNotFoundException, SQLException;
	User findUserByUsernameDao(String user_name) throws SQLException, ClassNotFoundException;
	User findUserByEmailDao(String email)throws SQLException, ClassNotFoundException;
	User findUserByPhoneDao(String phone)throws SQLException, ClassNotFoundException;
	User findUserByIdDao(int id) throws ClassNotFoundException, SQLException;


}
