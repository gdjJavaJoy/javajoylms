package kr.co.javajoy.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.SubjectDataService;
import kr.co.javajoy.lms.vo.SubjectBoardInsertForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SubjectDataController {
	@Autowired SubjectDataService subjectDataService;
	
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
			
		Map<String, Object> map = subjectDataService.getSubjectDataListByPage(currentPage, rowPerPage, subjectNo,searchName);
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
		
		// 강좌 자료 추가
		@PostMapping("addSubjectData")
		public String addSubjectData (SubjectBoardInsertForm subjectBoardInsertForm
									,HttpServletRequest request) {
			String path = request.getServletContext().getRealPath("/file/subjectDataFile/"); //경로 지정
			
			// 디버깅
			log.debug(CF.YHJ + "SubjectDataController.addSubjectData.subejctBoardInsertForm : " + subjectBoardInsertForm + CF.RESET); 
			log.debug(CF.YHJ + "SubjectDataController.addSubjectData.post() path : "+ path + CF.RESET);
			
			subjectDataService.addSubjectData(subjectBoardInsertForm, path);
			
			return "redirect:getSubjectDataListByPage?subjectNo="+ subjectBoardInsertForm.getSubjectNo();
		}
		
		// 강좌자료 상세보기
		@GetMapping("getSubjectDataOne")
		public String getSubjectDataOne(@RequestParam(value="subjectDataNo") int subjectDataNo
										,@RequestParam(value="subjectNo") int subjectNo
										,Model model) {
			// 디버깅
			log.debug(CF.YHJ + "SubjectDataController.getSubjectDataOne.subjectDataNo : " + subjectDataNo + CF.RESET);
			log.debug(CF.YHJ + "SubjectDataController.getSubjectDataOne.subjectNo : " + subjectNo + CF.RESET); 
			
			Map<String,Object> map = subjectDataService.getSubjectDataOne(subjectDataNo);
			
			// 디버깅
			log.debug(CF.YHJ + "SubjectDataController.getSubjectDataOne.map : " + map + CF.RESET); 
			log.debug(CF.YHJ + "SubjectDataController.getSubjectDataOne.dataMap : " + map.get("dataMap") + CF.RESET);
			log.debug(CF.YHJ + "SubjectDataController.getSubjectDataOne.subjectFile : " + map.get("subjectFile") + CF.RESET);
			log.debug(CF.YHJ + "SubjectDataController.getSubjectDataOne.fileCount : " + map.get("fileCount") + CF.RESET);
			
			model.addAttribute("dataMap",map.get("dataMap"));
			model.addAttribute("subjectDataFile",map.get("subjectDataFile"));
			model.addAttribute("fileCount",map.get("fileCount"));
			model.addAttribute("subjectNo",subjectNo);
			
			return "/subject/getSubjectDataOne"; 
		}
		
		// 강좌자료 삭제
		@GetMapping("removeSubjectData")
		public String removeSubjectData(HttpServletRequest request
										,@RequestParam(value="subjectDataNo") int subjectDataNo
										,@RequestParam(value="subjectNo") int subjectNo) {
			String path = request.getServletContext().getRealPath("/file/subjectDataFile/");
			// 디버깅
			log.debug(CF.YHJ + "SubjectDataController.removeSubjectData.subjectDataNo : " + subjectDataNo + CF.RESET);
			log.debug(CF.YHJ + "SubjectDataController.removeSubjectData.subjectNo : " + subjectNo + CF.RESET);
			log.debug(CF.YHJ + "SubjectDataController.removeSubjectData.path : " + path + CF.RESET);
			
			// 파일삭제 -> One삭제 -> subjectBoard삭제
			subjectDataService.removeSubjectDataOne(subjectDataNo,path);
			log.debug(CF.YHJ + "SubjectDataController.removeSubjectData 삭제 성공!" + CF.RESET);
			
			return "redirect:getSubjectDataListByPage?subjectNo="+ subjectNo;
		}
		
		@GetMapping("modifySubjectData")
		public String modifySubjectData(@RequestParam(value="subjectDataNo") int subjectDataNo
										,@RequestParam(value="subjectNo") int subjectNo
										,Model model) {
			// 디버깅
			log.debug(CF.YHJ + "SubjectDataController.modifySubjectData.subjectDataNo : " + subjectDataNo + CF.RESET); 
			log.debug(CF.YHJ + "SubjectDataController.modifySubjectData.subjectNo : " + subjectNo + CF.RESET); 
			
			Map<String,Object> map = subjectDataService.getSubjectDataOne(subjectDataNo);
			
			// 디버깅
			log.debug(CF.YHJ + "SubjectDataController.modifySubjectData.map : " + map + CF.RESET); 
			log.debug(CF.YHJ + "SubjectDataController.modifySubjectData.dataMap : " + map.get("dataMap") + CF.RESET);
			log.debug(CF.YHJ + "SubjectDataController.modifySubjectData.subjectDataFile : " + map.get("subjectDataFile") + CF.RESET);
			log.debug(CF.YHJ + "SubjectDataController.modifySubjectData.fileCount : " + map.get("fileCount") + CF.RESET);
			
			model.addAttribute("dataMap",map.get("dataMap"));
			model.addAttribute("subjectDataFile",map.get("subjectDataFile"));
			model.addAttribute("fileCount",map.get("fileCount"));
			model.addAttribute("subjectNo",subjectNo);
			
			
			return "/subject/modifySubjectData";
		}
		
		@PostMapping("modifySubjectData")
		public String modifySubjectData(SubjectBoardInsertForm subjectBoardInsertForm
										,HttpServletRequest request
										,Model model) {
			String path = request.getServletContext().getRealPath("/file/subjectDataFile/");
			
			// 디버깅
			log.debug(CF.YHJ + "SubjectDataController.modifySubjectData.path : " + path + CF.RESET);
			log.debug(CF.YHJ + "SubjectDataController.modifySubjectData.subjectBoardInsertForm : " + subjectBoardInsertForm + CF.RESET);
			
			List<MultipartFile> subjectDataFileList = subjectBoardInsertForm.getSubjectBoardFileList();
			log.debug(CF.PBJ + "SubjectDataController.modifySubjectData.subjectDataFileList : " + subjectDataFileList);
			// 파일이 한개 이상 업로드 되면
			if (subjectDataFileList != null && subjectDataFileList.get(0).getSize() > 0) {
				for (MultipartFile mf : subjectDataFileList) {
					log.debug(CF.PBJ + "SubjectDataController.modifySubjectData.OriginalFilename : " + mf.getOriginalFilename());
				}
			}

			subjectDataService.modifySubjectDataOne(subjectBoardInsertForm, path);
						
			return "redirect:getSubjectDataListByPage?subjectNo="+ subjectBoardInsertForm.getSubjectNo();
		}
		
		// 파일 하나만 삭제
		@GetMapping("removeSubjectDataFileOne")
		public String removeSubjectDataFileOne(HttpServletRequest request
												,@RequestParam(value="subjectFileNo") int subjectFileNo 
												,@RequestParam(value="subjectBoardNo") int subjectBoardNo
												,@RequestParam(value="subjectNo") int subjectNo) {
			String path = request.getServletContext().getRealPath("/file/subjectDataFile/");
			
			// 디버깅
			log.debug(CF.YHJ + "SubjectDataController.modifySubjectData.subjectNo : " + subjectFileNo + CF.RESET);
			log.debug(CF.YHJ + "SubjectDataController.modifySubjectData.subjectFileBoardNo : " + subjectBoardNo + CF.RESET);
			log.debug(CF.YHJ + "SubjectDataController.modifySubjectData.path : " + path + CF.RESET);
			
			subjectDataService.removeSubjectDataFileOne(subjectBoardNo,subjectFileNo,path);
			
			return "redirect:modifySubjectData?subjectDataNo="+ subjectBoardNo +"&subjectNo=" + subjectNo;
		}
		
		
}