package kr.co.javajoy.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.SubjectService;
import kr.co.javajoy.lms.service.SurveyService;
import kr.co.javajoy.lms.vo.Student;
import kr.co.javajoy.lms.vo.Survey;
import kr.co.javajoy.lms.vo.SurveyResult;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SurveyController {
	@Autowired SurveyService surveyService;
	@Autowired SubjectService subjectService;
	
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
	
	// 만족도 질문 추가
	@PostMapping("addSurveyQuestion")
	public String addSurveyQuestion(Survey survey) {
		log.debug(CF.YHJ + "SurveyController.addSurveyQuestion.survey : " + survey + CF.RESET);// 디버깅
		
		surveyService.addSurveyQuestion(survey);
		
		return "redirect:/getSubjectOne?subjectNo=" + survey.getSubjectNo();
	}
	
	// 운영자용 만족도를 확인하기 위한 강좌리스트
	@GetMapping("getSurveyByPage")
	public String getSurveyByPage(HttpSession session
									,Model model	
									,@RequestParam @Nullable String searchName
									,@RequestParam(name = "currentPage", defaultValue = "1") int currentPage
									,@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		// 디버깅
		log.debug(CF.YHJ + "SurveyController.getSurveyByPage.currentPage : " + currentPage + CF.RESET);
		log.debug(CF.YHJ + "SurveyController.getSurveyByPage.rowPerPage : " + rowPerPage + CF.RESET);

		// 운영자용 강좌 리스트
		Map<String, Object> map = subjectService.getSubjectByPage(currentPage,rowPerPage,searchName);
		
		// 디버깅
		log.debug(CF.YHJ + "SurveyController.getSurveyByPage.map : " + map + CF.RESET);
		log.debug(CF.YHJ + "SurveyController.getSurveyByPage.currentPage : " + currentPage + CF.RESET);
		log.debug(CF.YHJ + "SurveyController.getSurveyByPage.currentPage : " + searchName + CF.RESET);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("searchName", map.get("searchName"));
		
		return "/member/admin/getSurveyByPage";
	}
	
	// 특정한 강좌에 대한 만족도 조사 학생 리스트
	@GetMapping("getSurveyStudentList")
	public String getSurveyStudentList(Model model
										,@RequestParam @Nullable String searchStudentName
										,@RequestParam(value = "subjectNo") int subjectNo) {
		log.debug(CF.YHJ + "SurveyController.getSurveyStudentList.subjectNo : " + subjectNo + CF.RESET); // 디버깅
		
		List<Map<String,Object>> studentList = subjectService.getSubjectStudentList(subjectNo,searchStudentName);
		
		log.debug(CF.YHJ + "SurveyController.getSurveyStudentList.studentList : " + studentList + CF.RESET);
		
		model.addAttribute("studentList",studentList);
		model.addAttribute("subjectNo",subjectNo);
		
		return "/member/admin/getSurveyStudentList";
	}
	
	@GetMapping("getStudentSurveyResult")
	public String getStudentSurveyResult(Model model
										,String memberId) {
		log.debug(CF.YHJ + "SurveyController.getStudentSurveyResult.memberId : " + memberId + CF.RESET); // 디버깅
		
		List<Map<String,Object>> surveyList = surveyService.getStudentSurveyResult(memberId);
		log.debug(CF.YHJ + "SurveyController.getStudentSurveyResult.surveyList : " + surveyList + CF.RESET); // 디버깅
		
		model.addAttribute("surveyList",surveyList);
		
		return "/member/admin/getStudentSurveyResult";
	}
	
	

	
}
