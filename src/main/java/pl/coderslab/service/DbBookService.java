package pl.coderslab.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import pl.coderslab.dao.BookDao;
import pl.coderslab.model.Book;

@Component
@Primary
public class DbBookService implements BookService {
	
	private BookDao bookDao;

	public List<Book> getList() {
		return bookDao.loadAllBooks(conn);
	}


	public Book getBookById(long id) {
		return bookDao.loadBookById(conn, id);
	}

	public void addBookToList(Book book) {
		bookDao.saveBookToDB(conn, book);
		
	}

	public void updateBook(Book book) {
		bookDao.saveBookToDB(conn, book);
		
	}

	public void deleteBook(Book book) {
		bookDao.deleteBook(conn, book);
		
	}


	public void updateBook(Book original, Book updated) {
		// TODO Auto-generated method stub
		
	}

}
