package ua.lviv.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/";
	static final String USER = "root";
	static final String DB_PROPERTIES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
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
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				connection = DriverManager.getConnection(DB_URL+DB_NAME+DB_PROPERTIES, USER, PASS);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return connection;
		}
	}
	
	public void createDatabase() throws SQLException{
		if(statement == null) statement = connection.createStatement();
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
	
	public boolean checkDBExists(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL+DB_NAME+DB_PROPERTIES, USER, PASS);
			ResultSet resultSet = connection.getMetaData().getCatalogs();
	        while (resultSet.next()) {

	          String databaseName = resultSet.getString(1);
	            if(databaseName.equals(DB_NAME)){
	                return true;
	            }
	        }
	        resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
