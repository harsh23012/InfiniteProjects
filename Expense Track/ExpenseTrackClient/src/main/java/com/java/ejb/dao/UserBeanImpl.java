package com.java.ejb.dao;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import com.java.ejb.bean.UserRemoteHelper;
import com.java.ejb.bean.UserStatelessBeanRemote;
import com.java.ejb.model.User;
import com.java.ejb.model.UserType;
import com.java.ejb.util.EncryptPassword;

public class UserBeanImpl {
	
	static UserStatelessBeanRemote remote;
	private String loginOption;
    private String userInput;
    private String password;

	public String getLoginOption() {
		return loginOption;
	}


	public void setLoginOption(String loginOption) {
		this.loginOption = loginOption;
	}


	public String getUserInput() {
		return userInput;
	}


	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	static {
		try {
			remote = UserRemoteHelper.lookupRemoteStatelessUser();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String validateUser(User user) {
        if (user.getUser_name() == null || user.getUser_name().length() < 5) {
            return "Username must be at least 5 characters long";
        }
        if (user.getName() == null || user.getName().length() < 5) {
        	return "Name must be at least 5 characters long";
        }
        // Email validation
        if (user.getEmail() == null || !user.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$")) {
            return "Invalid email format";
        }
        String phone = user.getPhone();
        if (phone == null || !(phone.matches("^\\d{10}$") || phone.matches("^0\\d{10}$"))) {
            return "Phone number must be 10 digits or 11 digits if starting with 0";
        }
        // Password validations
        String password = user.getPassword();
        if (password == null || password.isEmpty()) {
            return "Password cannot be empty";
        }
        if (!Character.isUpperCase(password.charAt(0))) {
            return "Password must start with a capital letter";
        }
        if (!password.matches(".*[@._\\-\\$].*")) {
            return "Password must contain at least one special character (@, ., _, -, $)";
        }
        return null;
    }

	
	public String addUserEjb(User user) throws ClassNotFoundException, SQLException {
		String validationError = validateUser(user);
        if (validationError != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, validationError, null));
            return null;  // Stay on the same page and show the error message
        }

		user.setPassword(EncryptPassword.getCode(user.getPassword()));
		return remote.addUser(user);	
	}
	
	public String login() {
        // Validate inputs first
        if (loginOption == null || loginOption.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select login option", null));
            return null;
        }
        if (userInput == null || userInput.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter your " + loginOption.toLowerCase(), null));
            return null;
        }
        if (password == null || password.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password is required", null));
            return null;
        }

        String encryptedPassword = EncryptPassword.getCode(password);
        User user = null;
        try {
            switch (loginOption) {
                case "USERNAME":
                    user = remote.findUserByUsername(userInput);
                    break;
                case "EMAIL":
                    user = remote.findUserByEmail(userInput);
                    break;
                case "PHONE":
                    user = remote.findUserByPhone(userInput);
                    break;
                default:
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid login option", null));
                    return null;
            }
            System.out.println("user " + user);
            if (user == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "User not found", null));
                return null;
            }

            if (!user.getPassword().equals(encryptedPassword)) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect password", null));
                return null;
            }

            // Login success — set user in session, or perform other login steps
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", user);

            if(user.getUser_type().equals(UserType.ADMIN)) {
            	return "AdminHome?faces-redirect=true";
            }
            else {            	
            	return "UserHome?faces-redirect=true";
            }

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed due to internal error", null));
            return null;
        }
    }
	
	 public String logout() {
	        try {
	            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	            // Redirect to login page or home page after logout
	            return "Login.jsp?faces-redirect=true";
	        } catch (Exception e) {
	            e.printStackTrace();
	            FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logout failed", null));
	            return null;
	        }
	    }



}
