package controller;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import security.PasswordEncryptor;
import dbhelper.UserHelper;

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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		try {
			UserHelper helper = new UserHelper();
			
			User u = helper.findUserbyUsername(request.getParameter("username"));
			
			if(u != null) {
				PasswordEncryptor pe = new PasswordEncryptor();
				boolean passwordIsEqual = pe.checkPassword(request.getParameter("password"), u.getPassword(), u.getSalt());
				
				System.out.println(this.getClass().getName() + " - equal password? = " + passwordIsEqual);
				session.setAttribute("username", u.getUsername());
				session.setAttribute("usertype", u.getType());
//				response.sendRedirect("/tie-novelty-shop/Home");
			} else {
//				response.getWriter().write("<script type=\"text/javascript\">");
//				response.getWriter().write("alert('User or password incorrect');");
//				response.getWriter().write("location='" + session.getAttribute("currentpage") + "'");
//				response.getWriter().write("</script>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
	}
	

}
