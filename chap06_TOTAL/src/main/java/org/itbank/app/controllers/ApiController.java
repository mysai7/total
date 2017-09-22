package org.itbank.app.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/api")
public class ApiController {
	@Autowired
	ObjectMapper mapper;
	
	@RequestMapping("/03")
	public void api_03Handle() {
		System.out.println("api/03");
		RestTemplate template = new RestTemplate();
		String resp = template.getForObject("http://openapi.seoul.go.kr:8088/6f66654142746530383254796c4859/xml/SearchSTNBySubwayLineService/1/{cnt}/{line}/", 
				String.class, 5, "1");	// 가변길이 인자로 설정하는 형태는 path 에 설정한 이름이 중요하지 않다.
		//System.out.println(resp);
		// JSON : ObjectMapper 로 객체 변환해서 사용하면 됨.
		// XML로만 데이터를 주는 곳도 존재함. XML 분석 라이브러리를 이용해서 해결 해보자
		// JSOUP
		Document doc = Jsoup.parse(resp);
		Elements elms = doc.getElementsByTag("row");
		for(Element e : elms) {
			String scd = e.getElementsByTag("station_cd").get(0).ownText();
			String snm = e.getElementsByTag("station_nm").get(0).ownText();
			String ln = e.getElementsByTag("line_num").get(0).ownText();
			//System.out.println(scd+" / "+snm+" / "+ln);
		}
		List<Map> list = new ArrayList();
		for(Element e : elms) {
			Map map = new HashMap();
			map.put("station_cd", e.getElementsByTag("station_cd").get(0).ownText());
			map.put("station_nm", e.getElementsByTag("station_nm").get(0).ownText());
			map.put("line_num", e.getElementsByTag("line_num").get(0).ownText());
			map.put("FR_CODE", e.getElementsByTag("FR_CODE").get(0).ownText());
			list.add(map);
		}
		System.out.println(list);
	}
	
	@RequestMapping("/01")
	public void api_01Handle() {
		System.out.println("api/01");
		// Spring에서 제공하는 RestTemplate객체를 사용하지 않는다면,
		try{
			URL url = new URL("http://openapi.seoul.go.kr:8088/6f66654142746530383254796c4859/json/SearchSTNBySubwayLineService/1/60/2/");
			Scanner sc = new Scanner(url.openStream(), "utf-8");
			String str = "";
			while(sc.hasNext()) {
				str += sc.nextLine();
			}
			System.out.println(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/02")
	public void api_02Handle(@RequestParam(defaultValue="1") String line) {
		System.out.println("api/02");
		// Spring에서 제공하는 RestTemplate객체를 사용하지 않는다면,
		RestTemplate template = new RestTemplate();
		// get 방식으로 요청결과를 얻어오고 싶으면,
		/*
		String resp = template.getForObject("http://openapi.seoul.go.kr:8088/6f66654142746530383254796c4859/json/SearchSTNBySubwayLineService/1/10/"+line+"/", 
				String.class);
		*/
		String resp = template.getForObject("http://openapi.seoul.go.kr:8088/6f66654142746530383254796c4859/json/SearchSTNBySubwayLineService/1/{cnt}/{line}/", 
				String.class, 5, line);	// 가변길이 인자로 설정하는 형태는 path 에 설정한 이름이 중요하지 않다.
		// 인자 설정을 Map으로 할수도 있는데, 이때는 설정했던 이름이 의미가 있음.
		// 세팅해야되는 데이터들이 많다면,
		System.out.println(resp);
		// ObjectMapper
		// (JSON 형태의 문자열 ==> 객체 : readValue)
		// (객체 ==> JSON 형태의 문자열 : writeValueAsString)
		try {
			Map obj = mapper.readValue(resp, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// RestTemplate, 특정경로로 요청을 보내고 거기서 응답을 받아볼 수 있음.
	// 이메일 발송하는거 해보았는데, HTML 만들기 힘듬.
}
