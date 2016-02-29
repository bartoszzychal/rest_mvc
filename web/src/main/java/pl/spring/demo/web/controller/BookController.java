package pl.spring.demo.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String bookList(Map<String, Object> params) {
        final List<BookTo> allBooks = bookService.findAllBooks();
        params.put("books", allBooks);
        return "bookList";
    }
    @RequestMapping(value = "/deleteBook/{id}", method = RequestMethod.GET)
    public String deleteBook(Map<String, Object> parameters,@PathVariable("id") String id) {
    	BookTo deleteBook = bookService.deleteBook(Long.valueOf(id));
    	if (deleteBook !=null) {
    		parameters.put("title", deleteBook.getTitle());
        	return "deleteBook";
		}
    	return "bookList";
    }
}
