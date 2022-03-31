package br.com.san.ls.service;

import java.util.List;

import br.com.san.ls.entity.Book;

public interface BookService {

	public void saveNewBook(Book book);
	
	public List<Book> getAllBooks();

	public Book getBookById(Integer id);

	public List<Book> searchBook(String search);
	
}
