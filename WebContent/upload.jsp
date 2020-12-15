<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	int maxSize = 5 * 1024 * 1024; //5M
	String fileSave = "/fileSave";
	String realPath = getServletContext().getRealPath(fileSave);
	System.out.println("realPath -> " + realPath);
	MultipartRequest multi = new MultipartRequest(request,realPath,maxSize,"utf-8", new DefaultFileRenamePolicy());
	Enumeration en = multi.getFileNames();
	while(en.hasMoreElements()){ //이미지를 여러장 할수 있음.
		//input 태그의 속성이 file인 태그의 name 속성값 : 피라미터 이름
		String fileName1 = (String)en.nextElement();
		//서버에 저장된 파일 이름
		String fileName = multi.getFilesystemName(fileName1);
		//전송전 원래의 이름
		String original = multi.getOriginalFileName(fileName1);
		//전송된 파일의 내용 타입
		String type = multi.getContentType(fileName1);
		//전송된 파일속성이 file인 태그의 name 속성값을 이용해 파일객체 생성
		File file = multi.getFile(fileName1);
		out.println("real path : "+realPath+"<br>" );
		out.println("파라메터 이름 : "+fileName1+"<br>" );
		out.println("실제 파일 이름 : "+original+"<br>" );
		out.println("저장된 파일 이름 : "+fileName+"<br>" );
		out.println("파일 타입 : "+type+"<br>" );
		if(file != null){
			out.println("크기 : "+file.length()+"<br>" );
		}
	}
	//           이미지를 보낼때는 MultipartRequest 를 사용
	String name = multi.getParameter("name");
	String title = multi.getParameter("title");
%>
작성자 : <%=name %><p>
제목 : <%=title %>
</body>
</html>