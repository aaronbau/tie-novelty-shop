package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dbhelper.DBUtilities;
import model.Product;
import model.User;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist; 


/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //2mb
		maxFileSize = 1024 * 1024 * 10, //10mb
		maxRequestSize = 1024 * 1024 * 50)

public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("addproduct.jsp");
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
		
		Part part = request.getPart("image");
		String fileName = extractFileName(part);
		String savePath = "C:\\Users\\Jabin\\Documents\\GitHub\\tie-novelty-shop\\WebContent\\resources\\" + File.separator + fileName;
		File fileSaveDir = new File(savePath);
		
		part.write(savePath + File.separator);
		
		String productName = Jsoup.clean(request.getParameter("name"), Whitelist.basic());
		String productDescription = Jsoup.clean(request.getParameter("description"), Whitelist.basic());
		String productQuantity = Jsoup.clean(request.getParameter("quantity"), Whitelist.basic());
		String productPrice= Jsoup.clean(request.getParameter("price"), Whitelist.basic());
		
		Product p = new Product(productName, productDescription, 
				Integer.parseInt(productQuantity),  Integer.parseInt(productPrice), "resources/" + fileName);
		
		try {
			db.addProduct(p);
			db.writeLog("[POST] AddProduct.java - Product " + p.toString() + " added by " + session.getAttribute("username") + " " + session.getAttribute("usertype"), new Date());
			response.sendRedirect("/tie-novelty-shop/Home");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename"))
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
		}
		
		return "";
	}

}
