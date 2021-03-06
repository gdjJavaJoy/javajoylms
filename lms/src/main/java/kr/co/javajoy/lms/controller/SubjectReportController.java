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
import kr.co.javajoy.lms.service.SubjectReportService;
import kr.co.javajoy.lms.vo.SubjectReportComment;
import kr.co.javajoy.lms.vo.SubjectReportForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller 
public class SubjectReportController {
	@Autowired SubjectReportService subjectReportService;
	
	// ------------------------ 1) 과제 게시판 글 리스트 출력 <SELECT> ------------------------ 
	
	// 1-1) 과제 게시판 글 리스트 출력 + 페이징
	@GetMapping("/getSubjectReportListByPage")
	public String getSubjectReportListByPage(HttpSession session
										   ,Model model
										   ,@RequestParam @Nullable String rSubjectReportTitle
										   ,@RequestParam(value="currentPage", defaultValue="1") int currentPage
										   ,@RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
										   ,@RequestParam(value="subjectNo") int subjectNo) {
		// session 처리
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.sessionId : " + memberId);
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.level : " + level);
		// 운영자 + 강사 / 학생 별로 보이는 페이지가 다름...
		
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.currentPage" + currentPage);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.rowPerPage" + rowPerPage);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.subjectNo" + subjectNo);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.rSubjectReportTitle" + rSubjectReportTitle);

		// 뷰를 호출 시, 모델계층으로 부터 반환된 모델값을 뷰로 보냄
		Map<String, Object> map = subjectReportService.getSubjectReportListByPage(currentPage, rowPerPage, subjectNo, rSubjectReportTitle);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("subjectNo", subjectNo);
		model.addAttribute("rSubjectReportTitle", map.get("rSubjectReportTitle"));
		
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.list" + map.get("list"));
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.currentPage" + currentPage);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.lastPage" + map.get("lastPage"));
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.subjectNo" + subjectNo);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.rSubjectReportTitle" + map.get("rSubjectReportTitle"));
		
		return "subject/getSubjectReportListByPage";
	}
	
	// 1-2) 과제 게시판 리스트에서 과목번호 넘겨주기
	@PostMapping("/getSubjectReportListByPage")
	public String getSubjectReportListByPage(Model model
			,@RequestParam(name="subjectNo") int subjectNo) {
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.subjectNo : " + subjectNo);
		model.addAttribute("subjectNo", subjectNo);

		return "redirect:/addSubjectReport?subjectNo=" + subjectNo;
	}
	
	// ------------------------ 2) 과제 게시판 글 입력 <INSERT> ------------------------ 
	
	// 2-1) 과제 게시판 글 입력 + 파일 입력 Form 받기
	@GetMapping("/addSubjectReport")
	public String addSubjectReport(HttpSession session
								,Model model
								,@RequestParam(name = "subjectNo") int subjectNo) {
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReport(Form).subjectNo : " + subjectNo);
		// session 처리 : 운영자와 강사만 글을 쓸 수 있다.
		String memberId = (String) session.getAttribute("loginUser");
		String level = (String) session.getAttribute("level");
		
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.sessionId : " + memberId);
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.level : " + level);
		
		// 운영자와 강사가 아니면.. memberIndex로 redirect
		if (level.equals("3")) {
			return "redirect:/login";
		}
		
		model.addAttribute("subjectNo", subjectNo);
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReport(Form).addAttribute.subjectNo : " + subjectNo);
		
		return "subject/addSubjectReport";
	}

	// 2-2) 과제 게시판 글 입력 + 파일 입력 Action
	@PostMapping("/addSubjectReport")
	public String addSubjectReport(Model model
								,HttpServletRequest request
								,SubjectReportForm subjectReportForm
								,@RequestParam(name = "subjectNo") int subjectNo) {
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReport(Action).subjectReportForm : " + subjectReportForm);
		
		String path = request.getServletContext().getRealPath("/file/subjectFile/");
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReport.path : " + path);
		
		List<MultipartFile> subjectReportFileList = subjectReportForm.getSubjectReportFileList();
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReport(Action).subjectReportFileList : " + subjectReportFileList);
		// 파일이 한개 이상 업로드 되면
		if (subjectReportFileList != null && subjectReportFileList.get(0).getSize() > 0) {
			for (MultipartFile mf : subjectReportFileList) {
				log.debug(CF.PBJ + "SubjectReportController.addSubjectReport(Action).subjectFileOriginalName : " + mf.getOriginalFilename());
			}
		}

		model.addAttribute("subjectNo", subjectNo);
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReport(Action).addAttribute.subjectNo : " + subjectNo);
		
		subjectReportService.addSubjectReport(subjectReportForm, path);
		return "redirect:/getSubjectReportListByPage?subjectNo=" + subjectNo;
	}

	// ------------------------ 3) 과제 게시판 글 상세보기 <SELECT ONE>------------------------ 
	
	// 3-1) 과제 게시판 글 상세보기 + 파일 이름 리스트 출력 + 댓글 리스트 출력
	@GetMapping("/getSubjectReportOne")
	public String getSubjectReportOne(HttpSession session
									,Model model
									,HttpServletRequest request
									,@RequestParam(name="subjectBoardNo") int subjectBoardNo
									,@RequestParam(name="commentCurrentPage", defaultValue="1") int commentCurrentPage
									,@RequestParam(name="commentRowPerPage", defaultValue="10") int commentRowPerPage) {
		// 운영자 + 강사 or  학생 별로 보이는 페이지가 다름...
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");
		log.debug(CF.PBJ + "SubjectController.getSubjectReportOne.sessionId : " + memberId);
		log.debug(CF.PBJ + "SubjectController.getSubjectReportOne.level : " + level);
		// 파일 업로드 위치 지정
		String path = request.getServletContext().getRealPath("/file/subjectFile/");
		log.debug(CF.PBJ + "SubjectController.getSubjectReportOne.path : " + path);
	
		// 댓글 데이터 
		Map<String, Object> map = new HashMap<>();
		map.put("subjectBoardNo", subjectBoardNo);
		map.put("commentCurrentPage", commentCurrentPage);
		map.put("commentRowPerPage", commentRowPerPage);
		
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.subjectBoardNo : " + subjectBoardNo);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.commentCurrentPage : " + commentCurrentPage);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.commentRowPerPage : " + commentRowPerPage);
		
		// 댓글 데이터 + 파일 리스트 데이터
		Map<String, Object> returnMap = subjectReportService.getSubjectReportAndFileNameListAndCommentList(map);
		model.addAttribute("path", path);
		model.addAttribute("subjectReport", returnMap.get("subjectReport"));
		model.addAttribute("subjectFileList", returnMap.get("subjectFileList"));
		model.addAttribute("commentList", returnMap.get("commentList"));
		model.addAttribute("commentCurrentPage", commentCurrentPage);
		model.addAttribute("commentRowPerPage", commentRowPerPage);
		model.addAttribute("commentLastPage", returnMap.get("commentLastPage"));
		model.addAttribute("commentTotalCount", returnMap.get("commentTotalCount"));
		
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.path : " + path);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.subjectReport : " + returnMap.get("subjectReport"));
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.subjectFileList : " + returnMap.get("subjectFileList"));
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.commentList : " + returnMap.get("commentList"));
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.commentRowPerPage: " + commentRowPerPage);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.commentCurrentPage: " + commentCurrentPage);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.commentLastPage : " + returnMap.get("commentLastPage"));
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.commentTotalCount : " + returnMap.get("commentTotalCount"));
		
		return "subject/getSubjectReportOne";
	}
	
	// ------------------------ 4) 과제 게시판 수정 <UPDATE>------------------------ 
	
	// 4-1) 과제 게시판 수정 Form
	@GetMapping("/modifySubjectReport")
	public String modifySubjectReport(HttpSession session
									,HttpServletRequest request
									,Model model
									,@RequestParam(name="subjectBoardNo") int subjectBoardNo) {
		// 게시판 번호
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Form).subjectBoardNo : " + subjectBoardNo);
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
		String path = request.getServletContext().getRealPath("/file/subjectFile/"); 
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Form).parh : " + path);
		// 파일 리스트 데이터
		Map<String, Object> returnMap = subjectReportService.getSubjectReportOne(subjectBoardNo);
		model.addAttribute("path", path);
		model.addAttribute("subjectReport", returnMap.get("subjectReport"));
		model.addAttribute("subjectFile", returnMap.get("subjectFile"));
		
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Form).addAttribute.parh : " + path);
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Form).addAttribute.subjectReport : " + returnMap.get("subjectReport"));
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Form).addAttribute.subjectFile : " + returnMap.get("subjectFile"));
		
		return "subject/modifySubjectReport";
	}
	
	// 4-2) 과제 게시판 수정 Form의 파일 삭제
	@GetMapping("/removeSubjectFile")
	public String removeSubjectFile(HttpServletRequest request
								,@RequestParam(name="subjectFileNo") int subjectFileNo
								,@RequestParam(name="subjectBoardNo") int subjectBoardNo) {
		log.debug(CF.PBJ + "SubjectReportController.removeSubjectFile(modify).subjectFileNo : " + subjectFileNo);
		log.debug(CF.PBJ + "SubjectReportController.removeSubjectFile(modify).subjectBoardNo : " + subjectBoardNo);
		
		String path = request.getServletContext().getRealPath("/file/subjectFile/");
		log.debug(CF.PBJ + "SubjectReportController.removeSubjectFile.path : " + path);
		
		subjectReportService.removeSubjectFile(subjectFileNo, path);
		return "redirect:/modifySubjectReport?subjectBoardNo=" + subjectBoardNo;
	}
	
	// 4-3) 과제 게시판 수정 Action
	@PostMapping("/modifySubjectReport")
	public String modifySubjectReport(Model model
									,HttpServletRequest request
									,SubjectReportForm subjectReportForm
									,@RequestParam(name = "subjectBoardNo") int subjectBoardNo) {
		String path = request.getServletContext().getRealPath("/file/subjectFile/");
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Action).path : " + path);
		
		List<MultipartFile> subjectReportFileList = subjectReportForm.getSubjectReportFileList();
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Action).subjectReportFileList : " + subjectReportFileList);
		// 파일이 한개 이상 업로드 되면
		if (subjectReportFileList != null && subjectReportFileList.get(0).getSize() > 0) {
			for (MultipartFile mf : subjectReportFileList) {
				log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Action).subjectFileOriginalName : " + mf.getOriginalFilename());
			}
		}
		
		model.addAttribute("subjectBoardNo", subjectBoardNo);
		log.debug(CF.PBJ + "SubjectReportController.modifySubjectReport(Action).subjectBoardNo : " + subjectBoardNo);
		
		subjectReportService.modifySubjectReport(subjectReportForm, path);
		return "redirect:/getSubjectReportOne?subjectBoardNo=" + subjectBoardNo;
	}
	
	// ------------------------ 5) 과제 게시판 댓글 ------------------------ 
	
	// 5-1) 과제 게시판 댓글 입력 Form
	@GetMapping("/addSubjectReportComment")
	public String addSubjectReportComment() {
		return "subject/getSubjectReportOne";
	}
	// 5-2) 과제 게시판 댓글 입력 Action
	@PostMapping("/addSubjectReportComment")
	public String addSubjectReportComment(Model model
										,SubjectReportComment subjectReportComment
										,@RequestParam(name = "subjectBoardNo") int subjectBoardNo) {
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReportComment(Action).subjectBoardNo : " + subjectBoardNo);
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReportComment(Action).subjectReportComment : " + subjectReportComment);
		
		int row = subjectReportService.addSubjectReportComment(subjectReportComment);
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReportComment(Action).row : " + row);
		
		model.addAttribute("subjectBoardNo", subjectBoardNo);
		log.debug(CF.PBJ + "SubjectReportController.addSubjectReportComment(Action).subjectBoardNo : " + subjectBoardNo);
		
		return "redirect:/getSubjectReportOne?subjectBoardNo=" + subjectBoardNo;
	}
	
	// 5-3) 댓글 삭제
	@GetMapping("/removeComment")
	public String removeComment(Model model
								,@RequestParam(name = "subjectReportCommentNo") int subjectReportCommentNo
								,@RequestParam(name = "subjectBoardNo") int subjectBoardNo) {
		log.debug(CF.PBJ + "SubjectReportController.removeComment.subjectBoardNo : " + subjectReportCommentNo);
		log.debug(CF.PBJ + "SubjectReportController.removeComment.subjectBoardNo : " + subjectBoardNo);
		
		model.addAttribute("subjectBoardNo", subjectBoardNo);
		log.debug(CF.PBJ + "SubjectReportController.removeComment.addAttribute.subjectBoardNo : " + subjectBoardNo);
		
		subjectReportService.deleteCommentByCommentNo(subjectReportCommentNo);
		return "redirect:/getSubjectReportOne?subjectBoardNo=" + subjectBoardNo;
	}
	
	// ------------------------ 6) 과제 게시판 글 삭제 <DELETE>------------------------ 
	
	@GetMapping("/removeSubjectReport")
	public String removeSubjectReport(HttpServletRequest request
								,@RequestParam(name = "subjectBoardNo") int subjectBoardNo
								,@RequestParam(name = "subjectNo") int subjectNo) {
		log.debug(CF.PBJ + "SubjectReportController.removeSubjectReport.subjectBoardNo : " + subjectBoardNo);
		log.debug(CF.PBJ + "SubjectReportController.removeSubjectReport.subjectNo : " + subjectNo);
		
		String path = request.getServletContext().getRealPath("/file/subjectFile/");
		log.debug(CF.PBJ + "SubjectReportController.removeSubjectReport.path : " + path);
		
		subjectReportService.removeSubjectReport(subjectBoardNo, path);
		log.debug("SubjectReportController.removeSubjectReport.param.subjectBoardNo");
		
		return "redirect:/getSubjectReportListByPage?subjectNo=" + subjectNo;
	} 
	
}
	














































