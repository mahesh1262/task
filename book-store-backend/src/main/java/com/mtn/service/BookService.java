package com.mtn.service;

import java.util.List;
import java.util.Optional;

import com.mtn.resources.Book;

public interface BookService {
	
	List<Book> getAllBooks();

	Optional<Book> getBookById(Long bookId);

	Book createBook(Book book);	

	Book updateBook(Book book);

	void deleteBook(Book book);
	
	Book searchBookByTitle(String title);
	
	
}
