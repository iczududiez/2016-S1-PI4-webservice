package br.com.senac.pi4.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

	private static Database instance = null;
	
	private Database () {};
	
	public static Database get () {
		if (instance == null)
			instance = new Database();
		return instance;
	}
	
	public Connection conn () throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://koo2dzw5dy.database.windows.net;user=TSI@koo2dzw5dy.database.windows.net;password=SistemasInternet123;database=SenaQuiz");
		return conn;
	}
	
}
