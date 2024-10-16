package pensionlife;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@MultipartConfig(
    fileSizeThreshold = 1024*1024*2,
    maxFileSize = 1024*1024*5,
    maxRequestSize = 1024*1024*10
)
public class qa_modify extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection con = null;
    dbconfig db = new dbconfig();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String b_idx = request.getParameter("b_idx");
        String b_subj = request.getParameter("b_subj");
        String b_post = request.getParameter("b_post");
        Part rfile1 = request.getPart("rfile1");
        Part rfile2 = request.getPart("rfile2");
        
        try {
            this.con = this.db.getConnection();
            System.out.println("DB 연결 성공");

            // 기존 파일 이름 가져오기
            String query = "SELECT rfile1, rfile2 FROM qa_board WHERE b_idx = ?";
            PreparedStatement psSelect = this.con.prepareStatement(query);
            psSelect.setString(1, b_idx);
            ResultSet rs = psSelect.executeQuery();
            String oldFileName1 = null;
            String oldFileName2 = null;
            
            if (rs.next()) {
                oldFileName1 = rs.getString("rfile1");
                oldFileName2 = rs.getString("rfile2");
            }
            rs.close();
            psSelect.close();

            String fileName1 = oldFileName1;
            if (rfile1 != null && rfile1.getSize() > 0) {
                String originalFileName1 = Paths.get(rfile1.getSubmittedFileName()).getFileName().toString();
                fileName1 = new rename(originalFileName1).filenm;
            }

            String fileName2 = oldFileName2;
            if (rfile2 != null && rfile2.getSize() > 0) {
                String originalFileName2 = Paths.get(rfile2.getSubmittedFileName()).getFileName().toString();
                fileName2 = new rename(originalFileName2).filenm;
            }

            String sql_update = "UPDATE qa_board SET b_subj=?, b_post=?, rfile1=?, rfile2=?, b_date=NOW() WHERE b_idx=?";
            PreparedStatement psUpdate = this.con.prepareStatement(sql_update);
            psUpdate.setString(1, b_subj);
            psUpdate.setString(2, b_post);
            psUpdate.setString(3, fileName1);
            psUpdate.setString(4, fileName2);
            psUpdate.setString(5, b_idx);

            int result = psUpdate.executeUpdate();
            if(result > 0) {
                String uploadPath = getServletContext().getRealPath("/upload/");
                System.out.println("업로드 경로: " + uploadPath);
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();

                // 기존 파일 삭제
                if (rfile1 != null && rfile1.getSize() > 0 && oldFileName1 != null) {
                    File oldFile1 = new File(uploadDir, oldFileName1);
                    if (oldFile1.exists()) oldFile1.delete();
                }
                if (rfile2 != null && rfile2.getSize() > 0 && oldFileName2 != null) {
                    File oldFile2 = new File(uploadDir, oldFileName2);
                    if (oldFile2.exists()) oldFile2.delete();
                }

                // 새로운 파일 저장
                if (rfile1 != null && rfile1.getSize() > 0) {
                    try (InputStream is = rfile1.getInputStream();
                         FileOutputStream fs = new FileOutputStream(new File(uploadDir, fileName1))) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = is.read(buffer)) != -1) {
                            fs.write(buffer, 0, bytesRead);
                        }
                        System.out.println("파일1 저장 성공");
                    } catch (Exception e) {
                        System.out.println("파일1 저장 오류: " + e.getMessage());
                    }
                }

                if (rfile2 != null && rfile2.getSize() > 0) {
                    try (InputStream is = rfile2.getInputStream();
                         FileOutputStream fs = new FileOutputStream(new File(uploadDir, fileName2))) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = is.read(buffer)) != -1) {
                            fs.write(buffer, 0, bytesRead);
                        }
                        System.out.println("파일2 저장 성공");
                    } catch (Exception e) {
                        System.out.println("파일2 저장 오류: " + e.getMessage());
                    }
                }

                System.out.println("DB 저장완료");
            } else {
                System.out.println("업데이트 실패");
            }
            psUpdate.close();
        } catch(SQLException sqle) {
            System.out.println("DB 접속 오류: " + sqle.getMessage());
        } catch(Exception e) {
            System.out.println("기타 오류: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                    System.out.println("DB 연결 종료");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("./m_qalist.jsp");
        rd.forward(request, response);
    }
}
