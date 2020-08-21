package org.green.student_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	@Autowired
	private StudentService service;

	@RequestMapping(value = "/")
	public String goHome(Model model) {
		model.addAttribute("list", service.getAll());
		return "list";
	}
	
	@RequestMapping(value = "goInsert")
	public String goInsert() {
		return "insertForm";
	}
	
	@RequestMapping(value = "goModify")
	public String goModify() {
		return "modify";
	}
	
	@RequestMapping(value = "goDelete")
	public String goDelete() {
		return "delete";
	}
	
	@RequestMapping(value = "doInsert")
	public String doInsert(@ModelAttribute Student student) {
		service.doInsert(student);
		return "redirect:/";
	}
	
	@RequestMapping(value = "doSearch")
	public String doSearch(Model model, @RequestParam String searchData, String work, @RequestParam String search) {
		if(work != null && work.equals("delete")) {
			model.addAttribute("student", service.doSearch(Integer.parseInt(searchData)));
			return "deleteForm";
		} else if(work != null && work.equals("searchStudent")) {
			Student temp = null;
			// ID 검색
			if(search.equals("id")) {
				try {
					temp = service.doSearch(Integer.parseInt(searchData));
				} catch (NumberFormatException e) {
					return "list";
				}
			} else if(search.equals("name") || search.equals("class")) {
				// Name 검색 // Class 검색
				temp = service.doSearch(searchData, work);
			}
			
			if(temp != null) {
				Student[] list = new Student[1];
				list[0] = temp;
				
				model.addAttribute("list", list);
			}
			
			return "list";
		}
		
		model.addAttribute("student", service.doSearch(Integer.parseInt(searchData)));
		return "modifyForm";
	}
	
	@RequestMapping(value = "doDelete")
	public String doDelete(@ModelAttribute Student student) {
		service.doDelete(student);
		return "redirect:/";
	}
	
	@RequestMapping(value = "doModify")
	public String doModify(@ModelAttribute Student student) {
		service.doModify(student);
		return "redirect:/";
	}
}
