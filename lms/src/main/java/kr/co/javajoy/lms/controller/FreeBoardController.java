package kr.co.javajoy.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.javajoy.lms.CF;
import kr.co.javajoy.lms.service.FreeBoardService;
import kr.co.javajoy.lms.vo.BoardComment;
import kr.co.javajoy.lms.vo.BoardForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FreeBoardController {
	@Autowired FreeBoardService freeBoardService;
	
	@GetMapping("/getFreeBoardByPage")
	public String getFreeBoardByPage(Model model
										,@RequestParam(value="currentPage", defaultValue="1") int currentPage
										,@RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
										,@RequestParam @Nullable String searchFreeBoardTitle) {
		
		log.debug(CF.PSG+"FreeBoardController.getFreeBoardByPage currentPage :" + currentPage + CF.RESET);
		log.debug(CF.PSG+"FreeBoardController.getFreeBoardByPage rowPerPage :" + rowPerPage + CF.RESET);
		log.debug(CF.PSG+"FreeBoardController.getFreeBoardByPage searchFreeBoardTitle :" + searchFreeBoardTitle + CF.RESET);
		Map<String,Object> map = freeBoardService.getFreeBoardByPage(currentPage, rowPerPage, searchFreeBoardTitle);
		
		model.addAttribute("searchFreeBoardTitle",searchFreeBoardTitle);
		model.addAttribute("list",map.get("list"));
		model.addAttribute("lastPage",map.get("lastPage"));
		model.addAttribute("rowPerPage",map.get("rowPerPage"));
		model.addAttribute("totalCount",map.get("totalCount"));
		model.addAttribute("currentPage",currentPage);
		
		return "board/freeBoard/getFreeBoardByPage";
	}
	@GetMapping("/getFreeBoardOne")
	public String getFreeBoardOne(@RequestParam(value="boardNo") int boardNo
								,Model model) {
	Map<String,Object> map = freeBoardService.getFreeBoardOne(boardNo);
	log.debug(CF.PSG+"FreeBoardController.board : " + map.get("board") +CF.RESET);
	log.debug(CF.PSG+"FreeBoardController.boardFile : " + map.get("boardFile") +CF.RESET);
	log.debug(CF.PSG+"FreeBoardController.boardComment : " + map.get("boardComment") +CF.RESET);
	log.debug(CF.PSG+"FreeBoardController.fileCount : " + map.get("fileCount") +CF.RESET);
	
	model.addAttribute("board",map.get("board"));
	model.addAttribute("boardFile",map.get("boardFile"));
	model.addAttribute("boardComment",map.get("boardComment"));
	model.addAttribute("fileCount",map.get("fileCount"));
	
		return "board/freeBoard/getFreeBoardOne"; 
	}
	@GetMapping("/addFreeBoard")
	public String addFreeBoard() {
		
		return "board/freeBoard/addFreeBoard";
	}
	@PostMapping("/addFreeBoard")
	public String addFreeBoard(BoardForm boardForm
							,HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/file/freeBoardFile/");
		log.debug(CF.PSG+"FreeBoardController.addFreeBoard.Path : " + path + CF.RESET);
		log.debug(CF.PSG+"FreeBOardController.addFreeBoard.boardForm : "+boardForm+CF.RESET);
		int row = freeBoardService.addFreeBoard(boardForm, path);
		
		if (row == 0) {
			log.debug(CF.PSG+"FreeBoardController.addFreeBoard.post() 자유게시판 등록실패 "+CF.RESET);
			return "redirect:/addFreeBoard";
		}
		log.debug(CF.PSG+"FreeboardController.addFreeBoard.post() 자유게시판 등록성공 "+CF.RESET);
		
		
		return "redirect:/getFreeBoardByPage";
	}
	@GetMapping("/removeFreeBoard")
	public String removeFreeBoard(@RequestParam(value="boardNo") int boardNo
								,HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/file/freeBoardFile/");
		log.debug(CF.PSG+"FreeBoardController.removeFreeBoard path : "+path +CF.RESET);
		
		int row = freeBoardService.removeFreeBoard(boardNo, path);
		
		if (row == 0) {
			log.debug(CF.PSG+"FreeBoardController.removeFreeBoard 삭제실패" + CF.RESET);
			return "redirect:/getFreeBoardOne?boardNo="+boardNo;
		}
		log.debug(CF.PSG+"FreeBoardController.removerFreeBoard 삭제성공 " +CF.RESET);
		
		return "redirect:/getFreeBoardByPage";
	}
	@PostMapping("/addFreeBoardComment")
	public String addFreeBoardComment(BoardComment boardComment) {
		int row = freeBoardService.addFreeBoardComment(boardComment);
		
		if (row == 1) {
			log.debug(CF.PSG+"FreeBoardController.addFreeboardComment 댓글 추가 성공 " + CF.RESET);
		} else {
			log.debug(CF.PSG+"FreeBoardController.addFreeboardComment 댓글 추가 실패 " + CF.RESET);
		}
		
 		return "redirect:/getFreeBoardOne?boardNo="+boardComment.getBoardNo();
	}
	@GetMapping("/removeFreeBoardComment")
	public String removeFreeboardComment(@RequestParam(value="boardCommentNo") int boardCommentNo
										,@RequestParam(value="boardNo") int boardNo) {
		int row = freeBoardService.removeFreeBoardCommentByBoardCommentNo(boardCommentNo);
	if(row == 1) {
			log.debug(CF.PSG+"FreeBoardController 댓글 삭제 성공 "+CF.RESET);
		} else {
			log.debug(CF.PSG+"FreeBoardController 댓글 삭제 실패 "+CF.RESET);
		}
	return "redirect:/getFreeBoardOne?boardNo="+boardNo;
	}
	@GetMapping("/modifyFreeBoard")
	public String modifyFreeBoard(@RequestParam(value="boardNo") int boardNo
							,Model model) {
		Map<String,Object> map = freeBoardService.getModifyFreeBoardOne(boardNo);
		
		model.addAttribute("board",map.get("board"));
		model.addAttribute("boardFile",map.get("boardFile"));
		model.addAttribute("fileCount",map.get("fileCount"));
		return "board/freeBoard/modifyFreeBoard";
	}
	@PostMapping("/modifyFreeBoard")
	public String modifyFreeBoard(BoardForm boardForm
							,HttpServletRequest request) {
		log.debug(CF.PSG+"FreeBoardController.modifyFreeBoard boardForm :" +boardForm+CF.RESET);
		String path = request.getServletContext().getRealPath("/file/freeBoardFile/");
		log.debug(CF.PSG+"FreeBoardController.modifyFreeBoard.Path :" +path + CF.RESET);
		
		int row = freeBoardService.modifyFreeBoard(boardForm, path);
		
		if (row == 0) {
			log.debug(CF.PSG+"FreeBoardController.modifyFreeBoard 수정 실패 " +CF.RESET);
			return "redirect:/modifyFreeBoard?boardNo="+boardForm.getBoardNo();
		}
			log.debug(CF.PSG+"FreeBoardController.modifyFreeBoard 수정 성공 " +CF.RESET);
		return "redirect:/getFreeBoardOne?boardNo="+boardForm.getBoardNo();
	}
	
	
	
	
	
	
	@GetMapping("/removeFreeBoardFileByBoardFileNo")
	public String removeFreeBoardFileByBoardFileNo(@RequestParam(value="boardFileNo") int boardFileNo
												,@RequestParam(value="boardNo") int boardNo
												,HttpServletRequest request) {
		log.debug(CF.PSG+"FreeBoardController.removeFreeBoardFileByBoardFileNo.boardFileNo: "+ boardFileNo +CF.RESET);
		log.debug(CF.PSG+"FreeBoardController.removeFreeBoardFileByBoardFileNo.BoardNo :" + boardNo + CF.RESET);
		String path = request.getServletContext().getRealPath("/file/freeBoardFile/");
		
		log.debug(CF.PSG+"FreeBoardController.removeFreeBoardFileByBoardFileNo.path :" +path + CF.RESET);
		
		int row = freeBoardService.removeFreeBoardfileByBoardFileNo(boardFileNo, path);
		if (row == 0) {
			log.debug(CF.PSG+"removeFreeBoardFileByBoardFileNo  파일 삭제 실패 "+CF.RESET);
			
		} else {
			log.debug(CF.PSG+"removeFreeBoardFileByBoardFileNo  파일 삭제 성공 "+CF.RESET);
		}
		return "redirect:/modifyFreeBoard?boardNo="+boardNo;
	}
				
}
