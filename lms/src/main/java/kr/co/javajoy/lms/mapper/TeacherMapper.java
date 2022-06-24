package kr.co.javajoy.lms.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {
	public Map<String,Object> teacherOne(String memberId);
}
