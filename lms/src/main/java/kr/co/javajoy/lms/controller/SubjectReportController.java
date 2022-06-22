package kr.co.javajoy.lms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.SubjectReportService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SubjectReportController {
	@Autowired SubjectReportService subjectReportService;
	
	// 과제 게시판 글 리스트 출력 + 페이징
	@GetMapping("/getSubjectReportListByPage")
	public String getSubjectReportListByPage(Model model
										   ,@RequestParam(value="currentPage", defaultValue="1") int currentPage
										   ,@RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
										   ,@RequestParam(value="subjectNo") int subjectNo) {
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.currentPage" +currentPage);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.rowPerPage" +rowPerPage);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.subjectNo" +subjectNo);
		// 뷰를 호출 시, 모델계층으로 부터 반환된 모델값을 뷰로 보냄
		Map<String, Object> map = subjectReportService.getSubjectReportListByPage(currentPage, rowPerPage, subjectNo);
		
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.list" + map.get("list"));
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.currentPage" +currentPage);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.lastPage" +map.get("lastPage"));
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportListByPage.subjectNo" +map.get("subjectNo"));
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("subjectNo", map.get("subjectNo"));
		
		return "subject/getSubjectReportListByPage";
	}
	
	// 공지사항 상세보기 + 파일 이름 리스트 출력 + 댓글 리스트 출력
	@GetMapping("/getSubjectReportOne")
	public String getSubjectReportOne(Model model
									,HttpServletRequest request
									,@RequestParam(name="subjectBoardNo") int subjectBoardNo
									,@RequestParam(name="commentCurrentPage", defaultValue="1") int commentCurrentPage
									,@RequestParam(name="rowPerPage", defaultValue="10") int rowPerPage) {
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.subjectBoardNo : ", subjectBoardNo);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.commentCurrentPage : ", commentCurrentPage);
		log.debug(CF.PBJ + "SubjectReportController.getSubjectReportOne.rowPerPage : ", rowPerPage);
		// 파일 업로드 위치 지정
		String path = request.getServletContext().getRealPath("/file/subject_file");
		// 댓글 데이터 
		Map<String, Object> map = new HashMap<>();
		map.put("subjectBoardNo", subjectBoardNo);
		map.put("commentCurrentPage", commentCurrentPage);
		map.put("rowPerPage", rowPerPage);
		
		// 댓글 데이터 + 파일 리스트 데이터
		Map<String, Object> returnMap = subjectReportService.getSubjectReportAndFileNameListAndCommentList(map);
		model.addAttribute("path", path);
		model.addAttribute("subjectReport", returnMap.get("subjectReport"));
		model.addAttribute("subjectFileList", returnMap.get("subjectFileList"));
		model.addAttribute("commentList", returnMap.get("commentList"));
		model.addAttribute("commentLastPage", returnMap.get("commentLastPage"));
		
		return "subject/getSubjectReportOne";
	}
}










;





















































