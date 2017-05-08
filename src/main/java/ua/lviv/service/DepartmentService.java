package ua.lviv.service;

import java.util.List;

import ua.lviv.Entity.Department;

public interface DepartmentService {

	List<Department> findAll();
	
	void generateDefault();
	
}
