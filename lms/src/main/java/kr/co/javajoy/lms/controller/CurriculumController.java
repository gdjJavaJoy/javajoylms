package kr.co.javajoy.lms.controller;

import java.util.HashMap;
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
public class CurriculumController {
	@Autowired CurriculumService curriculumService;
	
	// ---------------------- 1) 커리쿨럼 리스트 출력 <SELECT> ----------------------
	
	// 1-1)
	@GetMapping("/getCurriculumList")
	public String getCurriculumList(HttpSession session
								,Model model
								,@RequestParam(value = "currentPage", defaultValue = "1") int currentPage
								,@RequestParam(value = "rowPerPage", defaultValue = "20") int rowPerPage
							    ,@RequestParam(value = "subjectNo") int subjectNo
							    ,@RequestParam(value = "subjectName") String subjectName) {
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.subjectNo : " + subjectNo);
		// 운영자 session 처리
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");
		
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.sessionId : " + memberId);
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.level : " + level);
		
		Map<String, Object> map = curriculumService.selectCurriculumList(currentPage, rowPerPage, subjectNo, subjectName);
		model.addAttribute("curriculumList", map.get("curriculumList"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("subjectNo", map.get("subjectNo"));
		model.addAttribute("subjectName", map.get("subjectName"));
		
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.curriculumList : " + map.get("curriculumList"));
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.currentPage : " + currentPage);
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.lastPage : " + map.get("lastPage"));
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.subjectNo : " + map.get("subjectNo"));
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.subjectName : " + map.get("subjectName"));
		
		// curriculum.jsp로 이동.
		return "curriculum/getCurriculumList";
	}
	
	// 1-2)
	@PostMapping("/getCurriculumList")
	public String getCurriculumList(Model model
									,@RequestParam(name="curriculumNo") int curriculumNo) {
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumList.curriculumNo : " + curriculumNo);
		model.addAttribute("curriculumNo",curriculumNo);
		
		return "redirect:/addCurriculum?curriculumNo=" + curriculumNo;		
	}
	
	// ---------------------- 2) 커리큘럼 입력(운영자용) <INSERT> ----------------------
	
	
	// 2-1) 커리큘럼 입력 Form
	@GetMapping("/addCurriculum")
	public String addCurriculum(HttpSession session
								, Model model
								,@RequestParam(name = "subjectNo") int subjectNo
								,@RequestParam(name = "subjectName") String subjectName) {
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
		model.addAttribute("subjectName", subjectName);
		
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
							,@RequestParam(name = "subjectNo") int subjectNo
							,@RequestParam(name = "subjectName") String subjectName) {
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum(Action).curriculumForm : " + curriculumForm);
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum(Action).subjectNo : " + subjectNo);
		
		model.addAttribute("subjectNo", subjectNo);
		model.addAttribute("subjectName", subjectName);
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum(Action).addAttribute.subjectNo : " + subjectNo);
		
		curriculumService.addCurriculum(curriculumForm);
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum(Action).curriculumForm : " + curriculumForm);
		return "redirect:/getCurriculumList?subjectNo=" + subjectNo + "&subjectName=" + subjectName;
	}
	
	// ------------------------ 3) 커리큘럼 상세보기 <SELECT ONE>------------------------ 
	
	// 3-1) 커리큘럼 상세보기
	@GetMapping("/getCurriculumOne")
	public String getCurriculumOne(HttpSession session
									,Model model
									,@RequestParam(name="curriculumNo") int curriculumNo) {
		// 운영자 + 강사 or  학생 별로 보이는 페이지가 다름...
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");

		log.debug(CF.PBJ + "CurriculumController.getCurriculumOne.sessionId : " + memberId);
		log.debug(CF.PBJ + "CurriculumController.getCurriculumOne.level : " + level);
		// 커리큘럼 no
		Map<String, Object> map = new HashMap<>();
		map.put("curriculumNo", curriculumNo);
		// 커리큘럼 상세보기 + 도서 리스트 
		Map<String, Object> returnMap = curriculumService.getCurriculumOneAndBookList(map);
		model.addAttribute("curriculum", returnMap.get("curriculum"));
		model.addAttribute("bookList", returnMap.get("bookList"));
		model.addAttribute("curriculumNo", curriculumNo);
		
		log.debug(CF.PBJ + "CurriculumController.getCurriculumOne.curriculum : " + returnMap.get("curriculum"));
		log.debug(CF.PBJ + "CurriculumController.getCurriculumOne.bookList : " + returnMap.get("bookList"));
		
		return "curriculum/getCurriculumOne";
	}
	
	// ------------------------ 4) 커리큘럼 수정 <UPDATE>------------------------ 
	
	// 4-1) 커리큘럼 수정 Form
		@GetMapping("/modifyCurriculum")
		public String modifyCurriculum(HttpSession session
									,Model model
									,@RequestParam(name = "curriculumNo") int curriculumNo) {
			log.debug(CF.PBJ + "CurriculumListController.modifyCurriculum(Form).curriculumNo : " + curriculumNo);
			String memberId = (String)session.getAttribute("loginUser");
			String level = (String)session.getAttribute("level");
			
			log.debug(CF.PBJ + "CurriculumListController.modifyCurriculum(Form).sessionId : " + memberId);
			log.debug(CF.PBJ + "CurriculumListController.modifyCurriculum(Form).level : " + level);
			
			// 운영자 아니면.. memberIndex로 redirect
			if(level.equals("2") || level.equals("3")) {
				return "redirect:/login";
			}
			
			// Form 불러오기 
			// 커리큘럼 no
			Map<String, Object> map = new HashMap<>();
			map.put("curriculumNo", curriculumNo);
			// 커리큘럼 상세보기 + 도서 리스트 
			Map<String, Object> returnMap = curriculumService.modifyCurriculumOneAndBookList(map);
			model.addAttribute("curriculum", returnMap.get("curriculum"));
			model.addAttribute("modifyBookList", returnMap.get("modifyBookList"));
			model.addAttribute("curriculumNo", curriculumNo);
			// 강사 이름 리스트 출력
			List<Map<String, Object>> teacherList = curriculumService.selectTeacherName();
			// 프로그래밍 언어 리스트 출력
			List<Map<String, Object>> languageList = curriculumService.selectLanguageName();
			// 교육 도서 리스트 출력
			List<Map<String, Object>> bookList = curriculumService.selectBookName();
			model.addAttribute("teacherList", teacherList);
			model.addAttribute("languageList", languageList);
			model.addAttribute("bookList", bookList);
			;
			log.debug(CF.PBJ + "CurriculumListController.modifyCurriculum(Form).teacherList : " + teacherList);
			log.debug(CF.PBJ + "CurriculumListController.modifyCurriculum(Form).languageList : " + languageList);
			log.debug(CF.PBJ + "CurriculumListController.modifyCurriculum(Form).bookList : " + bookList);
			
			return "curriculum/modifyCurriculum";
		}
		
		// 4-2) 커리큘럼 수정 Action
		@PostMapping("/modifyCurriculum")
		public String modifyCurriculum(Model model
								,CurriculumForm curriculumForm
								,@RequestParam(name = "curriculumNo") int curriculumNo) {
			log.debug(CF.PBJ + "CurriculumListController.modifyCurriculum(Action).curriculumForm : " + curriculumForm);
			log.debug(CF.PBJ + "CurriculumListController.modifyCurriculum(Action).curriculumNo : " + curriculumNo);
			
			model.addAttribute("curriculumNo", curriculumNo);
			log.debug(CF.PBJ + "CurriculumListController.modifyCurriculum(Action).addAttribute.curriculumNo : " + curriculumNo);
			
			curriculumService.modifyCurriculum(curriculumForm);
			log.debug(CF.PBJ + "CurriculumListController.modifyCurriculum(Action).curriculumForm : " + curriculumForm);
			return "redirect:/getCurriculumOne?curriculumNo=" + curriculumNo;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}