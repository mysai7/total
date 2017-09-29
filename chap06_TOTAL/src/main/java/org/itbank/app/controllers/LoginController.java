package org.itbank.app.controllers;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.itbank.app.model.MemberDaoMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.mail.iap.Response;

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
	
	@PostMapping("/log/session")
	public ModelAndView sessionHandle(HttpSession session, @RequestParam Map map,
			@RequestParam(name="redirect", required=false) String url, HttpServletResponse response) throws SQLException {
		ModelAndView mav = new ModelAndView();
		int r = dao.loginCheck(map);
		if(r == 1) {
			session.setAttribute("auth", map.get("id"));
			if(map.get("keep") != null) {
				Cookie ck = new Cookie("AutoLogin", (String)session.getAttribute("auth"));
				ck.setMaxAge(60*60*24*7);
				ck.setPath("/");
				response.addCookie(ck);
			}
			//mav.addObject("section", "index");
			System.out.println("["+url+"]");
			if(url != null) {
				mav.setViewName("redirect:"+url);
			}else {
				mav.setViewName("redirect:/");
			}
		}else {
			mav.setViewName("t_expr");
			mav.addObject("section", "log/login");
		}
		return mav;
	}
	
	@RequestMapping("log/logout")
	public String logoutHandle(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.invalidate();
		
		Cookie[] ck = request.getCookies();
		if(ck != null) {
			for(Cookie t : ck) {
				String n = t.getName();
				if(n.equals("AutoLogin")) {
					t.setMaxAge(0);
					t.setPath("/");
					response.addCookie(t);
				}
			}
		}
		
		return "redirect:/";
	}
}
