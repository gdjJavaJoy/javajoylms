package kr.co.javajoy.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.InquiryService;
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
		log.debug(CF.PSG+"InquiryController.addInquiry.memberId :" + memberId + CF.RESET);
		List<String> teacherlist = inquiryService.getTeacherListBySubject(memberId);
		
		model.addAttribute("teacherList",teacherlist);
		return "board/inquiry/addInquiry";
	}
}
