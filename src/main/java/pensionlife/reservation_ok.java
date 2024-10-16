package pensionlife;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class reservation_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;

	dbconfig db = new dbconfig(); //db정보
	PrintWriter pw = null;
	private Connection con = null; //보안 중요
	private Statement st = null;
	private PreparedStatement ps = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String r_id = request.getParameter("r_id");
		String p_code = request.getParameter("p_code");
		
		String r_roomname = request.getParameter("r_rname");
		String r_reservedate = request.getParameter("r_date").replaceAll("T", " ") + ":00";
		String r_reservedate1 = request.getParameter("r_date").split("T")[0];
		
		String r_name = request.getParameter("r_name");
		String r_phone = request.getParameter("r_phone");
		String r_person = request.getParameter("r_person");
		String r_email = request.getParameter("r_email");
		
		try {
			this.pw = response.getWriter();
			this.con = this.db.getConnection();
			
			String sql_ck = "select * from pension_reservation where r_reservedate like ? and p_code=?";
			this.ps = this.con.prepareStatement(sql_ck);
			
			this.ps.setString(1, r_reservedate1+"%");
			this.ps.setString(2, p_code);
			ResultSet rs1 = this.ps.executeQuery();
			
			int count=0;
			while(rs1.next()) {
				count++;
			}
			if(count > 0) {
				this.pw.write("<script>"
				+ "alert('이미 예약이 완료된 객실입니다');"
				+ "location.href='./index.jsp';"
				+ "</script>");
			}else {
				String sql = "insert into pension_reservation (r_idx,m_id,p_code,r_roomname,r_reservedate,r_name,r_phone,r_person,r_email,r_submitdate) "
						+ "values('0','"+ r_id +"','"+ p_code +"','"+ r_roomname +"','"+ r_reservedate +"','"+ r_name +"','"+ r_phone +"','"+ r_person +"','"+ r_email +"',now())";
				this.st = this.con.createStatement(); //sql 문법 실행시키는 라이브러리
				int result = this.st.executeUpdate(sql); //실행 및 결과값을 return(숫자로 return)
				this.pw = response.getWriter();
				
				if(result>0) { //정상 query 작동
					this.pw.write("<script>"
							+ "alert('예약이 완료되었습니다');"
							+ "location.href='./index.jsp';"
							+ "</script>");
				}else { //오류 query 작동
					this.pw.write("<script>"
							+ "alert('회원가입 실패!');"
							+ "location.href='./index.jsp';"
							+ "</script>");
				}	
			}
		}catch(Exception e) {
			System.out.println("db연결 실패");
		}finally {
			try {
				this.pw.close();
				this.ps.close();
				this.st.close();
				this.con.close();
			}catch(Exception e) {
				System.out.println("database 접속해제 실패");
			}
		}
	}

}
