package br.com.san.ls.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.san.ls.entity.Author;
import br.com.san.ls.service.AuthorService;

@Controller
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

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

	@GetMapping("/editAuthor")
	public ModelAndView showAuthorEditForm(@RequestParam Integer id) {
		ModelAndView mv = new ModelAndView("/author_templates/author_edit_form");

		Author author = authorService.getAuthorById(id);

		mv.addObject("author", author);

		return mv;
	}

	@PostMapping("/editAuthor/edit")
	public String processAuthorEdit(@Valid Author author, BindingResult bdResult, RedirectAttributes attributes) {

		if (bdResult.hasErrors()) {
			return "/author_templates/author_edit_form";
		} else {
			authorService.updateAuthor(author);
			attributes.addFlashAttribute("saved", true);
		}

		return "redirect:/author/listAuthors/author/" + author.getId();
	}

}
