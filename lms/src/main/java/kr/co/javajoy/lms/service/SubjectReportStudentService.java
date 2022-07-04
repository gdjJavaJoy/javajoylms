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
import kr.co.javajoy.lms.mapper.SubjectReportStudentMapper;
import kr.co.javajoy.lms.vo.StudentFile;
import kr.co.javajoy.lms.vo.SubjectReportStudent;
import kr.co.javajoy.lms.vo.SubjectReportStudentForm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class SubjectReportStudentService {
	@Autowired SubjectReportStudentMapper subjectReportStudentMapper;
	
	// ------------------------ 1) 과제 게시판 글 리스트 출력 <SELECT> ------------------------ 
	
	// 1-1) 과제 게시판 글 리스트 출력
	public Map<String, Object> getSubjectReportStudentListByPage(int currentPage, int rowPerPage, int subjectReportNo, String sSubjectReportStudentTitle) {
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.currentPage :" + currentPage);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.rowPerPage :" + rowPerPage);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.subjectReportNo :" + subjectReportNo);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.sSubjectReportStudentTitle :" + sSubjectReportStudentTitle);
		// 페이징
		int startRow = (currentPage - 1) * rowPerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRow", startRow);
		map.put("rowPerPage", rowPerPage);
		map.put("subjectReportNo", subjectReportNo);
		map.put("sSubjectReportStudentTitle", sSubjectReportStudentTitle);
		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.startRow" + startRow);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.rowPerPage" + rowPerPage);

		// 1) 컨트롤러에서 넘오온 변수값 가공 후 맵퍼 호출
		List<SubjectReportStudent> list = subjectReportStudentMapper.selectSubjectReportStudentListByPage(map);
		// 2) 맵퍼에서 반환된 값을 가공 후, 컨트롤러에 변환
		int totalCount =subjectReportStudentMapper.selectTotalCount(); // 과제 게시판 글 총 수
		int lastPage = (int) (Math.ceil((double) totalCount / (double) rowPerPage));

		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.list------------" + list);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.totalCount" + totalCount);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.lastPage" + lastPage);

		// 리스트 결과값 해쉬 맵에 저장
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("subjectReportNo", subjectReportNo);
		returnMap.put("sSubjectReportStudentTitle", sSubjectReportStudentTitle);
		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.returnMap.list.size()  :" + list.size());
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.returnMap.lastPage	:" + lastPage);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.returnMap.subjectNo	:" + subjectReportNo);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportListByPage.returnMap.sSubjectReportStudentTitle	:" + sSubjectReportStudentTitle);

		return returnMap;
	}
	
	// ------------------------ 2) 과제 게시판 글 입력 <INSERT> ------------------------ 
	
	// 2-1) 과제 게시판 글 입력 + 파일 입력
	public void addSubjectReportStudent (SubjectReportStudentForm subjectReportStudentForm, String path) {
		log.debug(CF.PBJ + "NoticeService.addNotice.path : " + path);
		log.debug(CF.PBJ + "NoticeService.addNotice.subjectReportStudentForm : " + subjectReportStudentForm);
		// SubjectReportMapper
		SubjectReportStudent subjectReportStudent = new SubjectReportStudent();
		subjectReportStudent.setSubjectReportNo(subjectReportStudentForm.getSubjectReportNo());
		subjectReportStudent.setMemberId(subjectReportStudentForm.getMemberId());
		subjectReportStudent.setSubjectReportStudentTitle(subjectReportStudentForm.getSubjectReportStudentTitle());
		subjectReportStudent.setSubjectReportStudentContent(subjectReportStudentForm.getSubjectReportStudentContent());
		subjectReportStudent.setScore(subjectReportStudentForm.getScore());
		subjectReportStudent.setScore(subjectReportStudentForm.getScore());
		// subjectReport.getSubjectBoardNo() --> 0
		int row = subjectReportStudentMapper.insertSubjectReportStudent(subjectReportStudent);
		log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.row : " + row);
		// insert 성공시, 기본키 값이 출력됨
	
		if (subjectReportStudentForm.getStudentFileList() != null && subjectReportStudentForm.getStudentFileList().get(0).getSize() > 0 && row == 1) {
			log.debug(CF.PBJ + "SubjectReportService.addSubjectReport : 첨부된 파일이 있습니다.");
			for (MultipartFile mf : subjectReportStudentForm.getStudentFileList()) {
				StudentFile studentFile = new StudentFile();

				String originName = mf.getOriginalFilename();
				// originName에서 마지막 .문자열 위치
				String ext = originName.substring(originName.lastIndexOf("."));
				// 파일을 저장할 때 사용할 증븍되지않는 새로운 이름 (UUID API사용)
				String filename = UUID.randomUUID().toString();
				filename = filename + ext;
				log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.originName : " + originName);
				log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.filename : " + filename);
				// subject_file 데이터 가공
				studentFile.setSubjectReportStudentNo(subjectReportStudent.getSubjectReportStudentNo());
				studentFile.setStudentFileName(filename);
				studentFile.setStudentFileOriginalName(mf.getOriginalFilename());
				studentFile.setStudentFileType(mf.getContentType());
				studentFile.setStudentFileSize(mf.getSize());
				log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.SubjectFile : " + studentFile);
				// 데이터베이스에 인서트
				subjectReportStudentMapper.insertStudentFile(studentFile);
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
	public Map<String, Object> getSubjectReportAndFileNameList (Map<String, Object> map) {
		int subjectReportStudentNo = (int)map.get("subjectReportStudentNo");
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("subjectReportNo", subjectReportStudentNo);

		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameListAndCommentList.subjectBoardNo :" + subjectReportStudentNo);
	
		// 선택된 과게 게시판 글 상세보기
		List<SubjectReportStudent> subjectReportStudent = subjectReportStudentMapper.selectSubjectReportStudentOne(subjectReportStudentNo);
		// 선택된 과제 게시판 글의 파일 리스트 
		List<StudentFile> studentFileList = subjectReportStudentMapper.selectSubjectReportStudentFileList(subjectReportStudentNo);
		// 상세보기 + 파일 리스트 저장
		Map<String, Object> returnMap = new HashMap<>();
		
		// 상세보기 값 + 파일 리스트 + 댓글 리스트 데이터 저장
		returnMap.put("subjectReportStudent", subjectReportStudent);
		returnMap.put("studentFileList", studentFileList);
		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameListAndCommentList.returnMap.subjectReport :" + subjectReportStudent);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportAndFileNameListAndCommentList.returnMap.subjectFileList :" + studentFileList);

		return returnMap;
	}
	
	// ------------------------ 4) 과제 게시판 수정 <UPDATE>------------------------ 
	
	// 4-1) 과제 게시판 수정 Form 
	public Map<String, Object> getSubjectReportStudentOne(int subjectReportStudentNo) {
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportOne(Form).subjectBoardNo :" + subjectReportStudentNo);
		// 선택된 과게 게시판 글 상세보기
		List<SubjectReportStudent> subjectReportStudent = subjectReportStudentMapper.selectSubjectReportStudentOne(subjectReportStudentNo);
		// 선택된 과제 게시판 글의 파일 리스트 
		List<StudentFile> studentFile = subjectReportStudentMapper.selectSubjectReportStudentFileList(subjectReportStudentNo);
		// 해시 맵으로 데이터 보냄
		Map<String, Object> map = new HashMap<>();
		map.put("subjectReportStudent", subjectReportStudent);
		map.put("studentFileList", studentFile);
		
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportOne(Form).subjectReport.map.put :" + subjectReportStudent);
		log.debug(CF.PBJ + "SubjectReportService.getSubjectReportOne(Form).subjectFile.map.put :" + studentFile);
		
		return map;
	}
	
	// 4-2) 과제 게시판 수정 Action
	public void modifySubjectReportStudent(SubjectReportStudentForm subjectReportStudentForm, String path) {
		log.debug(CF.PBJ + "SubjectReportService.modifySubjectReport(Action).subjectReportForm: " + subjectReportStudentForm);
		log.debug(CF.PBJ + "SubjectReportService.modifySubjectReport(Action).path: " + path);
		
		// SubjectReportMapper
		SubjectReportStudent subjectReportStudent = new SubjectReportStudent();
		subjectReportStudent.setSubjectReportNo(subjectReportStudentForm.getSubjectReportNo());
		subjectReportStudent.setMemberId(subjectReportStudentForm.getMemberId());
		subjectReportStudent.setSubjectReportStudentTitle(subjectReportStudentForm.getSubjectReportStudentTitle());
		subjectReportStudent.setSubjectReportStudentContent(subjectReportStudentForm.getSubjectReportStudentContent());
		subjectReportStudent.setScore(subjectReportStudentForm.getScore());
		subjectReportStudent.setScore(subjectReportStudentForm.getScore());
		// subjectReport.getSubjectBoardNo() --> 0
		int row = subjectReportStudentMapper.insertSubjectReportStudent(subjectReportStudent);
		log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.row : " + row);
		// insert 성공시, 기본키 값이 출력됨
			
		if (subjectReportStudentForm.getStudentFileList() != null && subjectReportStudentForm.getStudentFileList().get(0).getSize() > 0 && row == 1) {
			log.debug(CF.PBJ + "SubjectReportService.addSubjectReport : 첨부된 파일이 있습니다.");
			for (MultipartFile mf : subjectReportStudentForm.getStudentFileList()) {
				StudentFile studentFile = new StudentFile();

				String originName = mf.getOriginalFilename();
				// originName에서 마지막 .문자열 위치
				String ext = originName.substring(originName.lastIndexOf("."));
				// 파일을 저장할 때 사용할 증븍되지않는 새로운 이름 (UUID API사용)
				String filename = UUID.randomUUID().toString();
				filename = filename + ext;
				log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.originName : " + originName);
				log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.filename : " + filename);
				// subject_file 데이터 가공
				studentFile.setSubjectReportStudentNo(subjectReportStudent.getSubjectReportStudentNo());
				studentFile.setStudentFileName(filename);
				studentFile.setStudentFileOriginalName(mf.getOriginalFilename());
				studentFile.setStudentFileType(mf.getContentType());
				studentFile.setStudentFileSize(mf.getSize());
				log.debug(CF.PBJ + "SubjectReportService.addSubjectReport.SubjectFile : " + studentFile);
				// 데이터베이스에 인서트
				subjectReportStudentMapper.insertStudentFile(studentFile);
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
	public void removeStudentFile(int studentFileNo, String path) {
		List<String> studentFileList = subjectReportStudentMapper.selectStudentFileNameListByStudentFileNo(studentFileNo);
		log.debug(CF.PBJ + "SubjectReportService.removeSubjectFile.subjectFileList : " + studentFileList);
		for (String sfl : studentFileList) {
			File f = new File(path + sfl);
			if (f.exists())
				f.delete();
		}
		subjectReportStudentMapper.deleteStudentFile(studentFileNo);
	}
	
	// ------------------------ 5) 과제 게시판 글 삭제 <DELETE>------------------------ 
	
	// 6-1) 과제 게시판 삭제
	public int removeSubjectReportStudent(int subjectReportStudentNo, String path) {
		int row = 0;
		log.debug(CF.PBJ + "SubjectReportService.removeSubjectReport.subjectBoardNo : " + subjectReportStudentNo);
		log.debug(CF.PBJ + "SubjectReportService.removeSubjectReport.path : " + path);
		
		List<String> studentFileList = subjectReportStudentMapper.selectSubjectReportStudentFileNameList(subjectReportStudentNo);
		log.debug(CF.PBJ + "SubjectReportService.removeSubjectReport.subjectReportFileList : " + studentFileList);
		for (String filename : studentFileList) {
			File f = new File(path + filename);
			if(f.exists()) {
				f.delete();
			}
		}
		
		subjectReportStudentMapper.deleteAllStudentFileBySubjectReportStudentNo(subjectReportStudentNo);
		row = subjectReportStudentMapper.deleteStudentReportStudentBySubjectReportStudentNo(subjectReportStudentNo);
		log.debug(CF.PBJ + "SubjectReportService.removeSubjectReport.row : " + row);
		
		return row;
	}
}
