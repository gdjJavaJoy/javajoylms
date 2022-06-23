package kr.co.javajoy.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.StudentMapper;
import kr.co.javajoy.lms.vo.Subject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StudentService {
	@Autowired StudentMapper studentMapper;
	
	public List<Subject> memberIndex(String memberId, String level){	
		List<Subject> list = null;
		
		if(level.equals("3")) { // member가 student면
			list = studentMapper.studentIndex(memberId);
			log.debug(CF.YHJ + "StudentService.memberIndex.list : " + list + CF.RESET); // 디버깅
			return list;
		}
		
		// 이외면 (teacher면)
		log.debug(CF.YHJ + "StudentService.memberIndex.list : " + list + CF.RESET); // 디버깅
		
		return list;
	}
}
