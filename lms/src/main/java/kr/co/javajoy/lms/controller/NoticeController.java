package kr.co.javajoy.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.NoticeService;
import kr.co.javajoy.lms.vo.Board;
import kr.co.javajoy.lms.vo.BoardForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NoticeController {
	@Autowired private NoticeService noticeService;
	
	// 리스트 보여주기
	@GetMapping("/getNoticeByPage")
	public String getNoticeByPage(Model model, HttpSession session
			,@RequestParam @Nullable String searchNoticeTitle
			,@RequestParam(name = "currentPage", defaultValue = "1") int currentPage
			,@RequestParam(name = "rowPerPager", defaultValue = "10") int rowPerPage) { // 10개 부터 페이징처리 기능

		// 세션 값 받아오기
		String memberId = (String) session.getAttribute("loginUser");
		String level = (String) session.getAttribute("level");
		log.debug(CF.WSH + "StudentController.memberIndex.loginUser : " + session.getAttribute("loginUser") + CF.WSH);
		log.debug(CF.WSH + "StudentController.memberIndex.level : " + session.getAttribute("level") + CF.WSH);
		
		Map<String, Object> map = noticeService.getNoticeByPage(currentPage, rowPerPage, searchNoticeTitle);
		model.addAttribute("list",map.get("list"));
		model.addAttribute("lastPage",map.get("lastPage"));
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalCount",map.get("totalCount"));
		model.addAttribute("rowPerPage",rowPerPage);
		model.addAttribute("searchNoticeTitle", searchNoticeTitle);
		log.debug(CF.WSH + "NoticeController.getNoticeByPage.notice : "+ currentPage);
		log.debug(CF.WSH + "NoticeController.getNoticeByPage.searchNoticeTitle : "+ searchNoticeTitle);
		
		return "board/notice/getNoticeByPage";
	}
	// 공지사항 상세보기
	@GetMapping("/getNoticeOne")
	public String getNoticeOne(Model model
			,HttpServletRequest request
			,HttpSession session
			,@RequestParam(name = "boardNo") int boardNo) {
		log.debug(CF.WSH + "NoticeController.getNoticeOne.boardNo : "+ boardNo);
		String path = request.getServletContext().getRealPath("/file/boardFile/");
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");
		
		Map<String, Object> map = noticeService.getNoticeOne(boardNo);
		model.addAttribute("path", path);
		model.addAttribute("board", map.get("board"));
		model.addAttribute("boardfile", map.get("boardfile"));
		model.addAttribute("fileTotalCount", map.get("fileTotalCount"));
		model.addAttribute("totalCount",map.get("totalCount"));
		log.debug(CF.WSH + "NoticeController.getNoticeOne.map : "+ map);
		return "board/notice/getNoticeOne";
	}
	
	// 공지사항 추가 Form
	@GetMapping("/addNotice")
	public String addNotice(HttpSession session) {
		String memberId = (String) session.getAttribute("loginUser");
		String level = (String) session.getAttribute("level");
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.sessionId : " + memberId);
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.level : " + level);
		if(level.equals("2, 3")) {
			return "redirect:/login";
		}
		
		return "board/notice/addNotice";
	}
	// 공지사항 추가 Action
	@PostMapping("/addNotice")
	public String addNotice(HttpServletRequest request, BoardForm boardForm) {
		String path = request.getServletContext().getRealPath("/file/boardFile/");
		log.debug(CF.WSH + "NoticeController.addNotice(Post).path : "+ path);
		log.debug(CF.WSH + "NoticeController.addNotice(Post).boardFrom : "+ boardForm);
		List<MultipartFile> boardfileList = boardForm.getBoardfileList();
		if(boardfileList != null && boardfileList.get(0).getSize() > 0) { // 하나 이상의 파일이 업로드 되면
			for(MultipartFile mf : boardfileList) {
				log.debug(CF.WSH + "NoticeController.addNotice(Post).boardfileList : "+boardfileList);
				log.debug(CF.WSH + "NoticeController.addNotice(Post).filename : "+mf.getOriginalFilename());
			}
			
		}
		noticeService.addNotice(boardForm, path);
		log.debug(CF.WSH + "NoticeController.addNotice(Post).boardForm : "+boardForm);
		return "redirect:/getNoticeByPage";
	}
	// 공지사항 파일 삭제 Form
	@GetMapping("/removeNoticefile") // 파일 먼저 삭제
	public String removeNoticefile(HttpServletRequest request
			,@RequestParam(name="boardFileNo") int boardfileNo
			,@RequestParam(name="boardNo") int boardNo) {
		log.debug(CF.WSH + "NoticeController.removeNoticefile(Get).boardFileNo : "+ boardfileNo);
		log.debug(CF.WSH + "NoticeController.removeNoticefile(Get).boardNo : "+ boardNo);
		String path = request.getServletContext().getRealPath("/file/boardFile/");
			noticeService.removefileNotice(boardfileNo,path);
		return "redirect:/modifyNotice?boardNo="+boardNo;
	}
	// 공지사항 삭제 Form
	@GetMapping("/removeNotice")
	public String removeNoice(HttpServletRequest request
			,@RequestParam(name="boardNo") int boardNo) {
		log.debug(CF.WSH + "NoticeController.removeNotice(Get).boardNo : "+ boardNo);
		String path = request.getServletContext().getRealPath("/file/boardFile/");
		noticeService.removeNotice(boardNo, path);
	return "redirect:/getNoticeByPage";
	}
	
	// 공지사항 수정 Form
	@GetMapping("/modifyNotice")
	public String modifyNotice(HttpSession session
			,HttpServletRequest request
			,Model model
			,@RequestParam(name="boardNo") int boardNo) {
		log.debug(CF.WSH + "NoticeController.modifyNotice(Get).boardNo : "+ boardNo);
		String path = request.getServletContext().getRealPath("/file/boardFile/"); 
		String memberId = (String) session.getAttribute("loginUser");
		String level = (String) session.getAttribute("level");
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.sessionId : " + memberId);
		log.debug(CF.PBJ + "SubjectController.getSubjectByPage.level : " + level);
		if(level.equals("2, 3")) {
			return "redirect:/login";
		}
		Map<String, Object> map = noticeService.getNoticeOne(boardNo);
		model.addAttribute("path", path);
		model.addAttribute("board",map.get("board"));
		model.addAttribute("boardfile",map.get("boardfile"));
		model.addAttribute("fileTotalCount", map.get("fileTotalCount"));
		model.addAttribute("totalCount",map.get("totalCount"));
		return "board/notice/modifyNotice";
	}
	// 공지사항 수정 Action
	@PostMapping("/modifyNotice") 
	public String modifyNotice(HttpServletRequest request
			,BoardForm boardForm
			,Board board
			,@RequestParam(name="boardNo") int boardNo) {
		String path = request.getServletContext().getRealPath("/file/boardFile/");
		log.debug(CF.WSH + "NoticeController.modifyNotice(Post).path : "+ path);
		log.debug(CF.WSH + "NoticeController.modifyNotice(Post).boardForm : "+ boardForm);
		
		List<MultipartFile> boardfileList = boardForm.getBoardfileList();
		if(boardfileList != null && boardfileList.get(0).getSize() > 0) { // 하나 이상의 파일이 업로드 되면
			for(MultipartFile mf : boardfileList) {
				log.debug(CF.WSH + "NoticeController.addNotice(Post).boardfileList : "+boardfileList);
				log.debug(CF.WSH + "NoticeController.addNotice(Post).filename : "+mf.getOriginalFilename());
			}
		}
		noticeService.modifyNotice(board, boardForm, path);
		log.debug(CF.WSH + "NoticeController.addNotice(Post).boardForm : "+boardForm);	
		log.debug(CF.WSH + "NoticeController.addNotice(Post).board : "+board);
		return "redirect:/getNoticeOne?boardNo="+boardNo;
	}
}



