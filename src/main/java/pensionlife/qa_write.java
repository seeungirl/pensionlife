package pensionlife;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(
    fileSizeThreshold = 1024*1024*2,
    maxFileSize = 1024*1024*5,
    maxRequestSize = 1024*1024*10
)
public class qa_write extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection con = null;
    dbconfig db = new dbconfig();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String b_cate = request.getParameter("selectedValue");
        String m_id = request.getParameter("m_id");
        String m_name = request.getParameter("m_name");
        String m_hp = request.getParameter("m_hp");
        String m_email = request.getParameter("m_email");
        String b_subj = request.getParameter("b_subj");
        String b_post = request.getParameter("b_post");
        Part rfile1 = request.getPart("rfile1");
        Part rfile2 = request.getPart("rfile2");
        try {
            this.con = this.db.getConnection();
            
            String fileName1 = null;
            if (rfile1 != null && rfile1.getSize() > 0) {
                String originalFileName1 = Paths.get(rfile1.getSubmittedFileName()).getFileName().toString();
                fileName1 = new rename(originalFileName1).filenm;
            }

            String fileName2 = null;
            if (rfile2 != null && rfile2.getSize() > 0) {
                String originalFileName2 = Paths.get(rfile2.getSubmittedFileName()).getFileName().toString();
                fileName2 = new rename(originalFileName2).filenm;
            }
            
            String sql_insert = "insert into qa_board(b_idx,b_cate,m_id,m_name,m_hp,m_email,b_subj,b_post,rfile1,rfile2,b_date) values (0,?,?,?,?,?,?,?,?,?,now())";
            PreparedStatement psInsert = this.con.prepareStatement(sql_insert);
            psInsert.setString(1, b_cate);
            psInsert.setString(2, m_id);
            psInsert.setString(3, m_name);
            psInsert.setString(4, m_hp);
            psInsert.setString(5, m_email);
            psInsert.setString(6, b_subj);
            psInsert.setString(7, b_post);
            psInsert.setString(8, fileName1);
            psInsert.setString(9, fileName2);

            int result = psInsert.executeUpdate();
            if(result > 0) {
                String uploadPath = getServletContext().getRealPath("/upload/");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();

                if (fileName1 != null) {
                    try (InputStream is = rfile1.getInputStream();
                         FileOutputStream fs = new FileOutputStream(new File(uploadDir, fileName1))) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = is.read(buffer)) != -1) {
                            fs.write(buffer, 0, bytesRead);
                        }
                    } catch (Exception e) {
                        System.out.println("파일1 저장 오류: " + e.getMessage());
                    }
                }

                if (fileName2 != null) {
                    try (InputStream is = rfile2.getInputStream();
                         FileOutputStream fs = new FileOutputStream(new File(uploadDir, fileName2))) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = is.read(buffer)) != -1) {
                            fs.write(buffer, 0, bytesRead);
                        }
                    } catch (Exception e) {
                        System.out.println("파일2 저장 오류: " + e.getMessage());
                    }
                }
                System.out.println("DB 저장완료");
            }
            psInsert.close();
        } catch(SQLException sqle) {
            System.out.println("DB 접속오류: " + sqle.getMessage());
        } catch(Exception e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("./m_qalist.jsp");
        rd.forward(request, response);
    }
}
