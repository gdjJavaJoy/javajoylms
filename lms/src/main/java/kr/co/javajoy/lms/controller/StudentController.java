package kr.co.javajoy.lms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.StudentService;
import kr.co.javajoy.lms.vo.MemberUpdateForm;
import kr.co.javajoy.lms.vo.StudentJob;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StudentController {
	@Autowired StudentService studentService;
	
	@GetMapping("/studentOne")
	public String studentOne(HttpSession session
							,Model model) {
		String loginUser = session.getAttribute("loginUser").toString();
		log.debug(CF.YHJ + "StudentController.getStudentOne.loginUser : " + loginUser); // 디버깅
		
		Map<String,Object> student = studentService.getStudentOne(loginUser);
		log.debug(CF.YHJ + "StudentController.getStudentOne.student : " + student); // 디버깅
		
		model.addAttribute("student",student);
		
		return "/member/student/studentOne";
	}
	
	@GetMapping("/modifyStudentOne")
	public String modifyStudentOne(@RequestParam(value="memberId") String memberId
									,Model model) {
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.memberId : " + memberId); // 디버깅
		
		Map<String,Object> student = studentService.getStudentOne(memberId);
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.student : " + student); // 디버깅
		
		model.addAttribute("student",student);
		
		return "/member/student/modifyStudentOne";
	}
	
	@PostMapping("/modifyStudentOne")
	public String modifyStudentOne(HttpSession session,
									MemberUpdateForm memberUpdateForm) {
		String level = (String) session.getAttribute("level");
		// 디버깅
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getMemberId : " + memberUpdateForm.getMemberId());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getMemberName : " + memberUpdateForm.getMemberName());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getMemberGender : " + memberUpdateForm.getMemberGender());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getMemberPhone : " + memberUpdateForm.getMemberPhone());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getMemberEmail : " + memberUpdateForm.getMemberEmail());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getMemberEducation : " + memberUpdateForm.getMemberEducation());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getCurrentMemberAddress : " + memberUpdateForm.getCurrentMemberAddress());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getChangeMemberAddress : " + memberUpdateForm.getChangeMemberAddress());
		log.debug(CF.YHJ + "StudentController.modifyStudentOne.getMemberDetailAddress : " + memberUpdateForm.getMemberDetailAddress());
		
		studentService.modifyStudent(memberUpdateForm);
		if(level.equals("1")) {
			log.debug(CF.LGN + "StudentController.allStudentList이동" + CF.RESET);
			return "redirect:allStudentList";
		} else {
			return "redirect:studentOne";
		}
	}
	
	// 취업한 학생 리스트 뽑기
	@GetMapping("employedStudentList")
	public String employedStudentList(Model model
									,@RequestParam @Nullable String searchName
									,@RequestParam(value = "currentPage", defaultValue = "1") int currentPage
									,@RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage)	{
		// 디버깅
		log.debug(CF.YHJ + "StudentController.employedStudentList.searchName : " + searchName);
		log.debug(CF.YHJ + "StudentController.employedStudentList.currentPage : " + currentPage);
		log.debug(CF.YHJ + "StudentController.employedStudentList.rowPerPage : " + rowPerPage);
		
		List<Map<String,Object>> list = studentService.getEmploytedStudentList(currentPage,rowPerPage,searchName); // 취업 학생 리스트
		log.debug(CF.YHJ + "StudentController.employedStudentList.list : " + list); // 디버깅
		
		model.addAttribute("list",list);
		
		return "/member/admin/getEmployedStudentList";
	}
	
	// 취업 학생 등록
	@GetMapping("addEmployedStudent")
	public String addEmployedStudent() {
		
		return "/member/admin/addEmployedStudent";
	}
	
	@PostMapping("addEmployedStudent")
	public String addEmployedStudent(StudentJob studentJob) {
		log.debug(CF.YHJ + "StudentController.addEmployedStudent.studentJob : " + studentJob); // 디버깅
		studentService.addEmployedStudent(studentJob); // 취업 학생 등록
		
		return "redirect:employedStudentList";
	}
	
	// 취업 학생 삭제
	@GetMapping("deleteEmployedStudent")
	public String deleteEmployedStudent(@RequestParam(value="memberId") String memberId) {
		log.debug(CF.YHJ + "StudentController.deleteEmployedStudent.memberId : " + memberId);
		studentService.removeEmployedStudent(memberId); // 취업 학생 삭제
		
		return "redirect:employedStudentList";
	}
	
	// 취업 학생 수정
	@GetMapping("modifyEmployedStudent")
	public String modifyEmployedStudent(String memberId
										,Model model) {
		log.debug(CF.YHJ + "StudentController.modifyEmployedStudent.memberId : " + memberId); // 디버깅
		
		StudentJob studentJob = studentService.selectEmployedStudentOne(memberId); // 취업학생 한명 출력
		log.debug(CF.YHJ + "StudentController.modifyEmployedStudent.studentJob : " + studentJob); // 디버깅
		
		// 모델에 값 넣기
		model.addAttribute("studentJob",studentJob);
		
		return "/member/admin/modifyEmployedStudent";
	}
	
	@PostMapping("modifyEmployedStudent")
	public String modifyEmployedStudent(StudentJob studentJob) {
		log.debug(CF.YHJ + "StudentController.modifyEmployedStudent.studentJob : " + studentJob); // 디버깅
		studentService.modifyEmployedStudent(studentJob); // 취업 학생 등록
		
		return "redirect:employedStudentList";
	}
	
}
