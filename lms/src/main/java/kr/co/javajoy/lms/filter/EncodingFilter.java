package kr.co.javajoy.lms.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import kr.co.javajoy.lms.CF;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@WebFilter("/*")
public class EncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.debug(CF.PSG +"EncodingFilter.doFilter : 선 실행 "+CF.RESET);
		request.setCharacterEncoding("utf-8");
		log.debug(CF.PSG +"EncodingFilter.doFilter : utf-8 인코딩 "+CF.RESET);
		// 요청보다 먼저 실행 
		chain.doFilter(request, response);
		
		log.debug(CF.PSG+ "EncodingFilter.doFilter : 후 실행 "+CF.RESET);
	}
}
