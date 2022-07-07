package kr.co.javajoy.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class StatsController {
	
	@GetMapping("/statsList")
	public String statsList() {
		
		return "statsPage";
	}
}
