package br.com.san.ls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.san.ls.service.BookService;

@Controller
public class HomeController {

	@Autowired
	private BookService bookService;

	@RequestMapping("/")
	public ModelAndView showHomePage() {
		ModelAndView mv = new ModelAndView("admin_home");
		Long quantityBookRegister = bookService.getQuantityOfBookRecords();
		mv.addObject("quantityBookRegister", quantityBookRegister);

		return mv;
	}

}
