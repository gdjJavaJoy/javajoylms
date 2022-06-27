package kr.co.javajoy.lms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.SurveyMapper;
import kr.co.javajoy.lms.vo.Survey;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class SurveyService {
	@Autowired SurveyMapper surveyMapper;

	public List<Survey> getSurvey(int subjectNo){
		log.debug(CF.YHJ + "SurveyService.getSurvey.subjectNo : " + subjectNo + CF.RESET); // 디버깅
		
		List<Survey> list = surveyMapper.getSurvey(subjectNo);
		
		
		log.debug(CF.YHJ + "SurveyService.getSurvey.list : " + list + CF.RESET); // 디버깅
		
		return list;
	}
	
	public void insertSurveyResult(Map<String,Object> map) {
		log.debug(CF.YHJ + "SurveyService.getSurvey.insertSurveyResult : " + map + CF.RESET); // 디버깅
		
		surveyMapper.insertSurveyResult(map);
	}
}
