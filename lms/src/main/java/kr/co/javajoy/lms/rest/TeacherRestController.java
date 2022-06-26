package kr.co.javajoy.lms.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.TeacherService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TeacherRestController {
	@Autowired TeacherService teacherService;
	@PostMapping("/modifyCareer")
	public String modifyCareer(@RequestParam(value="careerNo") int careerNo
							  ,@RequestParam(value="careerInfo") String careerInfo
							  ,@RequestParam(value="detailCareer") String detailCareer) {
		int row = teacherService.modifyCareer(careerInfo, detailCareer, careerNo);
		
		if (row == 1) {
			log.debug(CF.PSG +"TeacherRestController.updateCareer 수정성공"+CF.RESET);
		} else {
			log.debug(CF.PSG +"TeacherRestController.updateCareer 수정실패"+CF.RESET);
		}
		
		
		return null;
	}
}
