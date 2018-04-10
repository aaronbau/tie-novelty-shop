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
 * Servlet implementation class CreateProductManager
 */
@WebServlet("/CreateProductManager")
public class CreateProductManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProductManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setAttribute("currentpage", "CreateProductManager");
		RequestDispatcher rd = request.getRequestDispatcher("createproductmanager.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		DBUtilities db = new DBUtilities();
//		HttpSession session = request.getSession();
//		
//		String username = Jsoup.clean(request.getParameter("username"), Whitelist.basic());
//		String email = Jsoup.clean(request.getParameter("email"), Whitelist.basic());
//		String password = Jsoup.clean(request.getParameter("password"), Whitelist.basic());
//		
//		User u = new User(username, email, password, "Product Manager");
//		
//		try {
//			if(!(u.getUsername() == "" || u.getUsername() == null))	{
//				db.addUser(u);
//				response.getWriter().write("<script type=\"text/javascript\">");
//				response.getWriter().write("alert('Product Manager successfully created');");
//				response.getWriter().write("location='AdminControls'");
//				response.getWriter().write("</script>");
//				
//				db.writeLog("[POST] CreateProductManager.java - Product Manager " + u.toString() + " was created by " + session.getAttribute("username") + " " + session.getAttribute("usertype"), new Date());
//			} else
//				response.getWriter().write("<script type=\"text/javascript\">");
//			response.getWriter().write("alert('Username already taken');");
//			response.getWriter().write("location='" + session.getAttribute("currentpage") + "'");
//			response.getWriter().write("</script>");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			response.sendRedirect("/tie-novelty-shop/Home");
//		}
		
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
				
				User u = new User(username, email, password, "Product Manager", salt);
				helper.addUser(u);
				
				HttpSession session = request.getSession();
				
				db.writeLog("[POST] CreateProductManager.java - Product Manager " + u.toString() + " was created by " + session.getAttribute("username") + " " + session.getAttribute("usertype"), new Date());
				
				response.sendRedirect("/tie-novelty-shop/AdminControls");
			}
			response.getWriter().write(jsonResponse + "}");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
