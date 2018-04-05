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
				+ "(username, password, email, type) VALUES"
				+ "(?,?,?,?)";
    	
    	Connection connection = dbUtil.getConnection();
    	PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
    	try {
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

			if (connection != null) {
				connection.close();
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
}
