package ua.lviv.Entity;

public class Employee {
	
	private int id;
	private String name;
	private boolean isActive;
	private int department_id;
	private Department department;

	public Employee(String name, boolean isActive, int department_id) {
		super();
		this.name = name;
		this.isActive = isActive;
		this.department_id = department_id;
	}
	
	public Employee(int id, String name, boolean isActive, int department_id) {
		super();
		this.id = id;
		this.name = name;
		this.isActive = isActive;
		this.department_id = department_id;
	}

	public Employee(int id, String name, boolean isActive, Department department) {
		super();
		this.id = id;
		this.name = name;
		this.isActive = isActive;
		this.department = department;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return isActive;
	}
	public boolean getActive(){
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getDepartmentId() {
		return department_id;
	}
	public void setDepartmentId(int department) {
		this.department_id = department;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
