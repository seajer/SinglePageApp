package ua.lviv.service;

import java.util.List;

import ua.lviv.Entity.Employee;

public interface EmployeeService {
	
	public static final int ITEMS_PER_PAGE = 10;
	
	public List<Employee> findAll();
	
	public List<Employee> findAllPagableWithDepartment(int pageCount);

	public void saveEmployee(int id, String name, Boolean isActive, int dep_id);
	
	public void generateDefault();

}
