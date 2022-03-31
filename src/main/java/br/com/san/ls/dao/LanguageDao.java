package br.com.san.ls.dao;

import java.util.List;

import br.com.san.ls.entity.Language;

public interface LanguageDao {

	public Language saveLanguage(Language lang);
	
	public List<Language> allLanguages();
	
	public Language getLanguageByName(String nameLanguage);
}
