package pensionlife;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	dbconfig db = new dbconfig();
	Connection con = null;
	PreparedStatement ps = null;
	Statement st = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		try {
			this.con = this.db.getConnection();
			
			String sql = "insert into pension_member(m_idx,agree,m_id,m_name,m_pass,m_email,m_hp,m_date) "
					+ "values('0','0','0','0','0','0','0',now())";
			
//			String sql = "select * from pension_list where p_idx='1'";
			
			
			this.st = this.con.createStatement();
			int result = this.st.executeUpdate(sql);
			
			if(result > 0) {
				System.out.println("ok");
			}else {
				System.out.println("no");
			}
			
		}catch(Exception e) {
			System.out.println("오류");
		}finally {
			try {
				this.st.close();
				this.con.close();
			}catch(Exception e2) {
				
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
	}

}
