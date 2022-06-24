package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Career;
import kr.co.javajoy.lms.vo.Language;

@Mapper
public interface TeacherMapper {
	public Map<String,Object> teacherOne(String memberId);
	public List<Career> selectTeacherCareer(String memberId);
	public List<Map<String,Object>> selectTeacherLanguage(String memberId);
	int deleteCareer(int careerNo);
}
