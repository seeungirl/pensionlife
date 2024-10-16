package pensionlife;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class m_idsearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con = null;
       PrintWriter pw = null;
       PreparedStatement ps = null;
       HttpSession hts = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json");
		//아이디 찾기
		String fname = request.getParameter("fname");
		String fhp = request.getParameter("fhp");
		String femail = request.getParameter("femail");		
				
		try {		
			dbconfig db = new dbconfig();
			this.con = db.getConnection();
		
			String sql = "select * from pension_member where m_name='"+fname+"' and m_hp='"+fhp+"' and m_email='"+femail+"'";
			this.ps = this.con.prepareStatement(sql);
			ResultSet rs = this.ps.executeQuery();
			
			this.pw = response.getWriter();
			
			String findout_id = "";
			
			while(rs.next()) {
				findout_id = rs.getString("m_id");
			}
			
			if(findout_id.equals("")) { //검색한 고객정보가 일치하지 않을 때
				this.pw.write("");
			}else { //검색한 고객정보가 일치할 때				
				this.pw.write(findout_id);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				this.pw.close();
				this.ps.close();
				this.con.close();
				
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	
	}
}
