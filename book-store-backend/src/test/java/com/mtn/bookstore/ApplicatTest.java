package com.mtn.bookstore;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

public class ApplicatTest {
	@RunWith(SpringRunner.class)
	@SpringBootTest(classes = BookStoreApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
	@AutoConfigureMockMvc
	@TestPropertySource(locations = "classpath:application-test.properties")
	public class ApplicationTest {

		@Inject
		private MockMvc mockMvc;

		@Test
		public void testPing() throws Exception {
			mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(equalTo("")));
		}
		
	}

}
