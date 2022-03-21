package br.com.san.ls.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.san.ls.dao.LanguageDao;
import br.com.san.ls.entity.Language;

@Service
public class LanguageServiceImpl implements LanguageService {
	
	@Autowired
	private LanguageDao langDao;

	@Override
	public Language saveIfNotExitsLanguage(Language lang) {
		
		Language langResut = langDao.saveIfNotExist(lang);
		
		return langResut;

	}

	@Override
	public List<Language> getAllLanguages() {
		
		return langDao.allLanguages();
	}

}
