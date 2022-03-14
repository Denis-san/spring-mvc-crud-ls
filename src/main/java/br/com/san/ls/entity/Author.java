package br.com.san.ls.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Author implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String biography;
	private String nationality;
	private String name;

	private Set<Book> books = new HashSet<Book>();

	public Author() {

	}

	public Author(Integer id, String name, String nationality, String biography) {
		super();
		this.id = id;
		this.name = name;
		this.nationality = nationality;
		this.biography = biography;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void addBook(Book book) {
		books.add(book);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, nationality);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(nationality, other.nationality);
	}

	@Override
	public String toString() {
		return "Author [biography=" + biography + ", nationality=" + nationality + ", name=" + name + "]";
	}
}
