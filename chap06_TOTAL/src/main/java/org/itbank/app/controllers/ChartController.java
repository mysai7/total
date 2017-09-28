package org.itbank.app.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.itbank.app.model.MemberDaoMyBatis;
import org.itbank.app.model.ResearchDaoMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/chart")
public class ChartController {
	@Autowired
	MemberDaoMyBatis dao;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	ResearchDaoMyBatis researchDao;
	
	@RequestMapping("/research")
	public ModelAndView researchChartHandle() throws JsonProcessingException {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "chart/research");
		return mav;
	}
	
	@RequestMapping("/researchAjax")
	@ResponseBody
	public List<Map> researchChartAjaxHandle(){
		List<Map> list = researchDao.scoreCount();
		List all = Arrays.asList("1점","2점","3점","4점","5점");
		List json = new ArrayList();
		for(Map m : list) {
			String s = (String)m.get("SCORE");
			all.remove(s);
			Object[] ar = new Object[] {m.get("SCORE"), m.get("CNT")};
			json.add(ar);
		}
		return json;
	}
	
	@RequestMapping("/01")
	public ModelAndView chart01Handle() {
		ModelAndView mav = new ModelAndView("t_expr");
		List<Map> list = dao.countByGender();
		mav.addObject("section", "chart/01");
		System.out.println(list);
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping("/02")
	public ModelAndView chart02Handle() throws JsonProcessingException {
		ModelAndView mav = new ModelAndView("t_expr");
		List<Map> list = dao.countByGender();
		mav.addObject("section", "chart/02");
		// 2차원 배열형태의 JSON 문자열을 설정해두는 .. JSON array = java arrayList , []
		List json = new ArrayList();
		for(Map m : list) {
			Object[] ar = new Object[] {m.get("GENDER"), m.get("CNT")};
			json.add(ar);
		}
		String str = mapper.writeValueAsString(json);
		System.out.println(str);
		mav.addObject("json", str);
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping("/03")
	public ModelAndView chart03Handle() {
		List<Map> list = dao.countByGender();
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "chart/03");
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping("/piedata")
	@ResponseBody
	public List<Map> chartPiedataHandle() throws JsonProcessingException {
		List<Map> list = dao.countByGender();
		List json = new ArrayList();
		for(Map m : list) {
			Object[] ar = new Object[] {m.get("GENDER"), m.get("CNT")};
			json.add(ar);
		}
		return json;
	}

}
