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
import kr.co.javajoy.lms.vo.MemberUpdateForm;
import kr.co.javajoy.lms.vo.Teacher;
import kr.co.javajoy.lms.vo.TeacherLanguage;
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
	public int addCareer(Career career) {
		return teacherMapper.insertCareer(career);
	}
	public int modifyTeacherOne(MemberUpdateForm memberUpdateForm) {
		int row  = 0;
		log.debug(CF.PSG+"TeacherService memberUpdateForm : " +memberUpdateForm+CF.RESET);
		// memberUpdateform에 있는 memberId 저장
		String memberId = memberUpdateForm.getMemberId();
		
		TeacherLanguage teacherLanguage = new TeacherLanguage();
		
		teacherMapper.deleteTeacherLanguage(memberId); // memberIdㅇ
		for(int i=0; i<memberUpdateForm.getLanguageNo().size(); i++) {
			log.debug(CF.PSG+"TeacherService.insertTeacherLanguageCount :" + i +CF.RESET);
			teacherLanguage.setMemberId(memberUpdateForm.getMemberId());
			teacherLanguage.setLanguageNo(memberUpdateForm.getLanguageNo().get(i));
			teacherMapper.insertTeacherLanguage(teacherLanguage);
			
		}
		Teacher teacher = new Teacher();
		String changeAddress = memberUpdateForm.getChangeMemberAddress();
		log.debug(CF.PSG+"TeacherService ChangeAddress : " +changeAddress + CF.RESET);
		
		teacher.setTeacherAddress(changeAddress);
		if (teacher.getTeacherAddress() == null) {
			teacher.setTeacherAddress(memberUpdateForm.getCurrentMemberAddress());
		}
		teacher.setMemberId(memberUpdateForm.getMemberId());
		teacher.setTeacherName(memberUpdateForm.getMemberName());
		teacher.setTeacherPhone(memberUpdateForm.getMemberPhone());
		teacher.setTeacherEmail(memberUpdateForm.getMemberEmail());
		teacher.setTeacherGender(memberUpdateForm.getMemberGender());
		teacher.setTeacherDetailAddress(memberUpdateForm.getMemberDetailAddress());
		
		
		
		row = teacherMapper.updateTeacherOne(teacher);
		
		
		return row;
	}
	public int modifyCareer(String careerInfo, String detailCareer, int careerNo) {
		Career career = new Career();
		career.setCareerNo(careerNo);
		career.setCareer(careerInfo);
		career.setDetailCareer(detailCareer);
		
		return teacherMapper.updateTeacherCareer(career);
	}
	
}
