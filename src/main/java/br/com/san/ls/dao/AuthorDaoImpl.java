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

		TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a ORDER BY a.name", Author.class);
		List<Author> results = query.getResultList();

		return results;
	}

	@Override
	public Author findAuthorById(Integer authorId) {
		return entityManager.find(Author.class, authorId);
	}

	@Override
	public List<Author> searchAuthorName(String searchName) {

		searchName = searchName.toLowerCase();
		TypedQuery<Author> query = entityManager
				.createQuery("SELECT a FROM Author a WHERE LOWER(a.name) LIKE :search ORDER BY a.name", Author.class);
		query.setParameter("search", "%" + searchName + "%");

		List<Author> results = query.getResultList();

		return results;

	}

	@Override
	public void update(Author author) {
		// get a object associated with a session by her id
		Author authorTemp = entityManager.find(Author.class, author.getId());
		authorTemp.setName(author.getName());
		authorTemp.setNationality(author.getNationality());
		authorTemp.setBiography(author.getBiography());

		if (authorTemp != null) {
			entityManager.merge(authorTemp);
		}
	}

}
