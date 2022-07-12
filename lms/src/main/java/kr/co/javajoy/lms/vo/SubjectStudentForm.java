package kr.co.javajoy.lms.vo;

import java.util.List;

import lombok.Data;
@Data
public class SubjectStudentForm {
	private int subjectNo;
	private List<String> studentId;
}
