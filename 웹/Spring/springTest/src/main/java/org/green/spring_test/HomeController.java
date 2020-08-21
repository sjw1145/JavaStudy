package org.green.spring_test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	/*
	   	/a/b/what
	   	myValue -> what
	 */
	@RequestMapping("/a/b/{myValue}") 
	public String pathVariable(@PathVariable("myValue") String myValue) {
		System.out.println(myValue);
		return "other";
	}
	@RequestMapping("/empty")
	public void emptyAction() {
		
	}
	
	/*
	 * - parameter 종류 
	 * request, response, session, OutputStream/Writer
	 * @RequestParam("파라미터이름")
	 * @RequestParam(value="파라미터이름", required=false, defaultValue="1")
	 * @ModelAttribute("속성이름")
	 * @CookieValue("쿠키이름")
	 * @CookieValue(value="쿠키이름", required=false, defaultValue="1")
	 * @PathVariable("myValue") = 요청 URL이 /a/b/what 일때 myValue -> what 
	 * 
	 * 
	 * - 리턴 종류
	 * String : view이름리턴
	 * ModelAndView : model + view
	 * void : URL에서 뷰이름을 구함(RequestToViewNameResolver)
	 * View
	 * RedirectView
	 */
	@RequestMapping(value="/userInfo", method=RequestMethod.POST)
	public String makeUser(User some, @CookieValue("what") String what) {		
		System.out.println(some);
		return "other";
	}	
	
	@RequestMapping(value="/formData", method=RequestMethod.POST)
	public String parameterProcess(String inputValue) {
		System.out.println(inputValue);
		return "redirect:start";
	}
	
	@RequestMapping(value = {"/", "/start", "*.do"}, method = RequestMethod.GET)
	public String start(Model model) {
//		request.setAttribute("some", "other");
		
//		mav.setViewName("hello");
//		mav.addObject("some", "other");
//		return mav;
		
		model.addAttribute("some", "other");
		return "hello";
	}
	
}











