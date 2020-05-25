package com.mtn.repository;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mtn.bookstore.BookStoreApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookStoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookRepositoryTest {
	@Inject
	private BookRepository bookRepository;
	
	@Test
	public void getAllBooks(){
		assertEquals(3, bookRepository.count());
	}
	
	@Test
	public void findByTitle() {
		assertEquals("Everyday Italian", bookRepository.findByTitle("Everyday Italian").getTitle());
	}
	
}
