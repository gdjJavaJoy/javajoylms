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
	
	// 스케줄러를 활용한 휴면 계정 처리(1년 이상 미접속) 
	public int modifyDormantMember() {
		int row = loginMapper.updateDormantMember();
		return row;
	}
	
	// 로그인
	public Map<String,Object> login(String memberId, String memberPw) {
		// 디버깅
		log.debug(CF.YHJ + "LoginService.login.memberId : " + memberId + CF.RESET);
		log.debug(CF.YHJ + "LoginService.login.memberPw : " + memberPw + CF.RESET);

		// loginMember()를 사용하기 위한 Map 정재
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("memberPw", memberPw);
		
		int period = memberMapper.selectMemberPwPeriod(memberId);
		String resultId = loginMapper.loginMember(map); // 로그인
		String memberActive = memberMapper.selectMemberActive(memberId); // active
		String level = memberMapper.selectMemberLevel(memberId); // level 
		
		//디버깅
		log.debug(CF.YHJ + "LoginService.login.resultId : " + resultId + CF.RESET);
		log.debug(CF.YHJ + "LoginService.login.memberActive : " + memberActive + CF.RESET);
		log.debug(CF.YHJ + "LoginService.login.level : " + level + CF.RESET);
		log.debug(CF.YHJ + "LoginService.login.period : " + period + CF.RESET);
		
		// returnMap 정재
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("memberId", resultId);
		resultMap.put("memberActive", memberActive);
		resultMap.put("level", level);
		resultMap.put("period", period);

		return resultMap;
	}
	
	// 로그인 성공시 마지막 로그인 날짜 현재 날짜로 변경
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
		
		// findTeacherId(), findStudentId()를 사용하기 위한 Map 정재
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberName", memberName);
		map.put("memberPhone", memberPhone);
				
		String memberId = null;
		if(memberLevel.equals("2")) { // 강사일 경우
			memberId = loginMapper.findTeacherId(map); // 강사 아아디 찾기
		} else if(memberLevel.equals("3")) { // 학생일 경우
			memberId = loginMapper.findStudentId(map); // 학생 아이디 찾기
		}
				
		log.debug(CF.YHJ + "LoginService.findMemberId.MemberId : " + memberId + CF.RESET); // 디버깅
		
		return memberId;
	}
	
	// 비밀번호 찾기
	public int findMemberPw(String memberId, String memberName, String memberPhone) {
		// 디버깅
		log.debug(CF.YHJ + "LoginService.findMemberPw.memberId : " + memberId + CF.RESET);
		log.debug(CF.YHJ + "LoginService.findMemberPw.memberName : " + memberName + CF.RESET);
		log.debug(CF.YHJ + "LoginService.findMemberPw.memberPhone : " + memberPhone + CF.RESET);
		
		String level = memberMapper.selectMemberLevel(memberId); // 사용자 레벨 
		log.debug(CF.YHJ + "LoginService.findMemberPw.level : " + level + CF.RESET);
		
		// findTeacherPw(), findStudentPw를 사용하기 위한 Map 정재
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("memberName", memberName);
		map.put("memberPhone", memberPhone);
		
		int row =0;
		if(level.equals("2")) { // 강사일 경우
			 row = loginMapper.findTeacherPw(map); // 강사 비밀번호 찾기
		} else if(level.equals("3")) { // 학생일 경우
			 row = loginMapper.findStudentPw(map); // 학생 비빌먼호 찾기
		}
		
		if(row == 1) {
			log.debug(CF.PSG+"member비밀번호 찾기 성공 UPDATE 쿼리 실행"+CF.RESET);
			// 비밀번호 찾기를 성공한 경우 
			// 비밀번호를 1111, active를 4(최초로그인 상태)로 바꾸어서 무조건 비밀번호 변경창으로 이동 설계 
			int resultRow = loginMapper.updateFindPwMember(memberId);
			if (resultRow == 1) {
				log.debug(CF.PSG+"member비밀번호 수정성공"+CF.RESET);
			} else {
				log.debug(CF.PSG+"member비밀번호 수정실패"+CF.RESET);
			}
		} else {
			log.debug(CF.PSG+"member비밀번호 찾기 실패"+CF.RESET);
		}
		
		return row;
	}
}
