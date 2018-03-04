package pl.coderslab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.coderslab.model.Book;
//this class obj needs to be injected into our Controller Class
@Component
public class MemoryBookService implements BookService {
	
	private List<Book> list;
    
	public MemoryBookService() {
        list = new ArrayList<Book>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                    "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                    "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                    "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }
	
    public List<Book> getList() {
    	return list;
    }
    
    /**
     * searches for Book obj with desired id
     * @param id
     * @return Book obj
     */
    public Book getBookById(long id) {
    	for (Book book : list) {
    		if (book.getId() == id) {
    			
    			return book;
    		}
		}
    	return null;
    } 
    
    public void addBookToList(Book book) {
    	this.list.add(book);
    }
    
    public void updateBook(Book original, Book updated) {
    	int index = this.list.indexOf(original);
    	this.list.set(index, updated);
    }
    
    public void deleteBook(Book book) {
    	this.list.remove(book);
    }

}
