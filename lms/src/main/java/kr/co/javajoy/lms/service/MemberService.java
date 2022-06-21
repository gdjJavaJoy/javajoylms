package kr.co.javajoy.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.javajoy.lms.mapper.MemberMapper;
import kr.co.javajoy.lms.vo.Admin;
import kr.co.javajoy.lms.vo.Member;
import kr.co.javajoy.lms.vo.SignupForm;

@Service
public class MemberService {
	@Autowired MemberMapper memberMapper;
	public List<String> getMemberId() {
		List<String> list = memberMapper.selectMemberId();
		return list;
	}
	public void addMember(SignupForm signupForm) {
		int level = signupForm.getLevel();
		if(level == 1) {
			Admin admin = new Admin();
			admin.setMemberId(signupForm.getMemberId());
			admin.setAdminPw(signupForm.getMemberPw());;
			admin.setAdminName(signupForm.getMemberName());
			admin.setAdminPhone(signupForm.getMemberPhone());
			admin.setAdminAddress(signupForm.getMemberAddress());
			admin.setAdminDetailAddress(signupForm.getMemberDetailAddress());
			admin.setAdminEmail(signupForm.getMemberEmail());
			
			Member member = new Member();
			member.setMemberId(signupForm.getMemberId());
		}
	}
 }
