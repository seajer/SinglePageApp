package ua.lviv.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.resources.MySQLConnection;
import ua.lviv.service.DepartmentService;
import ua.lviv.service.EmployeeService;
import ua.lviv.service.impl.DepartmentServiceImpl;
import ua.lviv.service.impl.EmployeeServiceImpl;

@SuppressWarnings("serial")
public class AllEmployeeController extends HttpServlet{
	
	private static String JSP_PAGE = "content/index.jsp";
	
	private EmployeeService empService;
	private DepartmentService depService;
	
	public AllEmployeeController(){
		empService = new EmployeeServiceImpl();
		depService = new DepartmentServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		try {
//			new MySQLConnection().createDatabase();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		depService.generateDefault();
//		empService.generateDefault();
		Integer page = Integer.valueOf(req.getParameter("page"));
		if(page == null) page = 1;
		req.setAttribute("employees", empService.findAllPagableWithDepartment(page));
		req.setAttribute("departments", depService.findAll());
		RequestDispatcher view = req.getRequestDispatcher(JSP_PAGE);
		view.forward(req, resp);
	}
	
}
