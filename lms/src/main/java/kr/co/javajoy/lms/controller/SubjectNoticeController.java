package kr.co.javajoy.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.service.SubjectNoticeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SubjectNoticeController {
	@Autowired SubjectNoticeService subjectnoticeService;
	/**
	 * 
	 * @param model
	 * @param currentPage
	 * @param rowPerPage
	 * @param subjectNo
	 * @return
	 */
	// 강의공지사항 리스트 URL GetMapping
	@GetMapping("/subjectNoticeList")
	public String subjectNotice(Model model
								,@RequestParam(name = "currentPage", defaultValue = "1") int currentPage
								,@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage
								,@RequestParam(value = "subjectNo") int subjectNo) {
	Map<String, Object> map = subjectnoticeService.getSubjectNoticeList(currentPage, rowPerPage, subjectNo);
	model.addAttribute("list", map.get("list"));
	model.addAttribute("currentPage", currentPage);
	model.addAttribute("lastPage", map.get("lastPage"));
	model.addAttribute("subjectNo", map.get("subjectNo"));
		// subjectNoticeList.jsp로 이동.
		return "subjectNoticeList";
	}
}