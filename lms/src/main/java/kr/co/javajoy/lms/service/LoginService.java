package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.LoginMapper;
import kr.co.javajoy.lms.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
public class LoginService {
	@Autowired LoginMapper loginMapper;
	@Autowired MemberMapper memberMapper;

	// 로그인
	public String login(String memberId, String memberPw) {
		// 디버깅
		log.debug(CF.YHJ + "LoginService.login.memberId : " + memberId);
		log.debug(CF.YHJ + "LoginService.login.memberPw : " + memberPw);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("memberPw", memberPw);

		String resultId = loginMapper.loginMember(map);
		log.debug(CF.YHJ + "LoginService.login.resultId : " + resultId); // 디버깅

		return resultId;
	}

	// 아이디 찾기
	public String findMemberId(String memberLevel, String memberName, String memberPhone) {
		// 디버깅
		log.debug(CF.YHJ + "LoginService.findMemberId.memberLevel : " + memberLevel);
		log.debug(CF.YHJ + "LoginService.findMemberId.memberName : " + memberName);
		log.debug(CF.YHJ + "LoginService.findMemberId.memberPhone : " + memberPhone);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberName", memberName);
		map.put("memberPhone", memberPhone);
				
		String memberId = null;
		if(memberLevel.equals("2")) {
			memberId = loginMapper.findTeacherId(map);
		} else if(memberLevel.equals("3")) {
			memberId = loginMapper.findStudentId(map);
		}
				
		log.debug(CF.YHJ + "LoginService.findMemberId.MemberId : " + memberId); // 디버깅
		
		return memberId;
	}
	
	// 비밀번호 찾기
	public String findMemberPw(String memberId, String memberName, String memberPhone) {
		// 디버깅
		log.debug(CF.YHJ + "LoginService.findMemberPw.memberId : " + memberId);
		log.debug(CF.YHJ + "LoginService.findMemberPw.memberName : " + memberName);
		log.debug(CF.YHJ + "LoginService.findMemberPw.memberPhone : " + memberPhone);
		
		String level = memberMapper.selectMemberLevel(memberId);
		log.debug(CF.YHJ + "LoginService.findMemberPw.level : " + level);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("memberName", memberName);
		map.put("memberPhone", memberPhone);
		
		String memberPw = null;
		if(level.equals("2")) {
			memberPw = loginMapper.findTeacherPw(map);
		} else if(level.equals("3")) {
			memberPw = loginMapper.findStudentPw(map);
		}
		
		log.debug(CF.YHJ + "LoginService.findMemberPw.memberPw : " + memberPw); // 디버깅
		
		return memberPw;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
