package br.com.san.ls.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.san.ls.dao.BookDao;
import br.com.san.ls.entity.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	
	@Override
	public void saveNewBook(Book book) {
		
		bookDao.saveBook(book);

	}

	@Override
	public List<Book> getAllBooks() {

		List<Book> results = bookDao.listAllBooks();

		return results;
	}

}