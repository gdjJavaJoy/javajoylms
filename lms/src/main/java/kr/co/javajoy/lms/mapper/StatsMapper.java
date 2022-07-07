package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatsMapper {
	List<Map<String,Object>> subjectReportCountBySubjectNo();
}
