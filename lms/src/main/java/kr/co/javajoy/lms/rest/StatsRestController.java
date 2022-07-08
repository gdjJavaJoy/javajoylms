package kr.co.javajoy.lms.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.StatsMapper;
import kr.co.javajoy.lms.service.StatsService;
import kr.co.javajoy.lms.service.StudentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController

public class StatsRestController {
	@Autowired StatsMapper statsMapper;
	@Autowired StatsService statsService;
	
	// 강좌별 과제 개수
	@GetMapping("/reportCountBySubject")
	List<Map<String,Object>> StatsService() {
		List<Map<String,Object>> list = statsMapper.subjectReportCountBySubjectNo();
		log.debug(CF.PSG+"statsService.list : " + list + CF.RESET);
		return list; 
	}
	
	// 학생 취업률
	@GetMapping("studentEmployedRate")
	Map<String,Object> studentEmployedRate(){
		Map<String,Object> map = statsService.studentEmployedRate();
		
		log.debug(CF.YHJ+"StatsRestController.studentEmployedRate.totalStudentCount : " + map.get("totalStudentCount") + CF.RESET);
		log.debug(CF.YHJ+"StatsRestController.studentEmployedRate.totalEmployedStudentCount : " +  map.get("totalEmployedStudentCount") + CF.RESET);
		log.debug(CF.YHJ+"StatsRestController.studentEmployedRate.employedAvg : " +  map.get("employedAvg") + CF.RESET);
		
		return map;
	}
}
