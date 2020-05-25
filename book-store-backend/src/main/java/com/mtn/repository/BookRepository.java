package com.mtn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtn.resources.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	Book findByTitle(String title);
}
