package javaee.other;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class DatabaseConnector {

	private void setProperties(String path) throws IOException, FileNotFoundException{
		Properties prop = new Properties();
		Scanner in = null;
		
		in = new Scanner(new File(path));

		prop.setProperty("database", in.nextLine());
		prop.setProperty("dbuser", in.nextLine());
		prop.setProperty("dbpassword", in.nextLine());

		try{
			in.close();
		} catch (Exception e){
			e.printStackTrace();			
		}

		OutputStream output = null;
		output = new FileOutputStream("config.properties");
		prop.store(output, null);
}
	
	
	public Connection getConnection(String path) throws FileNotFoundException, IOException {

		setProperties(path);

		// korzystanie z config.properties w celu polaczenia z baza
		Properties prop = new Properties();
		InputStream input = null;
		String database;
		String url = null;
		String username = null;
		String password = null;

		input = new FileInputStream("config.properties");

		// load a properties file
		prop.load(input);

		// get the property value
		database = prop.getProperty("database");
		url = "jdbc:mysql://localhost:3306/" + database;
		username = prop.getProperty("dbuser");
		password = prop.getProperty("dbpassword");

		loadDriver();
		
		System.out.println("Connecting database...");
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");
		} catch (SQLException e) {
			System.out.println("Cannot connect the database!");
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		return connection;
	}
	
	private void loadDriver(){
		System.out.println("Loading driver...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}		
	}
}
