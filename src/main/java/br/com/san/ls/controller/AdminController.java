package br.com.san.ls.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.san.ls.entity.Author;
import br.com.san.ls.entity.Book;
import br.com.san.ls.entity.Language;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/register")
	public ModelAndView showRegisterForm() {
		ModelAndView mv = new ModelAndView("/register_book_templates/register_book.html");
		// mock
		List<Language> listAllLanguages = Arrays.asList(new Language(null, "Inglês"),
				new Language(null, "Português Br"));

		// CHANGE TO BOOKDTO
		mv.addObject("book", new Book());
		mv.addObject("allLanguages", listAllLanguages);
		return mv;
	}

	@RequestMapping(value = "/register", params = { "addAuthor" })
	public ModelAndView addAuthor(final Book book, BindingResult bdResult) {
		ModelAndView mv = new ModelAndView("/register_book_templates/register_book.html");

		book.getAuthors().add(new Author());

		return mv;
	}

	@RequestMapping(value = "/register", params = { "removeBook" })
	public ModelAndView removeRow(final Book book, final BindingResult bindingResult, final HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("/register_book_templates/register_book.html");

		final Integer rowId = Integer.valueOf(req.getParameter("removeBook"));
		book.getAuthors().remove(rowId.intValue());

		return mv;
	}

	@PostMapping(value = "/register", params = { "send" })
	public ModelAndView processBookRegister(@Valid Book book, BindingResult bdResult) {

		ModelAndView mv = new ModelAndView("redirect:/admin/register");

		// Save into database
		Book bookSend = book;

		System.out.println(bookSend);

		for (Author a : bookSend.getAuthors()) {
			System.out.println(a);
		}

		// mock
		List<Language> listAllLanguages = Arrays.asList(new Language(null, "Inglês"),
				new Language(null, "Português Br"));
		mv.addObject("allLanguages", listAllLanguages);

		return mv;

	}

	@GetMapping("/listBooks")
	public ModelAndView showListBook() {
		ModelAndView mv = new ModelAndView("/list_book_templates/list_books");

		// get from the database
		List<Book> books = new ArrayList<Book>();

		mv.addObject("listBook", books);
		return mv;
	}

	@GetMapping("/listBooks/book/{id}")
	public ModelAndView showBookDetails(@PathVariable(required = true, name = "id") Integer id, Book book) {
		ModelAndView mv = new ModelAndView("/list_book_templates/book_details");

		// get from the database by id

		// get from the database
		Book bookTemp = new Book();
		bookTemp.setLanguage(new Language());

		mv.addObject("book", bookTemp);

		return mv;
	}

	@GetMapping("/listAuthors")
	public ModelAndView showListAuthor() {

		ModelAndView mv = new ModelAndView("/author_templates/list_authors");
		List<Author> listAuthor = new ArrayList<Author>();

		mv.addObject("listAuthor", listAuthor);
		return mv;
	}

	@GetMapping("/listAuthors/author/{id}")
	public ModelAndView showAuthorDetails(@PathVariable(required = true, name = "id") Integer id, Author author) {
		ModelAndView mv = new ModelAndView("/author_templates/author_details");

		// get from the database by id
		// get from the database
		Author authorTemp = new Author();

		mv.addObject("author", authorTemp);

		return mv;
	}

	@GetMapping("/editBook")
	public ModelAndView showEditRegister() {
		ModelAndView mv = new ModelAndView("/edit_book_templates/edit_book");

		List<Book> books = new ArrayList<Book>();

		mv.addObject("listBook", books);

		return mv;
	}

	@GetMapping("/editBook/edit/{id}")
	public ModelAndView showEditForm(@PathVariable(required = true, name = "id") Integer id, Book book) {
		ModelAndView mv = new ModelAndView("/edit_book_templates/edit_book_form");

		// get from the database by id
		// get from the database
		// add this object to mv

		return mv;
	}

	@GetMapping("/deleteBook")
	public ModelAndView showDeleteRegister() {
		ModelAndView mv = new ModelAndView("/delete_book_templates/delete_book");

		// get from the database

		return mv;
	}

	@GetMapping("/delete/book")
	public ModelAndView processDelete(@RequestParam(name = "id", required = true) Integer id) {
		ModelAndView mv = new ModelAndView("/delete_book_templates/delete_book");

		// get from the database

		System.out.println(id);

		// if else
		mv.addObject("deleted", true);
		return mv;
	}

}
