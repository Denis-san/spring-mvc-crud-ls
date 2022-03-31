package br.com.san.ls.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.san.ls.dao.AuthorDao;
import br.com.san.ls.entity.Author;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorDao authorDao;

	@Override
	public List<Author> getAllAuthors() {

		List<Author> result = authorDao.listAllAuthors();

		return result;
	}

	@Override
	public Author getAuthorById(Integer authorId) {
		Author resultAuthor = authorDao.findAuthorById(authorId);
		return resultAuthor;
	}

	@Override
	public List<Author> searchAuthorByName(String searchName) {

		List<Author> results = authorDao.searchAuthorName(searchName);

		return results;
	}

}
