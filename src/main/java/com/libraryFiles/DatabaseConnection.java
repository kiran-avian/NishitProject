package com.libraryFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;

public class DatabaseConnection {
	// MySQL connection details
	/**
	 * Database=>connect to database=>url, username, password Schemas=> cars=> add
	 * in DB_URL , system
	 */
	private static final String DB_URL = "jdbc:mysql://localhost:3306/cars";// Schemas=> cars
	private static final String USER = "root";
	private static final String PASS = "123456";

	public static ResultSet fetchData(String sqlQuery) {
		Connection conn = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();		
			rs = stmt.executeQuery(sqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return rs;
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			// Establish connection to the database
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Database connected successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Database connection closed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
