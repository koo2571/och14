

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class UploadMember
 */
@WebServlet("/UploadMember")
public class UploadMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UploadMember doPost Start!");
		request.setCharacterEncoding("utf-8");
		int result = 0;
		int maxSize = 5 * 1024 * 1024; //5M
		String fileSave = "/fileSave";
		String realPath = getServletContext().getRealPath(fileSave);
		String fileName = "";
		System.out.println("realPath -> " + realPath);
		MultipartRequest multi = new MultipartRequest(request,realPath,maxSize,"utf-8", new DefaultFileRenamePolicy());
		Enumeration en = multi.getFileNames();
		while(en.hasMoreElements()){ //이미지를 여러장 할수 있음.
			//input 태그의 속성이 file인 태그의 name 속성값 : 피라미터 이름
			String fileName1 = (String)en.nextElement();
			//서버에 저장된 파일 이름
			fileName = multi.getFilesystemName(fileName1);
			//전송전 원래의 이름
			String original = multi.getOriginalFileName(fileName1);
			//전송된 파일의 내용 타입
			String type = multi.getContentType(fileName1);
			//전송된 파일속성이 file인 태그의 name 속성값을 이용해 파일객체 생성
			File file = multi.getFile(fileName1);
			System.out.println("real path : "+realPath);
			System.out.println("파라메터 이름 : "+fileName1);
			System.out.println("실제 파일 이름 : "+original);
			System.out.println("저장된 파일 이름 : "+fileName);
			System.out.println("파일 타입 : "+type+"<br>" );
			if(file != null){
				System.out.println("크기 : "+file.length());
			}
		}
		//           이미지를 보낼때는 MultipartRequest 를 사용
		String id = multi.getParameter("id");
		String password = multi.getParameter("password");
		String name = multi.getParameter("name");
		
		MemberDao md = new MemberDao();
		MemberDto member = new MemberDto();
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setImage("fileSave\\"+fileName);
		try {
			result = md.insert(member);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("id", id);
		request.setAttribute("password", password);
		request.setAttribute("name", name);
		request.setAttribute("result", result);
		request.setAttribute("fileName", "fileSave\\"+fileName);
		
		RequestDispatcher rd = request.getRequestDispatcher("uploadMemberResult.jsp");
		rd.forward(request, response);
	}

}
