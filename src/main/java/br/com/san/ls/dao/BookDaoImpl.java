package br.com.san.ls.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.san.ls.entity.Book;
import br.com.san.ls.entity.Language;

@Repository
@Transactional
public class BookDaoImpl implements BookDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveBook(Book book) {

		Language lang = book.getLanguage();
		lang.getBooks().add(book);

		if (book.getLanguage().getId() != null) {
			book.setLanguage(entityManager.find(Language.class, book.getLanguage().getId()));
		}

		entityManager.merge(book);

	}

	@Override
	public List<Book> listAllBooks() {
		TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);

		List<Book> booksResult = query.getResultList();

		return booksResult;
	}

	@Override
	public Book findBookById(Integer id) {
		Book result = entityManager.find(Book.class, id);

		return result;
	}

	@Override
	public List<Book> findBookBySearchTitle(String search) {
		search = search.toLowerCase();
		TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE LOWER(b.title) LIKE :search",
				Book.class);
		query.setParameter("search", "%" + search + "%");

		List<Book> result = query.getResultList();
		return result;
	}

}
