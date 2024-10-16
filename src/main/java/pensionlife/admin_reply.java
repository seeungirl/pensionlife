package pensionlife;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class admin_reply extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con = null;
    dbconfig db = new dbconfig();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession hs = request.getSession();
		String reply = request.getParameter("reply");
		String b_idx = (String)hs.getAttribute("b_idx");
		try {
			this.con = this.db.getConnection();
			String sql = "update qa_board set reply=?, reply_ck='Y', reply_date=now() where b_idx=?";
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setString(1, reply);
			ps.setString(2, b_idx);
			int result = ps.executeUpdate();
			if(result > 0) {
				System.out.println("답변 등록 완료");
			}
			else {
				System.out.println("답변 등록 실패");
			}
			ps.close();
		}
		catch(Exception e) {
			System.out.println("DB접속오류");
		}
		finally {
			try {
				this.con.close();
			}
			catch(Exception e) {
				System.out.println("DB접속해제 오류");
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("./admin_qalist.jsp");
		rd.forward(request, response);
	}

}
