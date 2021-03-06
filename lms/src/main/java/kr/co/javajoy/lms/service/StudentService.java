package kr.co.javajoy.lms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.StudentMapper;
import kr.co.javajoy.lms.mapper.SubjectMapper;
import kr.co.javajoy.lms.vo.MemberUpdateForm;
import kr.co.javajoy.lms.vo.Student;
import kr.co.javajoy.lms.vo.StudentJob;
import kr.co.javajoy.lms.vo.Subject;
import kr.co.javajoy.lms.vo.SubjectStudent;
import kr.co.javajoy.lms.vo.SubjectStudentForm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class StudentService {
	@Autowired StudentMapper studentMapper;
	@Autowired SubjectMapper subjectMapper;
	
	public int deleteStudent(String memberId) {
		return studentMapper.deleteStudent(memberId);
	}
	
	public int deleteMemberId(String memberId) {
		return studentMapper.deleteMemberId(memberId);
	}
	
	public Map<String,Object> getStudentOne(String memberId) {
		return studentMapper.selectStudentOne(memberId);
	}
	
	public void modifyStudent(MemberUpdateForm memberUpdateForm) {
		// 디버깅
		log.debug(CF.YHJ + "StudentService.modifyStudentOne.getMemberId : " + memberUpdateForm.getMemberId());
		log.debug(CF.YHJ + "StudentService.modifyStudentOne.getMemberName : " + memberUpdateForm.getMemberName());
		log.debug(CF.YHJ + "StudentService.modifyStudentOne.getMemberGender : " + memberUpdateForm.getMemberGender());
		log.debug(CF.YHJ + "StudentService.modifyStudentOne.getMemberPhone : " + memberUpdateForm.getMemberPhone());
		log.debug(CF.YHJ + "StudentService.modifyStudentOne.getMemberEamil : " + memberUpdateForm.getMemberEmail());
		log.debug(CF.YHJ + "StudentService.modifyStudentOne.getMemberEducation : " + memberUpdateForm.getMemberEducation());
		log.debug(CF.YHJ + "StudentService.modifyStudentOne.getCurrentMemberAddress : " + memberUpdateForm.getCurrentMemberAddress());
		log.debug(CF.YHJ + "StudentService.modifyStudentOne.getChangeMemberAddress : " + memberUpdateForm.getChangeMemberAddress());
		log.debug(CF.YHJ + "StudentService.modifyStudentOne.getMemberDetailAddress : " + memberUpdateForm.getMemberDetailAddress());
		
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
		
		log.debug(CF.YHJ + "StudentService.modifyStudentOne.student : " + student); // 디버깅
		studentMapper.updateStudentOne(student);
	}
	
	// 취업 학생 리스트 출력
	public List<Map<String,Object>> getEmploytedStudentList( int currentPage, int rowPerPage,String searchName){
		// 디버깅
		log.debug(CF.YHJ + "StudentService.employedStudentList.searchName : " + searchName);
		log.debug(CF.YHJ + "StudentService.employedStudentList.currentPage : " + currentPage);
		log.debug(CF.YHJ + "StudentService.employedStudentList.rowPerPage : " + rowPerPage);
		
		// selectEmploytedStudentList을 사용하기 위한 map 정재
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("searchName", searchName);
		map.put("currentPage", currentPage);
		map.put("rowPerPage", rowPerPage);
		
		List<Map<String,Object>> list = studentMapper.selectEmploytedStudentList(map); // 취업한 학생 리스트 
		
		return list;
	}
	
	// 취업 학생 등록
	public void addEmployedStudent(StudentJob studentJob) {
		log.debug(CF.YHJ + "StudentService.addEmployedStudent.studentJob : " + studentJob); // 디버깅
		studentMapper.insertEmployedStudent(studentJob);
	}
	
	// 취업 학생 삭제
	public void removeEmployedStudent(String memberId){
		log.debug(CF.YHJ + "StudentService.removeEmployedStudent.memberId : " + memberId); // 디버깅
		studentMapper.deleteEmployedStudent(memberId);
	}
	
	// 취업학생 한명 출력
	public StudentJob selectEmployedStudentOne(String memberId) {
		log.debug(CF.YHJ + "StudentService.selectEmployedStudentOne.memberId : " + memberId); // 디버깅
		
		StudentJob employedStudentOne = studentMapper.selectEmployedStudentOne(memberId); // 취업학생 한명 출력
		log.debug(CF.YHJ + "StudentService.selectEmployedStudentOne.selectEmployedStudentOne : " + employedStudentOne); // 디버깅
		
		return employedStudentOne;
	}
	
	// 취업학생 수정
	public void modifyEmployedStudent(StudentJob studentJob) {
		log.debug(CF.YHJ + "StudentService.modifyEmployedStudent.studentJob : " + studentJob); // 디버깅
		studentMapper.updateEmployedStudent(studentJob);
	}
	// 강의추가에 필요한 리스트 뽑는 서비스 
		public Map<String,Object> getSubjectListForm() {
			//강의 리스트 
			List<Subject> subjectList = subjectMapper.selectSubjectListByEndDate();
			List<Student> studentList = studentMapper.selectStudentList();
			log.debug(CF.PSG+"StudentService.getStudentList studentList : " +studentList +CF.RESET);
			// 학생리스트 
			log.debug(CF.PSG+"SubjectService.getInsertSubjectStudentForm.subjectList :" + subjectList + CF.RESET);
			Map<String,Object> map = new HashMap<>();
			map.put("subjectList", subjectList);
			map.put("studentList",studentList);
			return map;
		}
			
		public int addSubjectStudent(SubjectStudentForm subjectStudentForm) {
			int row = 0;
			SubjectStudent sb = new SubjectStudent();
			sb.setSubjectNo(subjectStudentForm.getSubjectNo());
			List<String> list = subjectStudentForm.getStudentId();
			for (int i=0; i<list.size(); i++) {
			sb.setSubjectNo(subjectStudentForm.getSubjectNo());
			sb.setMemberId(list.get(i));
			row = studentMapper.insertSubjectStudent(sb);
			}
			if (row > 0) {
				log.debug(CF.PSG+"addSubjectStudent 등록성공"+CF.RESET);
			} else {
				log.debug(CF.PSG+"addSubjectStudent 등록실패"+CF.RESET);
			}
			return row;
		}

}

