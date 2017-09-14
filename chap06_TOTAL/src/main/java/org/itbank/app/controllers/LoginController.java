package org.itbank.app.controllers;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.itbank.app.model.MemberDaoMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@Autowired
	MemberDaoMyBatis dao;
	
	@RequestMapping({"/","/index"})
	public String rootHandle(Model model) {
		model.addAttribute("section", "index");
		return "t_expr";
	}
	
	@RequestMapping("/log/login")
	public String loginHandle(Model model) {
		model.addAttribute("section", "log/login");
		return "t_expr";
	}
	
	@RequestMapping("/log/session")
	public ModelAndView sessionHandle(HttpSession session, @RequestParam Map map) throws SQLException {
		int r = dao.loginCheck(map);
		if(r == 1) {
			session.setAttribute("auth", map.get("id"));
		}
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "index");
		mav.addObject("r", r);
		return mav;
	}
	
	@RequestMapping("log/logout")
	public String logoutHandle(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
