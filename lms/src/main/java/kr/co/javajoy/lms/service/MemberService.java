package kr.co.javajoy.lms.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.MemberMapper;
import kr.co.javajoy.lms.vo.Admin;
import kr.co.javajoy.lms.vo.InsertMemberPhotoForm;
import kr.co.javajoy.lms.vo.Member;
import kr.co.javajoy.lms.vo.MemberPhoto;
import kr.co.javajoy.lms.vo.MemberUpdateForm;
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
			teacher.setTeacherGender(signupForm.getGender());
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
	public Map<String,Object> modifyPw(String memberId) {
		Map<String,Object> map = new HashMap<String,Object>();
		String active = memberMapper.selectMemberActive(memberId);
		int period = memberMapper.selectMemberPwPeriod(memberId); // 비밀번호 현재날짜 - 마지막으로 
		String level = memberMapper.selectMemberLevel(memberId);	
		map.put("active",active);
		map.put("period", period);
		map.put("level", level);
		return map;
	}
	
	// 비활성화 member 활성화
	public void modifyMemberActive(String memberId) {
		memberMapper.updateMemberActive(memberId);
	}
	// 해당 회원이 같은비밀번호 사용하는 지 검사하는서비스 
	public int getFindSamePw(String memberId,String password) {
		log.debug(CF.PSG+password+"asdasdd"+CF.RESET);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("memberId", memberId);
		map.put("memberPw", password);
		int cnt = memberMapper.selectMemberPw(map);
		log.debug(CF.PSG+"asdasdsadad"+cnt+CF.RESET);
		return cnt;
	}
	public int modifyMemberPassword(Password password) {
		int row = 0;
		String memberId = password.getMemberId();
		log.debug(CF.PSG +"MemberService.modifyMemberPassword.memberPw : "+password.getPassword() +CF.RESET);
		log.debug(CF.PSG +"MemberService.modifyMemberPassword.memberId : "+memberId +CF.RESET);
		String active = memberMapper.selectMemberActive(memberId);
		if (active.equals("4") || active.equals("2")) {
			memberMapper.updateMemberActive(memberId);
			memberMapper.updateMemberPassword(password);
	  row = memberMapper.insertPassword(password);
		} else {
			memberMapper.updateMemberPassword(password);
	  row = memberMapper.insertPassword(password);
		}
			
		return row;
	}

	// 탈퇴한 회원 리스트 
	public List<String> getResignedMemberId() {
		List<String> list = memberMapper.selectResignedMemberId();
		return list;
	}
	
	// memberIndex에서 나오는 list 
	public List<Map<String,Object>> memberIndex(String memberId, String level){	
		List<Map<String,Object>> list = null;
		
		if(level.equals("3")) { // member가 student면
			list = memberMapper.studentIndex(memberId);
			log.debug(CF.YHJ + "StudentService.memberIndex.list : " + list + CF.RESET); // 디버깅
			return list;
		}

		// 이외면 (teacher면)
		list = memberMapper.teacherIndex(memberId);
		log.debug(CF.YHJ + "StudentService.memberIndex.list : " + list + CF.RESET); // 디버깅
		
		return list;
	}
	public int addMemberPhoto(InsertMemberPhotoForm insertMemberPhotoForm, String path) {
		int row = 0;
		String memberId = insertMemberPhotoForm.getMemberId();
		log.debug(CF.PSG+"MemberService.addMemberPhoto.memberId : " + memberId + CF.RESET);
		int cnt = memberMapper.selectMemberPhotoCnt(memberId); // 파일이 존재하는지 확인하는 쿼리문
		
		
		for(MultipartFile mf : insertMemberPhotoForm.getMemberPhotoList()) {
			MemberPhoto memberPhoto = new MemberPhoto();
			
			String originalFileName = mf.getOriginalFilename(); // 파일originalName 변수에저장 
			String ext = originalFileName.substring(originalFileName.lastIndexOf(".")); // 확장자
			String fileName = UUID.randomUUID().toString(); //fileName 을 저장할때 사용하는 중복되지않는새로운 이름필요 (UUID API)
			log.debug(CF.PSG+"MemberService.addMember.originalFileName :"+ originalFileName+CF.RESET);
			
			fileName = fileName + ext; // 파일이름 + 확장자 붙이기 
			log.debug(CF.PSG+"MemberService.addMember.fileName :"+fileName+CF.RESET);
			
			memberPhoto.setMemberId(memberId);
			memberPhoto.setMemberPhotoName(fileName);
			memberPhoto.setMemberPhotoOriginalName(originalFileName);
			memberPhoto.setMemberPhotoSize(mf.getSize());
			memberPhoto.setMemberPhotoType(mf.getContentType());
			//jpeg, jpg, png이면 
			if (mf.getContentType().equals("image/jpeg") || mf.getContentType().equals("image/jpg") || mf.getContentType().equals("image/png")) {
				if (cnt>0) { // 만약 저장되어있는 파일이 있으면
					log.debug(CF.PSG+"MemberService.addMemberPhoto 기존에있던 파일 삭제"+CF.RESET);
					List<String> deleteFileNameList = memberMapper.selectPhotoNameByMemberId(memberId); // 파일 이름을 List에저장
					for (String deleteFileName : deleteFileNameList) {
						File f = new File(path+deleteFileName); // 파일경로
						if(f.exists()) { // 파일이 존재한다면 
							f.delete(); // 파일삭제
						}
					}
					memberMapper.deleteMemberPhoto(memberId); // db에 행 삭제 
				}
				
				// 사진 추가 성공
				row = memberMapper.insertMemberPhoto(memberPhoto); // db 에 사진저장 
				try {
					mf.transferTo(new File(path+fileName));
				} catch (Exception e) {
					e.printStackTrace();
					// 새로운 예외 발생시켜야지만 @Transactional 작동함 
					throw new RuntimeException(); //RuntimeException은 예외처리 하지않아도 컴파일 됨
				}
				
			}
		}
		
		return row;
	}
	
	public Admin getAdminOne(String memberId){
		log.debug(CF.YHJ+"MemberService.getAdminOne.memberId: "+ memberId + CF.RESET); // 디버깅
		return memberMapper.selectAdminOne(memberId);
	}
	
	public void modifyAdmin(MemberUpdateForm memberUpdateForm) {
		 // 디버깅
		log.debug(CF.YHJ + "MemberService.modifyAdminOne.getMemberId : " + memberUpdateForm.getMemberId());
		log.debug(CF.YHJ + "MemberService.modifyAdminOne.getMemberName : " + memberUpdateForm.getMemberName());
		log.debug(CF.YHJ + "MemberService.modifyAdminOne.getMemberPhone : " + memberUpdateForm.getMemberPhone());
		log.debug(CF.YHJ + "MemberService.modifyAdminOne.getMemberEmail : " + memberUpdateForm.getMemberEmail());
		log.debug(CF.YHJ + "MemberService.modifyAdminOne.getCurrentMemberAddress : " + memberUpdateForm.getCurrentMemberAddress());
		log.debug(CF.YHJ + "MemberService.modifyAdminOne.getChangeMemberAddress : " + memberUpdateForm.getChangeMemberAddress());
		log.debug(CF.YHJ + "MemberService.modifyAdminOne.getMemberDetailAddress : " + memberUpdateForm.getMemberDetailAddress());
		
		Admin admin = new Admin();
		admin.setMemberId(memberUpdateForm.getMemberId());
		admin.setAdminName(memberUpdateForm.getMemberName());
		admin.setAdminPhone(memberUpdateForm.getMemberPhone());
		admin.setAdminEmail(memberUpdateForm.getMemberEmail());
		// getChangeMemberAddress가 없으면 변경을 하지 않음
		if(memberUpdateForm.getChangeMemberAddress() == null) {
			admin.setAdminAddress(memberUpdateForm.getCurrentMemberAddress());
		} else { // 변경했다면 
			admin.setAdminAddress(memberUpdateForm.getChangeMemberAddress());
		}
		admin.setAdminDetailAddress(memberUpdateForm.getMemberDetailAddress());
		
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.student : " + admin + CF.RESET); // 디버깅
		
		memberMapper.updateAdmin(admin);
	}
	
 }
