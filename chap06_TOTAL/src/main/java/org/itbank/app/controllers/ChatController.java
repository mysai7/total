package org.itbank.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {
	@RequestMapping("/chat")
	public ModelAndView chatHandle() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "chat");
		
		return mav;
	}
}
