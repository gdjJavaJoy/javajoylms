package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.SubjectBoard;
import kr.co.javajoy.lms.vo.SubjectFile;
import kr.co.javajoy.lms.vo.SubjectNotice;
@Mapper
public interface SubjectNoticeMapper {
	// 강좌공지사항 리스트 출력
	List<Map<String,Object>> getSubjectNoticeList(Map<String, Object> map);
	// 강좌공지사항 총 수
	int selectTotalCount(int subjectNo);
	
	// 강좌를 확인할 SubjectNo를 subject_board에 전달
	int insertSubjectNoticeBoardNo(SubjectBoard subjectBoard);
	// 글 입력
	int insertSubjectNotice(SubjectNotice subjectNotice);
	// 파일 입력
	void insertSubjectNoticeFile(SubjectFile subjectFile);
	
	// 상세보기
	List<Map<String, Object>> subjectNoticeOne(int subjectNo);
	// 파일 상세보기
	List<Map<String, Object>> subjectNoticeFileOne(int studentFileNo);
	// 파일 개수
	int subjectNoticeFileCount();
}
