package kr.co.javajoy.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.mapper.StudentMapper;
import kr.co.javajoy.lms.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class StudentService {
	@Autowired StudentMapper studentMapper;
	
	public Student getStudentOne(String memberId) {
		// 사진도 추가해서 map으로 해야함
		return studentMapper.selectStudentOne(memberId);
	}
}
