package kr.co.javajoy.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.controller.ParkseongjunController;
import kr.co.javajoy.lms.mapper.MemberMapper;
import kr.co.javajoy.lms.vo.Admin;
import kr.co.javajoy.lms.vo.Member;
import kr.co.javajoy.lms.vo.Password;
import kr.co.javajoy.lms.vo.SignupForm;
import kr.co.javajoy.lms.vo.Student;
import kr.co.javajoy.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Transactional
@Service
public class MemberService {
	@Autowired MemberMapper memberMapper;
	
	public List<String> getMemberId() {
		List<String> list = memberMapper.selectMemberId();
		return list;
	}
	public int addMember(SignupForm signupForm) {
		int row = 0;
		String level = signupForm.getLevel();
		
		Member member = new Member();
		member.setMemberId(signupForm.getMemberId());
		member.setMemberPw(signupForm.getMemberPw());
		member.setLevel(signupForm.getLevel());
		member.setMemberActive(signupForm.getMemberActive());
		memberMapper.insertMemberId(member);
		
		Password password = new Password();
		password.setMemberId(signupForm.getMemberId());
		password.setPassword(signupForm.getMemberPw());
		memberMapper.insertPassword(password);
		
		log.debug(CF.PSG+"MemberService.addMember.level:" +level+CF.RESET);
		if(level.equals("'1'")){
			log.debug(CF.PSG+"MemberService.Adminlevel"+CF.RESET);
			Admin admin = new Admin();
			admin.setMemberId(signupForm.getMemberId());
			admin.setAdminPw(signupForm.getMemberPw());;
			admin.setAdminName(signupForm.getMemberName());
			admin.setAdminPhone(signupForm.getMemberPhone());
			admin.setAdminAddress(signupForm.getMemberAddress());
			admin.setAdminDetailAddress(signupForm.getMemberDetailAddress());
			admin.setAdminEmail(signupForm.getMemberEmail());
		row	= memberMapper.insertAdmin(admin);
			
		} else if(level.equals("'2'")) {
			log.debug(CF.PSG+"MemberService.Teacherlevel"+CF.RESET);
			Teacher teacher = new Teacher();
			teacher.setMemberId(signupForm.getMemberId());
			teacher.setTeacherName(signupForm.getMemberName());
			teacher.setTeacherPhone(signupForm.getMemberPhone());
			teacher.setTeacherAddress(signupForm.getMemberAddress());
			teacher.setTeacherDetailAddress(signupForm.getMemberDetailAddress());
			teacher.setTeacherEmail(signupForm.getMemberEmail());
			teacher.setTeacherJoin(signupForm.getMemberJoin());
		 row = memberMapper.insertTeacher(teacher);
			
			
		} else if (level.equals("'3'")) {
			log.debug(CF.PSG+"MemberService.Studentevel"+CF.RESET);
			Student student = new Student();
			student.setMemberId(signupForm.getMemberId());
			student.setStudentName(signupForm.getMemberName());
			student.setStudentGender(signupForm.getGender());
			student.setStudentPhone(signupForm.getMemberPhone());
			student.setStudentAddress(signupForm.getMemberAddress());
			student.setStudentDetailAddress(signupForm.getMemberDetailAddress());
			student.setStudentEmail(signupForm.getMemberEmail());
			student.setStudentEducation(signupForm.getEducation());
			student.setStudentRegisterDate(signupForm.getMemberJoin());
			row = memberMapper.insertStudent(student);
			
		}
		return row;
	}
	
	public String getMemberActive(String memberId) {
		String active = memberMapper.selectMemberActive(memberId);
		return active;
	}
	
	// 비활성화 member 활성화
	public void modifyMemberActive(String memberId) {
		memberMapper.updateMemberActive(memberId);
	}
	
	// 탈퇴한 회원 리스트 
	public List<String> getResignedMemberId() {
		List<String> list = memberMapper.selectResignedMemberId();
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
 }
