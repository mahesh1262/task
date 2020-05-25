package com.mtn.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.jayway.jsonpath.JsonPath;
import com.mtn.bookstore.BookStoreApplication;
import com.mtn.repository.BookRepository;
import com.mtn.resources.Book;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookStoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookStoreControllerTest {

	@Inject
	private MockMvc mockMvc;

	@Inject
	private BookRepository bookRepository;

	@Test
	public void getAllBooks() throws Exception {
		mockMvc.perform(get("/bookstore/book").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").exists()).andExpect(jsonPath("$.[0].title").value("Everyday Italian"));
	}

	@Test
	public void getBookById() throws Exception {
		Book book = new Book("title", 2005, 20.00, "language", "catagory");
		book = bookRepository.save(book);

		mockMvc.perform(get("/bookstore/book/" + book.getId()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$.title").value("title"));
	}
	

	@Test
	public void getBookByTitle() throws Exception {
		Book book = new Book("title", 2005, 20.00, "language", "catagory");
		book = bookRepository.save(book);

		mockMvc.perform(get("/bookstore/book/search").param("title", "title").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$.title").value("title"));
	}

	@Test
	public void createBook() throws Exception {
		MvcResult result = mockMvc.perform(post("/bookstore/book").contentType(MediaType.APPLICATION_JSON).content(
				"{\"title\":\"title2\",\"year\":2005,\"price\":20.00,\"language\":\"language\",\"catagory\":\"catagory\"}"))
				.andExpect(status().isOk()).andReturn();
		String title = JsonPath.compile("$.title").read(result.getResponse().getContentAsString());
		assertEquals("title2", title);
	}

	@Test
	public void updateBook() throws Exception {
		Book book = new Book("title", 2005, 20.00, "language", "catagory");
		book = bookRepository.save(book);
		MvcResult result = mockMvc.perform(put("/bookstore/book").contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"" + book.getId()
						+ "\",\"title\":\"title1\",\"year\":2005,\"price\":20.00,\"language\":\"language\",\"catagory\":\"catagory\"}"))
				.andExpect(status().isOk()).andReturn();
		String title = JsonPath.compile("$.title").read(result.getResponse().getContentAsString());
		assertEquals("title1", title);

	}

	@Test
	public void updateBookNotFound() throws Exception {

		mockMvc.perform(put("/bookstore/book").contentType(MediaType.APPLICATION_JSON).content(
				"{\"id\":10,\"title\":\"title\",\"year\":2005,\"price\":20.00,\"language\":\"language\",\"catagory\":\"catagory\"}"))
				.andExpect(status().isNotFound());

	}

	@Test
	public void deleteBook() throws Exception {
		Book book = new Book("title", 2005, 20.00, "language", "catagory");
		book = bookRepository.save(book);
		mockMvc.perform(delete("/bookstore/book/" + book.getId()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		assertEquals(6, bookRepository.count());

	}

}
