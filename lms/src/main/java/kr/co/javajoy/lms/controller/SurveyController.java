package kr.co.javajoy.lms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.SurveyService;
import kr.co.javajoy.lms.vo.Survey;
import kr.co.javajoy.lms.vo.SurveyResult;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SurveyController {
	@Autowired SurveyService surveyService;
	
	// 만족도 보여주기
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
		model.addAttribute("subjectNo",subjectNo);
		
		return "/subject/getSurvey";
	}
	
	// 만족도 결과 입력
	@PostMapping("/getSurveyResult")
	public String getSurveyResult(SurveyResult surveyResult
								,@RequestParam (value="subjectNo") int subjectNo){
		log.debug(CF.YHJ + "SurveyController.getSurveyResult.surveyResult : " + surveyResult + CF.RESET); // 디버깅
		
		surveyService.addSurveyResult(surveyResult);
		
		return "redirect:/getSubjectOne?subjectNo=" + subjectNo;
	}
	
	// 만족도 질문 추가
	@GetMapping("addSurveyQuestion")
	public String addSurveyQuestion(HttpSession session
									,@RequestParam(value="subjectNo") int subjectNo
									,Model model) {
		String memberId = (String) session.getAttribute("loginUser");
		
		// 디버깅
		log.debug(CF.YHJ + "SurveyController.addSurveyQuestion.loginUser : " + memberId + CF.RESET);
		log.debug(CF.YHJ + "SurveyController.addSurveyQuestion.subjectNo : " + subjectNo + CF.RESET);	
		
		model.addAttribute("subjectNo",subjectNo);
		
		return "/subject/addSurveyQuestion";
	}
	
	@PostMapping("addSurveyQuestion")
	public String addSurveyQuestion(Survey survey) {
		log.debug(CF.YHJ + "SurveyController.addSurveyQuestion.survey : " + survey + CF.RESET);// 디버깅
		
		surveyService.addSurveyQuestion(survey);
		
		return "redirect:/getSubjectOne?subjectNo=" + survey.getSubjectNo();
	}
}
