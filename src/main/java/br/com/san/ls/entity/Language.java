package br.com.san.ls.entity;

import java.util.Objects;

public class Language {
	
	private Integer id;
	private String language;
	
	public Language() {
		
	}

	public Language(Integer id, String language) {
		super();
		this.id = id;
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, language);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		return Objects.equals(id, other.id) && Objects.equals(language, other.language);
	}
	
}
