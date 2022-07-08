<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <!-- 필수, SheetJS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.14.3/xlsx.full.min.js"></script>
	<!--필수, FileSaver savaAs 이용 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<h1>다운로드 완료</h1>
</body>
<script>
	let data = [];
	// Ajax json데이터를 이중배열로 변환 
	$.ajax({
		url : '/lms/downLoadExcelSubjectList'
		, type : 'get'
		, success : function(value) {
			$(value).each(function(index, item) {
				let arr = [];
				arr.push(item.subjectNo);
				arr.push(item.teacherName);
				arr.push(item.adminId);
				arr.push(item.subjectName);
				arr.push(item.subjectStudentMax);
				arr.push(item.subjectInfo);
				arr.push(item.subjectStartDate);
				arr.push(item.subjectFinishDate);
				arr.push(item.subjectStartTime);
				arr.push(item.subjectEndTime);
				data.push(arr);
			});
			
			console.log(data);
			
			// 엑셀파일객체 생성 함수
			let wb = XLSX.utils.book_new();
			
			// 생성된 엑셀파일객체에 첫번째 워크시트 이름 설정
			wb.SheetNames.push("subjectList");
			
			// 엑셀파일 데이터를 이용하여 첫번째 워크시트를 생성
			let ws1 = XLSX.utils.aoa_to_sheet(data); // 하나의 엑셀파일객체에 여러개의 시트를 만들어서 추가할 경우 let ws2 = XLSX.utils.aoa.... 사용하여 추가
			
			// 엑셀파일에 생성한 워크시트(ws1)
			wb.Sheets['subjectList'] = ws1; // 여러개의 시트를 추가할 경우 wb.Sheets['두번째워크시트이름'] = 두번째워크시트에 사용할 데이터(이중배열)
			
			// 엑셀파일객체를 엑셀파일로 만들기
			let source = XLSX.write(wb, {bookType:'xlsx',  type: 'binary'});
			
			// source의 크기와 같은 빈 buffer 생성
			let buf = new ArrayBuffer(source.length);
			// 8비트 배열로 빈 buffer를 랩핑함
	        let view = new Uint8Array(buf);
			// 8비트 배열로 랩핑된 buffer안에 source객체를 바이너리 형태로 이동
	        for (var i=0; i<source.length; i++) {
	        	view[i] = source.charCodeAt(i) & 0xFF; //convert to octet
	        }

			// 8비트배열안에 buffer를 application/octet-stream 타입으로 다운로드
			saveAs(new Blob([buf],{type:"application/octet-stream"}), '수강리스트.xlsx');
		}
	});
</script>
</html>