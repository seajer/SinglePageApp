package ua.lviv.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.service.EmployeeService;
import ua.lviv.service.impl.EmployeeServiceImpl;

@SuppressWarnings("serial")
public class EditEmployeeController extends HttpServlet{
	
	private EmployeeService empService;
	
	public EditEmployeeController() {
		this.empService = new EmployeeServiceImpl(); 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		boolean isActive = (req.getParameter("isActive") == null) ? false : true;
		int dep_id = Integer.parseInt(req.getParameter("dep_id"));
		empService.saveEmployee(id, name, isActive, dep_id);
		resp.sendRedirect(EmployeeService.URL);
	}
}
