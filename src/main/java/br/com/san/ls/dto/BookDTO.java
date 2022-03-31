package br.com.san.ls.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.ISBN;

import br.com.san.ls.entity.Author;
import br.com.san.ls.entity.Book;
import br.com.san.ls.entity.Language;
import br.com.san.ls.validation.Year;

public class BookDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer authorId;

	@NotNull
	@NotBlank
	private String title;
	@ISBN
	private String isbn;
	private String edition;
	private String description;
	private String pathCloak;

	@NotNull
	@Year
	private Integer year;

	@NotNull
	@NotBlank
	private String publishCompany;

	@NotNull
	private Language language;

	@NotNull
	private Integer numberPages;

	@NotNull
	@Min(1)
	private Integer inventoryQuantity;

	@NotNull
	@NotBlank
	private String shelfCode;

	@NotEmpty
	@Valid
	private List<Author> authors = new ArrayList<Author>();

	public BookDTO() {

	}

	public BookDTO(Integer authorId, String title, String isbn, String edition, String description, String pathCloak,
			Integer year, String publishCompany, Language language, Integer numberPages, Integer inventoryQuantity,
			String shelfCode) {
		super();
		this.authorId = authorId;
		this.title = title;
		this.isbn = isbn;
		this.edition = edition;
		this.description = description;
		this.pathCloak = pathCloak;
		this.year = year;
		this.publishCompany = publishCompany;
		this.language = language;
		this.numberPages = numberPages;
		this.inventoryQuantity = inventoryQuantity;
		this.shelfCode = shelfCode;
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

	public Integer getInventoryQuantity() {
		return inventoryQuantity;
	}

	public void setInventoryQuantity(Integer inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getShelfCode() {
		return shelfCode;
	}

	public void setShelfCode(String shelfCode) {
		this.shelfCode = shelfCode;
	}

	public Book toBook() {
		Book book = new Book();
		book.setTitle(this.title);
		book.setIsbn(this.isbn);
		book.setEdition(this.edition);
		book.setDescription(this.description);
		book.setPathCloak(this.pathCloak);
		book.setYear(this.year);
		book.setPublishCompany(this.publishCompany);
		book.setLanguage(this.language);
		book.setNumberPages(this.numberPages);
		book.setInventoryQuantity(this.inventoryQuantity);
		book.setShelfCode(this.shelfCode);
		book.getAuthors().addAll(this.authors);

		return book;
	}

	public Book toBook(Book book) {
		book.setTitle(this.title);
		book.setIsbn(this.isbn);
		book.setEdition(this.edition);
		book.setDescription(this.description);
		book.setPathCloak(this.pathCloak);
		book.setYear(this.year);
		book.setPublishCompany(this.publishCompany);
		book.setLanguage(this.language);
		book.setNumberPages(this.numberPages);
		book.setInventoryQuantity(this.inventoryQuantity);
		book.setShelfCode(this.shelfCode);
		book.getAuthors().addAll(this.authors);
		return book;
	}

	public Book fromBook(Book book) {
		this.title = book.getTitle();
		this.isbn = book.getIsbn();
		this.edition = book.getEdition();
		this.description = book.getDescription();
		this.pathCloak = book.getPathCloak();
		this.year = book.getYear();
		this.publishCompany = book.getPublishCompany();
		this.language = book.getLanguage();
		this.numberPages = book.getNumberPages();
		this.inventoryQuantity = book.getInventoryQuantity();
		this.shelfCode = book.getShelfCode();
		this.authors.addAll(book.getAuthors());
		return book;
	}

	@Override
	public int hashCode() {
		return Objects.hash(edition, authorId, language, numberPages, pathCloak, publishCompany, title, year,
				inventoryQuantity, shelfCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookDTO other = (BookDTO) obj;
		return Objects.equals(edition, other.edition) && Objects.equals(authorId, other.authorId)
				&& Objects.equals(language, other.language) && Objects.equals(numberPages, other.numberPages)
				&& Objects.equals(pathCloak, other.pathCloak) && Objects.equals(publishCompany, other.publishCompany)
				&& Objects.equals(title, other.title) && Objects.equals(year, other.year)
				&& Objects.equals(inventoryQuantity, other.inventoryQuantity);
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", isbn=" + isbn + ", edition=" + edition + ", description=" + description
				+ ", pathCloak=" + pathCloak + ", year=" + year + ", publishCompany=" + publishCompany + ", language="
				+ language + ", numberPages=" + numberPages + ", inventoryQuantity=" + inventoryQuantity
				+ ", shelfCode=" + shelfCode + "]";
	}

}
