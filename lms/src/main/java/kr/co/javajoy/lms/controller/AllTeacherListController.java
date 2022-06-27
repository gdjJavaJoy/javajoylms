package kr.co.javajoy.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.service.AllTeacherListService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class AllTeacherListController {
	@Autowired AllTeacherListService allTeacherListService;
	/**
	 * 
	 * @param model
	 * @param currentPage
	 * @param rowPerPage
	 * @param subjectNo
	 * @return
	 */
	// 학생 리스트 URL GetMapping
	@GetMapping("/allTeacherList")
	public String allStudentList(Model model
							,@RequestParam(value = "currentPage", defaultValue = "1") int currentPage
							,@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
	Map<String, Object> map = allTeacherListService.AllTeacherList(currentPage, rowPerPage);
	model.addAttribute("list", map.get("list"));
	model.addAttribute("currentPage", currentPage);
	model.addAttribute("lastPage", map.get("lastPage"));
		// studentList.jsp로 이동.
		return "allTeacherList";
	}
}