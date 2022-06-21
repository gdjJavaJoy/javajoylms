package kr.co.javajoy.lms.controller;

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
	
	@GetMapping("/login")
	public String login(HttpSession session) {
		// 로그인이 되어있을 시
		if(session.getAttribute("memberId") != null) {
			log.debug(CF.YHJ + "LoginController.login.memberId : " + session.getAttribute("memberId"));
			return "redirect:index";
		}
		log.debug(CF.YHJ+"login page로");
		return "login/login";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session
						,@RequestParam(value="memberId") String memberId
						,@RequestParam(value="memberPw") String memberPw) {
		log.debug(CF.YHJ + "LoginController.login.memberId : " + memberId + CF.RESET);
		log.debug(CF.YHJ + "LoginController.login.memberPw : " + memberPw  + CF.RESET);
		
		String result = loginService.login(memberId, memberPw); // 로그인
		log.debug(CF.YHJ + "LoginController.login.result : " + result + CF.RESET);
		
		// 로그인 실패 시
		if(result == null) {
			return "redirect:login"; 
		}
		session.setAttribute("loginUser", result);
		
		return "index";
	}
	
	// 아이디 찾기
	@GetMapping("/findMemberId")
	public String findMemberId(HttpSession session) {
		// 로그인이 되어있을 시
		if (session.getAttribute("memberId") != null) {
			log.debug(CF.YHJ + "LoginController.login.memberId : " + session.getAttribute("memberId") + CF.RESET);
			return "redirect:index";
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
		
		log.debug(CF.YHJ + "LoginController.findMemberId.memberLevel : " + memberLevel + CF.RESET);
		log.debug(CF.YHJ + "LoginController.findMemberId.memberName : " + memberName + CF.RESET);
		log.debug(CF.YHJ + "LoginController.findMemberId.memberPhone : " + memberPhone + CF.RESET);
		
		String memberId = loginService.findMemberId(memberLevel, memberName, memberPhone);
		log.debug(CF.YHJ + "LoginController.findMemberId.memberId : " + memberId + CF.RESET);
		
		model.addAttribute("memberId",memberId);
		model.addAttribute("memberName",memberName);
		
		return "login/resultMemberId";
	}
	
	// 비밀번호 찾기
	@GetMapping("/findMemberPw")
	public String findMemberPw(HttpSession session) {
		// 로그인이 되어있을 시
		if (session.getAttribute("memberId") != null) {
			log.debug(CF.YHJ + "LoginController.login.memberId : " + session.getAttribute("memberId") + CF.RESET);
			return "redirect:index";
		}
		
		return "login/findMemberPw";
	}
	
	@PostMapping("/findMemberPw")
	public String findMemberPw(HttpSession session
							,Model model
							,@RequestParam(value="memberId") String memberId
							,@RequestParam(value="memberName") String memberName
							,@RequestParam(value="memberPhone") String memberPhone) {
		
		log.debug(CF.YHJ + "LoginController.findMemberId.memberId : " + memberId + CF.RESET);
		log.debug(CF.YHJ + "LoginController.findMemberId.memberName : " + memberName + CF.RESET);
		log.debug(CF.YHJ + "LoginController.findMemberId.memberPhone : " + memberPhone + CF.RESET);
		
		String memberPw = loginService.findMemberPw(memberId, memberName, memberPhone);
		log.debug(CF.YHJ + "LoginController.findMemberId.memberId : " + memberPw + CF.RESET);
		
		if(memberPw == null) {
			return "redirect:findMemberPw";
		}
		
		model.addAttribute("memberId",memberPw);
		model.addAttribute("memberName",memberId);
		
		
		
		return "login/resultMemberPw"; // 비밀번호 수정창으로 수정해야함
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
