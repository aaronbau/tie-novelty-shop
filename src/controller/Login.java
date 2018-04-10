package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import model.User;
import security.PasswordEncryptor;
import dbhelper.DBUtilities;
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
		DBUtilities db = new DBUtilities();
		String jsonResponse = "{";
		
		try {
			UserHelper helper = new UserHelper();
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			if(username != null)
			{
				String cleanUsername = Jsoup.clean(username, Whitelist.basic());
				User u = helper.findUserbyUsername(cleanUsername);
				
				if(u != null) 
				{
					PasswordEncryptor pe = new PasswordEncryptor();
					boolean passwordIsEqual = pe.checkPassword(password, u.getPassword(), u.getSalt());
					
					if(passwordIsEqual)
					{
						session.setAttribute("username", u.getUsername());
						session.setAttribute("usertype", u.getType());
						
						Cookie loginCookie = new Cookie("user", u.getUsername());
						loginCookie.setMaxAge(30 * 60); // expires in 30 mins
						response.addCookie(loginCookie);
						
						db.writeLog("[POST] Login.java - Success - " + u.getUsername()  + " " + u.getType(), new Date());
						jsonResponse = jsonResponse.concat("\"href\":\"Home\"");
					}
					else
					{
						jsonResponse = jsonResponse.concat("\"invalid\":\"Invalid username or password.\"");
						db.writeLog("[POST] Login.java - Failed - " + u.getUsername()  + " " + u.getType(), new Date());
					}
				}
				else
				{
					jsonResponse = jsonResponse.concat("\"invalid\":\"Invalid username or password.\"");
				}
			}
			else
			{
				jsonResponse = jsonResponse.concat("\"invalid\":\"Enter a username\"");
			}
			
			response.getWriter().write(jsonResponse + "}");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
	}
	

}
