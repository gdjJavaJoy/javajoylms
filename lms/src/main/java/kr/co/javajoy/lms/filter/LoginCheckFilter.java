package kr.co.javajoy.lms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.javajoy.lms.CF;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@WebFilter("/loginCheck")

public class LoginCheckFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 선
		//HttpServletrequest에서만 session 있다 
		if(request instanceof HttpServletRequest) {
		log.debug(CF.PSG +"AdminLoginFilter.브라우저를 통한 요청" + CF.RESET);
		HttpSession session = ((HttpServletRequest)request).getSession(); // 세션정보 불러오기
		if(session.getAttribute("loginUser") == null) { // session 에있는 loginUser 값이 Null 이면 
			((HttpServletResponse)response).sendRedirect( 
					((HttpServletRequest)request).getContextPath()+"/login"); // 로그인 페이지로 이동 
			}
		} else {
		 log.debug(CF.PSG +"브라우저가 아닌 다른 요청"+CF.RESET);
		}
		chain.doFilter(request, response); // 원 요청 처리 
		
		// 후
	}
}
