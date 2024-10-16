package pensionlife;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       PrintWriter pw = null;
       HttpSession hs = null;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		this.hs = request.getSession();
		this.hs.invalidate();
		this.pw = response.getWriter();
		this.pw.write("<script>"
				+ "alert('정상적으로 로그아웃 되었습니다.');"
				+ "location.href='./index.jsp';"
				+ "</script>");
		this.pw.close();
	}
}
