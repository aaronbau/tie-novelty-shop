package controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

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
		
		DBUtilities db = new DBUtilities();
		
		String username = Jsoup.clean(request.getParameter("username"), Whitelist.basic());
		String email = Jsoup.clean(request.getParameter("email"), Whitelist.basic());
		String password = Jsoup.clean(request.getParameter("password"), Whitelist.basic());
		
		String jsonResponse = "{";
		
		try 
		{
			UserHelper helper = new UserHelper();
			boolean isNewUser = false;
			boolean isGoodPassword = false;
	
			if(username != null)
			{	
				if(username.length() < 3)
					jsonResponse = jsonResponse.concat("\"username\":\"Username should contain at least 3 characters.\"");
				else
				{
					isNewUser = helper.findUserbyUsername(username) == null;
					System.out.println(isNewUser);
					
					jsonResponse = isNewUser ? jsonResponse.concat("") : jsonResponse.concat("\"username\":\"Username is already taken\"");
				}
			}
			
			if(password != null)
			{
				isGoodPassword = true;
			}
			
			if(isNewUser && isGoodPassword)
			{
				String salt = new RandomStringGenerator().generateRandomString(5);
				PasswordEncryptor pe = new PasswordEncryptor();
				password = pe.encryptPassword(password, salt);
				
				User u = new User(username, email, password, "User", salt);
				helper.addUser(u);
				
				HttpSession session = request.getSession();
				session.setAttribute("username", u.getUsername());
				session.setAttribute("usertype", u.getType());
				
				db.writeLog("[GET] Signup.java - Success - " + u.getUsername()  + " " + u.getType(), new Date());
				
				response.sendRedirect("/tie-novelty-shop/Home");
			}
			response.getWriter().write(jsonResponse + "}");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
