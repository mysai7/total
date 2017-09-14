package org.itbank.app.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.itbank.app.model.MemberDaoMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/my")
public class MemberController {
	@Autowired
	MemberDaoMyBatis dao;
	
	@GetMapping("/info")
	public ModelAndView infoPostHandle(HttpSession session) {
		ModelAndView mav = new ModelAndView("t_expr");
		Map map = dao.getDetail((String)session.getAttribute("auth"));
		mav.addObject("map", map);
		mav.addObject("section", "my/info");
		return mav;
	}
	@PostMapping("/info")
	public ModelAndView infoUpdateHandle(@RequestParam Map map, HttpSession session) {
		ModelAndView mav = new ModelAndView("t_expr");
		dao.updateDetail(map);
		mav.addObject("section", "my/info");
		return mav;
	}
}
