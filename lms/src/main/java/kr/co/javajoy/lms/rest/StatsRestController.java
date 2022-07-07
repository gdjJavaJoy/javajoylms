package kr.co.javajoy.lms.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.StatsMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController

public class StatsRestController {
	@Autowired StatsMapper statsMapper;
	
	@GetMapping("/reportCountBySubject")
	List<Map<String,Object>> StatsService() {
		List<Map<String,Object>> list = statsMapper.subjectReportCountBySubjectNo();
		log.debug(CF.PSG+"statsService.list : " + list + CF.RESET);
		return list; 
	}
}
