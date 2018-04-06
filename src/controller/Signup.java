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

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.google.gson.Gson;

import dbhelper.DBUtilities;
import model.User;

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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
//		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		request.getParameter("username").toString();
		
//		DBUtilities db = new DBUtilities();
		
//		String username = Jsoup.clean(request.getParameter("username"), Whitelist.basic());
//		String email = Jsoup.clean(request.getParameter("email"), Whitelist.basic());
//		String password = Jsoup.clean(request.getParameter("password"), Whitelist.basic());
//		
//		User u = new User(request.getParameter(username, email, password);
		
//		try {
//			db.addUser(u);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			db.writeLog("[GET] Signup.java - Success - " + u.getUsername()  + " " + u.getType(), new Date());
//			response.sendRedirect("/tie-novelty-shop/Home");
//		}
		System.out.println("TRIGGERED");
		response.getWriter().write("{type:success}");
	}

}
