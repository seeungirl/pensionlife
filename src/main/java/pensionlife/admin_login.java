package pensionlife;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class admin_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con = null;
    PrintWriter pw = null;
    PreparedStatement ps = null;
    HttpSession hs_in = null;
    dbconfig db = new dbconfig();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
		
		String admin_id = request.getParameter("ad_id");
		String admin_pw = request.getParameter("ad_pw");
		String admin_name = "";

		String idx="";
		
		try {
			this.con = this.db.getConnection();
			String sql = "select * from admin_user where a_id='"+admin_id+"'and a_pass='"+admin_pw+"'";
			this.ps=this.con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				idx = rs.getString("a_idx");
				admin_name = rs.getString("a_name");
				System.out.println(admin_name);
			}
			this.pw=response.getWriter();
			
			if(idx.equals("")) {
				this.pw.write("<script>"
						+ "alert('아이디 및 비밀번호를 확인해주십시오.');"
						+ "history.go(-1);"
						+ "</script>");
			}else { //있을 때 세션 걸기
				this.hs_in = request.getSession();
				this.hs_in.setAttribute("admin_id", admin_id);	
				this.hs_in.setAttribute("admin_name", admin_name);	
				this.pw.write("<script>"
						+ "alert('로그인 되셨습니다.');"
						+ "location.href='./admin_qalist.jsp';"
						+ "</script>");	
			}
			this.pw.flush();
			this.pw.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			try {
				this.ps.close();
				this.con.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
	}

}
