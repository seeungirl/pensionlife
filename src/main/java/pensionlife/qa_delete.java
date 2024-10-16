package pensionlife;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class qa_delete extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    dbconfig db = new dbconfig();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    	    // 데이터베이스 연결 설정
    	    this.con = this.db.getConnection();
    	    
    	    // 해당 문의 정보를 데이터베이스에서 삭제하기 전에 파일 경로를 조회
    	    String b_idx = request.getParameter("b_idx");
    	    String sql = "SELECT rfile1, rfile2 FROM qa_board WHERE b_idx=?";
    	    ps = con.prepareStatement(sql);
    	    ps.setString(1, b_idx);
    	    rs = ps.executeQuery();

    	    String rfile1 = null;
    	    String rfile2 = null;

    	    if (rs.next()) {
    	        rfile1 = rs.getString("rfile1");
    	        rfile2 = rs.getString("rfile2");
    	    }
    	    
    	    // 문의 삭제
    	    sql = "DELETE FROM qa_board WHERE b_idx=?";
    	    ps = con.prepareStatement(sql);
    	    ps.setString(1, b_idx);
    	    int result = ps.executeUpdate();

    	    if (result > 0) {
    	        // 실제 파일 삭제 경로 설정
    	        String uploadPath = getServletContext().getRealPath("/upload/");
    	        
    	        if (rfile1 != null) {
    	            File file1 = new File(uploadPath + rfile1);
    	            if (file1.exists() && file1.isFile()) {
    	                if (file1.delete()) {
    	                    System.out.println("rfile1 삭제 성공");
    	                } else {
    	                    System.out.println("rfile1 삭제 실패");
    	                }
    	            }
    	        }

    	        if (rfile2 != null) {
    	            File file2 = new File(uploadPath + rfile2);
    	            if (file2.exists() && file2.isFile()) {
    	                if (file2.delete()) {
    	                    System.out.println("rfile2 삭제 성공");
    	                } else {
    	                    System.out.println("rfile2 삭제 실패");
    	                }
    	            }
    	        }
    	        System.out.println("DB 삭제 성공");
    	    } else {
    	        System.out.println("DB 삭제 실패");
    	    }
    	} catch (SQLException sqle) {
    	    sqle.printStackTrace();
    	    System.out.println("DB 접속 오류: " + sqle.getMessage());
    	} catch (Exception e) {
    	    e.printStackTrace();
    	    System.out.println("기타 오류: " + e.getMessage());
    	} finally {
    	    try {
    	        if (rs != null) rs.close();
    	        if (ps != null) ps.close();
    	        if (con != null) con.close();
    	    } catch(Exception e) {
    	        System.out.println("리소스 닫기 오류: " + e.getMessage());
    	    }
    	}

        
        RequestDispatcher rd = request.getRequestDispatcher("./m_qalist.jsp");
        rd.forward(request, response);
    }
}
