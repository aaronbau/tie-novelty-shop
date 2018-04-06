package controller;


import java.io.IOException;
import java.sql.SQLException;

import javax.crypto.spec.PBEKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbhelper.DBUtilities;
import dbhelper.UserHelper;
import model.User;
import security.PasswordEncryptor;
import security.RandomStringGenerator;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
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
		
		String username = request.getParameter("username").toString();
		String email = request.getParameter("email").toString();
		String password = request.getParameter("password").toString();
		
		String okgo = request.getParameter("okgo").toString();
		
		String jsonResponse = "{";
		
		try 
		{
			UserHelper helper = new UserHelper();
			
			boolean newUser = helper.findUserbyUsername(username) == null;
			boolean goodPassword = true;
			
			if(!newUser)
			{
				jsonResponse = jsonResponse.concat("\"username\":\"Username is already taken\"");
			}
			
			if(!goodPassword)
			{
				
			}
			
			if(newUser && goodPassword && okgo.equals("yes"))
			{
				String salt = new RandomStringGenerator().generateRandomString(5);
				PasswordEncryptor pe = new PasswordEncryptor();
				password = pe.encryptPassword(password, salt);
				
				User u = new User(username, email, password, "User", salt);
				helper.addUser(u);
				
				HttpSession session = request.getSession();
				session.setAttribute("username", u.getUsername());
				session.setAttribute("usertype", u.getType());
				
				response.sendRedirect("/tie-novelty-shop/Home");
			}
			
			response.getWriter().write(jsonResponse + "}");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
