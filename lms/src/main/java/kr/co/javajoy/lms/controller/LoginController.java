package kr.co.javajoy.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.LoginService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	@Autowired LoginService loginService;
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.debug(CF.YHJ + "LoginController.logout.loginUser : " + session.getAttribute("loginUser"));
		
		session.invalidate(); // 세션에 저장된 값 삭제
		
		return "redirect:/login";
	}
	
	// 로그인
	@GetMapping("/login")
	public String login(HttpSession session) {
		// 로그인이 되어있을 시
		if( (session.getAttribute("level") == "2") || (session.getAttribute("level") == "3")) { // 학생이나 강사로 로그인 되어있을시
			log.debug(CF.YHJ + "LoginController.login.loginUser : " + session.getAttribute("loginUser"));
			return "redirect:memberIndex"; // 초기화면으로
		} else if((session.getAttribute("level") == "1")) { // 운영자로 로그인 되어있을시
			log.debug(CF.YHJ + "LoginController.login.loginUser : " + session.getAttribute("loginUser"));
			return "redirect:getSubjectByPage";
		}
		
		log.debug(CF.YHJ+"login page로");
		return "login/login";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session
						,Model model
						,@RequestParam(value="memberId") String memberId
						,@RequestParam(value="memberPw") String memberPw) {
		// 디버깅
		log.debug(CF.YHJ + "LoginController.login.memberId : " + memberId + CF.RESET);
		log.debug(CF.YHJ + "LoginController.login.memberPw : " + memberPw  + CF.RESET);
		
		Map<String, Object> loginMap = loginService.login(memberId, memberPw); // 로그인
		
		// 디버깅
		log.debug(CF.YHJ + "LoginController.login.loginMap.memberId : " + loginMap.get("memberId") + CF.RESET);
		log.debug(CF.YHJ + "LoginController.login.loginMap.memberActive : " + loginMap.get("memberActive") + CF.RESET);
		log.debug(CF.YHJ + "LoginController.login.loginMap.level : " + loginMap.get("level") + CF.RESET);

		// 로그인 실패 시
		if(loginMap.get("memberId") == null) {
			return "redirect:login"; 
		}
		
		int row = loginService.modifyLastLoginUpdate(memberId); // 마지막 로그인 날짜를 현재 로그인한 시간으로 변경
		
		// 디버깅
		if (row == 1) {
			log.debug(CF.PSG+"LoginController.LoginPost().modifyLastLoginUpdate 수정 성공 " +CF.RESET);
		} else {
			log.debug(CF.PSG+"LoginController.LoginPost().modifyLastLoginUpdate 수정 실패 " +CF.RESET);
		}
		
		// session에 ID와 level 저장
		session.setAttribute("loginUser", loginMap.get("memberId"));
		session.setAttribute("level", loginMap.get("level"));
		
		// level과 active에 따른 로그인 분기
		if(loginMap.get("level").equals("1")) { // 운영자일 경우
			log.debug(CF.YHJ + "getSubjectByPage로 이동 " + CF.RESET);
			return "redirect:getSubjectByPage";
		} else if( (loginMap.get("level").equals("2")) || (loginMap.get("level").equals("3")) ) { // 강사나 학생일 때
			if(loginMap.get("memberActive").equals("4")) { // 강사나 학생이 최초 로그인일 때
				return "redirect:modifyPw";
			} else if(loginMap.get("memberActive").equals("2")) { // 강사나 학생이 비활성화일 때
				return "redirect:modifyMemberActive";
			}
			return "redirect:memberIndex"; // 강사, 학생 정상 로그인
		}
		return "redirect:/errorPage"; // 조건에 맞지않으면 error페이지로
	}
	
	// 아이디 찾기
	@GetMapping("/findMemberId")
	public String findMemberId(HttpSession session) {
		// 로그인이 되어있을 시
		if (session.getAttribute("loginUser") != null) {
			log.debug(CF.YHJ + "LoginController.findMemberId.memberId : " + session.getAttribute("loginUser") + CF.RESET);
			return "redirect:memberIndex";
		}
		
		return "login/findMemberId";
	}
	
	// 아이디 찾기
	@PostMapping("/findMemberId")
	public String findMemberId(HttpSession session
							,Model model
							,@RequestParam(value="memberLevel") String memberLevel
							,@RequestParam(value="memberName") String memberName
							,@RequestParam(value="memberPhone") String memberPhone) {
		
		// 디버깅
		log.debug(CF.YHJ + "LoginController.findMemberId.memberLevel : " + memberLevel + CF.RESET);
		log.debug(CF.YHJ + "LoginController.findMemberId.memberName : " + memberName + CF.RESET);
		log.debug(CF.YHJ + "LoginController.findMemberId.memberPhone : " + memberPhone + CF.RESET);
		
		String memberId = loginService.findMemberId(memberLevel, memberName, memberPhone); // level, 이름, 번호에 따른 ID찾기
		log.debug(CF.YHJ + "LoginController.findMemberId.memberId : " + memberId + CF.RESET); // 디버깅
		
		// model 저장
		model.addAttribute("memberId",memberId);
		model.addAttribute("memberName",memberName);
		
		return "login/resultMemberId";
	}
	
	// 비밀번호 찾기
	@GetMapping("/findMemberPw")
	public String findMemberPw(HttpSession session) {
		// 로그인이 되어있을 시
		if (session.getAttribute("loginUser") != null) {
			log.debug(CF.YHJ + "LoginController.findMemberPw.memberId : " + session.getAttribute("loginUser") + CF.RESET);
			return "redirect:memberIndex";
		}
		
		return "login/findMemberPw";
	}
	
	@PostMapping("/findMemberPw")
	public String findMemberPw(HttpSession session
							,Model model
							,@RequestParam(value="memberId") String memberId
							,@RequestParam(value="memberName") String memberName
							,@RequestParam(value="memberPhone") String memberPhone) {
		// 디버깅
		log.debug(CF.YHJ + "LoginController.findMemberId.memberId : " + memberId + CF.RESET);
		log.debug(CF.YHJ + "LoginController.findMemberId.memberName : " + memberName + CF.RESET);
		log.debug(CF.YHJ + "LoginController.findMemberId.memberPhone : " + memberPhone + CF.RESET);
		
		int row = loginService.findMemberPw(memberId, memberName, memberPhone); // ID,이름, 전화번호에 따른 Pw찾기
		log.debug(CF.YHJ + "LoginController.findMemberId.row : " + row + CF.RESET);
		
		// Pw를 찾기 위한 조건이 다를 시
		if(row == 0) {
			return "redirect:findMemberPw"; // 재호출
		}
		
		model.addAttribute("memberId",memberId); // model 저장
		
		return "login/resultMemberPw"; // 비밀번호 수정창으로 수정해야함
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
