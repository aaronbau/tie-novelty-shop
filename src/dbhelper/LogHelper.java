package dbhelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class LogHelper {
	private DBUtilities dbUtil;
	
	public LogHelper()
	{
		dbUtil = new DBUtilities();
	}
	
	public boolean checkBruteForceLogs(String logs) throws SQLException
	{
		String query = "SELECT * FROM logs WHERE log LIKE ? ORDER BY id desc";
		
		Connection connection = dbUtil.getConnection();
    	PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
    	try {
			preparedStatement.setString(1, "%" + logs + "%");

			// execute insert SQL statement
			ResultSet rs = preparedStatement.executeQuery();
			
			ArrayList<Date> dateArr = new ArrayList<Date>();

			int ctr = 0;
			while(rs.next() && ctr < 10)
			{
				System.out.println("WENT HERE");
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
				try
				{
					System.out.println(sdf.parse(rs.getString("time")));
					dateArr.add(sdf.parse(rs.getString("time")));
				}
				catch(ParseException pe)
				{
					pe.printStackTrace();
				}
				
				ctr++;
			}
			
			if(!(dateArr.size() < 10))
			{
				Date compareDate = new Date();
				compareDate.setTime(compareDate.getTime() - 10);
				
				for(Date d : dateArr)
				{
					if(!d.after(compareDate))
						return false;
				}
			}			

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
    	
    	return true;
	}
}
