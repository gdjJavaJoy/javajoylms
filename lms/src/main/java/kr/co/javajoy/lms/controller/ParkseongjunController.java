package kr.co.javajoy.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ParkseongjunController {
	@GetMapping("/addMember")
	public String addMember() {
		
		return "login/addMember";
	}
}
