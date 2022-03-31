package br.com.san.ls.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.san.ls.dto.BookDTO;
import br.com.san.ls.entity.Author;
import br.com.san.ls.entity.Book;
import br.com.san.ls.entity.Language;
import br.com.san.ls.service.AuthorService;
import br.com.san.ls.service.BookService;
import br.com.san.ls.service.LanguageService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AuthorService authorService;

	@Autowired
	private LanguageService langService;

	@Autowired
	private BookService bookService;

	@RequestMapping("/register")
	public ModelAndView showRegisterForm() {
		ModelAndView mv = new ModelAndView("/register_book_templates/register_book.html");
		List<Language> listAllLanguages = langService.getAllLanguages();

		mv.addObject("bookDTO", new BookDTO());
		mv.addObject("allLanguages", listAllLanguages);
		return mv;
	}

	@PostMapping(value = "/register", params = { "searchAuthor" })
	public ModelAndView searchAuthorToAdd(final BookDTO bookDTO, BindingResult bdResult, @RequestParam String search) {
		ModelAndView mv = new ModelAndView("/register_book_templates/register_book.html");

		// change to like sql search
		List<Author> resultAuthors = authorService.searchAuthorByName(search);

		if (!search.isBlank()) {
			mv.addObject("searchAuthorObj", search);
			mv.addObject("resultsAuthors", resultAuthors);
		}

		return mv;
	}

	@RequestMapping(value = "/register", params = { "addAuthor" })
	public ModelAndView addAuthor(final BookDTO bookDTO, BindingResult bdResult, final HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/register_book_templates/register_book.html");

		String authorId = request.getParameter("addAuthor");

		if (authorId.isBlank()) {
			bookDTO.getAuthors().add(new Author());
		} else {
			try {
				Integer theAuthord = Integer.valueOf(authorId);
				bookDTO.getAuthors().add(authorService.getAuthorById(theAuthord));
			} catch (NumberFormatException err) {
				mv.addObject("errorAuthorId", err);
			}
		}

		addFillForLanguageField(bookDTO, mv);

		mv.addObject("bookDTO", bookDTO);

		return mv;
	}

	@RequestMapping(value = "/register", params = { "removeBook" })
	public ModelAndView removeRow(final BookDTO bookDTO, final BindingResult bindingResult,
			final HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("/register_book_templates/register_book.html");

		final Integer rowId = Integer.valueOf(req.getParameter("removeBook"));
		bookDTO.getAuthors().remove(rowId.intValue());

		addFillForLanguageField(bookDTO, mv);

		return mv;
	}

	@RequestMapping(value = "/register", params = { "newLanguage" })
	public ModelAndView newLanguage(BookDTO bookDTO) {
		ModelAndView mv = new ModelAndView("/register_book_templates/register_book");

		mv.addObject("newLang", true);

		return mv;
	}

	@RequestMapping(value = "/register", params = { "cancelNewLanguage" })
	public ModelAndView cancelNewLanguage(BookDTO bookDTO) {
		ModelAndView mv = new ModelAndView("/register_book_templates/register_book");

		mv.addObject("newLang", false);

		List<Language> listAllLanguages = langService.getAllLanguages();
		mv.addObject("allLanguages", listAllLanguages);

		return mv;
	}

	@PostMapping(value = "/register", params = { "send" })
	public ModelAndView processBookRegister(@Valid BookDTO bookDTO, BindingResult bdResult,
			RedirectAttributes attributes) {

		ModelAndView mv = new ModelAndView("redirect:/admin/register");

		List<Language> listAllLanguages = langService.getAllLanguages();

		if (bookDTO.getLanguage().getLanguage() == null && bookDTO.getLanguage().getId() == null) {
			bdResult.addError(new FieldError("errorLang", "language.id", "Não deve ser nulo!"));
		}

		if (bdResult.hasErrors()) {
			if (bookDTO.getLanguage().getId() == null && bookDTO.getLanguage().getLanguage() != null) {
				mv.addObject("newLang", true);
			} else {
				mv.addObject("allLanguages", listAllLanguages);
			}
			mv.setViewName("/register_book_templates/register_book");
		} else {

			Language lang = listAllLanguages.stream().filter(e -> e.equals(bookDTO.getLanguage())).findFirst()
					.orElse(bookDTO.getLanguage());

			bookDTO.setLanguage(lang);

			bookService.saveNewBook(bookDTO.toBook());
			attributes.addFlashAttribute("saved", true);
		}

		return mv;

	}

	@GetMapping("/listBooks")
	public ModelAndView showListBook() {
		ModelAndView mv = new ModelAndView("/list_book_templates/list_books");

		List<Book> books = new ArrayList<Book>();

		mv.addObject("listBook", books);
		return mv;
	}

	@RequestMapping("/listBooks/search")
	public ModelAndView searchBook(@RequestParam(name = "search") String search) {
		ModelAndView mv = new ModelAndView("/list_book_templates/list_books");

		List<Book> results;
		
		if (search.isBlank()) {
			results = bookService.getAllBooks();
		}else {
			results = bookService.searchBook(search);
		}

		mv.addObject("listBook", results);
		mv.addObject("search", search);
		return mv;
	}

	@GetMapping("/listBooks/book/{id}")
	public ModelAndView showBookDetails(@PathVariable(required = true, name = "id") Integer id) {
		ModelAndView mv = new ModelAndView("/list_book_templates/book_details");

		Book bookTemp = bookService.getBookById(id);

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

	@RequestMapping("/listAuthors/search")
	public ModelAndView searchAuthor(@RequestParam(name = "search") String search) {
		ModelAndView mv = new ModelAndView("/author_templates/list_authors");

		List<Author> result;

		if (search.isBlank()) {
			result = authorService.getAllAuthors();
		} else {
			result = authorService.searchAuthorByName(search);
		}

		mv.addObject("listAuthor", result);
		mv.addObject("search", search);

		return mv;
	}

	@GetMapping("/listAuthors/author/{id}")
	public ModelAndView showAuthorDetails(@PathVariable(required = true, name = "id") Integer id) {
		ModelAndView mv = new ModelAndView("/author_templates/author_details");

		Author authorTemp = authorService.getAuthorById(id);

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

	private void addFillForLanguageField(BookDTO bookDTO, ModelAndView mv) {
		if (bookDTO.getLanguage().getId() == null && bookDTO.getLanguage().getLanguage() != null) {
			mv.addObject("newLang", true);
		} else {
			List<Language> listAllLanguages = langService.getAllLanguages();
			mv.addObject("allLanguages", listAllLanguages);
		}
	}

}
