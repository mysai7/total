package org.itbank.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ParkInfoController {
	@Autowired
	ObjectMapper mapper;
			
	@RequestMapping("/parkinfo")
	public ModelAndView parkinfoHandle() {
		ModelAndView mav = new ModelAndView("t_expr");
		RestTemplate template = new RestTemplate();
		String parkinfo = template.getForObject("http://openapi.seoul.go.kr:8088/6f66654142746530383254796c4859/json/GetParkInfo/1/10/", String.class);
		try {
			Map obj = mapper.readValue(parkinfo, Map.class);
			mav.addObject("obj", obj);
			mav.addObject("section", "parkinfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
}
