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

import dbhelper.DBUtilities;
import dbhelper.LogHelper;
import dbhelper.ResetHelper;
import dbhelper.UserHelper;
import model.User;
import security.PasswordEncryptor;
import security.PasswordEstimator;
import security.RandomStringGenerator;

/**
 * Servlet implementation class ResetFromEmail
 */
@WebServlet("/ResetFromEmail")
public class ResetFromEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetFromEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		
		ResetHelper resetHelper = new ResetHelper();
		
		try {
			request.setAttribute("valid", resetHelper.checkValidLink(id));
			RequestDispatcher rd = request.getRequestDispatcher("resetfromemail.jsp");
			rd.forward(request, response);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String currPassword = request.getParameter("current-password");
		String newPassword = request.getParameter("new-password");
		String id = request.getParameter("id");
		
		UserHelper uh = new UserHelper();
		ResetHelper rh = new ResetHelper();
		String jsonResponse = "{";
		
		DBUtilities db = new DBUtilities();
				
		boolean isCorrectPassword = false;
		boolean isGoodPassword = false;
		
		try {
			User u = rh.findUserbyLink(id);
			
			if(currPassword != null)
			{
				LogHelper lh = new LogHelper();
				if(lh.checkBruteForceLogs("[POST] ResetFromEmail.java - Recovery Falied - " + u.getUsername()))
				{
					jsonResponse = jsonResponse.concat("\"currpass\":\"Account locked. Try again in 10 minutes.\",");
				}
				else
				{
					PasswordEncryptor pe = new PasswordEncryptor();
					if(pe.checkPassword(currPassword, u.getPassword(), u.getSalt()))
					{
						jsonResponse = jsonResponse.concat("\"currpass\":\"yes\",");
						isCorrectPassword = true;
					}
					else
					{
						jsonResponse = jsonResponse.concat("\"currpass\":\"Incorrect password.\",");
						db.writeLog("[POST] ResetFromEmail.java - Recovery Failed - " + u.getUsername()  + " " + u.getType(), new Date());
					}
				}					
			}
			
			if(newPassword != null)
			{
				PasswordEstimator pe = new PasswordEstimator();
				int strength = pe.estimatePassword(newPassword);
				
				if(strength > 0)
				{
					isGoodPassword = true;
					jsonResponse = jsonResponse.concat("\"newpass\":\"yes\",");
				}
				else
					jsonResponse = jsonResponse.concat("\"newpass\":\"no\",");
				
				
				jsonResponse = jsonResponse.concat("\"strength\":" + strength );
			}
			
			if(isCorrectPassword && isGoodPassword)
			{
				String salt = new RandomStringGenerator().generateRandomString(5);
				PasswordEncryptor pe = new PasswordEncryptor();
				newPassword = pe.encryptPassword(newPassword, salt);
				
				u.setPassword(newPassword);
				u.setSalt(salt);
				
				rh.invalidateReset(id);
				
				uh.updatePassword(u);
				
				jsonResponse = jsonResponse.concat(",\"success\":\"yes\"");
				db.writeLog("[POST] ResetFromEmail.java - Recovery Success - " + u.getUsername()  + " " + u.getType(), new Date());
			}
			
			
			response.getWriter().write(jsonResponse + "}");
		} catch(SQLException e) {
			
		}
	}

}
