package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

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
		HttpSession session = request.getSession();
		session.setAttribute("currentpage", "CreateAdministrator");
		RequestDispatcher rd = request.getRequestDispatcher("createadministrator.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBUtilities db = new DBUtilities();
		HttpSession session = request.getSession();
		
		String username = Jsoup.clean(request.getParameter("username"), Whitelist.basic());
		String email = Jsoup.clean(request.getParameter("email"), Whitelist.basic());
		String password = Jsoup.clean(request.getParameter("password"), Whitelist.basic());
		
		User u = new User(username, email, password, "Administrator");
		
		try {
			if(!(u.getUsername() == "" || u.getUsername() == null))	{
				db.addUser(u);
				response.getWriter().write("<script type=\"text/javascript\">");
				response.getWriter().write("alert('Administrator successfully created');");
				response.getWriter().write("location='AdminControls'");
				response.getWriter().write("</script>");

				db.writeLog("[POST] CreateAdministrator.java - Administrator " + u.toString() + " was created by " + session.getAttribute("username") + " " + session.getAttribute("usertype"), new Date());
			} else
				response.getWriter().write("<script type=\"text/javascript\">");
			response.getWriter().write("alert('Username already taken');");
			response.getWriter().write("location='" + session.getAttribute("currentpage") + "'");
			response.getWriter().write("</script>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("/tie-novelty-shop/Home");
		}
	}

}
