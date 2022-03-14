package br.com.san.ls.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.san.ls.entity.Author;
import br.com.san.ls.entity.Book;
import br.com.san.ls.entity.Language;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/register")
	public ModelAndView showRegisterForm() {
		ModelAndView mv = new ModelAndView("register-book");
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
		ModelAndView mv = new ModelAndView("register-book");

		book.getAuthors().add(new Author());

		return mv;
	}

	@RequestMapping(value = "/register", params = { "removeBook" })
	public ModelAndView removeRow(final Book book, final BindingResult bindingResult, final HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("register-book");

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
		
		for(Author a : bookSend.getAuthors()) {
			System.out.println(a);
		}

		// mock
		List<Language> listAllLanguages = Arrays.asList(new Language(null, "Inglês"),
				new Language(null, "Português Br"));
		mv.addObject("allLanguages", listAllLanguages);
		mv.setViewName("register-book");

		return mv;

	}

}
