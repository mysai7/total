package org.itbank.app.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ResearchDaoMyBatis {
	@Autowired
	SqlSessionTemplate template;
	
	public List<Map> scoreCount(){
		return template.selectList("research.scoreCount");
	}
}
