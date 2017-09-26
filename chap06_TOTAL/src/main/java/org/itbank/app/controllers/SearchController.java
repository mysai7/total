package org.itbank.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.itbank.app.model.MemberDaoMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member")
public class SearchController {
	@Autowired
	MemberDaoMyBatis dao;
	
	@RequestMapping("/search")
	public ModelAndView searchIdHandle() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "member/search");
		return mav;
	}
	
	@RequestMapping("/rst")
	@ResponseBody
	public List<Map> searchRstHandle(@RequestParam String search){
		List<Map> list;
		list = dao.searchMember(search);
		System.out.println(list);		
		return list;
	}
}
