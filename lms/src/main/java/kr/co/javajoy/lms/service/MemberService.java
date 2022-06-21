package kr.co.javajoy.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.javajoy.lms.mapper.MemberMapper;
import kr.co.javajoy.lms.vo.SignupForm;

@Service
public class MemberService {
	@Autowired MemberMapper memberMapper;
	public List<String> getMemberId() {
		List<String> list = memberMapper.selectMemberId();
		return list;
	}
	public int addMember(SignupForm signupForm) {
		int row = 0;
		return row;
	}
 }
