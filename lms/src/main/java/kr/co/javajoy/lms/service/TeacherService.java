package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.MemberMapper;
import kr.co.javajoy.lms.mapper.TeacherMapper;
import kr.co.javajoy.lms.vo.Career;
import kr.co.javajoy.lms.vo.Language;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class TeacherService {
	@Autowired TeacherMapper teacherMapper;
	@Autowired MemberMapper memberMapper;
	public Map<String,Object> getTeacherOne(String memberId) {
		Map<String,Object> teacherOne = teacherMapper.teacherOne(memberId);
			log.debug(CF.PSG+"TeacherService.getTeacherOne.teacherOne: "+ teacherOne + CF.RESET);
		return teacherOne;
	}
	public List<Career> getTeacherCareer(String memberId) {
		List<Career> careerList = teacherMapper.selectTeacherCareer(memberId);
			log.debug(CF.PSG+"TeacherService.getTeacherCareer");
		return careerList;
	}
	public Map<String,Object> getModifyTeacherList(String memberId) {
		List<Language> languageList = memberMapper.selectLanguageList();
		List<Career> careerList = teacherMapper.selectTeacherCareer(memberId);
		Map<String,Object> teacherOne = teacherMapper.teacherOne(memberId);
		List<Map<String,Object>> teacherLanguageList = teacherMapper.selectTeacherLanguage(memberId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("languageList",languageList);
		map.put("careerList", careerList);
		map.put("teacherOne",teacherOne);
		map.put("teacherLanguageList",teacherLanguageList);
		return map;
	}
	public int removeCareer(int careerNo) {
		return teacherMapper.deleteCareer(careerNo);
	}
	
}
