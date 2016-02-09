package com.mfly.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestController {

	
	@RequestMapping(value = "/sayhello", method = RequestMethod.GET)
	public ModelAndView helloWorld(HttpServletRequest request, 
	        HttpServletResponse response) {
		return new ModelAndView("RegistraionSuccessful", "message", "Hello World");
	}
}
