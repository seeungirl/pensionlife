package pensionlife;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con = null;
    PrintWriter pw = null;
    PreparedStatement ps = null;
    HttpSession hs = null;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("mid");
		String pw = request.getParameter("mpw");
		String auto_login =(String)request.getParameter("login_auto");
		
		if(auto_login==null) {
			auto_login="false";
		}		
		try {
			
			dbconfig db = new dbconfig();
			this.con = db.getConnection();
			//가져올 컬럼 값
			String mname="";
			String mhp="";
			String memail="";
				
			if(auto_login.equals("true")) { //자동로그인 
				
				String sql = "select * from pension_member where m_id='"+id+"'and m_pass='"+pw+"'";
				this.ps = this.con.prepareStatement(sql);
				ResultSet rs = this.ps.executeQuery();
				this.pw=response.getWriter();
				
				while(rs.next()) {
					mname= rs.getString("m_name");
					mhp = rs.getString("m_hp");
					memail = rs.getString("m_email");
				}
				if(mname.equals("")) {
					this.pw.write("<script>"
							+ "alert('아이디 및 비밀번호를 확인하세요');"
							+ "history.go(-1);"
							+ "</script>");
				}else {
					//자동로그인 세션걸기
					
					this.hs = request.getSession();
					this.hs.setAttribute("id", id);
					
					ArrayList<String> all = new ArrayList<String>();
					all.add(id);
					all.add(mname);
					all.add(mhp);
					all.add(memail);
					this.hs.setAttribute("user", all); //세션 생성
					
					this.pw.write("<script>"
							+ "alert('로그인 되셨습니다.');"
							+ "localStorage.setItem('auto','"+auto_login+"');"
							+ "localStorage.setItem('auto_id','"+id+"');"
							+ "location.href='./index.jsp';"
							+ "</script>");
				}
				this.pw.close();
				rs.close();								
			}else {				//자동로그인 x
				String sql = "select * from pension_member where m_id='"+id+"'and m_pass='"+pw+"'";
				this.ps = this.con.prepareStatement(sql);
				ResultSet rs = this.ps.executeQuery();
				this.pw=response.getWriter();				
				
				while(rs.next()) {
					mname= rs.getString("m_name");
				}
				if(mname.equals("")) {
					this.pw.write("<script>"
							+ "alert('아이디 및 비밀번호를 확인하세요');"
							+ "history.go(-1);"
							+ "</script>");
				}else {
					//세션 생성
					this.hs = request.getSession();
					this.hs.setAttribute("id", id);
					
					ArrayList<String> all = new ArrayList<String>();
					all.add(id);
					all.add(mname);
					all.add(mhp);
					all.add(memail);
					this.hs.setAttribute("user", all); //세션 생성
					
					this.pw.write("<script>"
							+ "alert('로그인 되셨습니다.');"
							+ "location.href='./index.jsp';"
							+ "</script>");
				}
				this.pw.close();
				rs.close();
			}			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("접속 오류");
		}finally {
			try {
				this.ps.close();
				this.con.close();
			} catch (Exception e2) {
				System.out.println("e2");
				e2.printStackTrace();
			}
		}
	}
}
