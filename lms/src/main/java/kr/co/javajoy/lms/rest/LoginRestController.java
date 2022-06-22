package kr.co.javajoy.lms.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.MemberService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class LoginRestController {
	@Autowired private MemberService memberService;

	@PostMapping("/idCheck")
	public String idCheck(@RequestParam(value = "id") String id) {
		List<String> list = memberService.getMemberId();
		for (String memberId : list) {
			if (memberId.equals(id)) {
				return "false";
			}
		}

		return id;
	}

	@GetMapping("/getAddr")
	@ResponseBody
	public String getAddr(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value = "keyword", defaultValue = "") String keyword) {
		// OPEN API 호출 URL 정보 설정
		final int countPerPage = 10;
		final String confmKey = "devU01TX0FVVEgyMDIyMDYyMTAxNDMyODExMjcwODU=";
		final String resultType = "json";
		log.debug(CF.PSG + "LoginRestController.getAddr.keyword :" + keyword + CF.RESET);
		StringBuffer sb = null;
		try {
			String apiUrl = "https://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage=" + currentPage
					+ "&countPerPage=" + countPerPage + "&keyword=" + URLEncoder.encode(keyword, "UTF-8") + "&confmKey="
					+ confmKey + "&resultType=" + resultType;
			URL url = new URL(apiUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			sb = new StringBuffer();
			String tempStr = null;
			while ((tempStr = br.readLine()) != null) {
				sb.append(tempStr); // 응답결과 JSON 저장
				log.debug(CF.PSG + "LoginRestController.getAddr.tempStr :" + tempStr + CF.RESET);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	@GetMapping("/pwCheck")
	public String pwCheck(HttpSession session
						 ,@RequestParam(value="password") String password) {
		String memberId = (String) session.getAttribute("loginUser"); // 세션에 있는 loginUser 값 변수에 저장 
		log.debug(CF.PSG+"LgoinRestController.pwCheck.memberId :" + memberId + CF.RESET);
		int cnt = memberService.getFindSamePw(memberId, password); 
		log.debug(CF.PSG+"LoginRestController.pwCheck.cnt :" + cnt + CF.RESET); 
		
		if(cnt > 0) { // 같은 비밀번호를 한번이라도 사용한적이 있다면 
			return "false";
		}
		return password;
	}
	@GetMapping("/resignMemberCheck")
	public String resignMemberCheck(@RequestParam(value = "id") String id) {
		List<String> list = memberService.getResignedMemberId();
		for (String memberId : list) {
			if (memberId.equals(id)) {
				return "false";
			}
		}
		return id;
	}
}