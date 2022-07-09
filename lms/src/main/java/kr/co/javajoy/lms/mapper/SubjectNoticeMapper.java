package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.SubjectBoard;
import kr.co.javajoy.lms.vo.SubjectBoardInsertForm;
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
	// 수정하기(Get)
	List<Map<String, Object>> subjectNoticeOne(int subjectBoardNo);
	// 파일 상세보기
	List<Map<String, Object>> subjectNoticeFileOne(int subjectBoardNo);
	// 파일 개수
	int subjectNoticeFileCount();
	// 파일 삭제 전 이름으로 조회
	List<String> selectsubjectFileNameByBoardNo(int subjectBoardNo);
	// 파일 삭제
	int deleteSubjectNoticeFile(int subjectBoardNo);
	// 게시글 삭제
	int deleteSubjectNotice(int subjectBoardNo);
	// subject_board_no 삭제
	int deleteSubjectNoticeBoard(int subjectBoardNo);
	
	// 수정하기(Post)
	int updateSubjectNotice(SubjectBoardInsertForm subjectBoardInsertForm);
}


