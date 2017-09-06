//Group members: Zhong Yi, Clement, Leyond

package Util;

import java.sql.*;

public class DB {
	public static Connection getConnection() {
		Connection conn = null;
		
		String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
		String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
		String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
		String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://" + host + ":" + port + "/mapp_shoplet?user=" + username + "&password=" + password;
			conn = DriverManager.getConnection(connURL);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return conn;
	}
}
