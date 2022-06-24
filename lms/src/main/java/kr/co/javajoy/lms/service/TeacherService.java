package kr.co.javajoy.lms.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.TeacherMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class TeacherService {
	@Autowired TeacherMapper teacherMapper;
	public Map<String,Object> getTeacherOne(String memberId) {
		Map<String,Object> teacherOne = teacherMapper.teacherOne(memberId);
			log.debug(CF.PSG+"TeacherService.getTeacherOne.teacherOne: "+ teacherOne + CF.RESET);
		return teacherOne;
	}
}
