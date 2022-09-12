package com.bookdemo.ServiceB.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookdemo.ServiceB.Entity.Book;
import com.bookdemo.ServiceB.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

@Autowired	
BookRepository bookRepo;
@Override
	public List<Book> getAllBooks ()
	{
		log.debug(" in BookServiceImpl ::getAllBooks () " );
		return bookRepo.findAll();
	}
	@Override
	public Optional <Book>  getBookById (int id)
	{
		log.debug(" in BookServiceImpl ::getBookById () for id "+id );
		return bookRepo.findById(id);
		
	}

	@Override
	public boolean addBook(Book book) {
		log.debug(" in BookServiceImpl ::addBook () ");
			if( null!=bookRepo.save(book))
				return true;
			else 
				return false;
		
		
	}
	
	
}
