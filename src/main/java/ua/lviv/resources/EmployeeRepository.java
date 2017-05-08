package ua.lviv.resources;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.lviv.Entity.Department;
import ua.lviv.Entity.Employee;
import ua.lviv.generator.EmployeeGenerator;

public class EmployeeRepository {

	private Connection mysqlConnection;
	private Statement statement;
	
	public EmployeeRepository(){
		mysqlConnection = MySQLConnection.getConnection();
	}
	
	public void createDefaultEmployees(){
		String query = "insert into " + MySQLConnection.TABLE_EMPLOEES_NAME + " (name, isActive, department_id) values";
		List<Integer> departmentIds = new DepartmentRepository().getAllDepartmentsIds();
		for(int i = 0; i < 100; i++){
			Employee emp = EmployeeGenerator.generateEmployee(departmentIds);
			query += "('" + emp.getName() + "', " + transformBoolToString(emp.isActive()) + ", " + emp.getDepartmentId() + ")";
			if(i < 99) query += ", ";
		}
		query += ";";
		try {
			if(statement == null )statement = mysqlConnection.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String transformBoolToString(boolean input){
		return (input) ? "TRUE" : "FALSE";
	}

	public List<Employee> findAll() {
		String query = "select id, name, isActive, department_id from " + MySQLConnection.TABLE_EMPLOEES_NAME;
		try {
			if(statement == null)statement = mysqlConnection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Employee> employees = new ArrayList<Employee>();
		try {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getBoolean("isActive"), rs.getInt("department_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	public List<Employee> findAllpagableWithDepartment(int pageNumber, int itemsPerPage) {
		List<Employee> employee = new ArrayList<Employee>();
		String query = "select e.id, e.name, e.isActive, d.id, d.name from " + MySQLConnection.TABLE_EMPLOEES_NAME + " as e" 
			+ " INNER JOIN " + MySQLConnection.TABLE_DEPARTMENTS_NAME + " as d where e.department_id = d.id LIMIT " 
			+ itemsPerPage*(pageNumber-1) + ", " + itemsPerPage*pageNumber + ";";
		try {
			if(statement == null) statement = mysqlConnection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				employee.add(new Employee(rs.getInt("e.id"), rs.getString("e.name"), rs.getBoolean("e.isActive"),
						new Department(rs.getInt("d.id"), rs.getString("d.name"))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	public void update(int id, String name, Boolean isActive, int dep_id) {
		String query = "update " + MySQLConnection.TABLE_EMPLOEES_NAME + " set name = '" + name + "' , isActive = " 
				+ transformBoolToString(isActive) + ", " + "department_id = " + dep_id + " where id = " + id + ";";
		try {
			if(statement == null) statement = mysqlConnection.createStatement();
			statement.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
