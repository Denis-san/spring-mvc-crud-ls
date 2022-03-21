package br.com.san.ls.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.san.ls.entity.Author;

@Repository
@Transactional
public class AuthorDaoImpl implements AuthorDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Author> listAllAuthors() {

		TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a", Author.class);
		List<Author> results = query.getResultList();

		return results;
	}

	@Override
	public Author findAuthorById(Integer authorId) {
		return entityManager.find(Author.class, authorId);
	}

}
