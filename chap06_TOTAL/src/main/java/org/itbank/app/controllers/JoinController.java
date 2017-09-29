package org.itbank.app.controllers;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.itbank.app.model.MemberDaoMyBatis;
import org.itbank.app.ws.controller.AlertWsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@Autowired
	JavaMailSender sender;
	
	@RequestMapping(path="/email_check", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String ekeyCheckHandle(@RequestBody String email, HttpSession session) {
		//System.out.println(email);
		UUID u = UUID.randomUUID();
		String authkey = u.toString().substring(0, 13);
		session.setAttribute("authkey", authkey);
		SimpleMailMessage msg = new SimpleMailMessage();
		try {
			msg.setTo(email);
			msg.setFrom("itbanksaan@gmail.com");
			msg.setSubject("ȸ������ ���� �����Դϴ�.");
			String text = "ȯ���մϴ�.\n�׻� �ּ��� ���񽺸� �����ϰڽ��ϴ�.\n����Ű : "+authkey;
			msg.setText(text);
			//System.out.println(text);
			sender.send(msg);
			return "true";
		}catch(Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	@RequestMapping("/join/authCheck")
	@ResponseBody
	public String joinAuthCheckHandle(@RequestParam String key, HttpSession session) {
		System.out.println(key);
		System.out.println(session.getAttribute("authkey"));
		System.out.println(key.equals(session.getAttribute("authkey")));
		if(key.equals((String)session.getAttribute("authkey")) ) {
			return "YYYYY";
		}else {
			return "NNNNN";
		}
	}
	
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
			session.setAttribute("auth", (String)map.get("id"));
			/*
			 * AlertWsHandler�� ���ؼ�, �޼����� ��������. 
			 */
			aws.sendMessage("���ο� �����ڰ� �ֽ��ϴ�.");
		
			return "redirect:/";
		}catch(Exception e){
			return "redirect:/member/join";
		}
	}
	
	@RequestMapping(path="/signup_check/id", method=RequestMethod.POST, produces="text/html;charset=utf-8")
	@ResponseBody
	public String idCheckHandle(@RequestBody String id) throws SQLException {
		int r = dao.idCheck(id);
		if(r == 1) {
			return "<b style=\"color: red;\">�̹� ������� ���̵��Դϴ�.</b>";
		}else {
			return "<b style=\"color: blue;\">��� ������ ���̵��Դϴ�.</b>";
		}
	}
	
	@RequestMapping(path="/signup_check/email", method=RequestMethod.POST, produces="text/html;charset=utf-8")
	@ResponseBody
	public String emailCheckHandle(@RequestBody String email) throws SQLException {
		int r = dao.emailCheck(email);
		if(r == 1) {
			return "<b style=\"color: red;\">�̹� ������� �̸����Դϴ�.</b>";
		}else {
			return "<b style=\"color: blue;\">��� ������ �̸����Դϴ�.</b>";
		}
	}
}
