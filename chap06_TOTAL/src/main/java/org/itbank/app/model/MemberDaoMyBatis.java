package org.itbank.app.model;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoMyBatis {
	@Autowired
	SqlSessionTemplate template;
	
	public int addMember(Map map) {
		template.insert("member.addMember", map);
		return template.insert("member.addDetail", map.get("id"));
	}
	
	public int idCheck(String id) {
		return template.selectOne("member.idCheck", id);
	}
	
	public int emailCheck(String email) {
		return template.selectOne("member.emailCheck", email);
	}
	
	public int loginCheck(Map map){
		return template.selectOne("member.loginCheck", map);
	}
	
	public Map getDetail(String id) {
		return template.selectOne("member.getDetail", id);
	}
	
	public int updateDetail(Map map) {
		return template.update("member.updateDetail", map);
	}
}
