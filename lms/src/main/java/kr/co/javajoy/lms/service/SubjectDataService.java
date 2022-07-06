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
import kr.co.javajoy.lms.mapper.SubjectReportMapper;
import kr.co.javajoy.lms.vo.SubjectBoard;
import kr.co.javajoy.lms.vo.SubjectBoardInsertForm;
import kr.co.javajoy.lms.vo.SubjectData;
import kr.co.javajoy.lms.vo.SubjectFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class SubjectDataService {
	@Autowired SubjectDataMapper subjectDataMapper;
	@Autowired SubjectReportMapper subjectReportMapper;

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
		
		List<Map<String, Object>> list = subjectDataMapper.selectSubjectDataListByPage(map);
		int totalCount = subjectDataMapper.selectTotalCount(searchName);
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
		
		subjectDataMapper.insertSubjectBoard(subjectBoard);
		
		SubjectData subjectData = new SubjectData();
		subjectData.setSubjectDataNo(subjectBoard.getSubjectBoardNo());
		subjectData.setMemberId(subjectBoardInsertForm.getMemberId());
		subjectData.setSubjectDataTitle(subjectBoardInsertForm.getSubjectBoardTitle());
		subjectData.setSubjectDataContent(subjectBoardInsertForm.getSubjectBoardContent());
		
		log.debug(CF.YHJ + "SubjectDataService.addSubjectData.subjectData : " + subjectData + CF.RESET); // 디버깅
		
		subjectDataMapper.insertSubjectData(subjectData);
		
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
			
			subjectDataMapper.insertSubjectFile(subjectFile);
			
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
		
		Map<String,Object> dataMap = subjectDataMapper.selectSubjectDataOne(subjectDataNo);
		List<SubjectFile> subjectDataFile = subjectDataMapper.selectSubjectDataFile(subjectDataNo);
		int fileCount = subjectDataMapper.selectDataFileCount(subjectDataNo);
		
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
	
	// 강좌좌료One 삭제
	public void removeSubjectDataOne(int subjectDataNo, String path) {
		// 디버깅
		log.debug(CF.YHJ + "SubjectDataService.removeSubjectDataOne.subjectDataNo : " + subjectDataNo + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataService.removeSubjectDataOne.path : " + path + CF.RESET);
		
		List<SubjectFile> subjectDataFileList = subjectDataMapper.selectSubjectDataFile(subjectDataNo);
		log.debug(CF.PBJ + "SubjectDataService.removeSubjectDataOne.subjectDataFileList : " + subjectDataFileList); // 디버깅
		
		for(int i = 0; i < subjectDataFileList.size(); i++) {
			File f = new File(path + subjectDataFileList.get(i).getSubjectFileName());
			if(f.exists()) {
				f.delete();
			}
		}
		
		subjectDataMapper.deleteSubjectDataFile(subjectDataNo);
		subjectDataMapper.deleteSubjectDataOne(subjectDataNo);
		subjectReportMapper.deleteSubjectBoardBySubjectBoardNo(subjectDataNo);
		
	}
	
	// 강의자료 수정
	public void modifySubjectDataOne(SubjectBoardInsertForm subjectBoardInsertForm, String path) {
		// 디버깅
		log.debug(CF.YHJ + "SubjectDataService.modifySubjectDataOne.subjectBoardInsertForm : " + subjectBoardInsertForm + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataService.modifySubjectDataOne.path : " + path + CF.RESET);
		
		SubjectData subjectData = new SubjectData();
		subjectData.setSubjectDataNo(subjectBoardInsertForm.getSubjectBoardNo());
		subjectData.setSubjectDataTitle(subjectBoardInsertForm.getSubjectBoardTitle());
		subjectData.setSubjectDataContent(subjectBoardInsertForm.getSubjectBoardContent());
		
		log.debug(CF.YHJ + "SubjectDataService.modifySubjectDataOne.subjectData : " + subjectData + CF.RESET);
		
		subjectDataMapper.updateSubjectOne(subjectData);
		
		if (subjectBoardInsertForm.getSubjectBoardFileList() != null && subjectBoardInsertForm.getSubjectBoardFileList().get(0).getSize() > 0) {
			log.debug(CF.YHJ + "SubjectDataService.modifySubjectDataOne : 첨부된 파일이 있습니다.");
			for (MultipartFile mf : subjectBoardInsertForm.getSubjectBoardFileList()) {
				String originName = mf.getOriginalFilename();
				String ext = originName.substring(originName.lastIndexOf(".")); // originName에서 마지막 .문자열 위치
				String filename = UUID.randomUUID().toString(); // 파일을 저장할 때 사용할 증븍되지않는 새로운 이름 (UUID API사용)
				filename = filename + ext;
				
				// 디버깅
				log.debug(CF.YHJ + "SubjectDataService.modifySubjectDataOne.originName : " + originName);
				log.debug(CF.YHJ + "SubjectDataService.modifySubjectDataOne.filename : " + filename);
				
				SubjectFile subjectFile = new SubjectFile();
				subjectFile.setSubjectFileBoardNo(subjectBoardInsertForm.getSubjectBoardNo());
				subjectFile.setSubjectFileName(filename);
				subjectFile.setSubjectFileOriginalName(mf.getOriginalFilename());
				subjectFile.setSubjectFileType(mf.getContentType());
				subjectFile.setSubjectFileSize(mf.getSize());
				
				log.debug(CF.YHJ + "SubjectDataService.modifySubjectDataOne.SubjectFile : " + subjectFile); // 디버깅
				
				subjectDataMapper.insertSubjectFile(subjectFile);
				try {
					mf.transferTo(new File(path + filename));
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
			}
		}
		
	}
	
	public void removeSubjectDataFileOne(int subjectFileNo, String path) {
		log.debug(CF.YHJ + "SubjectDataService.removeSubjectDataFileOne.subjectFileNo : " + subjectFileNo + CF.RESET);
		log.debug(CF.YHJ + "SubjectDataService.removeSubjectDataFileOne.path : " + path + CF.RESET);
		
		List<SubjectFile> subjectDataFileList = subjectDataMapper.selectSubjectDataFile(subjectFileNo);
		log.debug(CF.PBJ + "SubjectDataService.removeSubjectDataFileOne.subjectDataFileList : " + subjectDataFileList); // 디버깅
		
		for(int i = 0; i < subjectDataFileList.size(); i++) {
			File f = new File(path + subjectDataFileList.get(i).getSubjectFileName());
			if(f.exists()) {
				f.delete();
			}
		}
		subjectDataMapper.deleteSubjectDataFileOne(subjectFileNo);
	}
	
}
