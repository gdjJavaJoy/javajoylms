package kr.co.javajoy.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ErrorController {
	@GetMapping("errorPage")
	public String error() {
		return "errorPage";
	}
	
}
