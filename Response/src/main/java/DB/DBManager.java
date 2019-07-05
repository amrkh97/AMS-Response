package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DBManager {

	private static String db_class_string = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String db_server = "jdbc:sqlserver://41.32.255.229:1433;databaseName=DESKTOP-HHPL1PP;";
	private static String db_name = "databaseName=KAN_AMO";
	private static String db_userid = "admin";
	private static String db_password = "1234";
	private static Connection conn;

	public static ResultSet ExecuteQuery(String SelectStatement) {
			
		return null;
	}

	public static int ExecuteNonQuery(String DMLStatement) {
		return 0;
	}
	
	public static Connection getDBConn() {
		try {
			Class.forName(db_class_string);
			conn = DriverManager.getConnection(db_server+db_name, db_userid, db_password);
			System.out.println("Connection Open");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static void main (String [] args) {
		Connection con = getDBConn();
	}
}