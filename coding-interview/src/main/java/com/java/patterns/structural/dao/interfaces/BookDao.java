package com.java.patterns.structural.dao.interfaces;


import com.java.patterns.structural.dao.model.Books;

import java.util.List;

public interface BookDao {

	List<Books> getAllBooks();

	Books getBookByIsbn(int isbn);

	void saveBook(Books book);

	void deleteBook(Books book);
}