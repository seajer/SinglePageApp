package ua.lviv.service.impl;

import java.util.List;

import ua.lviv.Entity.Employee;
import ua.lviv.resources.EmployeeRepository;
import ua.lviv.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(){
		employeeRepository = new EmployeeRepository();
	}
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public List<Employee> findAllPagableWithDepartment(int pageCount) {
		return employeeRepository.findAllpagableWithDepartment(pageCount, ITEMS_PER_PAGE);
	}

	public void saveEmployee(int id, String name, Boolean isActive, int dep_id) {
		employeeRepository.update(id, name, isActive, dep_id);
	}

	public void generateDefault() {
		employeeRepository.createDefaultEmployees();
	}

	public void delete(Integer id) {
		employeeRepository.deleteEmployeeById(id);
	}

}
