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
import dbhelper.UserHelper;
import model.User;
import security.PasswordEncryptor;
import security.PasswordEstimator;
import security.RandomStringGenerator;

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
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String jsonResponse = "{";
		
		try 
		{
			UserHelper helper = new UserHelper();
			boolean isGoodUser = false;
			boolean isGoodPassword = false;
			boolean isGoodEmail = false;
	
			if(username != null)
			{	
				String cleanUsername = Jsoup.clean(username, Whitelist.basic());
				if(!cleanUsername.equals(username))
					jsonResponse = jsonResponse.concat("\"username\":\"Invalid username.\"");
				else if(username.length() < 3)
					jsonResponse = jsonResponse.concat("\"username\":\"Username should contain at least 3 characters.\"");
				else
				{
					isGoodUser = helper.findUserbyUsername(username) == null;
					jsonResponse = isGoodUser ? jsonResponse.concat("\"username\":\"yes\"") : jsonResponse.concat("\"username\":\"Username is already taken\"");
				}
			}
			
			if(email != null)
			{
				String cleanEmail = Jsoup.clean(email, Whitelist.basic());
				if(!cleanEmail.equals(email))
					jsonResponse = jsonResponse.concat("\"email\":\"Invalid email.\"");
				else
				{
					isGoodEmail = true;
					jsonResponse = jsonResponse.concat("\"email\":\"yes\"");
				}
			}
			
			if(password != null)
			{
				PasswordEstimator pe = new PasswordEstimator();
				int strength = pe.estimatePassword(password);
				if(strength > 0)
				{
					isGoodPassword = true;
					jsonResponse = jsonResponse.concat("\"password\":\"yes\",");
				}
				else
					jsonResponse = jsonResponse.concat("\"password\":\"no\",");
				
				
				jsonResponse = jsonResponse.concat("\"strength\":" + strength );
			}
			
			if(isGoodUser && isGoodPassword && isGoodEmail)
			{
				String salt = new RandomStringGenerator().generateRandomString(5);
				PasswordEncryptor pe = new PasswordEncryptor();
				password = pe.encryptPassword(password, salt);
				
				User u = new User(username, email, password, "Administrator", salt);
				helper.addUser(u);
				
				HttpSession session = request.getSession();
				
				db.writeLog("[POST] CreateAdministrator.java - Administrator " + u.toString() + " was created by " + session.getAttribute("username") + " " + session.getAttribute("usertype"), new Date());
				
				response.sendRedirect("/tie-novelty-shop/AdminControls");
			}
			response.getWriter().write(jsonResponse + "}");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
