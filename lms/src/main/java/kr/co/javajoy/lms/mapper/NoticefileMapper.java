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
}
