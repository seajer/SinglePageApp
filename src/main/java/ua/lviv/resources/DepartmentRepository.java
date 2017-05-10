package ua.lviv.resources;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.lviv.Entity.Department;

public class DepartmentRepository {

	private Connection mysqlConnection;
	private Statement statement = null;
	
	public DepartmentRepository(){
		mysqlConnection = MySQLConnection.getConnection();
	}
	
	public void creatingDefaultDepartments(){
		String query = ("insert into " + MySQLConnection.TABLE_DEPARTMENTS_NAME + "(name) values("
				+"  'First department'),"
				+ "('Second department'),"
				+ "('Third department'),"
				+ "('Forth department'),"
				+ "('Fifth department'),"
				+ "('Sixth department'),"
				+ "('Seventh department'),"
				+ "('Eighth department'),"
				+ "('Ninth department'),"
				+ "('Secret department');");
		try {
			statement = mysqlConnection.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Integer> getAllDepartmentsIds(){
		List<Integer> ids = new ArrayList<Integer>();
		String query = "select id from " + MySQLConnection.TABLE_DEPARTMENTS_NAME + ";";
		try {
			if(statement == null) statement = mysqlConnection.createStatement();
			ResultSet re = statement.executeQuery(query);
			while(re.next()){
				ids.add(re.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}

	public List<Department> findAll() {
		List<Department> departments = new ArrayList<Department>();
		String query = "select id, name from " + MySQLConnection.TABLE_DEPARTMENTS_NAME + ";";
		try {
			if(statement == null) statement = mysqlConnection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				departments.add(new Department(rs.getInt("id"), rs.getString("name")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return departments;
	}
}
