package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import dbhelper.DBUtilities;
import model.Product;

/**
 * Servlet implementation class SearchProducts
 */
@WebServlet("/SearchProducts")
public class SearchProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		DBUtilities db = new DBUtilities();
		ArrayList<Product> products;
		
		String searchValue = Jsoup.clean(request.getParameter("search"), Whitelist.basic());;
		
		try {
			products = db.getArrayListSearchProducts(searchValue);			
			request.setAttribute("productList", products);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		DBUtilities db = new DBUtilities();
		ArrayList<Product> products = new ArrayList<>();
		
		String filterValue = Jsoup.clean(request.getParameter("filter"), Whitelist.basic());;
		
		System.out.println(filterValue);
		
		try {
			if(filterValue.equalsIgnoreCase("f1"))
				products = db.getArrayListAlphabeticalProducts();
			else if(filterValue.equalsIgnoreCase("f2"))
				products = db.getArrayListReverseAlphabeticalProducts();
			else if(filterValue.equalsIgnoreCase("f3"))
				products = db.getArrayListPriceLowestProducts();
			else if(filterValue.equalsIgnoreCase("f4"))
				products = db.getArrayListPriceHighestProducts();
			
			request.setAttribute("productList", products);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
	}

}
