package kr.co.javajoy.lms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class InsertMemberPhotoForm {
	private String memberId;
	private List<MultipartFile> memberPhotoList;
}
