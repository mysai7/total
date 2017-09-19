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
	AlertWsHandler aws;	// ������ �ڵ鷯�� AutoWired �ɾ ���� ���
	
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
			 * AlertWsHandler�� ���ؼ�, �޼����� ��������. 
			 */
			aws.sendMessage("���ο� �����ڰ� �ֽ��ϴ�.");
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
			return "<b style=\"color: red;\">�̹� ������� ���̵��Դϴ�.</b>";
		}else {
			return "<b style=\"color: blue;\">��� ������ ���̵��Դϴ�.</b>";
		}
	}
	
	@RequestMapping(path="/signup_check/email/{email}", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String emailCheckHandle(@PathVariable String email) throws SQLException {
		int r = dao.emailCheck(email);
		if(r == 1) {
			return "<b style=\"color: red;\">�̹� ������� �̸����Դϴ�.</b>";
		}else {
			return "<b style=\"color: blue;\">��� ������ �̸����Դϴ�.</b>";
		}
	}
}
