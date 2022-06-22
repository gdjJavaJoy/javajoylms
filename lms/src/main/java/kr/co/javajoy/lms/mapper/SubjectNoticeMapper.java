package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.SubjectNotice;
@Mapper
public interface SubjectNoticeMapper {
	// 강좌공지사항 리스트 출력
	List<SubjectNotice> getSubjectNoticeList(Map<String, Object> map);
	// 강좌공지사항 총 수
	int selectTotalCount();
}
