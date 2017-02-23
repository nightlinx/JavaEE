package javaee.other;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class DatabaseConnector {

	public String connect(String path) {
		
		//wyczytanie z pliku + zapisanie konfiguracji
		Properties prop = new Properties();
		Scanner in = null;
		try{
			
			in = new Scanner(new File(path));  
	      
			prop.setProperty("database", in.nextLine());
			prop.setProperty("dbuser", in.nextLine());
			prop.setProperty("dbpassword", in.nextLine());
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		OutputStream output = null;		
		try {
			output = new FileOutputStream("config.properties");
			prop.store(output, null);
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
		
		//korzystanie z config.properties w celu polaczenia z baza
		prop = new Properties();
		InputStream input = null;
		String database;
		String url = null;
		String username = null;
		String password = null;
		
		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			// get the property value
			database = prop.getProperty("database");
			url = "jdbc:mysql://localhost:3306/"+database;
			username = prop.getProperty("dbuser");
			password = prop.getProperty("dbpassword");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Loading driver...");
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		
		System.out.println("Connecting database...");
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
		    System.out.println("Database connected!");
		} catch (SQLException e) {
		    System.out.println("Cannot connect the database!");
		    throw new IllegalStateException("Cannot connect the database!", e);
		}

	return url+"?user="+username+"&password="+password;
	}	
}
