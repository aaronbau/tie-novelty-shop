package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbhelper.DBUtilities;
import model.User;

/**
 * Servlet implementation class CreateAdministrator
 */
@WebServlet("/CreateAdministrator")
public class CreateAdministrator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAdministrator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("createadministrator.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBUtilities db = new DBUtilities();
		
		User u = new User(request.getParameter("username").toString(), request.getParameter("email").toString(), request.getParameter("password").toString(), "Administrator");
		
		try {
			if(!(u.getUsername() == "" || u.getUsername() == null))	{
				db.addUser(u);
				response.sendRedirect("/tie-novelty-shop/Login");
			} else
				response.sendRedirect("/tie-novelty-shop/CreateAdministrator");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("/tie-novelty-shop/Home");
		}
	}

}
