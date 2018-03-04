package pl.coderslab.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.model.Book;
import pl.coderslab.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

//	@RequestMapping("/helloBook")
//	public Book helloBook(){
//		return new Book(1L,"9788324631766","Thinking in Java",
//	                    "Bruce Eckel","Helion","programming");
//	}
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/")
	@ResponseBody
	private List<Book> getBooks() {
		List<Book> list = bookService.getList();
		return list;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	private Book getBookById(@PathVariable long id) {
		Book book = bookService.getBookById(id);
		return book;
	}
	

//	@PostMapping("/")
//	private void addBookToList(HttpServletRequest request) {
//		Book book = new Book();
//		book.setId(Long.parseLong(request.getParameter("id")));
//		book.setIsbn(request.getParameter("isbn"));
//		book.setTitle(request.getParameter("title"));
//		book.setAuthor(request.getParameter("author"));
//		book.setPublisher(request.getParameter("publisher"));
//		book.setType(request.getParameter("type"));
//		bookService.addBookToList(book);
//	}
	
	@PostMapping("/")
	private void addBookToList(@RequestBody Book book) {
		bookService.addBookToList(book);
	}
	
//	@PutMapping("/{id}")
//	private void updateBook(HttpServletRequest request, @PathVariable long id) {
//		Book original = bookService.getBookById(id);
//		Book updated = new Book();
//		updated.setId(Long.parseLong(request.getParameter("id")));
//		updated.setIsbn(request.getParameter("isbn"));
//		updated.setTitle(request.getParameter("title"));
//		updated.setAuthor(request.getParameter("author"));
//		updated.setPublisher(request.getParameter("publisher"));
//		updated.setType(request.getParameter("type"));
//		bookService.updateBook(original, updated);
//	}
	
	@PutMapping("/{id}")
	private void updateBook(@RequestBody Book book, @PathVariable long id) {
		Book original = bookService.getBookById(id);
		bookService.updateBook(original, book);
	}
	
	@DeleteMapping("/{id}")
	private void deleteBook(HttpServletRequest request, @PathVariable long id) {
		Book toDelete = bookService.getBookById(id);
		bookService.deleteBook(toDelete);
	}
}
