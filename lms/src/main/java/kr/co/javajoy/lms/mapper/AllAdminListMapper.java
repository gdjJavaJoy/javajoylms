package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Admin;

@Mapper
public interface AllAdminListMapper {
	// 관리자 출력
	List<Admin> AllAdminList(Map<String, Object> map);
	// 관리자 삭제
	// int deleteAdminId(DeleteMemberId deleteMemberId);
	int deleteAdmin(Admin Admin);
	// 관리자 수
	int selectTotalCount();
}