package kr.co.javajoy.lms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.SubjectMapper;
import kr.co.javajoy.lms.vo.Subject;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ExcelRestController {
	@Autowired SubjectMapper subjectMapper;
	
	@GetMapping("downLoadExcelSubjectList")
	public List<Subject> downLoadExcelSubjectList(){
		List<Subject> list = subjectMapper.selectSubjectList();
		log.debug(CF.PBJ + "ExcelRestController.downLoadExcelSubjectList list : " + list + CF.RESET);

		return list;
	}
}
