package kr.co.javajoy.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
	@GetMapping("errorPage")
	public String error() {
		return "errorPage";
	}
}
