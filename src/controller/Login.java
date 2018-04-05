package controller;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

import dbhelper.DBUtilities;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
//		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBUtilities db = new DBUtilities();
		User u;
		HttpSession session = request.getSession();
		
		try {
			u = (User) db.login(request.getParameter("username"), request.getParameter("password"));
			if(u != null) {
				session.setAttribute("username", u.getUsername());
				session.setAttribute("usertype", u.getType());
				
				Cookie loginCookie = new Cookie("user", u.getUsername());
				loginCookie.setMaxAge(30 * 60); // expires in 30 mins
				response.addCookie(loginCookie);
			
				response.sendRedirect("/tie-novelty-shop/Home");
			} else {
				response.getWriter().write("<script type=\"text/javascript\">");
				response.getWriter().write("alert('User or password incorrect');");
				response.getWriter().write("location='" + session.getAttribute("currentpage") + "'");
				response.getWriter().write("</script>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	

}
