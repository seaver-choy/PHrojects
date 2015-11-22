package phrojects;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import phrojects.model.interface_dao.AccountDAO;
import phrojects.model.interface_dao.ProjectDAO;
import phrojects.model.mysql_dao.MySQLAccountDAO;
import phrojects.model.mysql_dao.MySQLProjectDAO;
import phrojects.model.objects.Account;
import phrojects.model.objects.Proposal;

/**
 * Servlet implementation class CreateProjectServlet
 */
@WebServlet("/CreateProjectServlet")
public class CreateProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String department = request.getParameter("department");
		String priority = request.getParameter("priority");
		String regions = request.getParameter("regions");
		
		Proposal proposal = new Proposal();
		proposal.setTitle(title);
		proposal.setDescription(description);
		proposal.setDepartment(department);
		proposal.setPriority(priority);
		proposal.setRegions(regions);
		
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		AccountDAO accountDAO = new MySQLAccountDAO();
		Account account = accountDAO.getAccount(username);
		
		
		ProjectDAO projectDAO = new MySQLProjectDAO();
		projectDAO.createProposedProject(proposal, account);
		PrintWriter out = response.getWriter();

		javax.servlet.RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		out.print("<script>alert('Project successfully created! Your project will be evaluated by the administrators.');</script>");
		rd.include(request, response);
	}

}
