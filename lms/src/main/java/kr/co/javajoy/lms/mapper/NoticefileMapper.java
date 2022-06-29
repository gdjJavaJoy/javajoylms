package kr.co.javajoy.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Boardfile;

@Mapper
public interface NoticefileMapper {
	// 파일 조회
	List<Boardfile> selectNoticefileList(int boardNo);
	// 파일 생성
	int insertNoticefile(Boardfile boardfile);
	// 파일이름 조회(삭제 전) boardfileNo를 이용해 boardfileName을 조회
	List<String> selectNoticefileNameListByBoardfileNo(int boardfileNo);
	// 파일 삭제
	int deleteNoticefileOne(int boardfileNo);
	// 파일이름 조회(삭제 전) boardNo를 이용해 boardfileName을 조회
	List<String> selectNoticefileNameList(int boardNo);
	// 파일 삭제
	int deleteNoticefileList(int boardNo);
	// boardfile 갯수 유무확인
	int selectBoardfileCnt(int boardNo);
}
