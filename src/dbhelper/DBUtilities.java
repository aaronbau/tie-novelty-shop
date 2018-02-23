package dbhelper;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Product;
import model.User;

public class DBUtilities {
	private final String host = "jdbc:mysql://localhost:3306/tie-novelty-shop";
    private final String user = "root";
    private final String pass = "password";
    
    public Connection getConnection(){    
        //Connection Establishment
        try
            {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Connecting to database...");
                Connection con = (Connection) DriverManager.getConnection(host, user, pass);
                System.out.println("Connection Successful");
                
                return con;
            
        } catch (Exception e){
            System.out.println("Failed to connect to db\n" + e.getMessage());
        }
        return null;
    } 
    
    public void addUser(User u) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
    	String query = "INSERT INTO users"
				+ "(username, password, email) VALUES"
				+ "(?,?,?)";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			preparedStatement.setString(1, u.getUsername());
			preparedStatement.setString(2, u.getPassword());
			preparedStatement.setString(3, u.getEmail());

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
    
    public boolean login(String username, String password) throws SQLException {
    	Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
    	String query = "SELECT * FROM users WHERE username = ? AND password = ?";
    	
    	try {
			dbConnection = getConnection();
			preparedStatement = (PreparedStatement) dbConnection.prepareStatement(query);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			int count = 0;
			
			while(rs.next()) {
				count++;
			}
			
			if(count > 0) {
				return true;
			} else 
				return false;
			
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
    	
    	return false;
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
}
