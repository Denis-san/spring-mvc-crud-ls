package br.com.san.ls.service;

import java.util.List;

import br.com.san.ls.entity.Author;

public interface AuthorService {
	
	public List<Author> getAllAuthors();

	public Author getAuthorById(Integer authorId);

}
