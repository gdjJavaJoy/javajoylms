package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.CurriculumMapper;
import kr.co.javajoy.lms.mapper.MemberMapper;
import kr.co.javajoy.lms.mapper.StudentMapper;
import kr.co.javajoy.lms.mapper.SubjectReportStudentMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class StatsService {
	@Autowired StudentMapper studentMapper;
	@Autowired MemberMapper memberMapper;
	@Autowired CurriculumMapper curriculumMapper;
	@Autowired SubjectReportStudentMapper subjectReportStudentMapper;
	
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
	
	// 학생 성별 률
	public Map<String,Object> studentGenderRate(){
		Map<String,Object> map = new HashMap<>();
		
		int totalStudentCount = studentMapper.totalStudent(); // 학원의 학생 총 수
		int totalStudentOfMan = studentMapper.totalStudentOfMan(); // 남자학생 수
		int totalStudentOfWoman = studentMapper.totalStudentOfWoman(); // 여자학생 수
		double studentGenderRate = Math.round(totalStudentOfMan / (double) totalStudentCount *100 ); // 남자기준 학생 성별 비율
		
		// 디버깅
		log.debug(CF.YHJ+"StatsService.studentGenderRate.totalStudentCount : " + totalStudentCount + CF.RESET);
		log.debug(CF.YHJ+"StatsService.studentGenderRate.totalStudentOfMan : " + totalStudentOfMan + CF.RESET);
		log.debug(CF.YHJ+"StatsService.studentGenderRate.totalStudentOfWoman : " + totalStudentOfWoman + CF.RESET);
		log.debug(CF.YHJ+"StatsService.studentGenderRate.studentGenderRate : " + studentGenderRate + CF.RESET);		
		
		map.put("totalStudentCount", totalStudentCount);
		map.put("totalStudentOfMan", totalStudentOfMan);
		map.put("totalStudentOfWoman", totalStudentOfWoman);
		map.put("studentGenderRate", studentGenderRate);
		
		return map;
	}
	
	// 학생 최종학력 비율
	public List<Map<String,Object>> studentEducationRate(){
		List<Map<String,Object>> list = studentMapper.studentEducationRate();
		log.debug(CF.YHJ+"StatsService.studentEducationRate.list : " + list + CF.RESET); // 디버깅
		
		return list;
	}
	
	// 취업한 학생 초봉 평균
	public Map<String,Object> employedStudentFirstSalaryRate(){
		Map<String,Object> map = studentMapper.employedStudentFirstSalaryRate();
		log.debug(CF.YHJ+"StatsService.employedStudentFirstSalaryRate.map : " + map + CF.RESET); // 디버깅
		
		return map;
	}
	
	// 아이디 사용률
	public Map<String,Object> useMemberRate(){
		Map<String,Object> map = memberMapper.useMemberRate();
		// 디버깅
		log.debug(CF.YHJ+"StatsService.useMemberRate.map : " + map + CF.RESET); 
		
		return map;
	}
	
	// 커리큘럼별 언어 비율
	public List<Map<String,Object>> languageRateByCurriculum(){
		List<Map<String,Object>> list = curriculumMapper.languageRateByCurriculum();
		
		log.debug(CF.YHJ+"StatsService.useMemberRate.map : " + list + CF.RESET); // 디버깅
		
		return list;
	}
	
	// 학생 과제 게시판 점수 통계
	public List<Map<String, Object>> subjectReportRate() {
		List<Map<String, Object>> list = subjectReportStudentMapper.studentReportRate();
		return list;
	}
}

























