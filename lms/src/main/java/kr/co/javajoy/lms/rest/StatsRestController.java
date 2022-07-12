package kr.co.javajoy.lms.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	// 학생 성별
	@GetMapping("studentGenderRate")
	Map<String,Object> studentGenderRate(){
		Map<String,Object> map = statsService.studentGenderRate();
		// 남자 학생수, 여자학생 수, 총 학생수, 남자기준 성별비율이 저장
		log.debug(CF.YHJ+"StatsRestController.studentGenderRate : " + map + CF.RESET); // 디버깅
		
		return map;
	} 
	
	// 학생 최종 학력 비율
	@GetMapping("studentEducationRate")
	List<Map<String,Object>> studentEducationRate(){
		List<Map<String,Object>> list = statsService.studentEducationRate();
		log.debug(CF.YHJ+"StatsRestController.studentEducationRate.list : " + list + CF.RESET); // 디버깅
		
		 return list;
	}
	
	// 취업한 학생 초봉
	@GetMapping("employedStudentFirstSalaryRate")
	Map<String,Object> employedStudentFirstSalaryRate(){
		Map<String,Object> map = statsService.employedStudentFirstSalaryRate();
		log.debug(CF.YHJ+"StatsRestController.employedStudentFirstSalaryRate.map : " + map + CF.RESET); // 디버깅
		
		return map;
	}
	
	// 아이디 사용률
	@GetMapping("useMemberRate")
	Map<String,Object> useMemberRate(){
		Map<String,Object> map = statsService.useMemberRate();
		log.debug(CF.YHJ+"StatsRestController.useMemberRate.useMemberRate : " + map + CF.RESET); // 디버깅
		
		return map;
	}
	
	// 커리큘럼 기준 언어 사용률
	@GetMapping("languageRateByCurriculum")
	public List<Map<String,Object>> languageRateByCurriculum(){
		List<Map<String,Object>> list = statsService.languageRateByCurriculum();
		log.debug(CF.YHJ+"StatsRestController.languageRateByCurriculum.map : " + list + CF.RESET); // 디버깅
		
		return list;
	}
	
	// 강좌별 과제 게시판 점수 통계
	@GetMapping("studentReportRate")
	public List<Map<String, Object>> studentReportRate() {
		List<Map<String, Object>> list = statsService.subjectReportRate();
		
		log.debug(CF.PBJ + "StatsRestController.studentReportRate.list : " + list); // 디버깅
		return list;
	}
}





















