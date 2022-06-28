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
	
	// 1) 과제 게시판 글 리스트 출력
	public Map<String, Object> getSubjectReportListByPage(int currentPage, int rowPerPage, int subjectNo) {
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.currentPage" + currentPage);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.rowPerPage" + rowPerPage);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.subjectNo" + subjectNo);
		// 페이징
		int startRow = (currentPage - 1) * rowPerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRow", startRow);
		map.put("rowPerPage", rowPerPage);
		map.put("subjectNo", subjectNo);

		// 1) 컨트롤러에서 넘오온 변수값 가공 후 맵퍼 호출
		List<SubjectReport> list = subjectReportMapper.selectSubjectReportListByPage(map);
		// 2) 맵퍼에서 반환된 값을 가공 후, 컨트롤러에 변환
		int totalCount = subjectReportMapper.selectTotalCount(); // 과제 게시판 글 총 수
		int lastPage = (int) (Math.ceil((double) totalCount / (double) rowPerPage));

		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.list------------" + list);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.startRow" + startRow);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.totalCount" + totalCount);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.lastPage" + lastPage);

		// 리스트 결과값 해쉬 맵에 저장
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("subjectNo", subjectNo);

		return returnMap;
	}
	
	// 2) 과제 게시판 글 입력 + 파일 입력
	public void addSubjectReport(SubjectReportForm subjectReportForm, String path) {
		log.debug(CF.PBJ + "NoticeService.addNotice.path : " + path);
		log.debug(CF.PBJ + "NoticeService.addNotice.subjectReportForm : " + subjectReportForm);

		// SubjectReportMapper
		SubjectReport subjectReport = new SubjectReport();
		subjectReport.setSubjectNo(subjectReportForm.getSubjectNo());
		subjectReport.setMemberId(subjectReportForm.getMemberId());
		subjectReport.setSubjectReportTitle(subjectReportForm.getSubjectReportTitle());
		subjectReport.setSubjectReportContent(subjectReportForm.getSubjectReportContent());
		subjectReport.setSubjectReportPeriod(subjectReportForm.getSubjectReportPeriod());
		// subjectReport.getSubjectBoardNo() --> 0
		int row = subjectReportMapper.insertSubjectBoard(subjectReport);
		int row2 = subjectReportMapper.insertSubjectReport(subjectReport);
		log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.row : " + row);
		log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.row2 : " + row2);
		// insert 성공시, 기본키 값이 출력됨
		log.debug(
				CF.PBJ + "SubjectReportService.addSubjectReport.subjectBoardNo : " + subjectReport.getSubjectBoardNo());
		log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.subjectReportNo : "
				+ subjectReport.getSubjectReportNo());

		// SubjectReportMapper -> file insert
		if (subjectReportForm.getSubjectReportFileList() != null
				&& subjectReportForm.getSubjectReportFileList().get(0).getSize() > 0 && row == 1 && row2 == 1) {
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
				subjectFile.setSubjectFileBoardNo(subjectReport.getSubjectBoardNo());
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

	// 3) 과제 게시판 글 상세보기 + 파일 이름 리스트 출력 + 댓글 리스트 출력
	public Map<String, Object> getSubjectReportAndFileNameListAndCommentList (Map<String, Object> map) {
		// 댓글 페이징 데이터
		int commentCurrentPage = (int)map.get("commentCurrentPage");
		int rowPerPage = (int)map.get("rowPerPage");
		int startRow = (commentCurrentPage - 1) * rowPerPage;
		int subjectBoardNo = (int)map.get("subjectBoardNo");
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("startRow", startRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("subjectBoardNo", subjectBoardNo);
	
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameList.subjectBoardNo :" + subjectBoardNo);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameList.startRow :" + startRow);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameList.rowPerPage :" + rowPerPage);
		
		// 선택된 과게 게시판 글 상세보기
		List<SubjectReport> subjectReport = subjectReportMapper.selectSubjectReportOne(subjectBoardNo);
		// 선택된 과제 게시판 글의 파일 리스트 
		List<SubjectFile> subjectFileList = subjectReportMapper.selectSubjectReportFileList(subjectBoardNo);
		// 선택된 과제 게시판 댓글 리스트
		List<SubjectReportComment> commentList = subjectReportMapper.selectCommentListByPage(paramMap);
		// 상세보기 + 파일 리스트 저장
		Map<String, Object> returnMap = new HashMap<>();
		// 댓글 페이징 코드
		int commentTotalCount = subjectReportMapper.selectCommentTotalCountByNotice(subjectBoardNo);
		int commentLastPage = commentTotalCount / (int)(map.get("rowPerPage"));
		if(commentTotalCount % (int)(map.get("rowPerPage")) != 0) {
			commentLastPage +=1;
		}
		// 상세보기 값 + 파일 리스트 + 댓글 리스트 데이터 저장
		returnMap.put("subjectReport", subjectReport);
		returnMap.put("subjectFileList", subjectFileList);
		returnMap.put("commentList", commentList);
		returnMap.put("commentLastPage", commentLastPage);
		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameList.subjectReport :" + subjectReport);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameList.subjectFileList :" + subjectFileList);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameList.commentList :" + commentList);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameList.commentLastPage :" + commentLastPage);
		
		return returnMap;
	}
	
	// 4) 과제 게시판 수정 Form 
	public Map<String, Object> getSubjectReportOne(int subjectBoardNo) {
		// 과제 게시판 글 불러오기
		List<SubjectReport> subjectReport = subjectReportMapper.selectSubjectReportOne(subjectBoardNo);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportOne.subjectReport :" + subjectReport);
		// 과제 게시판 파일 리스트 불러오기
		List<SubjectFile> subjectFile = subjectReportMapper.selectSubjectReportFileList(subjectBoardNo);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportOne.subjectFile :" + subjectFile);
		// 해시 맵으로 데이터 보냄
		Map<String, Object> map = new HashMap<>();
		map.put("subjectReport", subjectReport);
		map.put("subjectFile", subjectFile);
		return map;
	}
	
	// 4-1) 과제 게시판 수정 Action
	public int modifySubjectReport(SubjectReport subjectReport) {
		int row = subjectReportMapper.updateSubjectReport(subjectReport);
		log.debug(CF.PBJ + "SubjectReportService.modifySubjectReport.subjectReport :" + subjectReport);
		log.debug(CF.PBJ + "SubjectReportService.modifySubjectReport.row :" + row);
		return row;
	}
	
	// 4-2) 과제 게시판 속 파일 삭제 처리
	public void removeSubjectFile(int subjectFileNo, String path) {
		List<String> subjectFileList = subjectReportMapper.selectSubjectFileNameListBySubjectFileNo(subjectFileNo);
		log.debug(CF.PBJ + "SubjectReportService.removeSubjectFile.subjectFileList : " + subjectFileList);
		for(String sfl : subjectFileList) {
			File f = new File(path + sfl);
			if(f.exists())
				f.delete();
		}
		subjectReportMapper.deleteSubjectFile(subjectFileNo);
	}
}

















































































