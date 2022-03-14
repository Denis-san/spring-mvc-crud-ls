package br.com.san.ls.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String title;
	private String edition;
	private String description;
	private String pathCloak;
	private Integer year;
	private String publishCompany;
	private Language language;
	private Integer numberPages;
	private List<Author> authors = new ArrayList<Author>();
	
	
	public Book() {
		
	}


	public Book(Integer id, String title, String edition, String description, String pathCloak, Integer year,
			String publishCompany, Language language, Integer numberPages) {
		super();
		this.id = id;
		this.title = title;
		this.edition = edition;
		this.description = description;
		this.pathCloak = pathCloak;
		this.year = year;
		this.publishCompany = publishCompany;
		this.language = language;
		this.numberPages = numberPages;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getEdition() {
		return edition;
	}


	public void setEdition(String edition) {
		this.edition = edition;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getPathCloak() {
		return pathCloak;
	}


	public void setPathCloak(String pathCloak) {
		this.pathCloak = pathCloak;
	}


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public String getPublishCompany() {
		return publishCompany;
	}


	public void setPublishCompany(String publishCompany) {
		this.publishCompany = publishCompany;
	}


	public Language getLanguage() {
		return language;
	}


	public void setLanguage(Language language) {
		this.language = language;
	}


	public Integer getNumberPages() {
		return numberPages;
	}


	public void setNumberPages(Integer numberPages) {
		this.numberPages = numberPages;
	}


	public List<Author> getAuthors() {
		return authors;
	}


	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}


	public Integer getId() {
		return id;
	}


	@Override
	public int hashCode() {
		return Objects.hash(edition, id, language, numberPages, pathCloak, publishCompany, title, year);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(edition, other.edition) && Objects.equals(id, other.id)
				&& Objects.equals(language, other.language) && Objects.equals(numberPages, other.numberPages)
				&& Objects.equals(pathCloak, other.pathCloak) && Objects.equals(publishCompany, other.publishCompany)
				&& Objects.equals(title, other.title) && Objects.equals(year, other.year);
	}
	
}
