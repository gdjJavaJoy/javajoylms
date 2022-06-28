package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Survey;
import kr.co.javajoy.lms.vo.SurveyResult;

@Mapper
public interface SurveyMapper {
	// 만족도 문제 나오기
	public List<Survey> getSurvey(int subjectNo);
	// 만족도 조사 결과 넣기
	public void insertSurveyResult(SurveyResult surveyResult);
}
