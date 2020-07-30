package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	
	public static void createDatabase(String fileName) {
		String url = "jdbc:sqlite:"+System.getProperty("user.dir")+"/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public static void populateNewDatabase() {
		
	}
}
