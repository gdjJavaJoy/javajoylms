package kr.co.javajoy.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.CurriculumService;
import kr.co.javajoy.lms.vo.CurriculumForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CurriculumListController {
	@Autowired CurriculumService curriculumService;
	
	// ---------------------- 1) 커리쿨럼 리스트 출력 <SELECT> ----------------------
	
	@GetMapping("/getCurriculumList")
	public String getCurriculumList(HttpSession session
								,Model model
								,@RequestParam(value = "currentPage", defaultValue = "1") int currentPage
								,@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage
							    ,@RequestParam(value = "subjectNo") int subjectNo) {
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.subjectNo : " + subjectNo);
		// 운영자 session 처리
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");
		
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.sessionId : " + memberId);
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.level : " + level);
		
		Map<String, Object> map = curriculumService.selectCurriculumList(currentPage, rowPerPage, subjectNo);
		model.addAttribute("curriculumList", map.get("curriculumList"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("subjectNo", map.get("subjectNo"));
		
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.curriculumList : " + map.get("curriculumList"));
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.currentPage : " + currentPage);
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.sessionId : " + map.get("lastPage"));
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.level : " + map.get("subjectNo"));
		
		// curriculum.jsp로 이동.
		return "curriculum/getCurriculumList";
	}
	
	// ---------------------- 2) 커리큘럼 입력(운영자용) <INSERT> ----------------------
	
	
	// 2-1) 커리큘럼 입력 Form
	@GetMapping("/addCurriculum")
	public String addCurriculum(HttpSession session
								, Model model
								,@RequestParam(name = "subjectNo") int subjectNo) {
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum(Form).subjectNo : " + subjectNo);
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");
		
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum(Form).sessionId : " + memberId);
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum(Form).level : " + level);
		
		// 운영자 아니면.. memberIndex로 redirect
		if(level.equals("2") || level.equals("3")) {
			return "redirect:/login";
		}
		
		// 강사 이름 리스트 출력
		List<Map<String, Object>> teacherList = curriculumService.selectTeacherName();
		// 프로그래밍 언어 리스트 출력
		List<Map<String, Object>> languageList = curriculumService.selectLanguageName();
		// 교육 도서 리스트 출력
		List<Map<String, Object>> bookList = curriculumService.selectBookName();
		model.addAttribute("teacherList", teacherList);
		model.addAttribute("languageList", languageList);
		model.addAttribute("bookList", bookList);
		model.addAttribute("subjectNo", subjectNo);
		
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum(Form).subjectNo : " + subjectNo);
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum(Form).teacherList : " + teacherList);
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum(Form).languageList : " + languageList);
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum(Form).bookList : " + bookList);
		
		return "curriculum/addCurriculum";
	}
	
	// 2-2) 커리큘럼 입력 Action
	@PostMapping("/addCurriculum")
	public String addCurriculum(Model model
							,CurriculumForm curriculumForm
							,@RequestParam(name = "subjectNo") int subjectNo) {
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum(Action).curriculumForm : " + curriculumForm);
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum(Action).subjectNo : " + subjectNo);
		
		model.addAttribute("subjectNo", subjectNo);
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum(Action).addAttribute.subjectNo : " + subjectNo);
		
		curriculumService.addCurriculum(curriculumForm);
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum(Action).curriculumForm : " + curriculumForm);
		return "redirect:/getCurriculumList?subjectNo=" + subjectNo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}