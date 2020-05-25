package com.mtn.bookstore;

import org.json.JSONObject;
import org.json.XML;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mtn.repository.BookRepository;
import com.mtn.resources.Book;

@EnableJpaRepositories("com.mtn.repository.")
@ComponentScan(basePackages = "com.mtn.*")
@EntityScan("com.mtn.resources")   
@SpringBootApplication
public class BookStoreApplication implements CommandLineRunner {
	
	@Autowired
	private BookRepository bookRepository;

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }
	@Override
	public void run(String... args) throws Exception {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"<bookstore>\n" + 
				"   <book catagory=\"cooking\">\n" + 
				"      <title lang=\"en\">Everyday Italian</title>\n" + 
				"      <year>2005</year>\n" + 
				"      <price>30.00</price>\n" + 
				"      <authors>\n" + 
				"         <author>Giada De laurentiis</author>\n" + 
				"         <author>sam T. bruce</author>\n" + 
				"      </authors>\n" + 
				"   </book>\n" + 
				"   <book catagory=\"childern\">\n" + 
				"      <title lang=\"en\">Harry Porter</title>\n" + 
				"      <year>2005</year>\n" + 
				"      <price>29.99</price>\n" + 
				"      <authors>\n" + 
				"         <author>J K. rowling</author>\n" + 
				"         <author>Erik T. Ray</author>\n" + 
				"      </authors>\n" + 
				"   </book>\n" + 
				"   <book catagory=\"web\">\n" + 
				"      <title lang=\"en\">Learning XML</title>\n" + 
				"      <year>2003</year>\n" + 
				"      <price>39.95</price>\n" + 
				"      <authors>\n" + 
				"         <author>Erik T. Ray</author>\n" + 
				"      </authors>\n" + 
				"   </book>\n" + 
				"</bookstore>";
		JSONObject xmlJSONObj = XML.toJSONObject(xml);
		JSONObject bookStorejs = xmlJSONObj.getJSONObject("bookstore");
		JSONObject objectBook =  (JSONObject) bookStorejs.getJSONArray("book").get(0);
		JSONObject titleObject =objectBook.getJSONObject("title");
		Book book = new Book(titleObject.getString("content"), objectBook.getInt("year"), objectBook.getDouble("price"),titleObject.getString("lang"),objectBook.getString("catagory"));
		
		bookRepository.save(book);
		JSONObject objectBook1 =  (JSONObject) bookStorejs.getJSONArray("book").get(1);
		JSONObject titleObject1 =objectBook1.getJSONObject("title");
		Book book1 = new Book(titleObject1.getString("content"), objectBook1.getInt("year"), objectBook1.getDouble("price"),titleObject1.getString("lang"),objectBook1.getString("catagory"));
		bookRepository.save(book1);
		
		JSONObject objectBook2 =  (JSONObject) bookStorejs.getJSONArray("book").get(2);
		JSONObject titleObject2 =objectBook2.getJSONObject("title");
		Book book2 = new Book(titleObject2.getString("content"), objectBook2.getInt("year"), objectBook2.getDouble("price"),titleObject2.getString("lang"),objectBook2.getString("catagory"));
		bookRepository.save(book2);
		
		
	}
    
    
}