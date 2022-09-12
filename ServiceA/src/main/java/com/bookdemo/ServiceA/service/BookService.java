package com.bookdemo.ServiceA.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bookdemo.ServiceA.Entity.Book;
//@FeignClient (name="bookservice", url="localhost:8080", path = "/Books/")
//@FeignClient (name="bookservice", url="${serviceB.url}", path = "/Books/")
@FeignClient (name="SERVICEB", path = "/Books/")
public interface BookService {

	@GetMapping("/")
	public List<Book> getAllBooks ();
	@GetMapping("/{id}")
	public Book getBookbyId (@PathVariable  ("id") int id);
	@PostMapping("/")
	public boolean addBook (@RequestBody Book b);
}
