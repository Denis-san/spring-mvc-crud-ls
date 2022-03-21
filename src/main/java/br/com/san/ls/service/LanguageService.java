package br.com.san.ls.service;

import java.util.List;

import br.com.san.ls.entity.Language;

public interface LanguageService {
	
	public Language saveIfNotExitsLanguage(Language lang);
	
	public List<Language> getAllLanguages();

}
