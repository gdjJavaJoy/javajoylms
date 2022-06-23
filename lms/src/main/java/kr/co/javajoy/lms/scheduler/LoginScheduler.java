package kr.co.javajoy.lms.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.LoginService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginScheduler {
	@Autowired LoginService loginService;
	// 초 분 시 일 월 요일
	@Scheduled(cron = "0 0 0 * * *") // 0초 0분 0시 매일 매월 매요일 
	public void modifyDormantMember() {
		int row = loginService.modifyDormantMember();
		if (row>0) { // row 가 1 이상이면 
			log.debug(CF.PSG+row+"명이 휴면 처리 되었습니다" + CF.RESET);
		}
	}
}
