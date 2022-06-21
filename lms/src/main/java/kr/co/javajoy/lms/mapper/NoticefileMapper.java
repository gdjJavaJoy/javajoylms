package kr.co.javajoy.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Boardfile;

@Mapper
public interface NoticefileMapper {

	List<Boardfile> selectNoticefileList(int boardNo);
}
