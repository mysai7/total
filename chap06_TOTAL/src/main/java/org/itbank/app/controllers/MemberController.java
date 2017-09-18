package org.itbank.app.controllers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.itbank.app.model.MemberDaoMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/my")
public class MemberController {
	@Autowired
	MemberDaoMyBatis dao;
	
	@Autowired
	ServletContext application;
	
	@Autowired
	SimpleDateFormat sdf;
	
	@GetMapping("/list")
	public ModelAndView memberListHandle(@RequestParam(name="page", defaultValue="1") int page) {
		ModelAndView mav = new ModelAndView("t_expr");
		//List<Map> list = dao.memberList();
		int tot = dao.countAllMember();
		int pageSize = 5;
		int size = tot / pageSize;
		if(tot % pageSize > 0) {
			size++;
		}
		mav.addObject("tot", tot);
		mav.addObject("last", size);
		if(page > size) {
			page = size;
		}
		if(page < 0) { 
			page = 1;
		}
		Map map = new HashMap();
		map.put("start", (page - 1) * pageSize + 1 );
		map.put("end", page * pageSize);
		List<Map> list = dao.memberListPage(map);
		mav.addObject("section", "member/list");
		mav.addObject("list", list);
		
		return mav;
	}
	
	@GetMapping("/profile")
	public ModelAndView profileGetHandle(HttpSession session) {
		ModelAndView mav = new ModelAndView("t_expr");
//		String fmt = sdf.format(System.currentTimeMillis());
//		System.out.println(fmt);
		Map map = dao.getProfile((String)session.getAttribute("auth"));
		mav.addObject("map", map);
		mav.addObject("section", "my/profile");
		return mav;
	}
	
	@PostMapping("/profile")
	public ModelAndView profilePostHandle(@RequestParam Map param, @RequestParam(name="profile") MultipartFile f, HttpServletRequest request,
			HttpSession session) throws InterruptedException {
//		System.out.println(request.getParameter("nick"));
//		System.out.println(application.getRealPath("/temp"));
//		Thread.sleep(10000);
//		ModelAndView mav = new ModelAndView("t_expr");
//		System.out.println("파일정보========================");
//		System.out.println(f.toString());
//		System.out.println(f.isEmpty());
//		System.out.println(f.getContentType());
//		System.out.println(f.getName());
//		System.out.println(f.getOriginalFilename());
//		System.out.println(f.getSize());
		// transferTo(File f) .. 실제 업로드. getInputStream()
		ModelAndView mav = new ModelAndView("redirect:profile");
		String id = (String)session.getAttribute("auth");
		String fmt = sdf.format(System.currentTimeMillis());
		String path = application.getRealPath("/profiles");
		String url = "/profiles/"+id+"_"+fmt;
		Map map = new HashMap();
		map.put("id", id);
		map.put("url", url);
		if(!f.isEmpty()) {
			dao.addProfile(map);
			File dst = new File(path, id+"_"+fmt);
			try {
				f.transferTo(dst);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
//		Thread.sleep(5000);
		
//		System.out.println("profilePostHandle.."+param);
//		mav.addObject("section", "my/profile");
		return mav;
	}
	
	@GetMapping("/info")
	public ModelAndView infoPostHandle(HttpSession session) {
		ModelAndView mav = new ModelAndView("t_expr");
		Map map = dao.getDetail((String)session.getAttribute("auth"));
		mav.addObject("map", map);
		mav.addObject("section", "my/info");
		return mav;
	}
	@PostMapping("/info")
	public ModelAndView infoUpdateHandle(@RequestParam Map map) {
		ModelAndView mav = new ModelAndView("redirect:/my/info");
		dao.updateDetail(map);
		return mav;
	}
}
