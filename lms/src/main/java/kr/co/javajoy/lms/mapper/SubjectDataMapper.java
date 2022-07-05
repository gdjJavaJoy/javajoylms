package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.SubjectBoard;
import kr.co.javajoy.lms.vo.SubjectData;
import kr.co.javajoy.lms.vo.SubjectFile;

@Mapper
public interface SubjectDataMapper {
	// 강좌자료리스트 출력
	List<Map<String, Object>> selectSubjectDataListByPage(Map<String, Object> map);
	// 강좌자료리스트 총 수
	int selectTotalCount(String searchName);
	// 강좌자료 추가
	void insertSubjectData(SubjectData subjectData);
	// subjectBoard추가
	void insertSubjectBoard(SubjectBoard subjectBoard);
	// 첨부파일 추가
	void insertSubjectFile(SubjectFile subjectFile);
	// 강좌 자료 상세보기
	Map<String,Object> selectSubjectDataOne(int subjectDataNo);
	// 강자 좌료 파일
	List<SubjectFile> selectSubjectDataFile(int subjectDataNo);
	// file 개수 구하기
	int selectDataFileCount(int subjectDataNo);
}
