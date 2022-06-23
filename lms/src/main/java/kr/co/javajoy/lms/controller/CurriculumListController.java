package kr.co.javajoy.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.service.CurriculumService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CurriculumListController {
	@Autowired CurriculumService curriculumService;
	/**
	 * 
	 * @param model
	 * @param currentPage
	 * @param rowPerPage
	 * @param subjectNo
	 * @return
	 */
	// 커리큘럼 리스트 URL GetMapping
	@GetMapping("/curriculumList")
	public String curriculumList(Model model
								,@RequestParam(value = "currentPage", defaultValue = "1") int currentPage
								,@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage
								,@RequestParam(value = "subjectNo") int subjectNo) {
	Map<String, Object> map = curriculumService.getCurriculumList(currentPage, rowPerPage, subjectNo);
	model.addAttribute("list", map.get("list"));
	model.addAttribute("currentPage", currentPage);
	model.addAttribute("lastPage", map.get("lastPage"));
	model.addAttribute("subjectNo", map.get("subjectNo"));
		// curriculum.jsp로 이동.
		return "curriculumList";
	}
}