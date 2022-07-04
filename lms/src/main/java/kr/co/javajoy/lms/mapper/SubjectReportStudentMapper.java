package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.StudentFile;
import kr.co.javajoy.lms.vo.SubjectReportStudent;

@Mapper
public interface SubjectReportStudentMapper {
	// 1) 학생 - 과제 게시판 글 리스트 출력
	
	// 학생 - 과제 게시판 글 총 개수
	int selectTotalCount();
	// 학생 - 과제 게시판 글 리스트 출력
	List<SubjectReportStudent> selectSubjectReportStudentListByPage(Map<String, Object> map);

	// 2) 학생 - 과제 게시판 글 상세보기 + 파일 이름 리스트 출력 + 댓글 리스트 출력
	
	// 학생 - 과제 게시판 글 상세보기
	List<SubjectReportStudent> selectSubjectReportStudentOne(int subjectReportStudentNo);
	// 학생 - 과제 게시판 글 상세보기의 파일 이름 리스트 출력
	List<String> selectSubjectReportStudentFileNameList(int subjectReportStudentNo);
	// 학생 - 과제 게시판 글 상세보기의 파일 리스트 출력
	List<StudentFile> selectSubjectReportStudentFileList(int subjectReportStudentNo);

	// 3) 학생 - 과제 게시판 글 입력 + 파일 입력

	// 학생 - 과제 게시판에 글 입력
	int insertSubjectReportStudent(SubjectReportStudent subjectReportStudent);
	// 학생 - 과제 게시판 글에 파일 입력
	int insertStudentFile(StudentFile studentFile);

	// 4) 학생 - 과제 게시판 글 수정 + 파일 변경(추가, 삭제)

	// subject_report_student 글 수정
	int updateSubjectReportStudent(SubjectReportStudent studentSubjectReport);
	// 학생 - 과제 게시판 파일 삭제 시, 파일 이름으로 된 리스트 출력
	List<String> selectStudentFileNameListByStudentFileNo(int studentfileNo);
	// 학생 - 과제 게시판의 student_file 부분 삭제
	int deleteStudentFile(int studentfileNo);

	// 5) 학생 - 과제 게시판 삭제

	// 학생 - 과제 게시판 파일 삭제
	int deleteAllStudentFileBySubjectReportStudentNo(int subjectReportStudentNo);
	// 학생 - 과제 게시판 삭제
	int deleteStudentReportStudentBySubjectReportStudentNo(int subjectReportStudentNo);
}
