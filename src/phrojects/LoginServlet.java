package phrojects;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import phrojects.model.interface_dao.AccountDAO;
import phrojects.model.mysql_dao.MySQLAccountDAO;
import phrojects.model.objects.Account;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usernameEntered = request.getParameter("username");
		String passwordEntered = request.getParameter("password");
		AccountDAO acctDAO = new MySQLAccountDAO();
		Account acct = acctDAO.getAccount(usernameEntered);
		if (acct != null && acct.getPassword().equals(passwordEntered)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("username", acct.getUsername());
			response.sendRedirect("index.jsp");
		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('Invalid username or password!');</script>");
			RequestDispatcher rd = request.getRequestDispatcher("PHrojectslogin.html");
			rd.include(request, response);
		}
	}

}
