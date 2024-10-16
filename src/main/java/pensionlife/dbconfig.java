package pensionlife;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbconfig {
	public Connection getConnection() throws Exception {
	    String db_driver = "com.mysql.jdbc.Driver"; 

	    String db_url = "jdbc:mysql://webmiwon.co.kr:3306/kkp_402";

	    String db_user = "kkp_402";
	    String db_pass = "kkpjava"; 

	    Class.forName(db_driver);

	    Connection dbcon = DriverManager.getConnection(db_url,db_user,db_pass);

	    return dbcon;
	}
}
