package kr.co.javajoy.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.CurriculumMapper;
import kr.co.javajoy.lms.vo.Book;
import kr.co.javajoy.lms.vo.Curriculum;
import kr.co.javajoy.lms.vo.CurriculumBook;
import kr.co.javajoy.lms.vo.CurriculumForm;
import kr.co.javajoy.lms.vo.Language;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CurriculumService {
	@Autowired CurriculumMapper curriculumMapper;
	
	// ---------------------- 1) 커리쿨럼 리스트 출력 <SELECT> ----------------------
	
	// 1-1) 커리큘럼 리스트 출력
	public Map<String, Object> selectCurriculumList(int currentPage, int rowPerPage, int subjectNo, String subjectName) {
		// 리스트 출력 페이징
		int startRow = (currentPage - 1) * rowPerPage;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		map.put("subjectNo", subjectNo);
		map.put("subjectName", subjectName);
		
		log.debug(CF.PBJ + "CurriculumListController.selectCurriculumList.subjectNo : " + subjectNo);
		log.debug(CF.PBJ + "CurriculumListController.selectCurriculumList.subjectName : " + subjectName);
		
		// Mapper에서 반환 된 값 가공
		List<Map<String, Object>> curriculumList = curriculumMapper.selectCurriculumList(map);
		int totalCount = curriculumMapper.selectTotalCount();
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("curriculumList", curriculumList);
		returnMap.put("lastPage", lastPage);
		returnMap.put("subjectNo", subjectNo);
		returnMap.put("subjectName", subjectName);

		log.debug(CF.PBJ + "CurriculumListController.selectCurriculumList.lastPage : " + lastPage );
		log.debug(CF.PBJ + "CurriculumListController.selectCurriculumList.curriculumList : " + curriculumList);
		log.debug(CF.PBJ + "CurriculumListController.selectCurriculumList.subjectNo: " + subjectNo);
		log.debug(CF.PBJ + "CurriculumListController.selectCurriculumList.subjectName: " + subjectName);
		
		return returnMap;
	}
	
	
	// ---------------------- 2) 커리큘럼 입력(운영자용) <INSERT> ----------------------
	
	// 2-1) 강사 리스트
	public List<Map<String, Object>> selectTeacherName() {
		return curriculumMapper.selectTeacherName();
	}
	// 2-2) 프로그래밍 언어 리스트
	public List<Map<String, Object>> selectLanguageName() {
		return curriculumMapper.selectLanguageName();
	}	
	// 2-3) 교육 도서 리스트
	public List<Map<String, Object>> selectBookName() {
		return curriculumMapper.selectBookName();
	}	
	
	// 2-4) 커리큘럼 추가
	public void addCurriculum(CurriculumForm curriculumForm) {
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum.curriculumFrom: " + curriculumForm);
		// 커리큘럼 데이터 입력
		Curriculum curriculum = new Curriculum();
		curriculum.setSubjectNo(curriculumForm.getSubjectNo());
		curriculum.setMemberId(curriculumForm.getMemberId());
		curriculum.setLanguageNo(curriculumForm.getLanguageNo());
		curriculum.setCurriculumTitle(curriculumForm.getCurriculumTitle());
		curriculum.setCurriculumContent(curriculumForm.getCurriculumContent());
		curriculum.setStartDay(curriculumForm.getStartDay());
		curriculum.setEndDay(curriculumForm.getEndDay());
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum.curriculum : " + curriculum);
		curriculumMapper.insertCurriculum(curriculum);
		// 커리큘럼 데이터 입력 후, 커리큘럼에 따른 도서 정보 입력
		CurriculumBook curriculumBook = new CurriculumBook();
		curriculumBook.setCurriculumNo(curriculum.getCurriculumNo());
		curriculumBook.setBookNo(curriculumForm.getBookNo());
		log.debug(CF.PBJ + "CurriculumListController.addCurriculum.curriculumBook : " + curriculumBook);
		curriculumMapper.insertBookByCurriculum(curriculumBook);
	}

	// ------------------------ 3) 커리큘럼 상세보기 <SELECT ONE>------------------------ 
	
	public Map<String, Object> getCurriculumOneAndBookList(Map<String, Object> map) {
		int curriculumNo = (int)map.get("curriculumNo");
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumOneAndBookList.curriculumNo : " + curriculumNo);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("curriculumNo", curriculumNo);
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumOneAndBookList.paramMap.put(curriculumNo) : " + curriculumNo);
		// 커리큘럼 상세보기
		List<Map<String, Object>> curriculum = curriculumMapper.selectCurriculumOne(curriculumNo);
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumOneAndBookList.curriculumNo : " + curriculumNo);
		// 커리큘럼의 교육 도서 리스트 출력
		List<Book> bookList = curriculumMapper.selectBookListByCurriculumNo(curriculumNo);
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumOneAndBookList.bookList : " + bookList);
		// 커리큘럼 상세보기 + 교육 도서 리스트 저장
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("curriculum", curriculum);
		returnMap.put("bookList", bookList);
		
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumOneAndBookList.curriculum : " + curriculum);
		log.debug(CF.PBJ + "CurriculumListController.getCurriculumOneAndBookList.bookList : " + bookList);
		
		return returnMap;
	}
	
	// ------------------------ 4) 커리큘럼 수정 <UPDATE>------------------------ 
	
	// 4-1) 커리큘럼 수정 Form
	public Map<String, Object> modifyCurriculumOneAndBookList(Map<String, Object> map) {
		int curriculumNo = (int)map.get("curriculumNo");
		log.debug(CF.PBJ + "CurriculumListController.modifyCurriculumOneAndBookList.curriculumNo : " + curriculumNo);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("curriculumNo", curriculumNo);
		log.debug(CF.PBJ + "CurriculumListController.modifyCurriculumOneAndBookList.paramMap.put(curriculumNo) : " + curriculumNo);
		// 커리큘럼 상세보기
		List<Map<String, Object>> curriculum = curriculumMapper.selectCurriculumOne(curriculumNo);
		log.debug(CF.PBJ + "CurriculumListController.modifyCurriculumOneAndBookList.curriculumNo : " + curriculumNo);
		// 커리큘럼의 교육 도서 리스트 출력
		List<Book> modifyBookList = curriculumMapper.selectBookListByCurriculumNo(curriculumNo);
		log.debug(CF.PBJ + "CurriculumListController.modifyCurriculumOneAndBookList.modifyBookList : " + modifyBookList);
		// 커리큘럼 상세보기 + 교육 도서 리스트 저장
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("curriculum", curriculum);
		returnMap.put("modifyBookList", modifyBookList);
		
		log.debug(CF.PBJ + "CurriculumListController.modifyCurriculumOneAndBookList.curriculum : " + curriculum);
		log.debug(CF.PBJ + "CurriculumListController.modifyCurriculumOneAndBookList.modifyBookList : " + modifyBookList);
		
		return returnMap;
	}
	
	// 4-2) 커리큘럼 수정 Action
		public void modifyCurriculum(CurriculumForm curriculumForm) {
		log.debug(CF.PBJ + "CurriculumListController.modifyCurriculum.curriculumFrom: " + curriculumForm);
		// 커리큘럼 데이터 입력
		Curriculum curriculum = new Curriculum();
		curriculum.setCurriculumNo(curriculumForm.getCurriculumNo());
		curriculum.setSubjectNo(curriculumForm.getSubjectNo());
		curriculum.setMemberId(curriculumForm.getMemberId());
		curriculum.setLanguageNo(curriculumForm.getLanguageNo());
		curriculum.setCurriculumTitle(curriculumForm.getCurriculumTitle());
		curriculum.setCurriculumContent(curriculumForm.getCurriculumContent());
		curriculum.setStartDay(curriculumForm.getStartDay());
		curriculum.setEndDay(curriculumForm.getEndDay());
		log.debug(CF.PBJ + "CurriculumListController.modifyCurriculum.curriculum : " + curriculum);
		curriculumMapper.updateCurriculum(curriculum);
	}
}
















































