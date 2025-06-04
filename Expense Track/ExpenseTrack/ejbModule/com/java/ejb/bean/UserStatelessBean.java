package com.java.ejb.bean;

import java.sql.SQLException;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.java.ejb.dao.UserDao;
import com.java.ejb.dao.UserDaoImpl;
import com.java.ejb.model.User;

/**
 * Session Bean implementation class UserStatelessBean
 */
@Stateless
@Remote(UserStatelessBeanRemote.class)
public class UserStatelessBean implements UserStatelessBeanRemote {
	
	static UserDao daoImpl;
	
	static {
		daoImpl = new UserDaoImpl();
	}


    /**
     * Default constructor. 
     */
    public UserStatelessBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String addUser(User user) throws ClassNotFoundException, SQLException {
		return daoImpl.addUserDao(user);
	}

	@Override
	public User findUserByUsername(String user_name) throws SQLException, ClassNotFoundException {
		return daoImpl.findUserByUsernameDao(user_name);
	}

	@Override
	public User findUserByEmail(String email) throws SQLException, ClassNotFoundException {
		return daoImpl.findUserByEmailDao(email);
	}

	@Override
	public User findUserByPhone(String phone) throws SQLException, ClassNotFoundException {
		return daoImpl.findUserByPhoneDao(phone);
	}

	@Override
	public User findUserById(Integer key) throws SQLException, ClassNotFoundException {
		return daoImpl.findUserByIdDao(key);
	}


}
