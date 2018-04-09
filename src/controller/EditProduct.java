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
import model.Product;

/**
 * Servlet implementation class EditProduct
 */
@WebServlet("/EditProduct")
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		DBUtilities db = new DBUtilities();
		try {
			String product = Jsoup.clean(request.getParameter("product"), Whitelist.basic());
			session.setAttribute("product", db.getProduct(product));
		}
		catch (SQLException e){
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("editproduct.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		DBUtilities db = new DBUtilities();
		HttpSession session = request.getSession();
		
		String productName =  session.getAttribute("currentProduct").toString();
				
		try {
			String description = Jsoup.clean(request.getParameter("description"), Whitelist.basic());
			String quantity = Jsoup.clean(request.getParameter("quantity"), Whitelist.basic());
			String price = Jsoup.clean(request.getParameter("price"), Whitelist.basic());
			
			db.editProduct(description, Integer.parseInt(quantity), Integer.parseInt(price), productName);
			db.writeLog("[POST] EditProduct.java - Product " + productName + " has been edited by " + session.getAttribute("username") + " " + session.getAttribute("usertype"), new Date());
			
			response.sendRedirect("/tie-novelty-shop/AdminControls");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
