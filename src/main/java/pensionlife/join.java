package pensionlife;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con = null;
       PrintWriter pw = null;
       PreparedStatement ps = null;
       HttpSession hs = null;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String mid = request.getParameter("mid");
		String mname = request.getParameter("mname");
		String mpw  = request.getParameter("mpw");
		String memail = request.getParameter("memail");
		String mhp = request.getParameter("mhp");
		String ag = request.getParameter("mag");
		
	
		
		try {
			
			dbconfig db = new dbconfig();
			this.con = db.getConnection();
			
			String sql = "insert into pension_member(m_idx,agree,m_id,m_name,m_pass,m_email,m_hp,m_date) "
					+ "values('0','"+ag+"','"+mid+"','"+mname+"','"+mpw+"','"+memail+"','"+mhp+"',now())";
			this.ps = this.con.prepareStatement(sql);
			int result = this.ps.executeUpdate();
			
			this.pw = response.getWriter();
			if(result>0) {
				
				HttpSession hs = request.getSession();
				ArrayList<String> al = new ArrayList<String>();
				al.add(mid);
				al.add(mname);
				al.add(mhp);
				al.add(memail);

				hs.setAttribute("user", al);
				
				this.pw.write("<script>"
						+ "alert('회원가입이 완료되었습니다.');"
						+ "location.href='./index.jsp';"
						+ "</script>");
			}
			
		} catch (Exception e) {
			this.pw.write("<script>"
					+ "alert('회원가입이 정상적으로 완료되지 못하였습니다.')"
					+ "</script>");
		}finally {
			try {
				this.pw.close();
				this.ps.close();
				this.con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
