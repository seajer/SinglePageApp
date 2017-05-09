package ua.lviv.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.Entity.Employee;
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
		int page = 1;
		if(req.getParameter("page") != null){
			page = Integer.parseInt( req.getParameter("page"));
		}
		String search = "";
		if(req.getParameter("search") != null){
			search = (String)req.getParameter("search");
		}
		int searchedCount = empService.findSearchedCount(search);
		int pageCount = (searchedCount % EmployeeService.ITEMS_PER_PAGE == 0) ? searchedCount/EmployeeService.ITEMS_PER_PAGE : searchedCount/EmployeeService.ITEMS_PER_PAGE + 1;
		if(page > pageCount) page = pageCount;
		List<Employee> employee = empService.findAllPagableWithDepartment(page, search);
		req.setAttribute("employees", employee);
		req.setAttribute("departments", depService.findAll());
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("searchedStr", search);
		RequestDispatcher view = req.getRequestDispatcher(JSP_PAGE);
		view.forward(req, resp);
	}
	
}
