package kr.co.javajoy.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	 public List<String> selectMemberId();
	 
	 public String selectMemberLevel(String memberId);
		 
}
