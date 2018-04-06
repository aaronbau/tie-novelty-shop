package dbhelper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.User;

public class UserHelper {
	private DBUtilities dbUtil;
	
	public UserHelper()
	{
		dbUtil = new DBUtilities();
	}
	
	public void addUser(User u) throws SQLException {		
    	String query = "INSERT INTO users"
				+ "(username, password, email, type, salt) VALUES"
				+ "(?,?,?,?,?)";
    	
    	Connection connection = dbUtil.getConnection();
    	PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
    	try {
			preparedStatement.setString(1, u.getUsername());
			preparedStatement.setString(2, u.getPassword());
			preparedStatement.setString(3, u.getEmail());
			preparedStatement.setString(4, u.getType());
			preparedStatement.setString(5, u.getSalt());

			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into users table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
    }
    
    public User findUserbyUsername(String username) throws SQLException {
    	
    	String query = "SELECT * FROM users WHERE username = ?";
    	
    	Connection connection = dbUtil.getConnection();
    	PreparedStatement preparedStatement = (PreparedStatement) connection.clientPrepareStatement(query);
    	
    	try {
			preparedStatement.setString(1, username);

			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				return new User(rs.getString("username").toString(), 
								rs.getString("email").toString(), 
								rs.getString("password").toString(), 
								rs.getString("type").toString(),
								rs.getString("salt").toString());
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
    	
    	return null;
    }
}
