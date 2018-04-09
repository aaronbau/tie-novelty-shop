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
import security.RandomStringGenerator;

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("reset.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
		
		UserHelper userHelper = new UserHelper();
		
		String jsonResponse = "{";
		
		try 
		{
			if(username != null)
			{
				User u = userHelper.findUserbyUsername(username);
				if(u != null)
				{
					jsonResponse = jsonResponse.concat("\"success\":\"yes\"");
					
					RandomStringGenerator rsg = new RandomStringGenerator();
					String generatedLink = rsg.generateRandomString(120);
					
					ResetHelper resetHelper = new ResetHelper();
					resetHelper.addReset(u, generatedLink);
				}
				else
				{
					jsonResponse = jsonResponse.concat("\"username\":\"There is no account with this username\"");
				}
			}
			else
			{
				jsonResponse = jsonResponse.concat("\"username\":\"Enter a username\"");
			}
			
			response.getWriter().write(jsonResponse + "}");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}
