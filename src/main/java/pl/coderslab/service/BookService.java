package pl.coderslab.service;

import java.util.List;

import pl.coderslab.model.Book;

public interface BookService {
	List<Book> getList();
	public Book getBookById(long id);
	public void addBookToList(Book book);
	public void updateBook(Book original, Book updated);
	public void deleteBook(Book book);

}
