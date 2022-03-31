package br.com.san.ls.dao;

import java.util.List;

import br.com.san.ls.entity.Book;

public interface BookDao {
	
	public void saveBook(Book book);
	
	public List<Book> listAllBooks();
	
}