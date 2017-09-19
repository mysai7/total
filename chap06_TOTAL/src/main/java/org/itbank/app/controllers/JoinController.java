package org.itbank.app.controllers;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.itbank.app.model.MemberDaoMyBatis;
import org.itbank.app.ws.controller.AlertWsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/member")
public class JoinController {
	@Autowired
	MemberDaoMyBatis dao;
	
	@Autowired
	AlertWsHandler aws;	// 웹소켓 핸들러를 AutoWired 걸어서 연결 잡고
	
	@RequestMapping(path="/join", method=RequestMethod.GET)
	public ModelAndView joinHandle() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "member/join");
		return mav;
	}
	
	@RequestMapping(path="/join", method=RequestMethod.POST)
	public String joinrstHandle(@RequestParam Map map, HttpSession session) {
		try {
			dao.addMember(map);
			session.setAttribute("auth", map.get("id"));
			/*
			 * AlertWsHandler를 통해서, 메세지를 보내보자. 
			 */
			aws.sendMessage("새로운 가입자가 있습니다.");
			return "redirect:/";
		}catch(Exception e){
			return "redirect:/member/join";
		}
	}
	
	@RequestMapping(path="/signup_check/id/{id}", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String idCheckHandle(@PathVariable String id) throws SQLException {
		int r = dao.idCheck(id);
		if(r == 1) {
			return "<b style=\"color: red;\">이미 사용중인 아이디입니다.</b>";
		}else {
			return "<b style=\"color: blue;\">사용 가능한 아이디입니다.</b>";
		}
	}
	
	@RequestMapping(path="/signup_check/email/{email}", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String emailCheckHandle(@PathVariable String email) throws SQLException {
		int r = dao.emailCheck(email);
		if(r == 1) {
			return "<b style=\"color: red;\">이미 사용중인 이메일입니다.</b>";
		}else {
			return "<b style=\"color: blue;\">사용 가능한 이메일입니다.</b>";
		}
	}
}
