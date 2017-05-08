package ua.lviv.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
	
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String USER = "root";
	static final String PASS = "1111";
	static final String DB_NAME = "spa";
	static final String TABLE_EMPLOEES_NAME = "Employees";
	static final String TABLE_DEPARTMENTS_NAME = "Departments";
	private static Connection connection = null;
	private Statement statement = null;
	
	public static Connection getConnection(){
		if(connection != null){
			return connection;
		}else{
			try {
				Class.forName("com.mysq.jdbc.Driver");
				connection = DriverManager.getConnection(DB_URL, USER, PASS);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}
	}
	
	public void createDatabase() throws SQLException{
		String createDb = "create database if not exist " +  DB_NAME;
		statement.execute(createDb);
		String createDepTable = "create table " + TABLE_DEPARTMENTS_NAME + " (id int not null auto_increment, name varchar(255), primary key(id))";
		statement.execute(createDepTable);
		String createEmpTable = "create table " + TABLE_EMPLOEES_NAME +
				" (id int not null auto_increment,"
				+ " name varchar(255),"
				+ " isActive bit(1),"
				+ " department_id int,"
				+ " primary key(id),"
				+ " foreign key(department_id) references " + TABLE_DEPARTMENTS_NAME + "(id));";
		statement.execute(createEmpTable);
	}
	
	public void closeConnection() {
		try {
			if (statement != null)
				connection.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
