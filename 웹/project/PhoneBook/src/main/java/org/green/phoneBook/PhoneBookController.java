package org.green.phoneBook;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PhoneBookController {
	
	@Autowired
	private PhoneBookService service;

	@RequestMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("list", service.selectPhone());
		return "main";
	}
	
	@RequestMapping(value = "goInsert")
	public String insertForm() {
		return "insertForm";
	}
	
	
	@RequestMapping(value = "goModify")
	public String modifyForm(@RequestParam int phone_id, Model model) {
		model.addAttribute("phoneBook", service.searchPhone(phone_id));
		return "modifyForm";
	}
	
	@RequestMapping(value = "insertPhone", method=RequestMethod.POST)
	public String insertPhone(@ModelAttribute PhoneBook pb) {
		service.insertPhone(pb);
		return "redirect:/";
	}
	
	@RequestMapping(value = "deletePhone", method=RequestMethod.POST)
	public String deletePhone(@RequestParam int phone_id) {
		service.deletePhone(phone_id);
		return "redirect:/";
	}
	
	@RequestMapping(value = "modifyPhone", method=RequestMethod.POST)
	public String modifyPhone(@ModelAttribute PhoneBook pb) {
		service.modifyPhone(pb);
		return "redirect:/";
	}
	
	@RequestMapping(value = "searchPhone", method=RequestMethod.GET)
	public String searchPhone(HttpServletRequest request) {
		String searchData = request.getParameter("searchData");
		String search = request.getParameter("search");
		PhoneBook[] list = new PhoneBook[1];
		if(search.equals("id")) {
			try {
				list[0] = service.searchPhone(Integer.parseInt(searchData));
			} catch (NumberFormatException e) {
			}
		} else {
			list[0] = service.searchPhone(searchData);
		}
		
		if(list[0] == null) {
			list = null;
		}
		
		request.setAttribute("list", list);
		
		return "main";
	}
}
