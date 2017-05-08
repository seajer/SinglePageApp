package ua.lviv.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.lviv.service.EmployeeService;
import ua.lviv.service.impl.EmployeeServiceImpl;

@SuppressWarnings("serial")
public class DeleteController extends HttpServlet{

	private static final String URL = "/SimplePageApplicaion/allEmp?page=1";
	
	private EmployeeService empService;
	
	public DeleteController() {
		empService = new EmployeeServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		empService.delete(id);
		resp.sendRedirect(URL);
	}
}