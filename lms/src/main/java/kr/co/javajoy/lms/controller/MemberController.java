package kr.co.javajoy.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.MemberMapper;
import kr.co.javajoy.lms.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	@Autowired MemberService memberService;
	
	@GetMapping("memberIndex")
	public String sudentIndex(HttpSession session
							,Model model) {
		String memberId = (String) session.getAttribute("loginUser");
		String level = (String) session.getAttribute("level");
		// 디버깅
		log.debug(CF.YHJ + "StudentController.memberIndex.loginUser : " + session.getAttribute("loginUser") + CF.RESET);
		log.debug(CF.YHJ + "StudentController.memberIndex.level : " + session.getAttribute("level") + CF.RESET);

		List<Map<String,Object>> memberList = memberService.memberIndex(memberId,level);
		
		log.debug(CF.YHJ + "StudentController.memberIndex.memberList : " + memberList + CF.RESET); // 디버깅
		
		model.addAttribute("memberList",memberList);
		
		return "member/memberIndex";
	}
	
	// 비활성화 member 활성화
	@GetMapping("modifyMemberActive")
	public String modifyMemberActive(HttpSession session) {
		log.debug(CF.YHJ + "MemberController.modifyMemberActive.loginUser : " + session.getAttribute("loginUser")); // 디버깅		
		
		return "member/modifyMemberActive";
	}
	
	@PostMapping ("modifyMemberActive")
	String modifyMemberActive(HttpSession session
							,@RequestParam(value="flag") String flag) {
		if(!flag.equals("1")) {
			return "redirect:/errorPage";
		}		
		memberService.modifyMemberActive(session.getAttribute("loginUser").toString());
		
		return "redirect:/memberIndex";
	}
}
