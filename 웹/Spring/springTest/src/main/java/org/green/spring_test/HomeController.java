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
	 * - parameter ���� 
	 * request, response, session, OutputStream/Writer
	 * @RequestParam("�Ķ�����̸�")
	 * @RequestParam(value="�Ķ�����̸�", required=false, defaultValue="1")
	 * @ModelAttribute("�Ӽ��̸�")
	 * @CookieValue("��Ű�̸�")
	 * @CookieValue(value="��Ű�̸�", required=false, defaultValue="1")
	 * @PathVariable("myValue") = ��û URL�� /a/b/what �϶� myValue -> what 
	 * 
	 * 
	 * - ���� ����
	 * String : view�̸�����
	 * ModelAndView : model + view
	 * void : URL���� ���̸��� ����(RequestToViewNameResolver)
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











