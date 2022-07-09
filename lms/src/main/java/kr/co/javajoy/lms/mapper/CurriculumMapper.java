package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Book;
import kr.co.javajoy.lms.vo.Curriculum;
import kr.co.javajoy.lms.vo.CurriculumBook;
import kr.co.javajoy.lms.vo.Language;

@Mapper
public interface CurriculumMapper {
	// 1) 커리큘럼 리스트 출력
	
	// 커리큘럼 리스트 출력
	List<Map<String, Object>> selectCurriculumList(Map<String, Object> map);
	// 강사 이름, 아이디 리스트
	List<Map<String, Object>> selectTeacherName();
	// 프로그래밍 언어, 넘버 리스트
	List<Map<String, Object>> selectLanguageName();
	// 도서 이름, 넘버 리스트
	List<Map<String, Object>> selectBookName();
	// 커리큘럼 총 수
	int selectTotalCount();
	
	// 2) 커리큘럼 추가
	
	// 커리큘럼 추가
	int insertCurriculum(Curriculum curriculum);
	// 프로그래밍 언어 추가
	int insertLanguage(Language language);
	// 교육 도서 추가
	int insertBook(Book book);
	// 커리큘럼에 교육 도서 정보 추가 
	int insertBookByCurriculum(CurriculumBook curriculumBook);
	
	// 3) 커리큘럼 상세보기
	
	// 커리큘럼 내용 상세보기
	List<Map<String, Object>> selectCurriculumOne(int curriculumNo);
	// 커리큘럼 하나당 도서목록 리스트
	List<Book> selectBookListByCurriculumNo(int curriculumNo);
	
	// 4) 커리큘럼 수정
	
	// 커리큘럼 수정
	int updateCurriculum(Curriculum curriculum);
	
	// 5) 커리큘럼 삭제
	
	// 커리큘럼 도서 목록 삭제
	int deleteBookNoAndCurriculumNo(int curriculumNo);
	// 커리큘럼 전체 삭제
	int deleteCurriculum(int curriculumNo);
	
	// -----------------------------------------------------------------
	// 언어 관련  
	List<Map<String,Object>> languageRateByCurriculum();
	
}















