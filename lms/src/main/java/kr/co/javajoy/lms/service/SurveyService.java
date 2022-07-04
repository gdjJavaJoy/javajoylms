package kr.co.javajoy.lms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.SurveyMapper;
import kr.co.javajoy.lms.vo.Survey;
import kr.co.javajoy.lms.vo.SurveyResult;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class SurveyService {
	@Autowired SurveyMapper surveyMapper;

	public List<Survey> getSurvey(int subjectNo){
		log.debug(CF.YHJ + "SurveyService.getSurvey.subjectNo : " + subjectNo + CF.RESET); // 디버깅
		
		List<Survey> list = surveyMapper.selectSurvey(subjectNo);
		
		
		log.debug(CF.YHJ + "SurveyService.getSurvey.list : " + list + CF.RESET); // 디버깅
		
		return list;
	}
	
	public void addSurveyResult(SurveyResult surveyResult) {
		// 디버깅
		log.debug(CF.YHJ + "SurveyService.addSurveyResult.surveyResult : " + surveyResult + CF.RESET); 
		
		for(int i = 0 ; i<surveyResult.getSurveyResultList().size(); i++ ) {
			log.debug(CF.YHJ + "SurveyService.addSurveyResult.surveyResult[" + i +"] : "+ surveyResult.getSurveyResultList().get(i) + CF.RESET); 
		}
		
		for(int i = 0 ; i<surveyResult.getSurveyResultList().size(); i++ ) {
			surveyResult.setSurveyNo(surveyResult.getSurveyResultList().get(i).getSurveyNo());
			surveyResult.setMemberId(surveyResult.getSurveyResultList().get(i).getMemberId());
			surveyResult.setResult(surveyResult.getSurveyResultList().get(i).getResult());
			surveyMapper.insertSurveyResult(surveyResult);
		}
	}
	
	public void addSurveyQuestion(Survey survey) {
		log.debug(CF.YHJ + "SurveyService.addSurveyQuestion.survey : " + survey + CF.RESET); // 디버깅
		surveyMapper.insertSurveyQuestion(survey);
	}
	
	public List<Map<String,Object>> getStudentSurveyResult(String memberId){
		log.debug(CF.YHJ + "SurveyService.getStudentSurveyResult.memberId : " + memberId + CF.RESET); // 디버깅
		
		List<Map<String,Object>> list = surveyMapper.selectStudentSurveyResult(memberId);
		log.debug(CF.YHJ + "SurveyService.getStudentSurveyResult.memberId : " + list + CF.RESET); // 디버깅
		
		return list;
	}
}
