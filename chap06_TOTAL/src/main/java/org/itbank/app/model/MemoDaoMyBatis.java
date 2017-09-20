package org.itbank.app.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemoDaoMyBatis {
	@Autowired
	SqlSessionTemplate template;
	
	public int sendMemo(Map map) {
		return template.insert("memo.sendMemo", map);
	}
	
	public List<Map> readAllMemo(String receiver){
		return template.selectList("memo.readAllMemo", receiver);
	}
}
