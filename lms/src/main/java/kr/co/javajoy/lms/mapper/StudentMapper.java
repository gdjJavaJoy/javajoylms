package kr.co.javajoy.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.javajoy.lms.vo.Student;
import kr.co.javajoy.lms.vo.StudentJob;

@Mapper
public interface StudentMapper {
	// 학생 정원 구하기
	public int totalStudent();
	// 남자 학생 수
	public int totalStudentOfMan();
	// 여자 학생 수
	public int totalStudentOfWoman();
	// 학생 상세보기
	public Map<String,Object> selectStudentOne(String memberId);
	// 학생 수정
	public void updateStudentOne(Student student);
	// 취업 학생 리스트 
	public List<Map<String,Object>> selectEmploytedStudentList(Map<String,Object> map);
	// 취업 학생 총 수
	public int totalEmployedStudent();
	// 취업 학생 등록
	public void insertEmployedStudent(StudentJob studentJob);
	// 취업 학생 삭제
	public void deleteEmployedStudent(String memberId);
	// 취업 학생 수정을 위한 학생 출력
	public StudentJob selectEmployedStudentOne(String memberId);
	// 취업 학생 수정
	public void updateEmployedStudent(StudentJob studentJob);
	// 학생 최종학력 수
	public List<Map<String,Object>> studentEducationRate();
	// 취업한 학생 초봉 평균
	public Map<String,Object> employedStudentFirstSalaryRate();
}
