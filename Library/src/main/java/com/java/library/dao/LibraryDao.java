package com.java.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.library.model.Books;
import com.java.library.model.History;
import com.java.library.model.LibAdmins;
import com.java.library.model.LibUsers;
import com.java.library.model.TranBook;

public interface LibraryDao {
	String createUser(LibUsers libUsers) throws ClassNotFoundException, SQLException;
	int loginUser(LibUsers libUsers) throws ClassNotFoundException, SQLException;
	int loginAdmin(LibAdmins libAdmins) throws ClassNotFoundException, SQLException;
	List<Books> searchBooks(String searchType, String searchValue)throws ClassNotFoundException, SQLException;
	int issueOrNot(String userName, int bookId)throws ClassNotFoundException, SQLException;
	String issueBook(String userName, int bookId)throws ClassNotFoundException, SQLException;
	List<TranBook> accountDetails(String userName) throws ClassNotFoundException, SQLException;
	String returnBook(String userName, int bookId) throws ClassNotFoundException, SQLException;
	List<History> historyDetails(String userName) throws ClassNotFoundException, SQLException;
	String createAdmin(LibAdmins libAdmins) throws ClassNotFoundException, SQLException;
	String addBooks(Books books) throws ClassNotFoundException, SQLException;
	int countDaysOfIssuedBook(int bookId) throws ClassNotFoundException, SQLException;
}
