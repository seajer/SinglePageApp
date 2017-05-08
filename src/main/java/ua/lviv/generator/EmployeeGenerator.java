package ua.lviv.generator;

import java.util.List;
import java.util.Random;

import ua.lviv.Entity.Employee;

public class EmployeeGenerator {
	
	private static Random rand = new Random();
	
	private static String[] names = {"John", "Hary", "Linda", "Jack", "Dazyy", "Stan", "Bill",
			"Christopher", "Ann", "Lindsey", "Mary"};

	public static Employee generateEmployee(List<Integer> departmentIds){
		String name = names[rand.nextInt(names.length-1)];
		boolean isActive = Math.random() < 0.5;
		Integer id = departmentIds.get(rand.nextInt(departmentIds.size()));
		return new Employee(name, isActive, id);
	}
	
}
