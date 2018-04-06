package dbhelper;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Order;
import model.Product;
import model.User;

public class DBUtilities {
	private final String host = "jdbc:mysql://localhost:3306/tie-novelty-shop";
    private final String user = "root";
    private final String password = "password";
    
    public Connection getConnection() {    
    	try
	    {
	        Class.forName("com.mysql.jdbc.Driver");
	        System.out.println("Connecting to database...");
	        Connection connection = (Connection) DriverManager.getConnection(host, user, password);
	        System.out.println("Connection Successful");
	        
	        return connection;
		} catch (Exception e) {
		    System.out.println("Failed to connect to database.\n" + e.getMessage());
		}
    	
    	return null;
    }
    
    public void addUser(User u) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
    	String query = "INSERT INTO users"
				+ "(username, password, email, type) VALUES"
				+ "(?,?,?,?)";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			preparedStatement.setString(1, u.getUsername());
			preparedStatement.setString(2, u.getPassword());
			preparedStatement.setString(3, u.getEmail());
			preparedStatement.setString(4, u.getType());

			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into users table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    }
    
    public User login(String username, String password) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
    	String query = "SELECT * FROM users WHERE username = ? AND password = ?";
    	User u;
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			int count = 0;
			
			while(rs.next()) {
				return u = new User(rs.getString("username").toString(), rs.getString("email").toString(), rs.getString("password").toString(), rs.getString("type").toString());
			}
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    	
    	return null;
    }
    
    public ArrayList<User> getArrayListUsers() throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<User> users = new ArrayList<User>();
    	String query = "SELECT * FROM users";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				users.add(new User(rs.getString("username"),rs.getString("email"),rs.getString("password"),rs.getString("type")));
			}
			
			
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    	
    	return users;
    }
    
    public ArrayList<Product> getArrayListProducts() throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Product> products = new ArrayList<Product>();
    	String query = "SELECT * FROM products";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				products.add(new Product(rs.getString("name"),rs.getString("description"),rs.getInt("quantity"),rs.getInt("price"),rs.getString("image")));
			}
			
			
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    	
    	return products;
    }
    
    public Product getProduct(String name) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
    	String query = "SELECT * FROM products where name = ?";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			preparedStatement.setString(1, name);
			
			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				return new Product(rs.getString("name"), rs.getString("description"), rs.getInt("quantity"), rs.getInt("price"), rs.getString("image"));
			}
				
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    	
    	return null;
    }
    
    public void deleteProduct(String name) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
    	String query = "DELETE FROM products where name = ?";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			preparedStatement.setString(1, name);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    }
    
    public void addToCart(String username, String productname, int quantity) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
    	String query = "INSERT INTO carts"
				+ "(username, productname, quantity) VALUES"
				+ "(?,?,?)";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, productname);
			preparedStatement.setInt(3, quantity);

			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into carts table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    }
    
    public void removeFromCart(String username, String productname, int quantity) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
    	String query = "DELETE FROM carts where username = ? AND productname = ? AND quantity = ?";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, productname);
			preparedStatement.setInt(3, quantity);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    }
    
    public ArrayList<Order> getArrayListOrders(String username) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Order> orders = new ArrayList<Order>();
    	String query = "SELECT * FROM carts WHERE username = ?";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);
			
			preparedStatement.setString(1, username);

			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				orders.add(new Order(rs.getString("username"), rs.getString("productname"), rs.getInt("quantity")));
			}
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    	
    	return orders;
    }
    
    public void addProduct(Product p) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
    	String query = "INSERT INTO products"
				+ "(name, description, quantity, price, image) VALUES"
				+ "(?,?,?,?,?)";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			preparedStatement.setString(1, p.getName());
			preparedStatement.setString(2, p.getDescription());
			preparedStatement.setInt(3, p.getQuantity());
			preparedStatement.setInt(4, p.getPrice());
			preparedStatement.setString(5, p.getImage());

			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into products table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    }
    
    public void editProduct(String description, int quantity, int price, String productName) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
    	String query = "UPDATE products SET description = ?, quantity = ?, price = ?"
    			+ " WHERE name = ?";
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			preparedStatement.setString(1, description);
			preparedStatement.setInt(2, quantity);
			preparedStatement.setInt(3, price);
			preparedStatement.setString(4, productName);
		
			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is edited in products table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    }
    
    public void addToPurchases(Order o) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
    	String query = "INSERT INTO purchases"
				+ "(username, productname, quantity) VALUES"
				+ "(?,?,?)";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			preparedStatement.setString(1, o.getUsername());
			preparedStatement.setString(2, o.getProductName());
			preparedStatement.setInt(3, o.getQuantity());

			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into carts table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    }
    
    public void Buy(String username) {
    	try {
			ArrayList<Order> orders = getArrayListOrders(username);
			
			for(Order order: orders) {
				addToPurchases(order);
				subtractQuantity(order.getProductName(), order.getQuantity());
				removeFromCart(order.getUsername(), order.getProductName(), order.getQuantity());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void subtractQuantity(String productname, int quantity) {
    	try {
			ArrayList<Product> products = getArrayListProducts();
			
			for(Product product: products) {
				if(product.getName().compareToIgnoreCase(productname) == 0) {
					int temp = product.getQuantity() - quantity;
					
					updateProduct(productname, temp);
				}
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public void updateProduct(String productname, int quantity) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
    	String query = "UPDATE products SET quantity = ?"
    			+ " WHERE name = ?";
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			preparedStatement.setInt(1, quantity);
			preparedStatement.setString(2, productname);
		
			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is edited in products table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    }
    
    public ArrayList<Order> getArrayListPurchases(String username) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Order> purchases = new ArrayList<Order>();
    	String query = "SELECT * FROM purchases WHERE username = ?";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);
			
			preparedStatement.setString(1, username);

			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				purchases.add(new Order(rs.getString("username"), rs.getString("productname"), rs.getInt("quantity")));
			}
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    	
    	return purchases;
    }
    
    public ArrayList<Order> getArrayListPurchases() throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Order> purchases = new ArrayList<Order>();
    	String query = "SELECT * FROM purchases";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				purchases.add(new Order(rs.getString("username"), rs.getString("productname"), rs.getInt("quantity")));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    	
    	return purchases;
    }
    
    public void removeFromPurchases(String username, String productname, int quantity) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
    	String query = "DELETE FROM purchases where username = ? AND productname = ? AND quantity = ?";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, productname);
			preparedStatement.setInt(3, quantity);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    }
    
    public ArrayList<Product> getArrayListSearchProducts(String value) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Product> products = new ArrayList<Product>();
    	String query = "SELECT * FROM products WHERE name LIKE ?";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);
			
			preparedStatement.setString(1, "%" + value + "%");
			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				products.add(new Product(rs.getString("name"),rs.getString("description"),rs.getInt("quantity"),rs.getInt("price"),rs.getString("image")));
			}
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    	
    	return products;
    }
    
    public ArrayList<Product> getArrayListAlphabeticalProducts() throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Product> products = new ArrayList<Product>();
    	String query = "SELECT * FROM products ORDER BY name";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);
			
			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				products.add(new Product(rs.getString("name"),rs.getString("description"),rs.getInt("quantity"),rs.getInt("price"),rs.getString("image")));
			}
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    	
    	return products;
    }
    
    public ArrayList<Product> getArrayListReverseAlphabeticalProducts() throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Product> products = new ArrayList<Product>();
    	String query = "SELECT * FROM products ORDER BY name desc";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);
			
			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				products.add(new Product(rs.getString("name"),rs.getString("description"),rs.getInt("quantity"),rs.getInt("price"),rs.getString("image")));
			}
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    	
    	return products;
    }
    
    public ArrayList<Product> getArrayListPriceLowestProducts() throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Product> products = new ArrayList<Product>();
    	String query = "SELECT * FROM products ORDER BY price";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);
			
			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				products.add(new Product(rs.getString("name"),rs.getString("description"),rs.getInt("quantity"),rs.getInt("price"),rs.getString("image")));
			}
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    	
    	return products;
    }
    
    public ArrayList<Product> getArrayListPriceHighestProducts() throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Product> products = new ArrayList<Product>();
    	String query = "SELECT * FROM products ORDER BY price desc";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);
			
			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				products.add(new Product(rs.getString("name"),rs.getString("description"),rs.getInt("quantity"),rs.getInt("price"),rs.getString("image")));
			}
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
    	
    	return products;
    }
}
