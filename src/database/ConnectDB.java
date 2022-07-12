package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectDB{
	public static Connection open()
	{
		Connection conn = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:mySQL://localhost:3306/user";
		String username = "root";
		String password = "";
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Ket noi thanh cong");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	public static void close(Connection conn)
	{
		if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
