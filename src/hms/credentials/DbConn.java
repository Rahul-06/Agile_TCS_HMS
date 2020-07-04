package hms.credentials;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn{
	private static final String DB_DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://ls-ea01ee6ad63a8022e01a44ff0028be35c233ace8.clahbm7vh23u.ap-south-1.rds.amazonaws.com:3306/dbmasterhms";
	private static final String DB_USERNAME = "agile2tcs";
	private static final String DB_PASSWORD = "agile_tcs";
	
	public static Connection getConnection()
	{
		Connection con = null;
		try {
			Class.forName(DB_DRIVER_NAME);
			con = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e);
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		return con;
	}
	
	public static void closeConnection(Connection con)
	{
		if(con!=null)
		{
			try {
				con.close();
			}
			catch(SQLException e)
			{
				System.out.println(e);
			}
		}
	}
	
}
