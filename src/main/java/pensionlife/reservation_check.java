package pensionlife;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class reservation_check extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	dbconfig db = new dbconfig(); 
	Connection con = null;
	PreparedStatement ps = null;
	ArrayList<ArrayList<String>> result = null;
	RequestDispatcher rd = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		
		HttpSession hs = request.getSession();//가져와야함
		String id = (String) hs.getAttribute("id");
		
		try {
			if(id != null) {
				this.con = this.db.getConnection();
				String sql = "select a.*,b.p_name,b.p_roomtype,b.p_person,b.p_maxperson,b.p_price from pension_reservation as a join pension_list as b where a.m_id=? and a.p_code = b.p_code order by r_reservedate asc";
				
				this.ps = this.con.prepareStatement(sql);
				this.ps.setString(1, id);
				ResultSet rs = this.ps.executeQuery();
				
				ArrayList<String> list = null;
				ArrayList<ArrayList<String>> result_list = new ArrayList<ArrayList<String>>();
				while(rs.next()){
					list = new ArrayList<String>();
					
					String p_name = rs.getString("p_name");
					String r_roomname = rs.getString("r_roomname");
					String r_reservedate = rs.getString("r_reservedate");
					String r_roomtype = rs.getString("p_roomtype");
					String p_person = rs.getString("p_person");
					String p_maxperson = rs.getString("p_maxperson");
					String p_price = rs.getString("p_price");

					String r_name = rs.getString("r_name");
					String r_phone = rs.getString("r_phone");
					String r_person = rs.getString("r_person");
					String r_email = rs.getString("r_email");
					
					String r_idx = rs.getString("r_idx"); 
					
					list.add(p_name);
					list.add(r_roomname);
					list.add(r_reservedate);
					list.add(r_roomtype);
					list.add(p_person);
					list.add(p_maxperson);
					list.add(p_price);
					
					list.add(r_name);
					list.add(r_phone);
					list.add(r_person);
					list.add(r_email);
					list.add(r_idx);
					
					
					result_list.add(list);
				}
				request.setAttribute("result_list", result_list);
				
				this.rd = request.getRequestDispatcher("./reservation_ck.jsp");
				this.rd.forward(request, response);
			}else {
				this.pw.write("<script>"
					+ "alert('로그인 후 확인이 가능합니다.');"
					+ "location.href='./index.jsp?rvlogincheck=no';"
					+ "</script>");
			}
		}catch(Exception e) {
			System.out.println("데이터 접속 실패");
		}finally {
			try {
				this.pw.close();
				this.ps.close();
				this.con.close();
			}catch(Exception e2) {
				
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
