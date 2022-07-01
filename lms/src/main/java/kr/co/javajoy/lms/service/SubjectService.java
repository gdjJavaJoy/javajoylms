package kr.co.javajoy.lms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.SubjectMapper;
import kr.co.javajoy.lms.vo.Student;
import kr.co.javajoy.lms.vo.Subject;
import kr.co.javajoy.lms.vo.SubjectVideo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SubjectService {
@Autowired SubjectMapper subjectMapper;

	// 강좌 입력
	public int addSubject(Subject subject) {
		return subjectMapper.insertSubject(subject);
	}
	
	// 강사 리스트
	public ArrayList<String> getTeacherId() {
		return subjectMapper.selectTeacherId();
	}
	
	// 강좌 리스트(운영자용) 출력
	public Map<String, Object> getSubjectByPage(int currentPage, int rowPerPage) {
		// 리스트 출력 페이징
		int startRow = (currentPage - 1) * rowPerPage;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		
		// Mapper에서 반환 된 값 가공
		List<Subject> list = subjectMapper.selectSubjectByPage(map);
		int totalCount = subjectMapper.selectTotalCount();
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		
		// 디버깅
		log.debug(CF.PBJ + "SubjectService.selecSubjectListByPage rowPerPage : " + rowPerPage);
		log.debug(CF.PBJ + "SubjectService.selecSubjectListByPage startRow : " + startRow);
		log.debug(CF.PBJ + "SubjectService.selecSubjectListByPage lastPage : " + lastPage );
		log.debug(CF.PBJ + "SubjectService.selecSubjectListByPage list.size() : " + list.size());
		
		return returnMap;
	}
	
	// 강좌 상세보기
	public Subject getSubjectOne(int subjectNo) {
		return subjectMapper.selectSubjectOne(subjectNo);
	}
	
	// 강좌 수정 Form
	public Subject modifySubject(int subjectNo) {
		return subjectMapper.selectSubjectOne(subjectNo);
	}

	// 강좌 수정 Action
	public int modifySubject(Subject subject) {
		return subjectMapper.updateSubject(subject);
	}
	
	// 강의 영상 List
	public List<SubjectVideo> getSubjectVideoList(int subjectNo){
		log.debug(CF.YHJ + "SubjectService.getSubjectVideoList.subjectNo : " +  subjectNo + CF.RESET); // 디버깅
		
		List<SubjectVideo> list = subjectMapper.selectSubjectVideoList(subjectNo);
		log.debug(CF.YHJ + "SubjectService.getSubjectVideoList.list : " +  list + CF.RESET); // 디버깅
		
		return list;
	}
	
	// 강의 영상 추가
	public int addSubjectVideo(SubjectVideo subjectVideo) {
		log.debug(CF.YHJ + "SubjectService.addSubjectVideo.subjectVideo : " + subjectVideo + CF.RESET); // 디버깅
		
		return subjectMapper.insertSubjectVideo(subjectVideo);
	}
	
	// 1개의 강의영상 정보 뽑기
	public SubjectVideo getSubjectVideoOne(int subjectVideoNo) {
		log.debug(CF.YHJ + "SubjectService.getSubjectVideoOne.subjectVideoNo : " + subjectVideoNo + CF.RESET); // 디버깅
		
		SubjectVideo subjectVideo = subjectMapper.selectSubjectVideoOne(subjectVideoNo);
		
		return subjectVideo;
	}
	
	// 강의 영상 1개 수정
	public void modifySubjectVideo(SubjectVideo subjectVideo) {
		log.debug(CF.YHJ + "SubjectService.modifySubjectVideo.subejctVideo : " + subjectVideo + CF.RESET); // 디버깅
		subjectMapper.updateSubjectVideo(subjectVideo);
	}
	
	// 강의 영상 1개 삭제
	public void removeSubjectVideo(int subjectVideoNo) {
		log.debug(CF.YHJ + "SubjectService.removeSubjectVideo.subjectVideoNo : " + subjectVideoNo + CF.RESET); // 디버깅
		
		subjectMapper.deleteSubjectVideoOne(subjectVideoNo);
	}
	
	// 만족도 조사 확인
	public int checkSurveyCnt(String memberId) {
		log.debug(CF.YHJ + "SubjectService.checkSurveyCnt.memberId : " + memberId + CF.RESET); // 디버깅
		return subjectMapper.checkSurveyCnt(memberId);
	}
	
	// 특정 강좌의 학생 리스트
	public List<Student> getSubjectStudentList(int subjectNo, String searchStudentName) {
		// 디버깅
		log.debug(CF.YHJ + "SubjectService.getSubjectStudentList.subjectNo : " + subjectNo + CF.RESET);
		log.debug(CF.YHJ + "SubjectService.getSubjectStudentList.searchStudentName : " + searchStudentName + CF.RESET);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subjectNo", subjectNo);
		map.put("searchStudentName", searchStudentName);
		
		List<Student> studentList = subjectMapper.selectSubjectStudentList(map);
		log.debug(CF.YHJ + "SubjectService.getSubjectStudentList.studentList : " + studentList + CF.RESET);
		
		return studentList;
	}
}




































