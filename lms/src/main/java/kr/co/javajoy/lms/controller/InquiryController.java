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

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.InquiryService;
import kr.co.javajoy.lms.vo.AddInquiryForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class InquiryController {
	@Autowired InquiryService inquiryService;
	@GetMapping("/getInquiryByPage")
	public String getInquriyByPage(Model model
									,@RequestParam(value="currentPage", defaultValue="1") int currentPage
									,@RequestParam(value="rowPerPage",  defaultValue="10") int rowPerPage) {
		log.debug(CF.PSG+"InquiryController.getInquiryByPage currentPage :" + currentPage + CF.RESET);
		log.debug(CF.PSG+"InquiryController.getInquiryByPage rowPerPage : " + rowPerPage + CF.RESET);
		Map<String,Object> map =  inquiryService.getInquiryByPage(currentPage, rowPerPage);
		
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
		
		model.addAttribute("fileCount",map.get("fileCount"));
		model.addAttribute("board",map.get("board"));
		model.addAttribute("boardFile",map.get("boardFile"));
		model.addAttribute("boardComment",map.get("boardComment"));
		return "board/inquiry/inquiryOne";
	}
}
