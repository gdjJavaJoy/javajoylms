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
import kr.co.javajoy.lms.service.TeacherService;
import kr.co.javajoy.lms.vo.Career;
import kr.co.javajoy.lms.vo.MemberUpdateForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TeacherController {
	@Autowired TeacherService teacherService;
	@GetMapping("/teacherOne")
	public String teacherOne(HttpSession session
							,Model model) {
		String memberId = (String)session.getAttribute("loginUser"); //session 에 저장되어있는 Id값 받아옴
		String level = (String)session.getAttribute("level"); // sle
		// 디버깅 
		log.debug(CF.PSG+"TeacherController.teacherOne.memberId :" + memberId + CF.RESET);
		log.debug(CF.PSG+"TeacherController.teacherOne.level :" + level + CF.RESET);
		Map<String,Object> teacherOne = teacherService.getTeacherOne(memberId);
		List<Career> careerList = teacherService.getTeacherCareer(memberId);
		
		model.addAttribute("teacherOne",teacherOne);
		model.addAttribute("careerList",careerList);
		return "member/teacher/teacherOne";
	}
	@GetMapping("/modifyTeacherOne")
	public String modifyTeacherOne(Model model
								  ,@RequestParam(name="memberId") String memberId) {
		Map<String,Object> map = teacherService.getModifyTeacherList(memberId); // 강사 정보 수정할 때 필요한 list 뽑아오는 메서드 
		// 디버깅 
		log.debug(CF.PSG+"TeacherController.modifyTeacherOne.teacherOne : " +map.get("teacherOne") +CF.RESET);
		log.debug(CF.PSG+"TeacherController.modifyTeacherOne.languageList : " +map.get("languageList") +CF.RESET);
		log.debug(CF.PSG+"TeacherController.modifyTeacherOne.careerList : " +map.get("careerList") +CF.RESET);
		log.debug(CF.PSG+"TeacherController.modifyTeacherOne.teacherLanguageList : " +map.get("teacherLanguageList") +CF.RESET);
		
		model.addAttribute("teacherOne",map.get("teacherOne"));
		model.addAttribute("languageList",map.get("languageList"));
		model.addAttribute("careerList",map.get("careerList"));
		model.addAttribute("teacherLanguageList",map.get("teacherLanguageList"));
		return "member/teacher/modifyTeacherOne";
	}
	@PostMapping("/modifyTeacherOne")
	public String modifyTeacherOne(MemberUpdateForm memberUpdateForm) {
		// 디버깅
		log.debug(CF.PSG+"TehacerController.modifyTeacherOne.post().memberId :"+ memberUpdateForm.getMemberId()+CF.RESET);
		log.debug(CF.PSG+"TehacerController.modifyTeacherOne.post().memberName :"+ memberUpdateForm.getMemberName()+CF.RESET);
		log.debug(CF.PSG+"TehacerController.modifyTeacherOne.post().memberGender :"+ memberUpdateForm.getMemberGender()+CF.RESET);
		log.debug(CF.PSG+"TehacerController.modifyTeacherOne.post().memberPhone :"+ memberUpdateForm.getMemberPhone()+CF.RESET);
		log.debug(CF.PSG+"TehacerController.modifyTeacherOne.post().memberEmail :"+ memberUpdateForm.getMemberEmail()+CF.RESET);
		log.debug(CF.PSG+"TehacerController.modifyTeacherOne.post().currentMemberAddress :"+ memberUpdateForm.getCurrentMemberAddress()+CF.RESET);
		log.debug(CF.PSG+"TehacerController.modifyTeacherOne.post().changeMemberAddress :"+ memberUpdateForm.getChangeMemberAddress()+CF.RESET);
		log.debug(CF.PSG+"TehacerController.modifyTeacherOne.post().MemberDetailAddress :"+ memberUpdateForm.getMemberDetailAddress()+CF.RESET);
		for(int i=0; i<memberUpdateForm.getLanguageNo().size(); i++) {
			log.debug(CF.PSG+"TeacherController.modifyTeacherOne.post().LanguageNo : "+memberUpdateForm.getLanguageNo().get(i)+CF.RESET);
		}
		
		int row = teacherService.modifyTeacherOne(memberUpdateForm);
		
		if (row == 0) {
			log.debug(CF.PSG+"TeacherController modifyTeacherOne  수정실패"+CF.RESET);
			return "redirect:/modifyTeacherOne?memberId="+memberUpdateForm.getMemberId();
		} else {
			log.debug(CF.PSG+"TeacherController.modifyTeacherOne 수정성공"+CF.RESET);
		}
		
		
		return "redirect:/teacherOne";
	}
	//경력 삭제 하는 컨트롤러 
	@GetMapping("/removeCareer")
	public String removeCareer(int careerNo
							  ,HttpSession httpSession) {
		log.debug(CF.PSG+"teacherController.removeCareer.memberId: "+ (String)httpSession.getAttribute("loginUser")+CF.RESET);
		log.debug(CF.PSG+"teacherController.removeCareer.carrerNo :" + careerNo +CF.RESET);
		
			int row = teacherService.removeCareer(careerNo);
			if (row == 1) {
				log.debug("teacherController.removeCareer  삭제성공");
			} else {
				log.debug("teacherController.removeCareer  삭제실패");
			}
			
		
		return "redirect:/modifyTeacherOne?memberId="+(String)httpSession.getAttribute("loginUser");
	}
	// 경력 추가하는 컨트롤러
	@PostMapping("/addCareer")
	public String addCareer(Career career
						   ,HttpSession httpeSession) {
		log.debug(CF.PSG+"TeacherController.addCarrer.memberId :" + career.getMemberId() + CF.RESET);
		log.debug(CF.PSG+"TeacherController.addCarrer.career:" + career.getCareer() + CF.RESET);
		log.debug(CF.PSG+"TeacherController.addCarrer.memberId :" + career.getDetailCareer() + CF.RESET);
		
		
		 int row = teacherService.addCareer(career);
		 
		 if (row ==1) {
			 log.debug(CF.PSG+"TeacherController.addCarrer 수정성공"+CF.RESET);
		 } else {
			 log.debug(CF.PSG+"TeacherController.addCarrer 수정실패 "+CF.RESET);
		 }
		 return "redirect:/modifyTeacherOne?memberId="+(String)httpeSession.getAttribute("loginUser");
	}
	
}
