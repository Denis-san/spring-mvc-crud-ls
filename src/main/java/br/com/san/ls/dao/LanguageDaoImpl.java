package br.com.san.ls.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
	public Language saveIfNotExist(Language lang) {

		Language langResult = getLanguageByName(lang.getLanguage());
		
		if(langResult == null) {
			entityManager.persist(lang);
			langResult = lang;
		}
		
		return langResult;

	}

	@Override
	public List<Language> allLanguages() {

		TypedQuery<Language> query = entityManager.createQuery("SELECT l FROM Language l", Language.class);
		List<Language> results = query.getResultList();

		return results;

	}

	@Override
	public Language getLanguageByName(String nameLanguage) {
		Query query = entityManager.createQuery("SELECT l FROM Language l WHERE l.language = :language",
				Language.class);
		query.setParameter("language", nameLanguage);
		
		Language langResult = null;
		
		try {
			langResult = (Language) query.getSingleResult();
		}catch(NoResultException err) {
			langResult = null;
		}
		
		return langResult;
		
	}

}
