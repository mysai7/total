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
import org.springframework.web.bind.annotation.PathVariable;
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
	AlertWsHandler aws;	// 웹소켓 핸들러를 AutoWired 걸어서 연결 잡고
	
	@Autowired
	JavaMailSender sender;
	
	@RequestMapping(path="/email_check", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String ekeyCheckHandle(@RequestBody String email, HttpSession session) {
		System.out.println(email);
		UUID u = UUID.randomUUID();
		String auth_str = u.toString().substring(0, 13);
		session.setAttribute("auth_str", auth_str);
		SimpleMailMessage msg = new SimpleMailMessage();
		try {
			msg.setTo(email);
			msg.setFrom("itbanksaan@gmail.com");
			msg.setSubject("회원가입 인증 메일입니다.");
			String text = "환영합니다.\n항상 최선의 서비스를 제공하겠습니다.\n"+auth_str;
			msg.setText(text);
			System.out.println(text);
			sender.send(msg);
			return "true";
		}catch(Exception e) {
			e.printStackTrace();
			return "false";
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
		System.out.println(session.getAttribute("auth_str"));
		String auth_str = (String)session.getAttribute("auth_str");
		String ekey = (String)map.get("ekey");
		try {
			if(ekey.equals(auth_str)) {
				dao.addMember(map);
				session.setAttribute("auth", map.get("id"));
				/*
				 * AlertWsHandler를 통해서, 메세지를 보내보자. 
				 */
				aws.sendMessage("새로운 가입자가 있습니다.");
			}
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
	
	@RequestMapping(path="/signup_check/email", method=RequestMethod.POST, produces="text/html;charset=utf-8")
	@ResponseBody
	public String emailCheckHandle(@RequestBody String email) throws SQLException {
		int r = dao.emailCheck(email);
		if(r == 1) {
			return "<b style=\"color: red;\">이미 사용중인 이메일입니다.</b>";
		}else {
			return "<b style=\"color: blue;\">사용 가능한 이메일입니다.</b>";
		}
	}
}
