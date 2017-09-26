package org.itbank.app.model;

import java.util.List;
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
	
	public int addProfile(Map map) {
		return template.insert("member.addProfile", map);
	}
	
	public Map getProfile(String id) {
		return template.selectOne("member.getProfile", id);
	}
	
	public List<Map> getProfileAll(String id){
		return template.selectList("member.getProfileAll", id);
	}
	
	public List<Map> memberList(){
		return template.selectList("member.memberList");
	}
	
	public List<Map> memberListPage(Map map){
		return template.selectList("member.memberListPage", map);
	}
	
	public int countAllMember() {
		return template.selectOne("member.countAllMember");
	}
	
	public List<Map> searchMember(String search) {
		return template.selectList("member.searchMember", search+"%");
	}
}
