package pensionlife;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class agree extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ag = request.getParameter("ag");
		
		if(ag==null) {
			ag="N";
		}
		request.setAttribute("ag", ag);
		
		RequestDispatcher rd = request.getRequestDispatcher("./m_join2.jsp");
		rd.forward(request, response);
		
	}
}
