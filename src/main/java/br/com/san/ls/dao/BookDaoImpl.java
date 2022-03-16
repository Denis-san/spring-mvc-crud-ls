package br.com.san.ls.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.san.ls.entity.Book;

@Repository
@Transactional
public class BookDaoImpl implements BookDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveBook(Book book) {
		entityManager.persist(book);
	}

	@Override
	public List<Book> listAllBooks() {
		TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);

		List<Book> booksResult = query.getResultList();

		return booksResult;
	}

}
