package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Language;
import kr.co.javajoy.lms.vo.Subject;
import kr.co.javajoy.lms.vo.SubjectVideo;

@Mapper
public interface SubjectMapper {
	// 강좌 입력
	int insertSubject(Subject subject);
	// 강사 리스트
	List<String> selectTeacherId();
	// 강좌 리스트(운영자 용) 출력
	List<Subject> selectSubjectByPage(Map<String, Object> map);
	// 페이징 없는 리스트 출력 
	List<Subject> selectSubjectList();
	// 강좌 총 수
	int selectTotalCount();
	// 강좌 상세보기s
	Subject selectSubjectOne(int subjectNo);
	// 강좌 수정
	int updateSubject(Subject subject);
	// 강좌 삭제
	int deleteSubject(int subjectNo);
	
	// 강의 영상 하나만 뽑기
	SubjectVideo selectSubjectVideoOne(int subjectVideoNo);
	
	// 강의 영상 리스트
	List<SubjectVideo> selectSubjectVideoList(int subjectNo);
	
	// 강의 영상 입력
	int insertSubjectVideo(SubjectVideo subjectVideo);
	
	// 강의 영상 수정
	void updateSubjectVideo(SubjectVideo subjectVideo);
	
	// 강의 영상
	void deleteSubjectVideoOne(int subjectVideoNo);
	
	// 만족도 검사
	int checkSurveyCnt(String memberId);
	
	// 특정 강좌의 학생 리스트
	List<Map<String,Object>> selectSubjectStudentList(Map<String,Object> map);
	
	// 강좌 학생 삭제
	void deleteSubjectStudent(Map<String,Object> map);
	
	// 언어 리스트 
	List<Language> selectLanguageList();
	
	// 언어 추가
	public void insertLanguage(Language language);
	
	// 언어 One 정보 출력
	public Language selectlanguageOne(int languageNo);
	
	// 언어 수정
	public void updateLanguageOne(Language language);
	
	// 언어 삭제
	public void deleteLanguageOne(int languageNo);
	
}
