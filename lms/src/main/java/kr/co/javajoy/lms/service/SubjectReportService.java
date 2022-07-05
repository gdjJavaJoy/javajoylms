package kr.co.javajoy.lms.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.mapper.SubjectReportMapper;
import kr.co.javajoy.lms.vo.InsertSubjectReportForm;
import kr.co.javajoy.lms.vo.SubjectBoard;
import kr.co.javajoy.lms.vo.SubjectBoardInsertForm;
import kr.co.javajoy.lms.vo.SubjectFile;
import kr.co.javajoy.lms.vo.SubjectReport;
import kr.co.javajoy.lms.vo.SubjectReportComment;
import kr.co.javajoy.lms.vo.SubjectReportForm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class SubjectReportService {
	@Autowired SubjectReportMapper subjectReportMapper;
	
	// ------------------------ 1) 과제 게시판 글 리스트 출력 <SELECT> ------------------------ 
	
	// 1-1) 과제 게시판 글 리스트 출력
	public Map<String, Object> getSubjectReportListByPage(int currentPage, int rowPerPage, int subjectNo, String rSubjectReportTitle) {
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.currentPage" + currentPage);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.rowPerPage" + rowPerPage);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.subjectNo" + subjectNo);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.rSubjectReportTitle" + rSubjectReportTitle);
		// 페이징
		int startRow = (currentPage - 1) * rowPerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRow", startRow);
		map.put("rowPerPage", rowPerPage);
		map.put("subjectNo", subjectNo);
		map.put("rSubjectReportTitle", rSubjectReportTitle);
		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.startRow" + startRow);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.rowPerPage" + rowPerPage);

		// 1) 컨트롤러에서 넘오온 변수값 가공 후 맵퍼 호출
		List<Map<String, Object>> list = subjectReportMapper.selectSubjectReportListByPage(map);
		// 2) 맵퍼에서 반환된 값을 가공 후, 컨트롤러에 변환
		int totalCount = subjectReportMapper.selectTotalCount(); // 과제 게시판 글 총 수
		int lastPage = (int) (Math.ceil((double) totalCount / (double) rowPerPage));

		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.list------------" + list);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.totalCount" + totalCount);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.lastPage" + lastPage);

		// 리스트 결과값 해쉬 맵에 저장
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("subjectNo", subjectNo);
		returnMap.put("rSubjectReportTitle)", rSubjectReportTitle);
		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.returnMap.list.size()" + list.size());
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.returnMap.lastPage" + lastPage);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.returnMap.subjectNo" + subjectNo);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.returnMap.rSubjectReportTitle" + rSubjectReportTitle);

		return returnMap;
	}
	
	// ------------------------ 2) 과제 게시판 글 입력 <INSERT> ------------------------ 
	
	// 2-1) 과제 게시판 글 입력 + 파일 입력
	public void addSubjectReport(SubjectReportForm subjectReportForm, String path) {
		log.debug(CF.PBJ + "NoticeService.addSubjectReport.path : " + path);
		log.debug(CF.PBJ + "NoticeService.addSubjectReport.subjectBoardInsertForm: " + subjectReportForm);
		//
		SubjectBoard subjectBoard = new SubjectBoard();
		subjectBoard.setSubjectNo(subjectReportForm.getSubjectNo());
		
		log.debug(CF.PBJ + "NoticeService.addSubjectReport.subjectBoard: " + subjectBoard);
		subjectReportMapper.insertSubjectBoard(subjectBoard);
		// InsertSubjectReportFormMapper
		SubjectReport subjectReport = new SubjectReport();
		subjectReport.setSubjectReportNo(subjectBoard.getSubjectBoardNo());
		subjectReport.setMemberId(subjectReportForm.getMemberId());
		subjectReport.setSubjectReportTitle(subjectReportForm.getSubjectReportTitle());
		subjectReport.setSubjectReportContent(subjectReportForm.getSubjectReportContent());
		subjectReport.setSubjectReportPeriod(subjectReportForm.getSubjectReportPeriod());
		// subjectReport.getSubjectBoardNo() --> 0
		log.debug(CF.PBJ + "NoticeService.addSubjectReport.subjectReport: " + subjectReport);
		subjectReportMapper.insertSubjectReport(subjectReport);
		// insert 성공시, 기본키 값이 출력됨
		log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.subjectBoardNo : " + subjectBoard.getSubjectBoardNo());
		log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.subjectReportNo : " + subjectReport.getSubjectReportNo());

		// SubjectReportMapper -> file insert
		if (subjectReportForm.getSubjectReportFileList() != null && subjectReportForm.getSubjectReportFileList().get(0).getSize() > 0) {
			log.debug(CF.PBJ + "SubjectReportService.addSubjectReport : 첨부된 파일이 있습니다.");
			for (MultipartFile mf : subjectReportForm.getSubjectReportFileList()) {
				SubjectFile subjectFile = new SubjectFile();

				String originName = mf.getOriginalFilename();
				// originName에서 마지막 .문자열 위치
				String ext = originName.substring(originName.lastIndexOf("."));
				// 파일을 저장할 때 사용할 증븍되지않는 새로운 이름 (UUID API사용)
				String filename = UUID.randomUUID().toString();
				filename = filename + ext;
				log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.originName : " + originName);
				log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.filename : " + filename);
				// subject_file 데이터 가공
				subjectFile.setSubjectFileBoardNo(subjectReport.getSubjectReportNo());
				subjectFile.setSubjectFileName(filename);
				subjectFile.setSubjectFileOriginalName(mf.getOriginalFilename());
				subjectFile.setSubjectFileType(mf.getContentType());
				subjectFile.setSubjectFileSize(mf.getSize());
				log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.SubjectFile : " + subjectFile);
				// 데이터베이스에 인서트
				subjectReportMapper.insertSubjectFile(subjectFile);
				// try + catch
				try {
					mf.transferTo(new File(path + filename));
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}
	}

	// ------------------------ 3) 과제 게시판 글 상세보기 <SELECT ONE>------------------------ 
	
	// 3-1) 과제 게시판 글 상세보기 + 파일 이름 리스트 출력 + 댓글 리스트 출력
	public Map<String, Object> getSubjectReportAndFileNameListAndCommentList (Map<String, Object> map) {
		// 댓글 페이징 데이터
		int commentCurrentPage = (int)map.get("commentCurrentPage");
		int commentRowPerPage = (int)map.get("commentRowPerPage");
		int startRow = (commentCurrentPage - 1) * commentRowPerPage;
		int subjectBoardNo = (int)map.get("subjectBoardNo");
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("startRow", startRow);
		paramMap.put("commentRowPerPage", commentRowPerPage);
		paramMap.put("subjectBoardNo", subjectBoardNo);
	
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameListAndCommentList.subjectBoardNo :" + subjectBoardNo);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameListAndCommentList.startRow :" + startRow);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameListAndCommentList.commentRowPerPage:" + commentRowPerPage);
		
		// 선택된 과게 게시판 글 상세보기
		List<Map<String, Object>> subjectReport = subjectReportMapper.selectSubjectReportOne(subjectBoardNo);
		// 선택된 과제 게시판 글의 파일 리스트 
		List<SubjectFile> subjectFileList = subjectReportMapper.selectSubjectReportFileList(subjectBoardNo);
		// 선택된 과제 게시판 댓글 리스트
		List<SubjectReportComment> commentList = subjectReportMapper.selectCommentListByPage(paramMap);
		// 상세보기 + 파일 리스트 저장
		Map<String, Object> returnMap = new HashMap<>();
		
		// 댓글 페이징 코드
		int commentTotalCount = subjectReportMapper.selectCommentTotalCountBySubjectReport(subjectBoardNo);
		int commentLastPage = commentTotalCount / (int)(map.get("commentRowPerPage"));
		if(commentTotalCount % (int)(map.get("commentRowPerPage")) != 0) {
			commentLastPage +=1;
		}
		// 상세보기 값 + 파일 리스트 + 댓글 리스트 데이터 저장
		returnMap.put("subjectReport", subjectReport);
		returnMap.put("subjectFileList", subjectFileList);
		returnMap.put("commentList", commentList);
		returnMap.put("commentRowPerPage", commentRowPerPage);
		returnMap.put("commentCurrentPage", commentCurrentPage);
		returnMap.put("commentLastPage", commentLastPage);
		returnMap.put("commentTotalCount", commentTotalCount);
		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameListAndCommentList.returnMap.subjectReport :" + subjectReport);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameListAndCommentList.returnMap.subjectFileList :" + subjectFileList);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameListAndCommentList.returnMap.commentList :" + commentList);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameListAndCommentList.returnMap.commentRowPerPage :" + commentRowPerPage);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameListAndCommentList.returnMap.commentCurrentPage :" + commentCurrentPage);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameListAndCommentList.returnMap.commentLastPage :" + commentLastPage);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameListAndCommentList.returnMap.commentTotalCount :" + commentTotalCount);
		
		return returnMap;
	}
	
	// ------------------------ 4) 과제 게시판 수정 <UPDATE>------------------------ 
	
	// 4-1) 과제 게시판 수정 Form 
	public Map<String, Object> getSubjectReportOne(int subjectBoardNo) {
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportOne(Form).subjectBoardNo :" + subjectBoardNo);
		// 과제 게시판 글 불러오기
		List<Map<String, Object>> subjectReport = subjectReportMapper.selectSubjectReportOne(subjectBoardNo);
		// 과제 게시판 파일 리스트 불러오기
		List<SubjectFile> subjectFile = subjectReportMapper.selectSubjectReportFileList(subjectBoardNo);
		// 해시 맵으로 데이터 보냄
		Map<String, Object> map = new HashMap<>();
		map.put("subjectReport", subjectReport);
		map.put("subjectFile", subjectFile);
		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportOne(Form).subjectReport.map.put :" + subjectReport);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportOne(Form).subjectFile.map.put :" + subjectFile);
		
		return map;
	}
	
	// 4-2) 과제 게시판 수정 Action
	public void modifySubjectReport(SubjectReportForm subjectReportForm, String path) {
		log.debug(CF.PBJ + "SubjectReportService.modifySubjectReport(Action).subjectReportForm: " + subjectReportForm);
		log.debug(CF.PBJ + "SubjectReportService.modifySubjectReport(Action).path: " + path);
		
		InsertSubjectReportForm insertSubjectReportForm = new InsertSubjectReportForm();
		insertSubjectReportForm.setSubjectBoardNo(subjectReportForm.getSubjectBoardNo());
		insertSubjectReportForm.setSubjectNo(subjectReportForm.getSubjectNo());
		insertSubjectReportForm.setMemberId(subjectReportForm.getMemberId());
		insertSubjectReportForm.setSubjectReportTitle(subjectReportForm.getSubjectReportTitle());
		insertSubjectReportForm.setSubjectReportContent(subjectReportForm.getSubjectReportContent());
		insertSubjectReportForm.setSubjectReportPeriod(subjectReportForm.getSubjectReportPeriod());		
		
		int row = subjectReportMapper.updateSubjectReport(insertSubjectReportForm);
		log.debug(CF.PBJ + "SubjectReportService.modifySubjectReport(Action).row : " + row);
		log.debug(CF.PBJ + "SubjectReportService.modifySubjectReport(Action).subjectReport :" + insertSubjectReportForm);
		
		if (subjectReportForm.getSubjectReportFileList() != null && subjectReportForm.getSubjectReportFileList().get(0).getSize() > 0) {
			log.debug(CF.PBJ + "SubjectReportService.modifySubjectReport(Action) : 첨부된 파일이 있습니다.");
			for (MultipartFile mf : subjectReportForm.getSubjectReportFileList()) {
				SubjectFile subjectFile = new SubjectFile();

				String originName = mf.getOriginalFilename();
				// originName에서 마지막 .문자열 위치
				String ext = originName.substring(originName.lastIndexOf("."));
				// 파일을 저장할 때 사용할 증븍되지않는 새로운 이름 (UUID API사용)
				String filename = UUID.randomUUID().toString();
				filename = filename + ext;
				log.debug(CF.PBJ + "SubjectReportService.modifySubjectReport(Action).originName : " + originName);
				log.debug(CF.PBJ + "SubjectReportService.modifySubjectReport(Action).filename : " + filename);
				// subject_file 데이터 가공
				subjectFile.setSubjectFileBoardNo(insertSubjectReportForm.getSubjectBoardNo());
				subjectFile.setSubjectFileName(filename);
				subjectFile.setSubjectFileOriginalName(mf.getOriginalFilename());
				subjectFile.setSubjectFileType(mf.getContentType());
				subjectFile.setSubjectFileSize(mf.getSize());
				log.debug(CF.PBJ + "SubjectReportService.modifySubjectReport(Action).SubjectFile : " + subjectFile);
				// 데이터베이스에 인서트
				subjectReportMapper.insertSubjectFile(subjectFile);
				// try + catch
				try {
					mf.transferTo(new File(path + filename));
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}
	}
	
	// 4-3) 과제 게시판 수정 할 때, 선택 파일 삭제 처리
	public void removeSubjectFile(int subjectFileNo, String path) {
		List<String> subjectFileList = subjectReportMapper.selectSubjectFileNameListBySubjectFileNo(subjectFileNo);
		log.debug(CF.PBJ + "SubjectReportService.removeSubjectFile.subjectFileList : " + subjectFileList);
		for (String sfl : subjectFileList) {
			File f = new File(path + sfl);
			if (f.exists())
				f.delete();
		}
		subjectReportMapper.deleteSubjectFile(subjectFileNo);
	}
	
	// ------------------------ 5) 과제 게시판 댓글 ------------------------ 
	
	// 5-1) 과제 게시판 댓글 입력
	public int addSubjectReportComment(SubjectReportComment subjectReportComment) {
		log.debug(CF.PBJ + "SubjectReportService.addSubjectReportComment.subjectReportComment: " + subjectReportComment);
		return subjectReportMapper.insertSubjectReportComment(subjectReportComment);
	}
	
	// 5-2) 댓글 삭제
	public void deleteCommentByCommentNo(int subjectReportCommentNo) {
		log.debug(CF.PBJ + "SubjectReportService.deleteCommentByCommentNo.subjectReportCommentNo: " + subjectReportCommentNo);
		subjectReportMapper.deleteCommentByCommentNo(subjectReportCommentNo);
	}
	
	// ------------------------ 6) 과제 게시판 글 삭제 <DELETE>------------------------ 
	
	// 6-1) 과제 게시판 삭제
	public int removeSubjectReport(int subjectBoardNo, String path) {
		int row = 0;
		log.debug(CF.PBJ + "SubjectReportService.removeSubjectReport.subjectBoardNo : " + subjectBoardNo);
		log.debug(CF.PBJ + "SubjectReportService.removeSubjectReport.path : " + path);
		
		List<String> subjectReportFileList = subjectReportMapper.selectSubjectReportFileNameList(subjectBoardNo);
		log.debug(CF.PBJ + "SubjectReportService.removeSubjectReport.subjectReportFileList : " + subjectReportFileList);
		for (String filename : subjectReportFileList) {
			File f = new File(path + filename);
			if(f.exists()) {
				f.delete();
			}
		}
		
		subjectReportMapper.deleteAllSubjectReportCommentBySubjectBoardNo(subjectBoardNo);
		subjectReportMapper.deleteAllSubjectFileBySubjectBoardNo(subjectBoardNo);
		subjectReportMapper.deleteSubjectReportBySubjectBoardNo(subjectBoardNo);
		row = subjectReportMapper.deleteSubjectBoardBySubjectBoardNo(subjectBoardNo);
		log.debug(CF.PBJ + "SubjectReportService.removeSubjectReport.row : " + row);
		
		return row;
	}
}

















































































