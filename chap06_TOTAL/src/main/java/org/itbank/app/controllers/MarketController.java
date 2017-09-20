package org.itbank.app.controllers;

import java.util.Map;

import org.itbank.app.model.MarketDaoMyBatis;
import org.itbank.app.ws.controller.AuctionWsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/market")
public class MarketController {
	@Autowired
	MarketDaoMyBatis dao;
	
	@Autowired
	AuctionWsHandler auctionws;
	
	@PostMapping("/auction")
	public ModelAndView addAuctionHandle(@RequestParam Map map) {
		ModelAndView mav = new ModelAndView("redirect:view/"+map.get("parent"));
		int r = dao.addAuction(map);
		String msg = String.format("{\"parent\":\"%s\",\"price\":\"%s\"}", map.get("parent"), map.get("price"));
		auctionws.sendMessageToUser((String)map.get("id"), msg);
		return mav;
	}
	
	@GetMapping(path="/view/{num}")
	public ModelAndView detailHandle(@PathVariable String num) {
		//ModelAndView mav = new ModelAndView("market/view");
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "market/view");
		mav.addObject("data", dao.readOne(num));
		return mav;
	}

	@GetMapping("/all")
	public ModelAndView allHandle() {
		//ModelAndView mav = new ModelAndView("market/all");
		ModelAndView mav = new ModelAndView("t_expr");
		//ModelAndView mav = new ModelAndView("t_m_expr");
		//mav.addObject("section", "market/all");
		mav.addObject("section", "market/all");
		mav.addObject("data", dao.getAll());
		return mav;
	} 
	
	@GetMapping("/search")
	public ModelAndView allHandle(@RequestParam Map param) {
		System.out.println(param);
		//ModelAndView mav = new ModelAndView("market/all");
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "market/all");
		mav.addObject("data", dao.search(param));
		return mav;
	}
	
	@GetMapping("/add")
	public ModelAndView addGetHanle() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "market/add");
		//return "market/add";
		return mav;
	}
	
	@PostMapping("/add")
	public ModelAndView addPost(@RequestParam Map param, Map model) {
		ModelAndView mav = new ModelAndView("t_expr");
		try {
			int r = dao.addItem(param);
			model.put("rst", r);
		}catch (Exception e) {
			model.put("rst", -1);
		}
		mav.addObject("section", "market/add");
		//return "market/add";
		return mav;
	}
}
