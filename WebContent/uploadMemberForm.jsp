<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Servlt로 파일 업로드</h2>
<form action="UploadMember" method="post" enctype="multipart/form-data">
	ID :    <input type="text" name="id" required="required"><p>
	password : <input type="text" name="password" required="required"><p>
	이름 :    <input type="text" name="name" required="required"><p>
	파일명1 : <input type="file" name="uploadFile1" required="required"><p>
	<input type="submit" value="확인">
</form>
</body>
</html>