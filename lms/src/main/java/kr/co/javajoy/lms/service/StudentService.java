package kr.co.javajoy.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.StudentMapper;
import kr.co.javajoy.lms.vo.MemberUpdateForm;
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
	
	public void modifyStudent(MemberUpdateForm memberUpdateForm) {
		// 디버깅
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getMemberId : " + memberUpdateForm.getMemberId());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getMemberName : " + memberUpdateForm.getMemberName());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getMemberGender : " + memberUpdateForm.getMemberGender());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getMemberPhone : " + memberUpdateForm.getMemberPhone());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getMemberEamil : " + memberUpdateForm.getMemberEmail());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getMemberEducation : " + memberUpdateForm.getMemberEducation());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getCurrentMemberAddress : " + memberUpdateForm.getCurrentMemberAddress());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getChangeMemberAddress : " + memberUpdateForm.getChangeMemberAddress());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getMemberDetailAddress : " + memberUpdateForm.getMemberDetailAddress());
		
		// 값 넣기
		Student student = new Student();
		student.setMemberId(memberUpdateForm.getMemberId());
		student.setStudentName(memberUpdateForm.getMemberName());
		student.setStudentGender(memberUpdateForm.getMemberGender());
		student.setStudentPhone(memberUpdateForm.getMemberPhone());
		student.setStudentEmail(memberUpdateForm.getMemberEmail());
		student.setStudentEducation(memberUpdateForm.getMemberEducation());
		// getChangeMemberAddress가 없으면 변경을 하지 않음
		if(memberUpdateForm.getChangeMemberAddress() == null) {
			student.setStudentAddress(memberUpdateForm.getCurrentMemberAddress());
		} else { // 변경했다면 
			student.setStudentAddress(memberUpdateForm.getChangeMemberAddress());
		}
		student.setStudentDetailAddress(memberUpdateForm.getMemberDetailAddress());
		
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.student : " + student); // 디버깅
		studentMapper.updateStudentOne(student);
	}
}

