package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseManager {

	public static void createDatabase(String fileName) throws FileNotFoundException {
		
		File f = new File(System.getProperty("user.dir") + "/" + fileName);
		if(f.exists() && !f.isDirectory()) { 
		    System.out.println("La base de données existe déjà.");
			return;
		}
		System.out.println("Création de la base de données...");
		
		String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/" + fileName;

		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();

				String query = "";
				File myObj = new File("src/database/generate_database.sql");
				Scanner myReader = new Scanner(myObj);
				while (myReader.hasNextLine()) {
					query += myReader.nextLine();
				}
				
				PreparedStatement stmt = conn.prepareStatement(query);
		        stmt.execute();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
