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

/**
 * Servlet implementation class CancelPurchase
 */
@WebServlet("/CancelPurchase")
public class CancelPurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelPurchase() {
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
		DBUtilities db = new DBUtilities();
		String username = Jsoup.clean(request.getParameter("username"), Whitelist.basic());
		String productname = Jsoup.clean(request.getParameter("productName"), Whitelist.basic());
		String quantity = Jsoup.clean(request.getParameter("productQuantity"), Whitelist.basic());
		
		try {
			db.removeFromPurchases(username, productname, Integer.parseInt(quantity));
			db.writeLog("[POST] CancelPurchase.java - Product " + productname + " was removed from the purchases of " + session.getAttribute("username") + " " + session.getAttribute("usertype"), new Date());
			response.sendRedirect("/tie-novelty-shop/AllPurchases");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
