package com.employee.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.employee.dao.EmployeeBOImpl;
import com.employee.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet")
public class Servlet extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int id = Integer.parseInt(req.getParameter("employeeId"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		int salary = Integer.parseInt(req.getParameter("salary"));
		String designation = req.getParameter("designation");

		String action = req.getParameter("action");

		Employee e = new Employee(id,name,email,salary,designation);
		EmployeeBOImpl emp = new EmployeeBOImpl();

		PrintWriter writer = resp.getWriter();

		if(action.equals("submit"))
		{
			int i = emp.save(e);

			if(i == 1)
			{
				writer.println("Inserted Successfully");
			}
		}
		else if(action.equals("update"))
		{
			int i = emp.update(e);
			if(i == 1)
			{
				writer.println("Updated Successfully");
			}
		}
		else if(action.equals("delete"))
		{
			int i = emp.delete(id);
			if(i == 1)
			{
				writer.println("Deleted Successfully");
			}
		}
		else if(action.equals("view"))
		{
			List<Employee> list = emp.getAll();
			writer.println("<table border='1'>");

			writer.println("<tr>"); 
			writer.println("<th>ID</th>"); 
			writer.println("<th>Name</th>"); 
			writer.println("<th>Email</th>"); 
			writer.println("<th>Salary</th>"); 
			writer.println("<th>Designation</th>"); 
			writer.println("</tr>");

			for(Employee employee : list)
			{
				writer.println("<tr>");
				writer.println("<td>" + employee.getId() + "</td>");
				writer.println("<td>" + employee.getName() + "</td>");
				writer.println("<td>" + employee.getEmail() + "</td>");
				writer.println("<td>" + employee.getSalary() + "</td>"); 
				writer.println("<td>" + employee.getDesignation() + "</td>");
				writer.println("</tr>");
			}
			writer.println("</table>");
		}
	}

}
