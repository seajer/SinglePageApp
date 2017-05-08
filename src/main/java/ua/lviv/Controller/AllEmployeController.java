package ua.lviv.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.service.DepartmentService;
import ua.lviv.service.EmployeeService;
import ua.lviv.service.impl.DepartmentServiceImpl;
import ua.lviv.service.impl.EmployeeServiceImpl;

@SuppressWarnings("serial")
public class AllEmployeController extends HttpServlet{
	
	private static String JSP_PAGE = "content/index.jsp";
	
	private EmployeeService empService;
	private DepartmentService depService;
	
	public AllEmployeController(){
		empService = new EmployeeServiceImpl();
		depService = new DepartmentServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("employees", empService.findAll());
		req.setAttribute("departments", depService.findAll());
		RequestDispatcher view = req.getRequestDispatcher(JSP_PAGE);
		view.forward(req, resp);
	}
	
}
