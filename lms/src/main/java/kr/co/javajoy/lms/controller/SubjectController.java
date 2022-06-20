package kr.co.javajoy.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.service.SubjectService;
import kr.co.javajoy.lms.vo.Subject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SubjectController {
	@Autowired SubjectService subjectService;
	
	// 강좌 입력 폼
	@GetMapping("/addSubject")
	public String addSubject() {
		// addSubject.jsp 불러옴
		return "addSubject";
	}
	
	// 강좌 입력 액션
	@PostMapping("/addSubject")
	public String addSubject(Subject subject) {
		int row = subjectService.addSubject(subject);
		// 디버깅
		log.debug("[박범진] SubjectController.addSubject.param.subject : ", subject);
		log.debug("[박범진] SubjectController.addSubject.row : ", row);
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
		
		return "getSubjectByPage";
	}
}
