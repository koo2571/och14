<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>jsp로 파일 업로드</h2>
<form action="upload.jsp" method="post" enctype="multipart/form-data">
	<label for="name">작성자</label>
	<input type="text" name="name" required="required"><p>
	제목 : <input type="text" name="title" required="required"><p>
	파일명1 : <input type="file" name="uploadFile1" required="required"><p>
	파일명2 : <input type="file" name="uploadFile2" required="required"><p>
	<input type="submit" value="확인">
</form>
</body>
</html>