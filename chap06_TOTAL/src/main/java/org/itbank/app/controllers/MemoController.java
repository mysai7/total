package org.itbank.app.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.itbank.app.model.MemoDaoMyBatis;
import org.itbank.app.ws.controller.LoginUserWsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/memo")
public class MemoController {
	@Autowired
	MemoDaoMyBatis dao;
	
	@Autowired
	LoginUserWsHandler lws;
	
	@GetMapping("/send")
	public ModelAndView sendMemoHandle() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "memo/send");
		
		return mav;
	}
	
	@PostMapping("/send")
	public ModelAndView sendMemoHandle(@RequestParam Map map) {
		ModelAndView mav = new ModelAndView("redirect:/");
		int r = dao.sendMemo(map);
		mav.addObject("r", r);
		lws.sendMessageToUser((String)map.get("receiver"), map.get("sender")+"님으로부터 수신된 쪽지가 있습니다.\n쪽지함으로 이동하시겠습니까??");
		
		return mav;
	}
	
	@GetMapping("/receive")
	public ModelAndView receiveMemoHandle(HttpSession session) {
		String receiver = (String)session.getAttribute("auth");
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "memo/receive");
		List<Map> list = dao.readAllMemo(receiver);
		mav.addObject("list", list);
		
		return mav;
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteMemoHandle(@RequestParam MultiValueMap map) {
		ModelAndView mav = new ModelAndView();
		System.out.println(map);
		
		return mav;
	}
}
