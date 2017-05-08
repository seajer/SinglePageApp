package ua.lviv.generator;

import java.util.List;
import java.util.Random;

import ua.lviv.Entity.Employee;

public class EmployeeGenerator {
	
	private static Random rand;
	
	private static String[] names = {"John", "Hary", "Linda", "Jack", "Dazyy", "Stan", "Bill",
			"Christopher", "Ann", "Lindsey", "Mary"};

	public static Employee generateEmployee(List<Integer> departmentIds){
		return new Employee(names[rand.nextInt(names.length)], Math.random() < 0.5, departmentIds.get(rand.nextInt(departmentIds.size())));
	}
	
}
