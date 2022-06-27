package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Survey;

@Mapper
public interface SurveyMapper {
	public List<Survey> getSurvey(int subjectNo);
}
