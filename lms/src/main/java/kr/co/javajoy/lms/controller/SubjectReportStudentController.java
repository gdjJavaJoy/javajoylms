package kr.co.javajoy.lms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.SubjectReportStudentService;
import kr.co.javajoy.lms.vo.SubjectReportComment;
import kr.co.javajoy.lms.vo.SubjectReportForm;
import kr.co.javajoy.lms.vo.SubjectReportStudentForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller 
public class SubjectReportStudentController {
	@Autowired SubjectReportStudentService subjectReportStudentService;
	
	// ------------------------ 1) 과제 게시판 글 리스트 출력 <SELECT> ------------------------ 
	
	// 1-1) 과제 게시판 글 리스트 출력 + 페이징
	@GetMapping("/getSubjectReportStudentListByPage")
	public String getSubjectReportStudentListByPage(HttpSession session
										   ,Model model
										   ,@RequestParam @Nullable String sSubjectReportStudentTitle
										   ,@RequestParam(value="currentPage", defaultValue="1") int currentPage
										   ,@RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
										   ,@RequestParam(value="subjectReportNo") int subjectReportNo) {
		// session 처리
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.sessionId : " + memberId);
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.level : " + level);
		// 운영자 + 강사 / 학생 별로 보이는 페이지가 다름...
		
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.currentPage" + currentPage);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.rowPerPage" + rowPerPage);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.subjectNo" + subjectReportNo);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.rSubjectReportTitle" + sSubjectReportStudentTitle);

		// 뷰를 호출 시, 모델계층으로 부터 반환된 모델값을 뷰로 보냄
		Map<String, Object> map = subjectReportStudentService.getSubjectReportStudentListByPage(currentPage, rowPerPage, subjectReportNo, sSubjectReportStudentTitle);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("subjectNo", map.get("subjectReportNo"));
		model.addAttribute("rSubjectReportTitle", map.get("sSubjectReportStudentTitle"));
		
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.list" + map.get("list"));
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.currentPage" + currentPage);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.lastPage" + map.get("lastPage"));
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.subjectNo" + map.get("subjectReportNo"));
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.rSubjectReportTitle" + map.get("sSubjectReportStudentTitle"));
		
		return "subject/getSubjectReportStudentListByPage";
	}
	
	/*
	// 1-2) 과제 게시판 리스트에서 과목번호 넘겨주기
	@PostMapping("/getSubjectReportListByPage")
	public String getSubjectReportListByPage(Model model
											,@RequestParam(name="subjectNo") int subjectNo) {
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.subjectNo : " + subjectNo);
		model.addAttribute("subjectNo", subjectNo);
		
		return "redirect:/addSubjectReport?subjectNo=" + subjectNo;
	}
	*/
	
	// ------------------------ 2) 과제 게시판 글 입력 <INSERT> ------------------------ 
	
	// 2-1) 과제 게시판 글 입력 + 파일 입력 Form 받기
	@GetMapping("/addSubjectReportStudent")
	public String addSubjectReport(HttpSession session
								,Model model
								,@RequestParam(name = "subjectReportNo") int subjectReportNo) {
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReport(Form).subjectNo : " + subjectReportNo);
		// session 처리 : 운영자와 강사만 글을 쓸 수 있다.
		String memberId = (String) session.getAttribute("loginUser");
		String level = (String) session.getAttribute("level");
		
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.sessionId : " + memberId);
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.level : " + level);
		

		// 학생만 과제 작성 가능 유효성?
		
		model.addAttribute("subjectNo", subjectReportNo);
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReport(Form).addAttribute.subjectNo : " + subjectReportNo);
		
		return "subject/addSubjectReportStudent";
	}

	// 2-2) 과제 게시판 글 입력 + 파일 입력 Action
	@PostMapping("/addSubjectReportStudent")
	public String addSubjectReport(Model model
								,HttpServletRequest request
								,SubjectReportStudentForm subjectReportStudentForm 
								,@RequestParam(name = "subjectReportNo") int subjectReportNo) {
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReport(Action).subjectReportForm : " + subjectReportStudentForm);
		
		String path = request.getServletContext().getRealPath("/file/studentFile/");
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReport.path : " + path);
		
		List<MultipartFile> studentFileList = subjectReportStudentForm.getStudentFileList();
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReport(Action).subjectReportFileList : " + studentFileList);
		// 파일이 한개 이상 업로드 되면
		if (studentFileList != null && studentFileList.get(0).getSize() > 0) {
			for (MultipartFile mf : studentFileList) {
				log.debug(CF.PBJ + "SubjectReportController.addSubjectReport(Action).subjectFileOriginalName : " + mf.getOriginalFilename());
			}
		}
		
		model.addAttribute("subjectReportNo", subjectReportNo);
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReport(Action).addAttribute.subjectNo : " + subjectReportNo);
		
		subjectReportStudentService.addSubjectReportStudent(subjectReportStudentForm, path);
		return "redirect:/getSubjectReportOne?subjectReportNo=" + subjectReportNo;
	}

	// ------------------------ 3) 과제 게시판 글 상세보기 <SELECT ONE>------------------------ 
	
	// 3-1) 과제 게시판 글 상세보기 + 파일 이름 리스트 출력 + 댓글 리스트 출력
	@GetMapping("/getSubjectReportStudentOne")
	public String getSubjectReportStudentOne(HttpSession session
									,Model model
									,HttpServletRequest request
									,@RequestParam(name="subjectReportNo") int subjectReportNo) {
		// 운영자 + 강사 or  학생 별로 보이는 페이지가 다름...
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");
		log.debug(CF.PBJ + "SubjectController.getSubjectReportOne.sessionId : " + memberId);
		log.debug(CF.PBJ + "SubjectController.getSubjectReportOne.level : " + level);
		// 파일 업로드 위치 지정
		String path = request.getServletContext().getRealPath("/file/studentFile/");
		log.debug(CF.PBJ + "SubjectController.getSubjectReportOne.path : " + path);
	
		// 댓글 데이터 
		Map<String, Object> map = new HashMap<>();
		map.put("subjectReportNo", subjectReportNo);
		
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.subjectBoardNo : " + subjectReportNo);
		
		// 댓글 데이터 + 파일 리스트 데이터
		Map<String, Object> returnMap = subjectReportStudentService.getSubjectReportAndFileNameList(map);
		model.addAttribute("path", path);
		model.addAttribute("subjectReportStudent", returnMap.get("subjectReportStudent"));
		model.addAttribute("studentFileList", returnMap.get("studentFileList"));
		
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.path : " + path);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.subjectReport : " + returnMap.get("subjectReportStudent"));
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.subjectFileList : " + returnMap.get("studentFileList"));
		
		return "subject/getSubjectReportStudentOne";
	}
	
	// ------------------------ 4) 과제 게시판 수정 <UPDATE>------------------------ 
	
	// 4-1) 과제 게시판 수정 Form
	@GetMapping("/modifySubjectReportStudent")
	public String modifySubjectReportStudent(HttpSession session
									,HttpServletRequest request
									,Model model
									,@RequestParam(name="subjectReportNo") int subjectReportStudentNo) {
		// 게시판 번호
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Form).subjectBoardNo : " + subjectReportStudentNo);
		// session 처리 : 운영자와 강사만 글을 수정 할 수 있다.
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");
		log.debug(CF.PBJ + "SubjectController.modifySubjectReport(Form).sessionId : " + memberId);
		log.debug(CF.PBJ + "SubjectController.modifySubjectReport(Form).level : " + level);
		// 운영자와 강사가 아니면.. memberIndex로 redirect
		if(level.equals("3")) {
			return "redirect:/login";
		}
		// 파일 경로
		String path = request.getServletContext().getRealPath("/file/studentFile/"); 
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Form).parh : " + path);
		// 파일 리스트 데이터
		Map<String, Object> returnMap = subjectReportStudentService.getSubjectReportStudentOne(subjectReportStudentNo);
		model.addAttribute("path", path);
		model.addAttribute("subjectReportStudent", returnMap.get("subjectReportStudent"));
		model.addAttribute("studentFile", returnMap.get("studentFile"));
		
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Form).addAttribute.parh : " + path);
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Form).addAttribute.subjectReport : " + returnMap.get("subjectReportStudent"));
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Form).addAttribute.subjectFile : " + returnMap.get("studentFile"));
		
		return "subject/modifySubjectReportStudent";
	}
	
	// 4-2) 과제 게시판 수정 Form의 파일 삭제
	@GetMapping("/removeStudentFile")
	public String removeStudentFile(HttpServletRequest request
								,@RequestParam(name="subjectFileNo") int studentFileNo
								,@RequestParam(name="subjectReportStudentNo") int subjectReportStudentNo) {
		log.debug(CF.PBJ + "SubjectReportController.removeSubjectFile(modify).subjectFileNo : " + studentFileNo);
		log.debug(CF.PBJ + "SubjectReportController.removeSubjectFile(modify).subjectBoardNo : " + subjectReportStudentNo);
		
		String path = request.getServletContext().getRealPath("/file/studentFile/");
		log.debug(CF.PBJ + "SubjectReportController.removeSubjectFile.path : " + path);
		
		subjectReportStudentService.removeStudentFile(studentFileNo, path);
		return "redirect:/modifySubjectReport?subjectBoardNo=" + subjectReportStudentNo;
	}
	
	// 4-3) 과제 게시판 수정 Action
	@PostMapping("/modifySubjectReportStudent")
	public String modifySubjectReportStudent(Model model
									,HttpServletRequest request
									,SubjectReportStudentForm subjectReportStudentForm
									,@RequestParam(name = "subjectReportStudentNo") int subjectReportStudentNo) {
		String path = request.getServletContext().getRealPath("/file/studentFile/");
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Action).path : " + path);
		
		List<MultipartFile> studentFileList = subjectReportStudentForm.getStudentFileList();
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Action).subjectReportFileList : " + studentFileList);
		// 파일이 한개 이상 업로드 되면
		if (studentFileList != null && studentFileList.get(0).getSize() > 0) {
			for (MultipartFile mf : studentFileList) {
				log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Action).subjectFileOriginalName : " + mf.getOriginalFilename());
			}
		}
		
		model.addAttribute("subjectReportStudentNo", subjectReportStudentNo);
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Action).subjectBoardNo : " + subjectReportStudentNo);
		
		subjectReportStudentService.modifySubjectReportStudent(subjectReportStudentForm, path);
		return "redirect:/getSubjectReportOne?subjectBoardNo=" + subjectReportStudentNo;
	}
	
	// ------------------------ 5) 과제 게시판 글 삭제 <DELETE>------------------------ 
	
	@GetMapping("/removeSubjectReportStudent")
	public String removeSubjectReportStudent(HttpServletRequest request
								,@RequestParam(name = "subjectReportStudentNo") int subjectReportStudentNo) {
		log.debug(CF.PBJ + "SubjectReportController.removeSubjectReport.subjectReportStudentNo : " + subjectReportStudentNo);
		
		String path = request.getServletContext().getRealPath("/file/studentFile/");
		log.debug(CF.PBJ + "SubjectReportController.removeSubjectReport.path : " + path);
		
		subjectReportStudentService.removeSubjectReportStudent(subjectReportStudentNo, path);
		log.debug("SubjectReportController.removeSubjectReport.param.subjectBoardNo");
		
		return "redirect:/getSubjectReportListByPage?subjectNo=" + subjectReportStudentNo;
	} 
}
