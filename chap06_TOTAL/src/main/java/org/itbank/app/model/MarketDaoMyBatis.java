package org.itbank.app.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MarketDaoMyBatis {
	@Autowired
	SqlSessionTemplate template;
	
	public int addItem(Map map) {
		return template.insert("market.addItem", map);
	}
	
	public List<Map> readAll(){
		return template.selectList("market.readAll");
	}
	
	public List<Map> search(Map search){
		return template.selectList("market.searchTwo", search); 
		
	}
	
	public List<Map> getAll() {
		return template.selectList("market.getAll");
	}
	
	public Map readOne(String num) {
		return template.selectOne("market.readOne" , num);
	}
	
	public int addAuction(Map map) {
		return template.insert("market.addAuction", map);
	}
}
