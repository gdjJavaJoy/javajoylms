package kr.co.javajoy.lms.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	// 로그인
	public String loginMember(Map<String,Object> map);
}
