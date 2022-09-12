package com.bookdemo.ServiceB.service;

import java.util.List;
import java.util.Optional;

import com.bookdemo.ServiceB.Entity.Book;

public interface BookService {

	public List<Book> getAllBooks ();
	public Optional <Book>  getBookById (int id);
	public boolean addBook (Book book);
}
