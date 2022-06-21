package kr.co.javajoy.lms.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.SubjectService;
import kr.co.javajoy.lms.vo.Subject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SubjectController {
	@Autowired SubjectService subjectService;
	
	// 강좌 입력 폼
	@GetMapping("/addSubject")
	public String addSubject(Model model) {
		// 강사 리스트 출력
		ArrayList<String> teacherList = subjectService.getTeacherId();
		model.addAttribute("teacherList", teacherList);
		// addSubject.jsp 불러옴
		return "subject/addSubject";
	}
	
	// 강좌 입력 액션
	@PostMapping("/addSubject")
	public String addSubject(Subject subject) {
		int row = subjectService.addSubject(subject);
		// 디버깅
		log.debug(CF.PBJ + "SubjectController.addSubject.param.subject : ", subject);
		log.debug(CF.PBJ + "SubjectController.addSubject.row : ", row);
		// 강좌 입력 성공 시, 강좌 리스트로
		return "redirect:/getSubjectByPage";
	}
	
	
	// 강좌 리스트(운영자용) 출력 페이징 처리
	@GetMapping("/getSubjectByPage")
	public String getSubjectByPage(Model model, 
			@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		Map<String, Object> map = subjectService.getSubjectByPage(currentPage, rowPerPage);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		
		return "subject/getSubjectByPage";
	}
	
	// 강좌 상세보기
	@GetMapping("/getSubjectOne")
	public String getSubjectOne(Model model, @RequestParam(name="subjectNo") int subjectNo) {
		// 디버깅
		log.debug(CF.PBJ + "SubjectController.getSubjectOne.param.subjectNo : ", subjectNo);
		
		Subject subject = subjectService.getSubjectOne(subjectNo);
		model.addAttribute("subject", subject);
		return "subject/getSubjectOne";
	}
	
	// 강좌 수정 Form
	@GetMapping("/modifySubject")
	public String modifySubject(Model model, @RequestParam(name = "subjectNo") int subjectNo) {
		ArrayList<String> teacherList = subjectService.getTeacherId();
		model.addAttribute("teacherList", teacherList);
		// 디버깅
		log.debug(CF.PBJ + "SubjectController.modifySubject.param.subjectNo : ", subjectNo);

		Subject subject = subjectService.getSubjectOne(subjectNo);
		model.addAttribute("subject", subject);
		return "subject/modifySubject";
	}
	
	// 강좌 수정 Action
	@PostMapping("/modifySubject")
	public String modifySubject(Subject subject) {
		// 디버깅 
		log.debug(CF.PBJ + "SubjectController.modifySubject.param.subject : ", subject);
		int row = subjectService.modifySubject(subject);
		// SubjectOne 컨트롤러 리디렉트
		return "redirect:/getSubjectOne?subjectNo=" + subject.getSubjectNo();
	}
}































