package br.com.san.ls.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.ISBN;

import br.com.san.ls.validation.Year;

@Entity
@Table(name = "book_tb")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@NotBlank
	private String title;
	@ISBN
	private String isbn;
	private String edition;
	private String description;
	@Column(name = "path_cloak")
	private String pathCloak;

	@NotNull
	@Year
	private Integer year;

	@NotNull
	@NotBlank
	@Column(name = "publish_company")
	private String publishCompany;

	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private Language language;

	@NotNull
	@Column(name = "number_pages")
	private Integer numberPages;

	@NotNull
	@Min(1)
	@Column(name = "inventory_quantity")
	private Integer inventoryQuantity;

	@NotNull
	@NotBlank
	@Column(name = "shelf_code")
	private String shelfCode;

	@NotEmpty
	@Valid
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "book_author", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "author_id") })
	private List<Author> authors = new ArrayList<Author>();

	public Book() {

	}

	public Book(Integer id, String title, String isbn, String edition, String description, String pathCloak,
			Integer year, String publishCompany, Language language, Integer numberPages, Integer inventoryQuantity,
			String shelfCode) {
		super();
		this.id = id;
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

	public Integer getId() {
		return id;
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

	@Override
	public int hashCode() {
		return Objects.hash(edition, id, language, numberPages, pathCloak, publishCompany, title, year,
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
		Book other = (Book) obj;
		return Objects.equals(edition, other.edition) && Objects.equals(id, other.id)
				&& Objects.equals(language, other.language) && Objects.equals(numberPages, other.numberPages)
				&& Objects.equals(pathCloak, other.pathCloak) && Objects.equals(publishCompany, other.publishCompany)
				&& Objects.equals(title, other.title) && Objects.equals(year, other.year)
				&& Objects.equals(inventoryQuantity, other.inventoryQuantity);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", isbn=" + isbn + ", edition=" + edition + ", description="
				+ description + ", pathCloak=" + pathCloak + ", year=" + year + ", publishCompany=" + publishCompany
				+ ", language=" + language.getLanguage() + ", numberPages=" + numberPages + ", inventoryQuantity="
				+ inventoryQuantity + ", shelfCode=" + shelfCode + "]";
	}

}
