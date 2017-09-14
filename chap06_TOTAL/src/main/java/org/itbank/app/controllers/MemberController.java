package org.itbank.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class MemberController {
	@GetMapping({"/", "/index"})
	public String infoGetHandle() {
		return "t_expr";
	}
	@PostMapping({"/", "/index"})
	public String infoPostHandle() {
		return "t_expr";
	}
	@RequestMapping({"/", "/index"})
	public String infoHandle() {
		return "t_expr";
	}
}
