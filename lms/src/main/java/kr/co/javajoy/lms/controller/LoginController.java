package kr.co.javajoy.lms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	
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
	
	@PostMapping("login")
	public String login(HttpSession session
						,@RequestParam(value="memberId") String memberId
						,@RequestParam(value="memeberPw") String memberPw) {
		
		
		return "index";
	}
}
