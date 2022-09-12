package com.bookdemo.ServiceA.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookdemo.ServiceA.Entity.Book;
import com.bookdemo.ServiceA.service.BookService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController

@RequestMapping ("/books")
public class ServiceAController {
	
	private static final Logger log = LogManager.getLogger(ServiceAController.class);
	@Autowired
	BookService bookServ;

	@GetMapping("/")
   @CircuitBreaker(name = "getAllBooks", fallbackMethod = "getAllBooksFallback") 
	public List<Book> getAllBooks() {
		log.info( "In ServiceAController:: getAllBooks()  call ");
		return bookServ.getAllBooks();
	}

	@GetMapping("/{id}")
    @CircuitBreaker(name = "getBookbyId", fallbackMethod = "getBooksFallback")
	public ResponseEntity<Book> getBookbyId(@PathVariable("id") int id) {
		
		log.info( " In   ServiceAController::getBookbyId for id "+id);
		Optional<Book> optBook = Optional.ofNullable(bookServ.getBookbyId(id));
		if (optBook.isPresent()) {
			log.debug( "Book found for  "+id);
			return new ResponseEntity<>((Book) optBook.get(), HttpStatus.OK);
			
		}	
		else {
			log.debug( "Book is not present for book id  "+id);
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
	}

	@GetMapping ("/ServiceATest")
	public ResponseEntity<String> getStatus ()
	{
		log.info( " In   ServiceAController::getStatus() ");
		return new ResponseEntity<>("Serivce A sucessfully inovke", HttpStatus.OK);
	}
	 
	
	@PostMapping("/")
	public ResponseEntity<String> addBook(@RequestBody Book b) {
		log.debug( " In   ServiceAController::addBook for book ");
		if (bookServ.addBook(b)) {
			log.debug( "Book name "+b.getBookName()+" is sucessfully added  ");
			return new ResponseEntity<>("Book Added Sucessfully", HttpStatus.OK);
			
		}	
		else {
			log.error( "Book name "+b.getBookName()+" is not able  add due to incorrect parameter or internal error  ");
			return new ResponseEntity<>("Parameters are wrong or Technical Issue occur", HttpStatus.BAD_REQUEST);
			
		}
		
	}

	public List<Book> getAllBooksFallback (Exception exp )
	{
		log.debug("getBooksFallback() is called due to exp "+exp );
		List<Book> bookList = new ArrayList();
		
		 bookList.add(new Book(0,"","",0));
		 return bookList;
	}
	
	
	public ResponseEntity<Book> getBooksFallback (int a, Exception exp )
	{
		log.debug("getBooksFallback() is called due to exp "+exp );
		return new ResponseEntity(new Book(0,"","",0),HttpStatus.EXPECTATION_FAILED);
	}
}
