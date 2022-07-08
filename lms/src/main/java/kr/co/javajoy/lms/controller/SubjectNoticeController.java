package kr.co.javajoy.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.SubjectNoticeService;
import kr.co.javajoy.lms.vo.SubjectBoardInsertForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SubjectNoticeController {
	@Autowired SubjectNoticeService subjectNoticeService;
	
	// 1) 강의공지사항 리스트 URL GetMapping
	@GetMapping("/subjectNoticeList")
	public String subjectNotice(HttpSession session
								,Model model
								,@RequestParam(name = "currentPage", defaultValue = "1") int currentPage
								,@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage
								,@RequestParam(value = "subjectNo") int subjectNo) {
		log.debug(CF.WSH + "SubjectNoticeController.subjectNoticeList.currentPage : " + currentPage + CF.WSH);
		log.debug(CF.WSH + "SubjectNoticeController.subjectNoticeList.rowPerPage : " + rowPerPage + CF.WSH);
		log.debug(CF.WSH + "SubjectNoticeController.subjectNoticeList.subjectNo : " + subjectNo + CF.WSH);
				
		// 세션값 처리(모든 이용자 리스트 확인가능, 비로그인 시 필터로 인해 login페이지로)
		String memberId = (String) session.getAttribute("loginUser");
		String level = (String) session.getAttribute("level");
		log.debug(CF.WSH + "SubjectNoticeController.subjectNoticeList.memberId : " + memberId + CF.WSH);
		log.debug(CF.WSH + "SubjectNoticeController.subjectNoticeList.level : " + level + CF.WSH);
		
		Map<String, Object> map = subjectNoticeService.getSubjectNoticeList(currentPage, rowPerPage, subjectNo);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("subjectNo", map.get("subjectNo"));
			
		log.debug(CF.WSH + "SubjectNoticeController.subjectNoticeList.list성공 : " + map + CF.WSH);
		return "subject/subjectNotice/subjectNoticeList";
		// subjectNoticeList.jsp로 이동
	}
	
	// 1-1) 강의 공지사항 리스트(add로 넘어갈때 번호를 못가져가서 model을 이용해 보내줌)
		@PostMapping("/subjectNoticeList")
		public String addSubjectNotice(Model model
										,@RequestParam(name="subjectNo") int subjectNo) {
		log.debug(CF.WSH + "SubjectNoticeController.subjectNoticeList.post().subjectNo : " + subjectNo + CF.WSH);
		model.addAttribute("subjcetNo", subjectNo);
			
		return "redirect:/addSubjectNotice?subjectNo=" + subjectNo;
		}
	// 2) 강의 추가하기(Post로 강좌 번호 받기)
	@GetMapping("/addSubjectNotice")
	public String addSubjectNotice(HttpSession session
									,Model model
									,@RequestParam(name = "subjectNo") int subjectNo) {
		// 세션값 처리(level별 접근권한 유효성 검사 -> 자바스크립트 이용) -> 그렇지만 한번 더
		String memberId = (String) session.getAttribute("loginUser");
		String level = (String) session.getAttribute("level");
		log.debug(CF.WSH + "SubjectNoticeController.subjectNoticeList.memberId : " + memberId + CF.WSH);
		log.debug(CF.WSH + "SubjectNoticeController.subjectNoticeList.level : " + level + CF.WSH);
		// 학생과 운영자가 addSubjectNotice에 접근하면 login으로
		if(level.equals("2")) {
			// subjectNo(강좌번호) 보내주기
			model.addAttribute("subjectNo", subjectNo);
			return "subject/subjectNotice/addSubjectNotice";
		}
		return "redirect:/login";
	}
	// 2-1) 강의 추가
	@PostMapping("/addsubjectNotice")
	public String addsbujectNoitce(HttpServletRequest request
									,Model model
									,@RequestParam(name="subjectNo") int subjectNo
									,SubjectBoardInsertForm subjectBoardInsertForm) {
		log.debug(CF.WSH + "SubjectNoticeController.addsubjectNotice.subjectNo.post() : " + subjectNo + CF.WSH);
		log.debug(CF.WSH + "SubjectNoticeController.addsubjectNotice.subjectBoardInsertForm.post() : " + subjectBoardInsertForm + CF.WSH);
		
		String path = request.getServletContext().getRealPath("/file/subjectFile/");
		log.debug(CF.WSH + "SubjectNoticeController.addsubjectNotice.path : " + path + CF.WSH);
		
		// SubjectBoardInsertForm에 받아왔던 자료들을 subjectNoitceFileList에 저장
		List<MultipartFile> subjectNoitceFileList = subjectBoardInsertForm.getSubjectBoardFileList();
		log.debug(CF.WSH + "SubjectNoticeController.addsubjectNotice.subjectNoitceFileList.post() : " + subjectNoitceFileList + CF.WSH);
		if(subjectNoitceFileList != null && subjectNoitceFileList.get(0).getSize() > 0) {
			// subjectNoitceFileList가 비어있지 않으면
			for(MultipartFile mf : subjectNoitceFileList) {
				log.debug(CF.WSH + "SubjectNoticeController.addsubjectNotice.post().mf : " + mf + CF.WSH);
			}
		}
		model.addAttribute("subjectNo", subjectNo);
		log.debug(CF.WSH + "SubjectNoticeController.addsubjectNotice.post().subjectNo : " + subjectNo + CF.WSH);
		
		subjectNoticeService.addSubjectNotice(subjectBoardInsertForm, path);
		return "redirect:/subjectNoticeList?subjectNo=" + subjectNo;
		
	}
	@GetMapping("/subjectNoticeOne")
	public String subjectNoticeOne(HttpSession session
									,Model model
									,HttpServletRequest request
									,@RequestParam(name="subjectBoardNo") int subjectBoardNo) {
		log.debug(CF.WSH + "SubjectNoticeController.subjectNoticeOne.subjectBoardNo.Get() : " + subjectBoardNo + CF.WSH);
		
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level"); 
		log.debug(CF.WSH + "SubjectNoticeController.subjectNoticeOne.memberId : " + memberId + CF.WSH);
		log.debug(CF.WSH + "SubjectNoticeController.subjectNoticeOne.level : " + level + CF.WSH);
		
		String path = request.getServletContext().getRealPath("/file/subjectFile/");
		log.debug(CF.WSH + "SubjectNoticeController.subjectNoticeOne.path.Get() : " + path + CF.WSH);
		
		Map<String, Object> returnMap = subjectNoticeService.subjectNoticeOne(subjectBoardNo);
		model.addAttribute("path", path);
		model.addAttribute("subjectNotice", returnMap.get("subjectNotice"));
		model.addAttribute("subjectNoticeFile", returnMap.get("subjectNoticeFile"));
		model.addAttribute("FileCount", returnMap.get("FileCount"));
		
		return "subject/subjectNotice/subjectNoticeOne";
	}
	
	
}