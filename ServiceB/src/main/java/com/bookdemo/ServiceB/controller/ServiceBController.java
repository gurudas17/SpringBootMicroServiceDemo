package com.bookdemo.ServiceB.controller;

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

import com.bookdemo.ServiceB.Entity.Book;
import com.bookdemo.ServiceB.service.BookService;

@RestController

@RequestMapping ("/Books")
public class ServiceBController {
	
	@Autowired
	BookService bookServ; 
	private static final Logger log = LogManager.getLogger(ServiceBController.class);
	
	@GetMapping("/")
	public List<Book> getAllBooks ()
	{
		log.debug(" In ServiceBController:: getAllBooks ( )");
		return bookServ.getAllBooks();
	}

	@GetMapping("/{id}")
	public Book getBookbyId (@PathVariable  ("id") int id)
	{
		log.debug(" In ServiceBController:: getBookbyId ( ) for id "+id);
		Optional<Book> book = 	bookServ.getBookById(id);
		
		if(book.isPresent())
			return (Book) book.get();
		else
			return null;
	}
	
	

		

	@PostMapping("/")
	public boolean addBook (@RequestBody Book b)
	{
		log.debug(" In ServiceBController:: addBook ( ) for book");
			return bookServ.addBook(b);
	}
	
	@GetMapping ("/ServiceBTest")
	public ResponseEntity<String> getStatus ()
	{
		log.info( " In   ServiceBController::getStatus() ");
		return new ResponseEntity<>("Serivce B sucessfully inovke", HttpStatus.OK);
	}

}
