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
import kr.co.javajoy.lms.mapper.SubjectNoticeMapper;
import kr.co.javajoy.lms.vo.SubjectBoard;
import kr.co.javajoy.lms.vo.SubjectBoardInsertForm;
import kr.co.javajoy.lms.vo.SubjectFile;
import kr.co.javajoy.lms.vo.SubjectNotice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SubjectNoticeService {
	@Autowired SubjectNoticeMapper subjectNoticeMapper;
	// 커리큘럼 리스트 출력
	public Map<String, Object> getSubjectNoticeList(int currentPage, int rowPerPage, int subjectNo) {
		// 리스트 출력 페이징
		int startRow = (currentPage - 1) * rowPerPage;
		// 디버깅
		log.debug(CF.WSH + "SubjectNoticeService.getSubjectNoticeList rowPerPage : " + CF.WSH + rowPerPage);
		log.debug(CF.WSH + "SubjectNoticeService.getSubjectNoticeList startRow : " + CF.WSH + startRow);
		log.debug(CF.WSH + "SubjectNoticeService.getSubjectNoticeList subjectNo : " + CF.WSH + subjectNo);
		// 값을 가공
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		map.put("subjectNo", subjectNo);
		
		// Mapper에서 반환(호출) 된 값 가공
		List<Map<String, Object>> list = subjectNoticeMapper.getSubjectNoticeList(map);
		int totalCount = subjectNoticeMapper.selectTotalCount(subjectNo);
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		returnMap.put("subjectNo", subjectNo);
			
		// 디버깅
		log.debug(CF.WSH + "SubjectNoticeService.getSubjectNoticeList lastPage : " + CF.WSH + lastPage );
		log.debug(CF.WSH + "SubjectNoticeService.getSubjectNoticeList list : " + CF.WSH + list);
		log.debug(CF.WSH + "SubjectNoticeService.getSubjectNoticeList subjectNo : " + CF.WSH + subjectNo);
		return returnMap;
	}
	// 번호 받고 추가하기
	public void addSubjectNotice(SubjectBoardInsertForm subjectBoardInsertForm, String path) {
		//  디버깅 초기화
		
		// 넘어온값 확인
		log.debug(CF.WSH + "SubjectNoticeService.getSubjectNoticeList subjectBoardInsertForm : " + CF.WSH + subjectBoardInsertForm );
		log.debug(CF.WSH + "SubjectNoticeService.getSubjectNoticeList path : " + CF.WSH + path );
		// subjectNo를 저장 -> 번호를 Subject Board로 넘겨주기
		SubjectBoard subjectBoard = new SubjectBoard();
		subjectBoard.setSubjectNo(subjectBoardInsertForm.getSubjectNo());
		subjectNoticeMapper.insertSubjectNoticeBoardNo(subjectBoard);
		
		
		SubjectNotice subjectNotice = new SubjectNotice();
		subjectNotice.setSubjectNoticeNo(subjectBoard.getSubjectBoardNo());
		subjectNotice.setMemberId(subjectBoardInsertForm.getMemberId());
		subjectNotice.setSubjectNoticeTitle(subjectBoardInsertForm.getSubjectBoardTitle());
		subjectNotice.setSubjectNoticeContent(subjectBoardInsertForm.getSubjectBoardContent());
		subjectNoticeMapper.insertSubjectNotice(subjectNotice); 
		// AutoIncrement값
		log.debug(CF.WSH + "SubjectNoticeService.getSubjectNoticeList subjectNoticeNo : " + CF.WSH + subjectNotice.getSubjectNoticeNo() );
		
		if(subjectBoardInsertForm.getSubjectBoardFileList() != null && subjectBoardInsertForm.getSubjectBoardFileList().get(0).getSize() > 0) {
			// subjectNoitceFileList가 비어있지 않으면
			for(MultipartFile mf : subjectBoardInsertForm.getSubjectBoardFileList()) {
				log.debug(CF.WSH + "SubjectNoticeController.addsubjectNotice.post().mf : " + mf + CF.WSH);
				
				// 파일 담기
				SubjectFile subjectFile = new SubjectFile();
				String originName = mf.getOriginalFilename(); 
				String ext = originName.substring(originName.lastIndexOf("."));
				// originName에서 마지막.(점)위치
				String filename = UUID.randomUUID().toString();
				// 저장 시 중복되지 않고 저장하기 위해 UUID API를 사용하여 이름 랜덤 지정
				filename = filename.replace("-", ""); 
				// -를 공백으로
				filename = filename + ext;
				subjectFile.setSubjectFileBoardNo(subjectNotice.getSubjectNoticeNo());
				subjectFile.setSubjectFileOriginalName(originName);
				subjectFile.setSubjectFileName(filename);
				subjectFile.setSubjectFileType(mf.getContentType());
				subjectFile.setSubjectFileSize(mf.getSize()); // long타입 // 파일의 배열 사이즈
				log.debug(CF.WSH + "NoticeService.addNotice.boardfile : "+ subjectFile);
				// Mapper에 입력 후 호출
				subjectNoticeMapper.insertSubjectNoticeFile(subjectFile);
				// 예외 처리
				try {
					mf.transferTo(new File(path + filename));
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
				
			}
			
			
		}
		
	}
	

	public Map<String, Object> subjectNoticeOne(int subjectBoardNo){
		// 잘 넘어왔는지 확인
		log.debug(CF.WSH + "SubjectNoticeService.subjectNoticeOne.subjectNo : " + subjectBoardNo + CF.WSH);
		
		List<Map<String, Object>> subjectNotice = subjectNoticeMapper.subjectNoticeOne(subjectBoardNo);
		log.debug(CF.WSH + "SubjectNoticeService.subjectNoticeOne.subjectNotice : " + subjectNotice + CF.WSH);
		
		List<Map<String, Object>> subjectNoticeFile = subjectNoticeMapper.subjectNoticeFileOne(subjectBoardNo);
		log.debug(CF.WSH + "SubjectNoticeService.subjectNoticeOne.subjectNoticeFile : " + subjectNoticeFile + CF.WSH);
		
		int FileCount = subjectNoticeMapper.subjectNoticeFileCount();
		
		Map<String, Object> map = new HashMap<>();
		map.put("subjectNotice",subjectNotice);
		map.put("subjectNoticeFile",subjectNoticeFile);
		map.put("FileCount",FileCount);
		return map;
	}
		
	
	
 }