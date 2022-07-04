package kr.co.javajoy.lms.controller;

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

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.InquiryService;
import kr.co.javajoy.lms.vo.AddInquiryForm;
import kr.co.javajoy.lms.vo.BoardComment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class InquiryController {
	@Autowired InquiryService inquiryService;
	@GetMapping("/getInquiryByPage")
	public String getInquriyByPage(Model model
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="rowPerPage",  defaultValue="10") int rowPerPage
									,@RequestParam @Nullable String searchInquiryTitle) {
		log.debug(CF.PSG+"InquiryController.getInquiryByPage currentPage :" + currentPage + CF.RESET);
		log.debug(CF.PSG+"InquiryController.getInquiryByPage rowPerPage : " + rowPerPage + CF.RESET);
		Map<String,Object> map =  inquiryService.getInquiryByPage(currentPage, rowPerPage,searchInquiryTitle);
		
		
		model.addAttribute("searchInquiryTitle",searchInquiryTitle); // 강사 receiver 리스트 
		model.addAttribute("receiverList",map.get("receiverList")); // 강사 receiver 리스트 
		model.addAttribute("list",map.get("list")); 
		model.addAttribute("lastPage",map.get("lastPage"));
		model.addAttribute("rowPerPage",map.get("rowPerPage"));
		model.addAttribute("totalCount",map.get("totalCount"));
		model.addAttribute("currentPage",currentPage);
		
		return "board/inquiry/getInquiryByPage";
	}
	@GetMapping("/addInquiry")
	public String addInquiry(HttpSession session
							,Model model) {
		String memberId = (String)session.getAttribute("loginUser");
		String level = (String)session.getAttribute("level");
		log.debug(CF.PSG+"InquiryController.addInquiry.memberId :" + memberId + CF.RESET);
		if (level.equals("3")) {
		List<Map<String,Object>> map = inquiryService.getTeacherListBySubject(memberId);
		model.addAttribute("teacherList",map);
		}
		return "board/inquiry/addInquiry";
	}
	@PostMapping("/addInquiry")
	public String addInquiry(AddInquiryForm addInquiryForm
							,HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/file/inquiryFile/"); //경로 지정
		log.debug(CF.PSG+"InquiryController.addInquiry.post() path :"+ path + CF.RESET);
		log.debug(CF.PSG+"InquiryController.addInquiry.post() addInquiryForm:"+ addInquiryForm + CF.RESET);
		int row = inquiryService.addInquiry(addInquiryForm, path);
		
		if (row == 0) {
			log.debug(CF.PSG+"InquiryController.addInquiry.post() 문의사항 등록실패"+ CF.RESET);
		return "redirect:/addInquiry";
		}
		log.debug(CF.PSG+"InquiryController.addInquiry.post() 문의사항 등록성공"+ CF.RESET);
		return "redirect:/getInquiryByPage";
	}
	@GetMapping("/getInquiryOne")
	public String inquiryOne(@RequestParam(value="boardNo") int boardNo
							,Model model){
		
		Map<String,Object> map = inquiryService.getInquiryOne(boardNo);
		log.debug(CF.PSG+"getInquiryOne receiver:" +map.get("receiver")+CF.RESET);
		model.addAttribute("receiver",map.get("receiver"));
		model.addAttribute("fileCount",map.get("fileCount"));
		model.addAttribute("board",map.get("board"));
		model.addAttribute("boardFile",map.get("boardFile"));
		model.addAttribute("boardComment",map.get("boardComment"));
		return "board/inquiry/getInquiryOne";
	}
	
	@GetMapping("/removeInquiry")
	public String removeInquiry(@RequestParam(value="boardNo") int boardNo
								,HttpServletRequest request) {
		
		String path = request.getServletContext().getRealPath("/file/inquiryFile/");
		log.debug(CF.PSG+"InquiryController.removeInquiry path:" + path + CF.RESET);
		
		
		int row = inquiryService.removeInquiry(boardNo, path);
		
		if (row == 0) {
			log.debug(CF.PSG+"InquiryController.removeInquiry 삭제 실패"+CF.RESET);
			return "redirect:/getInquiryOne?boardNo="+boardNo;
			
		}
		log.debug(CF.PSG+"InquiryController.removeInquiry 삭제 성공"+CF.RESET);
		
		return "redirect:/getInquiryByPage";
	}
	
	@PostMapping("/addComment")
	public String addComment(BoardComment boardComment) {
		
		int row = inquiryService.addInquiryComment(boardComment);
		
		if (row == 1) {
			log.debug(CF.PSG+"InquiryController.addComment 답변 추가 성공 " + CF.RESET);
		} else {
			log.debug(CF.PSG+"InquiryController.addComment 답변 추가 실패 " + CF.RESET);
		}
		
		return "redirect:/getInquiryOne?boardNo="+boardComment.getBoardNo();
	}
	@GetMapping("/removeInquiryComment")
	public String removeComment(@RequestParam(value="boardCommentNo") int boardCommentNo
								,@RequestParam(value="boardNo") int boardNo) {
		
		int row = inquiryService.removeInquiryCommentByBoardCommentNo(boardCommentNo);
		if(row == 1) {
			log.debug(CF.PSG+"removeInquiryComment 답변삭제 성공 " + CF.RESET);
		} else {
			log.debug(CF.PSG+"removeInquiryComment 답변삭제 실패 " + CF.RESET);
		}
		
		return "redirect:/getInquiryOne?boardNo="+boardNo;
	}
	@GetMapping("/modifyInquiry")
	public String modifyInquiry(@RequestParam(value="boardNo") int boardNo
							,Model model
							,HttpSession session) {
		String memberId = (String)session.getAttribute("loginUser");
		Map<String,Object> map =inquiryService.getModifyInquiryOne(boardNo,memberId); 
		log.debug(CF.PSG+"InquiryControlelr receiver :" +map.get("receiver")+CF.RESET);
	
		model.addAttribute("board",map.get("board"));
		model.addAttribute("boardFile",map.get("boardFile"));
		model.addAttribute("fileCount",map.get("fileCount"));
		model.addAttribute("teacherNameList",map.get("teacherNameList"));
		model.addAttribute("teacherList",map.get("teacherList"));
		return "board/inquiry/modifyInquiry";
	}
	
	@PostMapping("/modifyInquiry")
	public String modifyInquiry(AddInquiryForm addInquiryForm
							,HttpServletRequest request) {
			log.debug(CF.PSG+"InquiryController.modifyInquiry.addInquiryForm : "+ addInquiryForm+CF.RESET);
			String path = request.getServletContext().getRealPath("/file/inquiryFile/");
			log.debug(CF.PSG+"InquiryController.modifyInquiry.Path :" +path + CF.RESET);
			
			
			int row = inquiryService.modifyInquiry(addInquiryForm,path);
		if (row == 0) {
			log.debug(CF.PSG+"InquiryController.modifyInquiry 수정 실패 " + CF.RESET);
			return "redirect:/moidfyInquiry?boardNo="+addInquiryForm.getBoardNo();
		}
			log.debug(CF.PSG+"InquiryController.modifyInquiry 수정 성공 " + CF.RESET);
			
		return "redirect:/getInquiryOne?boardNo="+addInquiryForm.getBoardNo();
	}
	@GetMapping("/removeFileByBoardFileNo")
	public String removeFileByBoardFileNo(@RequestParam(value="boardFileNo") int boardFileNo
										,@RequestParam(value="boardNo")int boardNo
										,HttpServletRequest request) {
		log.debug(CF.PSG+"InquiryController.removeFileByBoardFileNo.removeFileByBoardFileNo : "+ boardFileNo +CF.RESET);
		log.debug(CF.PSG+"InquiryController.removeFileByBoardFileNo.removeFileByBoardNo :" + boardNo + CF.RESET);
		String path = request.getServletContext().getRealPath("/file/inquiryFile/");
		log.debug(CF.PSG+"InquiryController.removeFileByBoardFileNo.path :" +path + CF.RESET);
		
		int row = inquiryService.removeBoardfileByBoardFileNo(boardFileNo, path);
		
		if (row == 0) {
			log.debug(CF.PSG+"removeFileByBoardFileNo  파일 삭제 실패 "+CF.RESET);
			
		} else {
			log.debug(CF.PSG+"removeFileByBoardFileNo  파일 삭제 성공 "+CF.RESET);
		}
			
		return "redirect:/modifyInquiry?boardNo="+boardNo;
	}
}
