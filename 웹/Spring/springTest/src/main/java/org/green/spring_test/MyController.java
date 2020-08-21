package org.green.spring_test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
	@RequestMapping("/callTodo")
	public String todo() {
		return "redirect:redirectCall";
	}
	@RequestMapping("/redirectCall")
	public String redirectCall() {		
		return "redirect";
	}
}




