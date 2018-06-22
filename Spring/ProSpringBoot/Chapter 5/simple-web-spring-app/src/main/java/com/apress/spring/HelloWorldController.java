package com.apress.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/showMessage2.html")
public class HelloWorldController {

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView helloWorld() {
		ModelAndView model = new ModelAndView("showMessage");
		model.addObject("message", "Spring MVC Web App with annotations"); 
		return model;
	}
	
}
