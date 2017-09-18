package org.itbank.app.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.itbank.app.model.BoardDaoMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/board")
public class BoardController {
//	@Autowired
//	BoardDao dao;
	
	@Autowired
	BoardDaoMyBatis dao;
	// View�� �ѱ涧 �����͸� �����ؾߵȴٸ�,
	// 1. �Ű������� (Map, Model, ModelMap)�� �޾Ƽ� �ű⿡ �����ϰ�, ���̸��� ��ȯ.
	// 2. ModelAndView ��ü�� ��ȯ
	
	// @GetMapping("/add")
	@RequestMapping(path="/add", method=RequestMethod.GET)
	public ModelAndView addHandle() {
		//return "tw_�۾���/add";
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "board/add");
		return mav;		
	}
	// @PostMapping("/add");
	@RequestMapping(path="/add", method=RequestMethod.POST)
	public ModelAndView addExecHandle(@RequestParam Map map) throws SQLException {
		//boolean r = dao.addOne(map);
		int r = dao.addOne(map);
		ModelAndView mav = new ModelAndView("t_expr");
		//mav.setViewName("tw_���/board/addExec");
		mav.addObject("section", "board/addExec");
		mav.addObject("r", r);
		return mav;
	}
	
	@RequestMapping(path="/list")
	public ModelAndView listHandle() throws SQLException {
		List<Map> all = dao.readAll();
		//ModelAndView mav = new ModelAndView("t_list");
		//ModelAndView mav = new ModelAndView("tw_�Խ���/list");
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "board/list");
		mav.addObject("all", all);
		return  mav;
	}
	
	@RequestMapping(path="/view/{num}")
	public ModelAndView viewHandle(@PathVariable String num) throws SQLException {
		Map map = dao.readOne(num);
		//ModelAndView mav = new ModelAndView("tw_�󼼺���/board/view");	// �ٷ� ���̸� ����
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "board/view");
		//mav.setViewName("board/view");
		mav.addObject("map", map);
		return mav;
	}
}
