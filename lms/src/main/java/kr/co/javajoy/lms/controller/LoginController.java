package kr.co.javajoy.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.javajoy.lms.CF;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	
	@GetMapping("/login")
	public String login() {
		
		log.debug(CF.YHJ+"login page로");
		return "login/login";
	}
}
