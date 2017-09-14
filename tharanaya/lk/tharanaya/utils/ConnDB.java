package tharanaya.utils;
import java.sql.*;

public class ConnDB {
	private String defaultUrl = "jdbc:mysql://localhost:3306/tharanaya_be";
	private String defaultUser = "root";
	private String defaultPw = "dulip123";
	private Connection con;
	//private boolean isCon = false;
	
	public ConnDB(String url, String user, String password){
		
		this.defaultUrl = url;
		this.defaultUser = user;
		this.defaultPw = password;
		createCon(url, user, password);
	}
	
	public ConnDB(){
		createCon(defaultUrl, defaultUser, defaultPw);
	}
	
	private void createCon(String url, String user, String password){
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		try {
			   Class.forName(JDBC_DRIVER);
			}
			catch(ClassNotFoundException ex) {
				ex.printStackTrace();
			   System.out.println("Error: unable to load driver class!");
			   //System.exit(1);
			}
		try {
			con = DriverManager.getConnection(url, user, password);
			//isCon = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: unable to establish db connection!");
		}
	}
	
	public Connection getDBConnection(){
		return con;
	}

}
