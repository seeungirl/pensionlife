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

public class m_pwsearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con = null;
    PrintWriter pw = null;
    PreparedStatement ps = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/x-json");
		
		String mid = request.getParameter("mid");
		String mname = request.getParameter("mname");
		String mhp = request.getParameter("mhp");
		
		try {
			dbconfig db = new dbconfig();
			this.con = db.getConnection();

			String sql = "select * from pension_member where m_id='"+mid+"' and m_hp='"+mhp+"' and m_name='"+mname+"'";
			
			this.ps=this.con.prepareStatement(sql);
			ResultSet rs = this.ps.executeQuery();
			
			this.pw = response.getWriter();
			
			String findout_pw = "";
			
			while(rs.next()) {
				findout_pw = rs.getString("m_pass");
			}

			if(findout_pw.equals("")) {
				this.pw.write("");
			}else {
				this.pw.write(findout_pw);
			}		
		} catch (Exception e) {
			System.out.println(e);
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
