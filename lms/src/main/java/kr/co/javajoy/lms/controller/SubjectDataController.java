package kr.co.javajoy.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.SubjectDataService;
import kr.co.javajoy.lms.vo.SubjectBoardInsertForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SubjectDataController {
	@Autowired SubjectDataService subjectdataService;
	
		// 강좌자료 리스트
		@GetMapping("/getSubjectDataListByPage")
		public String getSubjectDataListByPage(Model model
									,@RequestParam(name = "currentPage", defaultValue = "1") int currentPage
									,@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage
									,@RequestParam(value = "subjectNo") int subjectNo
									,@RequestParam @Nullable String searchName) {
		// 디버깅
		log.debug(CF.YHJ + "SubjectDataController.getSubjectDataListByPage.currentPage : " + currentPage + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataController.getSubjectDataListByPage.rowPerPage : " + rowPerPage + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataController.getSubjectDataListByPage.subjectNo : " + subjectNo + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataController.getSubjectDataListByPage.searchName : " + searchName + CF.RESET);
			
		Map<String, Object> map = subjectdataService.getSubjectDataListByPage(currentPage, rowPerPage, subjectNo,searchName);
		log.debug(CF.YHJ + "SubjectDataController.getSubjectDataListByPage.map : " + map + CF.RESET); // 디버깅
		log.debug(CF.YHJ + "SubjectDataController.getSubjectDataListByPage.list : " + map.get("list") + CF.RESET); // 디버깅
		log.debug(CF.YHJ + "SubjectDataController.getSubjectDataListByPage.lastPage : " + map.get("lastPage") + CF.RESET); // 디버깅
		log.debug(CF.YHJ + "SubjectDataController.getSubjectDataListByPage.subjectNo : " + map.get("subjectNo") + CF.RESET); // 디버깅
		
		model.addAttribute("list",map.get("list"));
		model.addAttribute("lastPage",map.get("lastPage"));
		model.addAttribute("subjectNo",subjectNo);
		model.addAttribute("currentPage",currentPage);
		
		
		return "/subject/getSubjectDataListByPage";
	}
		
		// 강좌자료 추가
		@GetMapping("addSubjectData")
		public String addSubjectData (@RequestParam(value="subjectNo") int subjectNo
									,Model model) {
			log.debug(CF.YHJ + "SubjectDataController.addSubjectData.subjectNo :" + subjectNo + CF.RESET); // 디버깅
			
			model.addAttribute("subjectNo",subjectNo);
			
			return "/subject/addSubjectData";
		}
		
		@PostMapping("addSubjectData")
		public String addSubjectData (SubjectBoardInsertForm subjectBoardInsertForm
									,HttpServletRequest request) {
			String path = request.getServletContext().getRealPath("/file/subjectDataFile/"); //경로 지정
			
			// 디버깅
			log.debug(CF.YHJ + "SubjectDataController.addSubjectData.subejctBoardInsertForm : " + subjectBoardInsertForm + CF.RESET); 
			log.debug(CF.YHJ + "SubjectDataController.addSubjectData.post() path : "+ path + CF.RESET);
			
			subjectdataService.addSubjectData(subjectBoardInsertForm, path);
			
			return "redirect:getSubjectDataListByPage?subjectNo="+ subjectBoardInsertForm.getSubjectNo();
		}
		
}