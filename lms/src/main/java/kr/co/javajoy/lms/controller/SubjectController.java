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
import kr.co.javajoy.lms.vo.Language;
import kr.co.javajoy.lms.vo.Student;
import kr.co.javajoy.lms.vo.Subject;
import kr.co.javajoy.lms.vo.SubjectVideo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SubjectController {
	@Autowired SubjectService subjectService;
	
	// ---------------------- 1) 강좌 리스트 출력(운영자용) <SELECT> ----------------------
	
	// 1-1) 강좌 리스트(운영자용) 출력 페이징 처리
	@GetMapping("/getSubjectByPage")
	public String getSubjectByPage(HttpSession session
									,Model model
									,@RequestParam @Nullable String sSubjectName
									,@RequestParam(name = "currentPage", defaultValue = "1") int currentPage
									,@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage) {
		// 운영자 session 처리
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");
			
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.sessionId : " + memberId);
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.level : " + level);
		
		// 운영자 아니면.. memberIndex로 redirect
		if(level.equals("2") || level.equals("3")) {
			return "redirect:/login";
		}
		// 운영자용 강좌 리스트
		Map<String, Object> map = subjectService.getSubjectByPage(currentPage, rowPerPage, sSubjectName);
		model.addAttribute("sSubjectName", sSubjectName);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("list", map.get("list"));

		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.sSubjectName : " + sSubjectName);
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.currentPage : " + currentPage);
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.lastPage : " + map.get("lastPage"));
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.list : " + map.get("list"));

		return "subject/getSubjectByPage";
	}
	
	// ---------------------- 2) 강좌 입력(운영자용) <INSERT> ----------------------
	
	// 2-1) 강좌 입력 폼
	@GetMapping("/addSubject")
	public String addSubject(HttpSession session, Model model) {
		// 운영자 session 처리
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");
		
		log.debug(CF.PBJ + "SubjectController.addSubject(Form).sessionId : " + memberId);
		log.debug(CF.PBJ + "SubjectController.addSubject(Form).level : " + level);
		
		// 운영자 아니면.. memberIndex로 redirect
		if(level.equals("2") || level.equals("3")) {
			return "redirect:/login";
		}
		// 강사 리스트 출력
		List<String> teacherList = subjectService.getTeacherId();
		model.addAttribute("teacherList", teacherList);
		
		log.debug(CF.PBJ + "SubjectController.addSubject(Form).teacherList : " + teacherList);
		
		// addSubject.jsp 불러옴
		return "subject/addSubject";
	}
	
	// 2-2) 강좌 입력 액션
	@PostMapping("/addSubject")
	public String addSubject(Subject subject) {
		int row = subjectService.addSubject(subject);
		// 디버깅
		log.debug(CF.PBJ + "SubjectController.addSubject(Action).param.subject : " + subject);
		log.debug(CF.PBJ + "SubjectController.addSubject(Action).row : " + row);
		// 강좌 입력 성공 시, 강좌 리스트로
		return "redirect:/getSubjectByPage";
	}
	
	// ---------------------- 3) 강좌 상세보기 <SELECT ONE> ----------------------

	// 강좌 상세보기
	@GetMapping("/getSubjectOne")
	public String getSubjectOne(HttpSession session
								,Model model
								,@RequestParam(name="subjectNo") int subjectNo) {
		// 운영자 session 처리
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");
		// 학생 + 강사 같은 페이지 사용 .. session 처리 x
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.sessionId : " + memberId);
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.level : " + level);
		// 선택된 강좌 번호 디버깅
		log.debug(CF.PBJ + "SubjectController.getSubjectOne.param.subjectNo : " + subjectNo);
		
		Subject subject = subjectService.getSubjectOne(subjectNo);
		int surveyChk = subjectService.checkSurveyCnt(memberId);
		
		log.debug(CF.PBJ + "SubjectController.getSubjectOne.param.subjectNo : " + subjectNo);
		
		model.addAttribute("subject", subject);
		model.addAttribute("surveyChk", surveyChk);
		
		log.debug(CF.PBJ + "SubjectController.getSubjectOne.param.subject : " + subject);
		log.debug(CF.PBJ + "SubjectController.getSubjectOne.param.surveyChk : " + surveyChk);
		
		return "subject/getSubjectOne";
	}
	
	// ---------------------- 4) 강좌 수정(운영자용) <UPDATE> ----------------------
	
	// 강좌 수정 Form
	@GetMapping("/modifySubject")
	public String modifySubject(HttpSession session
								,Model model
								,@RequestParam(name = "subjectNo") int subjectNo) {
		// 운영자 session 처리
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");
	
		log.debug(CF.PBJ + "SubjectController.modifySubject(Form).sessionId : " + memberId);
		log.debug(CF.PBJ + "SubjectController.modifySubject(Form).level : " + level);
		// 운영자 아니면.. memberIndex로 redirect
		if(level.equals("2") || level.equals("3")) {
			return "redirect:/login";
		}
		
		List<String> teacherList = subjectService.getTeacherId();
		model.addAttribute("teacherList", teacherList);
		
		log.debug(CF.PBJ + "SubjectController.modifySubject(Form).param.subjectNo : " + subjectNo);

		Subject subject = subjectService.getSubjectOne(subjectNo);
		model.addAttribute("subject", subject);
		
		log.debug(CF.PBJ + "SubjectController.modifySubject(Form).param.subject : " + subject);
		
		return "subject/modifySubject";
	}
	
	// 강좌 수정 Action
	@PostMapping("/modifySubject")
	public String modifySubject(Subject subject) {
		log.debug(CF.PBJ + "SubjectController.modifySubject(Action).param.subject : " +  subject);
		int row = subjectService.modifySubject(subject);
		log.debug(CF.PBJ + "SubjectController.modifySubject(Action).row : " +  row);
		// SubjectOne 컨트롤러 리디렉트
		return "redirect:/getSubjectOne?subjectNo=" + subject.getSubjectNo();
	}
	
	// ---------------------- 5) 강좌 삭제(운영자용) <DELETE> ----------------------
	
	// 강좌 삭제
	@GetMapping("/removeSubject")
	public String removeSubject(int subjectNo) {
		log.debug("SubjectController.removeSubject.param.subjectNo");
		subjectService.removeSubject(subjectNo);
	
		return "redirect:/getSubjectByPage";
	}
	
	// ----------------------- 강의 영상 -----------------------
	
	// 강좌 영상으로 가기
	@GetMapping("/getSubjectVideo")
	public String getSubjectVideo(@RequestParam(value="subjectNo") int subjectNo
								,Model model) {
		log.debug(CF.YHJ + "SubjectController.getSubjectVideo.subjectNo : " +  subjectNo + CF.RESET); // 디버깅
		
		List<SubjectVideo> subjectVideoList = subjectService.getSubjectVideoList(subjectNo);
		log.debug(CF.YHJ + "SubjectService.getSubjectVideoList.subjectVideoList : " +  subjectVideoList + CF.RESET); // 디버깅
		
		model.addAttribute("subjectVideoList",subjectVideoList);
		model.addAttribute("subjectNo",subjectNo);
		
		return "/subject/getSubjectVideo";
	}
	
	// 강의영상 추가
	@GetMapping("/addSubjectVideo")
	public String addSubejctVideo(@RequestParam(value="subjectNo") int subjectNo
								,Model model) {
		log.debug(CF.YHJ + "SubjectController.addSubjectVideo.subjectNo : " +  subjectNo + CF.RESET); // 디버깅
		
		model.addAttribute("subjectNo",subjectNo);
		
		return "/subject/addSubjectVideo";
	}
	
	// 강의영상 추가
	@PostMapping("/addSubjectVideo")
	public String addSubjectVideo(SubjectVideo subjectVideo) {
		log.debug(CF.YHJ + "SubjectController.addSubjectVideo.subjectVideo : " + subjectVideo + CF.RESET); // 디버깅
		
		int row = subjectService.addSubjectVideo(subjectVideo);
		log.debug(CF.YHJ + "SubjectController.addSubjectVideo.row : " + row + CF.RESET); // 디버깅
		
		return "redirect:/getSubjectVideo?subjectNo="+subjectVideo.getSubjectNo();
	}

	// 강의 영상 수정
	@GetMapping("/modifySubjectVideo")
	public String modifySubjectVideo(@RequestParam(value="subjectVideoNo") int subjectVideoNo
									,Model model) {
		log.debug(CF.YHJ + "SubjectController.modifySubjectVideo.subjectVideoNo : " + subjectVideoNo + CF.RESET); // 디버깅
		
		SubjectVideo subjectVideo = subjectService.getSubjectVideoOne(subjectVideoNo);
		log.debug(CF.YHJ + "SubjectController.modifySubjectVideo.subejctVideoOne : " + subjectVideo + CF.RESET); // 디버깅
		
		model.addAttribute("subjectVideo",subjectVideo);
		
		return "/subject/modifySubjectVideo";
	}
	
	// 강의 영상 수정
	@PostMapping("modifySubjectVideo")
	public String modifySubjectVideo(SubjectVideo subjectVideo) {
		log.debug(CF.YHJ + "SubjectController.modifySubjectVideo.subejctVideo : " + subjectVideo + CF.RESET); // 디버깅
		
		subjectService.modifySubjectVideo(subjectVideo);
				
		return "redirect:/getSubjectVideo?subjectNo="+subjectVideo.getSubjectNo();
	}
	
	// 강의 영상 삭제
	@GetMapping("/removeSubjectVideo")
	public String removeSubjectVideo(@RequestParam(value="subjectVideoNo") int subjectVideoNo
									,@RequestParam(value="subjectNo") int subjectNo) {
		// 디버깅
		log.debug(CF.YHJ + "SubjectController.removeSubjectVideo.subjectVideoNo : " + subjectVideoNo + CF.RESET);
		log.debug(CF.YHJ + "SubjectController.removeSubjectVideo.subjectNo : " + subjectNo + CF.RESET);
		
		subjectService.removeSubjectVideo(subjectVideoNo);
		
		return "redirect:/getSubjectVideo?subjectNo="+subjectNo;
	}
	
	// ----------------------- 강좌 학생 -----------------------
	
	// 강좌 학생 리스트 출력
	@GetMapping("getSubjectStudentList")
	public String getSubjectStudentList(HttpSession session
										,Model model
										,@RequestParam @Nullable String searchStudentName
										,@RequestParam(value = "subjectNo") int subjectNo) { // @NULLABLE = 널값허용 + 검색 파라메터 값 받아오기
		// 디버깅
		log.debug(CF.YHJ + "SubjectController.getSubjectStudentList.subjectNo : " + subjectNo + CF.RESET);
		log.debug(CF.YHJ + "SubjectController.getSubjectStudentList.searchStudentName : " + searchStudentName + CF.RESET);
				
		List<Map<String,Object>> studentList = subjectService.getSubjectStudentList(subjectNo,searchStudentName);
		log.debug(CF.YHJ + "SubjectController.getSubjectStudentList.studentList : " + studentList + CF.RESET);
		
		model.addAttribute("studentList",studentList);
		model.addAttribute("subjectNo",subjectNo);
		
		return "/subject/getSubjectStudentList";
	}
	
	// 언어 리스트 출력
	@GetMapping("getLanguageList")
	public String getLanguageList(Model model) {
		List<Language> languageList = subjectService.getLanguageList(); // 언어 리스트 출력
		log.debug(CF.YHJ + "SubjectController.getSubjectStudentList.languageList : " + languageList + CF.RESET); // 디버깅
		
		model.addAttribute("languageList",languageList);
		
		return "/subject/getLanguageList";
	}
	
	// 언어 추가
	@GetMapping("addLanguage")
	public String addLanguage() {
		return "/subject/addLanguage";
	}
	
	@PostMapping("addLanguage")
	public String addLanguage(Language language) {
		log.debug(CF.YHJ + "SubjectController.addLanguage.language : " + language + CF.RESET); // 디버깅
		subjectService.addLanguage(language); // 언어 추가
		
		return "redirect:/getLanguageList";
	}
	
	// 언어 수정
	@GetMapping("modifyLanguage")
	public String modifyLanguage(@RequestParam(value="languageNo") int languageNo
								,Model model) {
		log.debug(CF.YHJ + "SubjectController.modifyLanguage.languageNo : " + languageNo + CF.RESET); // 디버깅
		
		Language language = subjectService.getlanguageOne(languageNo); // LanguageOne 출력
		log.debug(CF.YHJ + "SubjectController.modifyLanguage.language : " + language + CF.RESET); // 디버깅
		
		model.addAttribute("language",language);
	
		return "/subject/modifyLanguage";
	}
	
	@PostMapping("modifyLanguage")
	public String modifyLanguage(Language language) {
		log.debug(CF.YHJ + "SubjectController.modifyLanguage.language : " + language + CF.RESET); // 디버깅
		
		subjectService.modifylanguageOne(language); // LanguageOne 출력
		log.debug(CF.YHJ + "SubjectController.modifyLanguage.language : " + language + CF.RESET); // 디버깅
		return "redirect:/getLanguageList";
	}
	
	// 언어 삭제
	@GetMapping("removeLanguage")
	public String removeLanguage(@RequestParam(value="languageNo") int languageNo) {
		log.debug(CF.YHJ + "SubjectController.removeLanguage.languageNo : " + languageNo + CF.RESET); // 디버깅
		subjectService.removeLanguage(languageNo); // 언어 삭제
		
		return "redirect:/getLanguageList";
	}
	
}