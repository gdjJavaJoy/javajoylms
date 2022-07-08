package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.StatsMapper;
import kr.co.javajoy.lms.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class StatsService {
	@Autowired StudentMapper studentMapper;
	
	// 학생 취업률 
	public Map<String,Object> studentEmployedRate(){
		// 학생 총 수 / 취업한 학생의 수
		Map<String,Object> map = new HashMap<>();
		
		int totalStudentCount = studentMapper.totalStudent(); // 학원의 학생 총 수
		int totalEmployedStudentCount = studentMapper.totalEmployedStudent(); // 취업한 학생의 수
		double employedAvg = Math.round(totalEmployedStudentCount / (double) totalStudentCount *100 ); // 취업 평균
		
		// 디버깅
		log.debug(CF.YHJ+"StatsService.studentEmployedRate.totalStudentCount : " + totalStudentCount + CF.RESET);
		log.debug(CF.YHJ+"StatsService.studentEmployedRate.totalEmployedStudentCount : " + totalEmployedStudentCount + CF.RESET);
		log.debug(CF.YHJ+"StatsService.studentEmployedRate.employedAvg : " + employedAvg + CF.RESET);
		
		map.put("totalStudentCount", totalStudentCount);
		map.put("totalEmployedStudentCount", totalEmployedStudentCount);
		map.put("employedAvg", employedAvg);
		
		return map;
	}
	
	
}
