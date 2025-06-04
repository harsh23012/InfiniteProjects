package com.java.ejb.bean;

import java.sql.SQLException;

import javax.ejb.Remote;

import com.java.ejb.model.User;

@Remote
public interface UserStatelessBeanRemote {
	String addUser(User user) throws ClassNotFoundException, SQLException;
	User findUserByUsername(String user_name) throws SQLException, ClassNotFoundException;
	User findUserByEmail(String email)throws SQLException, ClassNotFoundException;
	User findUserByPhone(String phone)throws SQLException, ClassNotFoundException;
	User findUserById(Integer key)throws SQLException, ClassNotFoundException;
}
