package com.example.sbs.cuni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String showMain(Model model) {
		return "home/main";
	}
	
	@RequestMapping("home/main")
	public String showMain2(Model model) {
		return "home/main";
	}
}
