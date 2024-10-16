package pensionlife;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class go_reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	dbconfig db = new dbconfig(); 
	Connection con = null;
	PreparedStatement ps = null;
	ArrayList<ArrayList<String>> result = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		
		String p_code = request.getParameter("pcode");
		String pcode = p_code.substring(0,2);
		
		try {
			this.con = this.db.getConnection();
			String sql = "select * from pension_list where p_code like ? ";
			//select * from pension_list where p_code like ? 
		
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, pcode+"%");
			ResultSet rs = this.ps.executeQuery();
			
			ArrayList<String> arr = null;
			this.result = new ArrayList<ArrayList<String>>();
			
			pw.write("var data = new Array();");
			while(rs.next()) {
				pw.write("var data2 = new Array();");
				arr = new ArrayList<String>();
				String p_name = rs.getString("p_name");
				String p_roomname = rs.getString("p_roomname");
				String p_roomtype = rs.getString("p_roomtype");
				String p_person = rs.getString("p_person");
				String p_maxperson = rs.getString("p_maxperson");
				String p_price = rs.getString("p_price");
				String p_img = rs.getString("p_img");
				String p_code1 = rs.getString("p_code");
				
				pw.write("data2.push('"+ p_name +"');");
				pw.write("data2.push('"+ p_roomname +"');");
				pw.write("data2.push('"+ p_roomtype +"');");
				pw.write("data2.push('"+ p_person +"');");
				pw.write("data2.push('"+ p_maxperson +"');");
				pw.write("data2.push('"+ p_price +"');");
				pw.write("data2.push('"+ p_img +"');");
				pw.write("data2.push('"+ p_code1 +"');");
				pw.write("data.push(data2);");
			}
			
		}catch(Exception e) {
			System.out.println("잘못된 접근");
		}finally {
			try {
				this.pw.close();
				this.ps.close();
				this.con.close();
			}catch(Exception e2) {
				
			}
		}
	}

}
