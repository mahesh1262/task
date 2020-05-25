package com.mtn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mtn.beans.BookDTO;
import com.mtn.exception.ResourceNotFoundException;
import com.mtn.resources.Book;
import com.mtn.service.BookService;

@CrossOrigin(origins = "http://localhost:9876/*")
@RestController
@RequestMapping("/bookstore/book")
public class BookStoreController {

	@Autowired
	private BookService bookService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BookDTO> getAllBooks() {
		List<Book> books = bookService.getAllBooks();
		return books.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable(value = "id") Long bookId) {
		Book book = bookService.getBookById(bookId).get();
		return ResponseEntity.ok().body(convertToDto(book));
	}
	
	 @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Book> getBookByTitle(@RequestParam(value = "title", required = false) String title) {
		 Book book = bookService.searchBookByTitle(title);
		 return ResponseEntity.ok().body(book);
		 
	 }
	 
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Book createBook(@RequestBody BookDTO bookDTO) {
		return bookService.createBook(convertToEntity(bookDTO));
	}

	@PutMapping()
	public ResponseEntity<Book> updateBook(@RequestBody BookDTO bookDTO) throws ResourceNotFoundException {
		 return bookService.getBookById(bookDTO.getId()).map(r -> {
			 modelMapper.map(bookDTO, r);
	            return ResponseEntity.ok(bookService.updateBook(r));
	        }).orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookDTO.getId()));
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteBook(@PathVariable(value = "id") Long bookId) throws ResourceNotFoundException {
		Book book = bookService.getBookById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book  not found for this id :: " + bookId));
		bookService.deleteBook(book);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	

	public BookDTO convertToDto(Book book) {
		BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
		return bookDTO;
	}

	public Book convertToEntity(BookDTO bookDTO) {
		Book book = modelMapper.map(bookDTO, Book.class);
		return book;
	}
}
