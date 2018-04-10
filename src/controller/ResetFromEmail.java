package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
				
		boolean isCorrectPassword = false;
		boolean isGoodPassword = false;
		
		try {
			User u = rh.findUserbyLink(id);
			
			if(currPassword != null)
			{
				PasswordEncryptor pe = new PasswordEncryptor();
				if(pe.checkPassword(currPassword, u.getPassword(), u.getSalt()))
				{
					jsonResponse = jsonResponse.concat("\"currpass\":\"yes\",");
					isCorrectPassword = true;
				}
				else
					jsonResponse = jsonResponse.concat("\"currpass\":\"Incorrect password.\",");
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
			}
			
			response.getWriter().write(jsonResponse + "}");
		} catch(SQLException e) {
			
		}
	}

}
