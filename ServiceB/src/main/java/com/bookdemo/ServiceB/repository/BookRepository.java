package com.bookdemo.ServiceB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookdemo.ServiceB.Entity.Book;


public interface BookRepository extends JpaRepository<Book, Integer>{

	//findBy
	
}
