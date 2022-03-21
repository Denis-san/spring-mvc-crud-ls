package br.com.san.ls.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "language_tb")
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@NotBlank
	@Column(name = "language_book")
	private String language;

	@OneToMany(mappedBy = "language", cascade = {  CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	private List<Book> books = new ArrayList<Book>();

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

	public List<Book> getBooks() {
		return books;
	}

	@Override
	public int hashCode() {
		return Objects.hash(language);
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
		return Objects.equals(language, other.language);
	}

	
}
