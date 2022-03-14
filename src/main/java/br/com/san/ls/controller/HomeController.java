package br.com.san.ls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HomeController {

	@RequestMapping("/")
	public String showHomePage() {
		return "admin-home";
	}
	
	
	
}
