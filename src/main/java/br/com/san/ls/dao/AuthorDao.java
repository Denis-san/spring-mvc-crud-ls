package br.com.san.ls.dao;

import java.util.List;

import br.com.san.ls.entity.Author;

public interface AuthorDao {

	public List<Author> listAllAuthors();

	public Author findAuthorById(Integer authorId);

	public List<Author> searchAuthorName(String searchName);

	public void update(Author author);

}
