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
	
	// 휴면 계정 처리 
	public int modifyDormantMember() {
		int row = loginMapper.updateDormantMember();
		return row;
	}
	// 로그인
	public Map<String,Object> login(String memberId, String memberPw) {
		// 디버깅
		log.debug(CF.YHJ + "LoginService.login.memberId : " + memberId + CF.RESET);
		log.debug(CF.YHJ + "LoginService.login.memberPw : " + memberPw + CF.RESET);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("memberPw", memberPw);

		String resultId = loginMapper.loginMember(map); // 로그인
		String memberActive = memberMapper.selectMemberActive(memberId); // active
		String level = memberMapper.selectMemberLevel(memberId); // level 
		
		//디버깅
		log.debug(CF.YHJ + "LoginService.login.resultId : " + resultId + CF.RESET);
		log.debug(CF.YHJ + "LoginService.login.memberActive : " + memberActive + CF.RESET);
		log.debug(CF.YHJ + "LoginService.login.level : " + level + CF.RESET);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("memberId", resultId);
		resultMap.put("memberActive", memberActive);
		resultMap.put("level", level);

		return resultMap;
	}
	// 로그인 시 lastLogindDate Update 
	public int modifyLastLoginUpdate(String memberId) {
		int row = loginMapper.updatelastLoginDate(memberId); // 로그인 시 lastLoginDateUpdate
		if (row == 1) {
			log.debug(CF.PSG+"LoginService.login.lastLoginDate 수정 성공"+CF.RESET);
		} else {
			log.debug(CF.PSG+"LoginService.login.lastLoginDate 수정 실패"+CF.RESET);
		}
		return row;
	}
	// 아이디 찾기
	public String findMemberId(String memberLevel, String memberName, String memberPhone) {
		// 디버깅
		log.debug(CF.YHJ + "LoginService.findMemberId.memberLevel : " + memberLevel + CF.RESET);
		log.debug(CF.YHJ + "LoginService.findMemberId.memberName : " + memberName + CF.RESET);
		log.debug(CF.YHJ + "LoginService.findMemberId.memberPhone : " + memberPhone + CF.RESET);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberName", memberName);
		map.put("memberPhone", memberPhone);
				
		String memberId = null;
		if(memberLevel.equals("2")) {
			memberId = loginMapper.findTeacherId(map);
		} else if(memberLevel.equals("3")) {
			memberId = loginMapper.findStudentId(map);
		}
				
		log.debug(CF.YHJ + "LoginService.findMemberId.MemberId : " + memberId + CF.RESET); // 디버깅
		
		return memberId;
	}
	
	// 비밀번호 찾기
	public String findMemberPw(String memberId, String memberName, String memberPhone) {
		// 디버깅
		log.debug(CF.YHJ + "LoginService.findMemberPw.memberId : " + memberId + CF.RESET);
		log.debug(CF.YHJ + "LoginService.findMemberPw.memberName : " + memberName + CF.RESET);
		log.debug(CF.YHJ + "LoginService.findMemberPw.memberPhone : " + memberPhone + CF.RESET);
		
		String level = memberMapper.selectMemberLevel(memberId);
		log.debug(CF.YHJ + "LoginService.findMemberPw.level : " + level + CF.RESET);
		
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
		
		log.debug(CF.YHJ + "LoginService.findMemberPw.memberPw : " + memberPw + CF.RESET); // 디버깅
		
		return memberPw;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
