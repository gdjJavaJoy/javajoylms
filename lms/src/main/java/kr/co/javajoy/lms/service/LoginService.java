package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.LoginMapper;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
public class LoginService {
	@Autowired LoginMapper loginMapper;
	
	public String login(String memberId, String memberPw) {
		// 디버깅
		log.debug(CF.YHJ + "LoginService.login.memberId : " + memberId);
		log.debug(CF.YHJ + "LoginService.login.memberPw : " + memberPw);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId", memberId);
		map.put("memberPw", memberPw);
		
		String resultId = loginMapper.loginMember(map);
		log.debug(CF.YHJ + "LoginService.login.resultId : " + resultId); // 디버깅
		
		return resultId;
		
	}
}
