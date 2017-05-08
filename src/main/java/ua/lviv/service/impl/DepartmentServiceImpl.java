package ua.lviv.service.impl;

import java.util.List;

import ua.lviv.Entity.Department;
import ua.lviv.resources.DepartmentRepository;
import ua.lviv.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{

	private DepartmentRepository depRepository;
	
	public DepartmentServiceImpl(){
		depRepository = new DepartmentRepository();
	}
	
	public List<Department> findAll() {
		return depRepository.findAll();
	}

}
