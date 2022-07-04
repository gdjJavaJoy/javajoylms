package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Student;
import kr.co.javajoy.lms.vo.Survey;
import kr.co.javajoy.lms.vo.SurveyResult;

@Mapper
public interface SurveyMapper {
	// 만족도 문제 나오기
	public List<Survey> selectSurvey(int subjectNo);
	// 만족도 조사 결과 넣기
	public void insertSurveyResult(SurveyResult surveyResult);
	// 만족도 질문 추가
	public void insertSurveyQuestion(Survey survey);
	// 특정 학생의 만족도 질문 결과
	public List<Map<String,Object>> selectStudentSurveyResult(String memberId);
	
	public List<Student> checkStudentSurveyList();
}
