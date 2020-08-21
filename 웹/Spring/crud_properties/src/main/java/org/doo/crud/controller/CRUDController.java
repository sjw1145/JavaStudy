package org.doo.crud.controller;

import org.doo.crud.bean.Dummy;
import org.doo.crud.dao.DummyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CRUDController {
	@Autowired
	private DummyDao dao;

	@RequestMapping(value = { "/", "/list" })
	public String list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String strNum,
			Model model) {
		int pageNum = Integer.parseInt(strNum);
		int dummyPerPage = 3;
		Dummy[] list = dao.getList((pageNum - 1) * dummyPerPage, dummyPerPage);
		model.addAttribute("list", list);
		int count = dao.getCount();
		int pageCount = count / dummyPerPage;
		if(count % dummyPerPage > 0) {
			pageCount++;
		}
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNum", pageNum);
		
		return "list";
	}
	
	@RequestMapping("/goInsert")
	public String insert() {
		return "insert";
	}
	
	@RequestMapping("/insert")
	public RedirectView insert(String dcontent) {
		dao.insert(new Dummy(dcontent));
		RedirectView rv = new RedirectView("/crud");
		rv.setExposeModelAttributes(false);
		return rv;
	}
	
	@RequestMapping("/delete")
	public String delete(int dnum) {
		dao.delete(dnum);
		return "redirect:/";
	}
	
	@RequestMapping("/get")
	public String get(int dnum, Model model) {
		model.addAttribute("dummy", dao.get(dnum));
		return "update";
	}
	
	@RequestMapping("/update")
	public String update(Dummy dummy) {
		dao.update(dummy);
		return "redirect:/";
	}

}
