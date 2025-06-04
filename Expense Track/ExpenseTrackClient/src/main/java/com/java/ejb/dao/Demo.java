package com.java.ejb.dao;

import com.java.ejb.util.EncryptPassword;

public class Demo {
	
	public static void main(String[] args) {
		EncryptPassword enc = new EncryptPassword();
		System.out.println(enc.getCode("12345"));
	}

}
