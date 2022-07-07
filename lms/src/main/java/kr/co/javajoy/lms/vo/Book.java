package kr.co.javajoy.lms.vo;

import lombok.Data;

@Data
public class Book {
	private int bookNo;
	private String bookTitle;
	private String bookWriter;
	private String bookCompany;
	private String createDate;
	private String updateDate;
}
