package br.com.san.ls.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.san.ls.entity.Author;
import br.com.san.ls.entity.Book;
import br.com.san.ls.entity.Language;
import br.com.san.ls.service.AuthorService;
import br.com.san.ls.service.BookService;
import br.com.san.ls.service.LanguageService;

@Controller
@RequestMapping("/book")
public class BookController {

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

		mv.addObject("book", new Book());
		mv.addObject("allLanguages", listAllLanguages);
		return mv;
	}

	@PostMapping(value = "/register", params = { "searchAuthor" })
	public ModelAndView searchAuthorToAdd(final Book book, BindingResult bdResult, @RequestParam String search) {
		ModelAndView mv = new ModelAndView("/register_book_templates/register_book.html");

		List<Author> resultAuthors = authorService.searchAuthorByName(search);

		if (!search.isBlank()) {
			mv.addObject("searchAuthorObj", search);
			mv.addObject("resultsAuthors", resultAuthors);
		}

		return mv;
	}

	@RequestMapping(value = "/register", params = { "addAuthor" })
	public ModelAndView addAuthor(final Book book, BindingResult bdResult, final HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/register_book_templates/register_book.html");

		String authorId = request.getParameter("addAuthor");

		if (authorId.isBlank()) {
			book.getAuthors().add(new Author());
		} else {
			try {
				Integer theAuthord = Integer.valueOf(authorId);
				book.getAuthors().add(authorService.getAuthorById(theAuthord));
			} catch (NumberFormatException err) {
				mv.addObject("errorAuthorId", err);
			}
		}

		addFillForLanguageField(book, mv);

		mv.addObject("book", book);

		return mv;
	}

	@RequestMapping(value = "/register", params = { "removeAuthor" })
	public ModelAndView removeRow(final Book book, final BindingResult bindingResult, final HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("/register_book_templates/register_book.html");

		final Integer rowId = Integer.valueOf(req.getParameter("removeAuthor"));
		book.getAuthors().remove(rowId.intValue());

		addFillForLanguageField(book, mv);

		return mv;
	}

	@RequestMapping(value = "/register", params = { "newLanguage" })
	public ModelAndView newLanguage(Book book) {
		ModelAndView mv = new ModelAndView("/register_book_templates/register_book");

		mv.addObject("newLang", true);

		return mv;
	}

	@RequestMapping(value = "/register", params = { "cancelNewLanguage" })
	public ModelAndView cancelNewLanguage(Book book) {
		ModelAndView mv = new ModelAndView("/register_book_templates/register_book");

		mv.addObject("newLang", false);

		List<Language> listAllLanguages = langService.getAllLanguages();
		mv.addObject("allLanguages", listAllLanguages);

		return mv;
	}

	@PostMapping(value = "/register", params = { "send" })
	public ModelAndView processBookRegister(@Valid Book book, BindingResult bdResult, RedirectAttributes attributes) {

		ModelAndView mv = new ModelAndView("redirect:/book/register");

		List<Language> listAllLanguages = langService.getAllLanguages();

		if (book.getAuthors().isEmpty()) {
			bdResult.addError(new FieldError("errorListAuthor", "book.authors", "Não deve estar vazio!"));
		} else {
			for (Author author : book.getAuthors()) {
				if (author.getId() != null) {
					int index = book.getAuthors().indexOf(author);
					book.getAuthors().set(index, authorService.getAuthorById(author.getId()));
				} else {	
					if (author.getName().isBlank()) {
						ObjectError authorNameError = new ObjectError("authorNameError", "Não deve estar em branco");
						bdResult.addError(authorNameError);
						mv.addObject("authorNameError", authorNameError.getDefaultMessage());
					}
					if (author.getNationality().isBlank()) {
						ObjectError authorNationError = new ObjectError("authorNationError",
								"Não deve estar em branco");
						bdResult.addError(authorNationError);
						mv.addObject("authorNationError", authorNationError.getDefaultMessage());
					}
				}
			}
		}

		if (book.getLanguage().getLanguage() == null && book.getLanguage().getId() == null) {
			bdResult.addError(new FieldError("errorLang", "language.id", "Não deve ser nulo!"));
		}

		if (bdResult.hasErrors()) {
			if (book.getLanguage().getId() == null && book.getLanguage().getLanguage() != null) {
				mv.addObject("newLang", true);
			} else {
				mv.addObject("allLanguages", listAllLanguages);
			}
			mv.setViewName("/register_book_templates/register_book");
		} else {

			Language lang = listAllLanguages.stream().filter(e -> e.equals(book.getLanguage())).findFirst()
					.orElse(book.getLanguage());

			book.setLanguage(lang);

			bookService.saveNewBook(book);
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
		} else {
			results = bookService.searchBook(search);
		}

		mv.addObject("listBook", results);
		mv.addObject("search", search);
		return mv;
	}

	@GetMapping("/info/{id}")
	public ModelAndView showBookDetails(@PathVariable(required = true, name = "id") Integer id) {
		ModelAndView mv = new ModelAndView("/list_book_templates/book_details");

		Book bookTemp = bookService.getBookById(id);

		mv.addObject("book", bookTemp);

		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView showEditForm(@PathVariable(required = true, name = "id") Integer id) {
		ModelAndView mv = new ModelAndView("/edit_book_templates/edit_book_form");

		if (id != null) {
			Book book = bookService.getBookById(id);
			mv.addObject("book", book);
		}

		return mv;
	}

	@GetMapping("/delete/{id}")
	public String showDeleteRegister(@PathVariable(required = true, name = "id") Integer id,
			RedirectAttributes attribute) {

		if (id != null) {
			bookService.deleteBookById(id);
			attribute.addFlashAttribute("deleted", true);
		}
		return "redirect:/book/listBooks";
	}

	private void addFillForLanguageField(Book book, ModelAndView mv) {
		if (book.getLanguage().getId() == null && book.getLanguage().getLanguage() != null) {
			mv.addObject("newLang", true);
		} else {
			List<Language> listAllLanguages = langService.getAllLanguages();
			mv.addObject("allLanguages", listAllLanguages);
		}
	}

}
