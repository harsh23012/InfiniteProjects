package com.java.library.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.library.model.Books;
import com.java.library.model.History;
import com.java.library.model.LibAdmins;
import com.java.library.model.LibUsers;
import com.java.library.model.TranBook;
import com.java.library.util.ConnectionHelper;
import com.java.library.util.EncryptPassword;

public class LibraryDaoImpl implements LibraryDao{
	
	Connection connection;
	PreparedStatement psmt;
	
	@Override
	public String createUser(LibUsers libUsers) throws ClassNotFoundException, SQLException {
		String encr = EncryptPassword.getCode(libUsers.getPassword());
		connection = ConnectionHelper.getConnection();
		String cmd = "Insert into LibUsers(UserName, Password) values(?,?)";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, libUsers.getUserName());
		psmt.setString(2, encr);
		psmt.executeUpdate();
		return "User Account Created...";
	}

	@Override
	public String createAdmin(LibAdmins libAdmins) throws ClassNotFoundException, SQLException {
		String encr = EncryptPassword.getCode(libAdmins.getPassword());
		connection = ConnectionHelper.getConnection();
		String cmd = "Insert into LibAdmins(UserName, Password) values(?,?)";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, libAdmins.getUserName());
		psmt.setString(2, encr);
		psmt.executeUpdate();
		return "Admin Account Created...";	
	}

	@Override
	public int loginUser(LibUsers libUsers) throws ClassNotFoundException, SQLException {
		String encr = EncryptPassword.getCode(libUsers.getPassword());
		connection = ConnectionHelper.getConnection();
		String cmd = "Select count(*) cnt from LibUsers where UserName = ? AND Password = ?";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, libUsers.getUserName());
		psmt.setString(2, encr);
		ResultSet rs = psmt.executeQuery();
		rs.next();
		int count = rs.getInt("cnt");
		return count;
	}
	
	@Override
	public int loginAdmin(LibAdmins libAdmins) throws ClassNotFoundException, SQLException {
		String encr = EncryptPassword.getCode(libAdmins.getPassword());
		connection = ConnectionHelper.getConnection();
		String cmd = "Select count(*) cnt from LibAdmins where UserName = ? AND Password = ?";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, libAdmins.getUserName());
		psmt.setString(2, encr);
		ResultSet rs = psmt.executeQuery();
		rs.next();
		int count = rs.getInt("cnt");
		return count;
	}


	@Override
	public List<Books> searchBooks(String searchType, String searchValue) throws ClassNotFoundException, SQLException {
		String cmd;
		boolean isValid = true;
		if(searchType.equals("id")) {
			cmd = "SELECT * FROM Books WHERE Id = ?";
		}
		else if(searchType.equals("name")) {
			cmd = "SELECT * FROM Books WHERE Name = ?";
		}
		else if(searchType.equals("dept")) {
			cmd = "SELECT * FROM Books WHERE dept = ?";
		}
		else if(searchType.equals("author")) {
			cmd = "SELECT * FROM Books WHERE author = ?";
		}
		else {
			isValid = false;
			cmd = "SELECT * FROM Books";
		}
		connection = ConnectionHelper.getConnection();
		psmt = connection.prepareStatement(cmd);
		if(isValid == true) {
			psmt.setString(1, searchValue);
		}
		ResultSet rs = psmt.executeQuery();
		Books books = null;
		List<Books> booksList = new ArrayList<Books>();
		while(rs.next()) {
			books = new Books();
			books.setId(rs.getInt("id"));
			books.setName(rs.getString("name"));
			books.setAuthor(rs.getString("author"));
			books.setEdition(rs.getString("edition"));
			books.setDept(rs.getString("dept"));
			books.setNoOfBooks(rs.getInt("TotalBooks"));
			booksList.add(books);
		}
		return booksList;
	}


	@Override
	public int issueOrNot(String userName, int bookId) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd = "SELECT COUNT(*) cnt FROM TranBook WHERE UserName = ? AND BookId = ?";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, userName);
		psmt.setInt(2, bookId);
		ResultSet rs = psmt.executeQuery();
		rs.next();
		int count = rs.getInt("cnt");
		return count;
	}
	
	public int countNoOfBooks(int bookId) throws SQLException, ClassNotFoundException {
		connection = ConnectionHelper.getConnection();
		String cmd = "SELECT TotalBooks FROM Books WHERE Id = ?";
		psmt = connection.prepareStatement(cmd);
		psmt.setInt(1, bookId);
		ResultSet rs = psmt.executeQuery();
		int count = 0;
	    if (rs.next()) {
	        count = rs.getInt("TotalBooks");
	    }
		return count;
	}
	
	public int countIssuedBook(String userName) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd = "SELECT COUNT(*) cnt FROM TranBook WHERE UserName = ?";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, userName);
		ResultSet rs = psmt.executeQuery();
		rs.next();
		int count = rs.getInt("cnt");
		return count;
	}

	@Override
	public String issueBook(String userName, int bookId) throws ClassNotFoundException, SQLException {
		int count = issueOrNot(userName, bookId);
		int countBook = countNoOfBooks(bookId);
		int countIssuedBook = countIssuedBook(userName);
		if(count == 0 && countBook > 1 && countIssuedBook < 4) {
			connection = ConnectionHelper.getConnection();
			String cmd = "Insert into TranBook(UserName, BookId) values(?,?)";
			psmt = connection.prepareStatement(cmd);
			psmt.setString(1, userName);
			psmt.setInt(2, bookId);
			psmt.executeUpdate();
			
			cmd = "Update Books set TotalBooks = TotalBooks - 1 where id = ? ";
			psmt = connection.prepareStatement(cmd);
			psmt.setInt(1, bookId);
			psmt.executeUpdate();
			return "Book with Id " + bookId + " Issued Successfully...";
		}
		if(countIssuedBook >=4) {
			return "Already 4 books are issued to you. You can't issue another book.";
		}
		if(countBook == 1) {
			return "Book Id : " + bookId + " is only one in Libarary so you can Read it only inside the Library ";
		}
		else {
			return "Book Id " + bookId + " for user " + userName + " Already Issued...";
		}
	}
	
	@Override
	public List<TranBook> accountDetails(String userName) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd = "select * from tranbook where username = ?";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, userName);
		ResultSet rs = psmt.executeQuery();
		List<TranBook> booksIssued = new ArrayList<TranBook>();
		TranBook tranBook = null;
		while(rs.next()) {
			tranBook = new TranBook();
			tranBook.setBookId(rs.getInt("BookId"));
			tranBook.setUserName(rs.getString("UserName"));
			tranBook.setFromDate(rs.getDate("FromDate"));
			booksIssued.add(tranBook);
		}
		return booksIssued;
	}
	
	@Override
	public int countDaysOfIssuedBook(int bookId) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd = "Select fromDate from TranBook where bookId = ?";
		psmt = connection.prepareStatement(cmd);
		psmt.setInt(1, bookId);
		ResultSet rs = psmt.executeQuery();
		
		int days = 0;
		if (rs.next()) {
			java.sql.Date fromDate = rs.getDate("fromDate");
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

			long diffInMillis = currentDate.getTime() - fromDate.getTime();
			days = (int) (diffInMillis / (1000 * 60 * 60 * 24));
		}
		
		return days;
		
	}

	@Override
	public String returnBook(String userName, int bookId) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd = "Select * from TranBook where UserName = ? AND bookId = ?";
		psmt =connection.prepareStatement(cmd);
		psmt.setString(1, userName);
		psmt.setInt(2, bookId);
		ResultSet rs = psmt.executeQuery();
		rs.next();
		Date fromDate = rs.getDate("fromDate");
		
		cmd = "Insert into TransReturn(userName, BookId, FromDate) values(?,?,?)";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, userName);
		psmt.setInt(2, bookId);
		psmt.setDate(3, fromDate);
		psmt.executeUpdate();
		
		String sql1 = "DELETE FROM TranBook WHERE BookId = ? AND Username = ? " ;
		psmt = connection.prepareStatement(sql1);
		psmt.setInt(1,bookId);
		psmt.setString(2,userName);
		psmt.executeUpdate();
		
		String sql3 = "Update Books set TotalBooks = TotalBooks + 1 where Id = ?";
		psmt = connection.prepareStatement(sql3);
		psmt.setInt(1, bookId);
		psmt.executeUpdate();
		return "Book with Id " +bookId + " For User " +userName + " Returned Successfully...";
	}
	
	@Override
	public List<History> historyDetails(String userName) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd = "Select * from TransReturn where Username = ?";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, userName);
		ResultSet rs = psmt.executeQuery();
		List<History> booksIssued = new ArrayList<History>();
		History history = null;
		while(rs.next()) {
			history = new History();
			history.setBookId(rs.getInt("BookId"));
			history.setUserName(rs.getString("UserName"));
			history.setFromDate(rs.getDate("FromDate"));
			history.setReturnedDate(rs.getDate("Todate"));
			booksIssued.add(history);
		}
		return booksIssued;
	}
	
	public boolean searchBook(String name, String author) throws ClassNotFoundException, SQLException {
		boolean isAvailable = false;
		connection = ConnectionHelper.getConnection();
		String cmd = "Select count(*) cnt from books where name=? AND author=?";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, name);
		psmt.setString(2, author);
		ResultSet rs = psmt.executeQuery();
		rs.next();
		int count = rs.getInt("cnt");
		if(count > 0) {
			isAvailable = true;
		}
		return isAvailable;
	}

	@Override
	public String addBooks(Books books) throws ClassNotFoundException, SQLException {
		boolean isAvailable = searchBook(books.getName(), books.getAuthor());
		connection = ConnectionHelper.getConnection();
		if(isAvailable) {
			String cmd = "Update books set TotalBooks = TotalBooks + 1 where name = ? AND author = ?";
			psmt = connection.prepareStatement(cmd);
			psmt.setString(1, books.getName());
			psmt.setString(2, books.getAuthor());
			psmt.executeUpdate();
			return "Book incremeted by 1 as this book is already available in Library";
		}
		else {
			String cmd = "Insert into books (name, author, edition, dept, totalBooks) values(?,?,?,?,?)";
			psmt = connection.prepareStatement(cmd);
			psmt.setString(1, books.getName());
			psmt.setString(2, books.getAuthor());
			psmt.setString(3, books.getEdition());
			psmt.setString(4, books.getDept());
			psmt.setInt(5, 1);
			psmt.executeUpdate();
			return "A new Book is Added Successfully";
		}
	}
}