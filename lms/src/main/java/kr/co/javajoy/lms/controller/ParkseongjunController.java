package kr.co.javajoy.lms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.MemberService;
import kr.co.javajoy.lms.vo.Password;
import kr.co.javajoy.lms.vo.SignupForm;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ParkseongjunController {
	@GetMapping("/addMember")
	public String addMember() {
		
		return "login/addMember";
	}
	@Autowired MemberService memberService;
	@PostMapping("/addMember")
	public String addMember(SignupForm signUpForm) {
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.gender :" + signUpForm.getGender()+CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.Education :" + signUpForm.getEducation()+CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.MemberJoin :" + signUpForm.getMemberJoin()+CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.getMemberAddr :" + signUpForm.getMemberAddress()+CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.memberPw :" + signUpForm.getMemberPw()+CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember.signUpForm.Active :" + signUpForm.getMemberActive()+CF.RESET);
		int row = memberService.addMember(signUpForm);
		
		if (row==1) {
			log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember 추가성공"+CF.RESET);
			return "redirect:/login";
		} else {
			log.debug(CF.PSG+"ParkseongjunController.PostMapping.addMember 추가실패"+CF.RESET);
		return "redirect:/addMember";
		}
	}
	@GetMapping("/modifyPw")
	public String modifyPw(HttpSession session) {
		String memberId = (String) session.getAttribute("loginUser"); // 세션에 있는 loginUser 값 변수에 저장 
		String active = memberService.getMemberActive(memberId); // memberId를 사용해서 active값 받아오기
		int period = memberService.getMemberPwPeriod(memberId); //  memberId사용해서 현재시간 - 마지막 변경날짜구하기 
		String level = memberService.getMemberLevel(memberId);
		//디버깅
		log.debug(CF.PSG+"ParkseongjunController.modifyPw.Get().memberId : " + memberId+ CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.modifyPw.Get().active : " + active + CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.modifyPw.Get().period : " + period + CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.modifyPw.Get().level : " + level +CF.RESET);
		
		if (active.equals("4") || period > 3) { //active가 4이거나 변경날짜가 3개월 이상일때
			log.debug(CF.PSG+"ParkseongjunController.mdoifyPw  비밀번호 변경창 이동" +CF.RESET);
			return "login/modifyPw"; // 비밀번호 변경창으로 이동
			} 
		if (level.equals("1")) { // 관리자일때
			log.debug(CF.PSG+"ParkseongjunController.modifyPw 관리자index 이동" +CF.RESET);
			return "redirect:/getSubjectByPage"; //관리자 페이지 이동  -> adminIndex 
		}
		
		return "redirect:/memberIndex"; // --> memberIndex 페이지 이동 
		}
	@PostMapping("/modifyPw")
	public String modifyPw(Password password) {
		//	받은 값 디버깅 
		log.debug(CF.PSG+"ParkseongjunController.modifyPw.Post().memberId :" + password.getMemberId()+CF.RESET);
		log.debug(CF.PSG+"ParkseongjunController.modifyPw.Post().password :" + password.getPassword()+CF.RESET);
		
		int row = memberService.modifyMemberPassword(password); 
		if (row == 0) { // 비밀번호 수정 실패 했을때
			// 비밀번호 수정 실패
			log.debug(CF.PSG+"parkseongjunController.modifyPw 수정실패"+CF.RESET);
			return "redirect:/modifyPw";
		}
		// 비밀번호 수정 성공시 로그인 페이지로 이동
		 log.debug(CF.PSG+"parkseongjunController.modifyPw 수정성공"+CF.RESET);
		return "redirect:/logout";
	}
}
