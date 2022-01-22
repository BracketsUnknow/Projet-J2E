package sn.instaphoto.dao;
import java.sql.*;

public class Connexion {


	private static String url = "jdbc:mysql://localhost/ALBUMPHOTO";
	private static String user = "root";
	private static String password = "";
	private static Connection connection = null;
	
	public static Connection getConnexion () {
			
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return connection;
}
}
