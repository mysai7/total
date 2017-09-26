package org.itbank.app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/jquery")
public class JQueryAjaxController {
	@Autowired
	ObjectMapper mapper;
	
	@RequestMapping("/src01")
	public ModelAndView jquerySrc01() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "jquery/src01");
		return mav;
	}
	
	@RequestMapping("/dst01")
	@ResponseBody
	public String jqueryDst01(@RequestParam Map param) {
		System.out.println("jqueryDst01 exec..");
		System.out.println(param);
		return "YYYYY";
	}
	
	@RequestMapping(path="/dst02", produces="text/plain;charset=utf-8") // application/json;charset=utf-8
	@ResponseBody
	public String jqueryDst02() throws JsonProcessingException {
		// ResponseBody 로 String 반환, 응답컨텐츠가 text/plain;charset=ISO-8859-1
		Map map = new HashMap<>();
			map.put("name", "윤형호");
			map.put("gender", "남");
			
		return mapper.writeValueAsString(map);
	}
	
	@RequestMapping(path="/dst03")
	@ResponseBody
	public Map jqueryDst03(){
		// ResponseBody 로 객체를 반환하면, ObjectMapper에 의해서 자동변환된 문자열이
		// 응답으로 전송되는데, 응답컨텐츠가 application/json;charset=utf-8
		Map map = new HashMap<>();
			map.put("name", "윤형호");
			map.put("gender", "남");
			
		return map;
	}
}
