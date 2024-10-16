package pensionlife;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class reservation_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	dbconfig db = new dbconfig(); //db정보
	PrintWriter pw = null;
	private Connection con = null; //보안 중요
	private PreparedStatement ps = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		
		String idx = request.getParameter("r_idx");
		System.out.println(idx);
		try {
			this.con = this.db.getConnection();
			
			String sql = "delete from pension_reservation where r_idx=?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, idx);
			
			int result = ps.executeUpdate();
			if(result > 0){
				this.pw.write("<script>"
						+ "alert('예약 취소가 완료되었습니다');"
						+ "location.href='./reservation_check.jsp';"
						+ "</script>");
			}else {
				
			}		
		}catch(Exception e) {
			System.out.println("db연결 실패");
		}finally {
			try {
				this.pw.close();
				this.ps.close();
				this.con.close();
			}catch(Exception e){
				System.out.println("database 접속해제 실패");
			}
		}
	}

}
