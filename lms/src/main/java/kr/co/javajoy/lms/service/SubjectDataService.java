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
import kr.co.javajoy.lms.mapper.SubjectDataMapper;
import kr.co.javajoy.lms.vo.SubjectBoard;
import kr.co.javajoy.lms.vo.SubjectBoardInsertForm;
import kr.co.javajoy.lms.vo.SubjectData;
import kr.co.javajoy.lms.vo.SubjectFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SubjectDataService {
	@Autowired SubjectDataMapper subjectdataMapper;

	// 강좌자료 리스트 출력
	public Map<String, Object> getSubjectDataListByPage(int currentPage, int rowPerPage, int subjectNo, String searchName) {
		// 디버깅
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataListByPage.currentPage : " + currentPage + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataListByPage.rowPerPage : " + rowPerPage + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataListByPage.subjectNo : " + subjectNo + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataListByPage.searchName : " + searchName + CF.RESET);

		// 페이징
		int beginRow = (currentPage - 1) * rowPerPage;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("beginRow", beginRow);
		map.put("subjectNo", subjectNo);
		map.put("searchName", searchName);
		
		List<Map<String, Object>> list = subjectdataMapper.selectSubjectDataListByPage(map);
		int totalCount = subjectdataMapper.selectTotalCount(searchName);
		int lastPage = (int)(Math.ceil((double)totalCount / (double)rowPerPage));
		
		// 디버깅
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataListByPage list : " + list + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataListByPage totalCount : " + totalCount + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataListByPage lastPage : " + lastPage + CF.RESET);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		
		return returnMap;
	}
	
	public void addSubjectData(SubjectBoardInsertForm subjectBoardInsertForm, String path) {
		// 디버깅
		log.debug(CF.YHJ + "SubjectDataService.addSubjectData.subjectBoardInsertForm : " + subjectBoardInsertForm + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataService.addSubjectData.path : " + path + CF.RESET);
		
		SubjectBoard subjectBoard = new SubjectBoard();
		subjectBoard.setSubjectNo(subjectBoardInsertForm.getSubjectNo());
		
		log.debug(CF.YHJ + "SubjectDataService.addSubjectData.subjectBoard : " + subjectBoard + CF.RESET); // 디버깅
		
		subjectdataMapper.insertSubjectBoard(subjectBoard);
		
		SubjectData subjectData = new SubjectData();
		subjectData.setSubjectDataNo(subjectBoard.getSubjectBoardNo());
		subjectData.setMemberId(subjectBoardInsertForm.getMemberId());
		subjectData.setSubjectDataTitle(subjectBoardInsertForm.getSubjectBoardTitle());
		subjectData.setSubjectDataContent(subjectBoardInsertForm.getSubjectBoardContent());
		
		log.debug(CF.YHJ + "SubjectDataService.addSubjectData.subjectData : " + subjectData + CF.RESET); // 디버깅
		
		subjectdataMapper.insertSubjectData(subjectData);
		
		if (subjectBoardInsertForm.getSubjectBoardFileList() != null && subjectBoardInsertForm.getSubjectBoardFileList().get(0).getSize() > 0) { // 파일을 첨부했을 때 
			log.debug(CF.YHJ +"SubjectDataService.addSubjectData 파일 첨부됨 " + CF.RESET); // 디버깅
			
		for(MultipartFile mf : subjectBoardInsertForm.getSubjectBoardFileList()) {
			SubjectFile subjectFile = new SubjectFile();
			
			String originalName = mf.getOriginalFilename(); // 파일 오리지널이름 
			String ext = originalName.substring(originalName.lastIndexOf("."));
			
			String fileName = UUID.randomUUID().toString(); // 파일 저장할때 중복되지않는 이름 사용 UUID API
			
			fileName = fileName+ext;
			subjectFile.setSubjectFileBoardNo(subjectData.getSubjectDataNo());
			subjectFile.setSubjectFileName(fileName);
			subjectFile.setSubjectFileOriginalName(originalName);
			subjectFile.setSubjectFileSize(mf.getSize());
			subjectFile.setSubjectFileType(mf.getContentType());
			
			log.debug(CF.YHJ + "SubjectDataService.addSubjectData.subjectFile : " + subjectFile + CF.RESET); // 디버깅
			
			subjectdataMapper.insertSubjectFile(subjectFile);
			
			try {
				mf.transferTo(new File(path+fileName));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(); //RuntimeException은 예외처리 하지않아도 컴파일 됨
				}
			}
		}
	}
	
	// 강좌자료 상세보기
	public Map<String,Object> getSubjectDataOne(int subjectDataNo){
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataOne.subjectDataNo : " + subjectDataNo + CF.RESET); // 디버깅
		
		Map<String,Object> dataMap = subjectdataMapper.selectSubjectDataOne(subjectDataNo);
		List<SubjectFile> subjectDataFile = subjectdataMapper.selectSubjectDataFile(subjectDataNo);
		int fileCount = subjectdataMapper.selectDataFileCount(subjectDataNo);
		
		// 디버깅
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataOne.dataMap : " + dataMap + CF.RESET); 
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataOne.subjectFile : " + subjectDataFile + CF.RESET); 
		log.debug(CF.YHJ + "SubjectDataService.getSubjectDataOne.fileCount : " + fileCount + CF.RESET); 
		
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("dataMap", dataMap);
		returnMap.put("subjectDataFile", subjectDataFile);
		returnMap.put("fileCount", fileCount);
		
		return returnMap;
	}
	
}
