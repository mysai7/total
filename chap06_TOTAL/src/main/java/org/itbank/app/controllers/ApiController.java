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
				String.class, 5, "1");	// �������� ���ڷ� �����ϴ� ���´� path �� ������ �̸��� �߿����� �ʴ�.
		//System.out.println(resp);
		// JSON : ObjectMapper �� ��ü ��ȯ�ؼ� ����ϸ� ��.
		// XML�θ� �����͸� �ִ� ���� ������. XML �м� ���̺귯���� �̿��ؼ� �ذ� �غ���
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
		// Spring���� �����ϴ� RestTemplate��ü�� ������� �ʴ´ٸ�,
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
		// Spring���� �����ϴ� RestTemplate��ü�� ������� �ʴ´ٸ�,
		RestTemplate template = new RestTemplate();
		// get ������� ��û����� ������ ������,
		/*
		String resp = template.getForObject("http://openapi.seoul.go.kr:8088/6f66654142746530383254796c4859/json/SearchSTNBySubwayLineService/1/10/"+line+"/", 
				String.class);
		*/
		String resp = template.getForObject("http://openapi.seoul.go.kr:8088/6f66654142746530383254796c4859/json/SearchSTNBySubwayLineService/1/{cnt}/{line}/", 
				String.class, 5, line);	// �������� ���ڷ� �����ϴ� ���´� path �� ������ �̸��� �߿����� �ʴ�.
		// ���� ������ Map���� �Ҽ��� �ִµ�, �̶��� �����ߴ� �̸��� �ǹ̰� ����.
		// �����ؾߵǴ� �����͵��� ���ٸ�,
		System.out.println(resp);
		// ObjectMapper
		// (JSON ������ ���ڿ� ==> ��ü : readValue)
		// (��ü ==> JSON ������ ���ڿ� : writeValueAsString)
		try {
			Map obj = mapper.readValue(resp, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// RestTemplate, Ư����η� ��û�� ������ �ű⼭ ������ �޾ƺ� �� ����.
	// �̸��� �߼��ϴ°� �غ��Ҵµ�, HTML ����� ����.
}
