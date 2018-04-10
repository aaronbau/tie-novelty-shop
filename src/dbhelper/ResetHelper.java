package dbhelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.User;

public class ResetHelper {
private DBUtilities dbUtil;
	
	public ResetHelper()
	{
		dbUtil = new DBUtilities();
	}
	
	public void addReset(User u, String link) throws SQLException {		
    	String query = "INSERT INTO reset"
				+ "(username, link, date, valid) VALUES"
				+ "(?,?,?,?)";
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	
    	Connection connection = dbUtil.getConnection();
    	PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
    	try {
			preparedStatement.setString(1, u.getUsername());
			preparedStatement.setString(2, link);
			preparedStatement.setString(3, dateFormat.format(date));
			preparedStatement.setInt(4, 1);

			// execute insert SQL statement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into reset table!");

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
	
	public User findUserbyLink(String link) throws SQLException {
		
		String query = "SELECT * FROM reset WHERE link = ?";
    	
    	Connection connection = dbUtil.getConnection();
    	PreparedStatement preparedStatement = (PreparedStatement) connection.clientPrepareStatement(query);
    	
    	try {
			preparedStatement.setString(1, link);

			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				UserHelper uh = new UserHelper();
				return uh.findUserbyUsername(rs.getString("username"));
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
    
    public void invalidateReset(String link) throws SQLException {
    	
    	String query = "UPDATE reset SET valid = ? WHERE link = ?";
    	
    	Connection connection = dbUtil.getConnection();
    	PreparedStatement preparedStatement = (PreparedStatement) connection.clientPrepareStatement(query);
    	
    	try {
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, link);

			// execute insert SQL statement
			preparedStatement.executeUpdate();
		
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
    }
    
    public boolean checkValidLink(String link) throws SQLException {
    	
    	String query = "SELECT * FROM reset WHERE link = ?";
    	
    	Connection connection = dbUtil.getConnection();
    	PreparedStatement preparedStatement = (PreparedStatement) connection.clientPrepareStatement(query);
    	
    	try {
			preparedStatement.setString(1, link);

			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				return rs.getInt("valid") == 1;
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
    	
    	return false;
    }
}
