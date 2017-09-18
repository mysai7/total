package org.itbank.app.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoMyBatis {
	@Autowired
	SqlSessionTemplate template;
	
	public int addOne(Map map) {
		return template.insert("board.addOne", map);
	}
	
	public List<Map> readAll(){
		return template.selectList("board.readAll");
	}
	
	public Map readOne(String num) {
		return template.selectOne("board.readOne", num);
	}
}
