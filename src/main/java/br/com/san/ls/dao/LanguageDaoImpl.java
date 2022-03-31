package br.com.san.ls.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.san.ls.entity.Language;

@Repository
@Transactional
public class LanguageDaoImpl implements LanguageDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Language saveLanguage(Language lang) {

		entityManager.persist(lang);

		return lang;

	}

	@Override
	public List<Language> allLanguages() {

		TypedQuery<Language> query = entityManager.createQuery("SELECT l FROM Language l", Language.class);
		List<Language> results = query.getResultList();

		return results;

	}

	@Override
	public Language getLanguageByName(String nameLanguage) {
		TypedQuery<Language> query = entityManager.createQuery("SELECT l FROM Language l WHERE l.language = :language",
				Language.class);
		query.setParameter("language", nameLanguage);

		Language langResult = null;

		try {
			langResult = (Language) query.getSingleResult();
		} catch (NoResultException err) {
			langResult = null;
		} catch (NonUniqueResultException e) {
			Optional<Language> op = query.getResultStream().findFirst();
			langResult = op.get();
		}

		return langResult;

	}

}
