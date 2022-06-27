package kr.co.javajoy.lms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.SurveyService;
import kr.co.javajoy.lms.vo.Survey;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SurveyController {
	@Autowired SurveyService surveyService;
	
	@GetMapping("getSurvey")
	public String getSurvey(HttpSession session
							,@RequestParam(value="subjectNo") int subjectNo
							,Model model) {
		String memberId = (String) session.getAttribute("loginUser");
		
		// 디버깅
		log.debug(CF.YHJ + "SurveyController.getSurvey.loginUser : " + memberId + CF.RESET);
		log.debug(CF.YHJ + "SurveyController.getSurvey.subjectNo : " + subjectNo + CF.RESET);
		
		List<Survey> surveyList = surveyService.getSurvey(subjectNo);
		
		log.debug(CF.YHJ + "SurveyController.getSurvey.surveyList : " + surveyList + CF.RESET); // 디버깅
		
		model.addAttribute("surveyList",surveyList);
		
		return "/subject/getSurvey";
	}
	
	@ResponseBody
	@PostMapping("/getSurveyResult")
	public String getSurveyResult(@RequestParam(value="resultList[]") ArrayList<String> resultList
								,Model model) {
		
		log.debug(CF.YHJ + "SurveyController.getSurveyResult.resultList : " + resultList + CF.RESET); // 디버깅
		
		
		model.addAttribute("resultList",resultList);
		return null;
	}
}
