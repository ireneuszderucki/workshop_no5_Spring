package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pl.coderslab.model.Book;

//private long id;
//private String isbn;
//private String title;
//private String author;
//private String publisher;
//private String type;

public class BookDao {
	static public ArrayList<Book> loadAllBooks(Connection conn) throws SQLException {
		ArrayList<Book> books = new ArrayList<Book>();
		String sql = "SELECT * FROM book";
		PreparedStatement preStm = conn.prepareStatement(sql);
		ResultSet rs = preStm.executeQuery();
		while (rs.next()) {
			Book loadedbook = new Book();
			loadedbook.setId(rs.getLong("id"));
			loadedbook.setIsbn(rs.getString("isbn"));
			loadedbook.setTitle(rs.getString("title"));
			loadedbook.setAuthor(rs.getString("author"));
			loadedbook.setPublisher(rs.getString("publisher"));
			loadedbook.setType(rs.getString("type"));
			books.add(loadedbook);			
		}
		return books;
	}
	
	static public Book loadBookById(Connection conn, long id) throws SQLException {
		String sql = "SELECT * FROM book where id=?";
		PreparedStatement preStm = conn.prepareStatement(sql);
		preStm.setLong(1, id);
		ResultSet rs = preStm.executeQuery();
		if (rs.next()) {
			Book loadedbook = new Book();
			loadedbook.setId(rs.getLong("id"));
			loadedbook.setIsbn(rs.getString("isbn"));
			loadedbook.setTitle(rs.getString("title"));
			loadedbook.setAuthor(rs.getString("author"));
			loadedbook.setPublisher(rs.getString("publisher"));
			loadedbook.setType(rs.getString("type"));
			return loadedbook;
		}
		return null;
	}
	//private long id;
	//private String isbn;
	//private String title;
	//private String author;
	//private String publisher;
	//private String type;
	
	static public void saveBookToDB(Connection conn, Book book) throws SQLException {
		if (book.getId() == 0) {
			String sql = "INSERT INTO book (isbn, title, author, publisher, type) VALUES (?, ?, ?, ?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preStm = conn.prepareStatement(sql, generatedColumns);
			preStm.setString(1, book.getIsbn());
			preStm.setString(2, book.getTitle());
			preStm.setString(3, book.getAuthor());
			preStm.setString(4, book.getPublisher());
			preStm.setString(5, book.getType());
			preStm.executeUpdate();
			ResultSet rs = preStm.getGeneratedKeys();
			if (rs.next()) {
				book.setId(rs.getLong(1));
			}
		}
		else {
			String sql = "UPDATE book SET isbn=?, title=?, author=?, publisher=?, type=? where id=?";
			PreparedStatement preStm = conn.prepareStatement(sql);
			preStm.setString(1, book.getIsbn());
			preStm.setString(2, book.getTitle());
			preStm.setString(3, book.getAuthor());
			preStm.setString(4, book.getPublisher());
			preStm.setString(5, book.getType());
			preStm.setLong(6, book.getId());
			preStm.executeUpdate();
		}
	}
	
	static public void deleteBook(Connection conn, Book book) throws SQLException {
		if (book.getId() != 0) {
			String sql = "DELETE FROM book where id=?";
			PreparedStatement preStm = conn.prepareStatement(sql);
			preStm.setLong(1, book.getId());
			preStm.executeUpdate();
			book.setId(0);
		}
	}

}
