package com.mtn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtn.repository.BookRepository;
import com.mtn.resources.Book;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> getAllBooks() {
		return this.bookRepository.findAll();
	}

	@Override
	public Optional<Book> getBookById(Long BookId) {
		return this.bookRepository.findById(BookId);
	}

	@Override
	public Book createBook(Book Book) {
		return this.bookRepository.save(Book);
	}

	@Override
	public Book updateBook(Book BookDetails) {
		return this.bookRepository.save(BookDetails);
	}

	@Override
	public void deleteBook(Book Book) {
		this.bookRepository.delete(Book);
	}

	@Override
	public Book searchBookByTitle(String title) {
		return this.bookRepository.findByTitle(title);
	}

}
