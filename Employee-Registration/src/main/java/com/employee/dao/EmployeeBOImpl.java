package com.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.employee.model.Employee;

public class EmployeeBOImpl implements EmployeeBO 
{
	private static Connection con;
	private final String INSERT_QUERY = "insert into employee(id,name,email,salary,designation) values(?,?,?,?,?)";
	private final String UPDATE_EMAIL = "update employee set email = ? where id = ?";
	private final String UPDATE_SALARY ="update employee set salry = ? where designation = ? ";
	private final String DELETE = "Delete from employee where id = ?";
	private final String GET = "select * from employee where id = ?";
	private final String GET_ALL = "select * from employee";

	public EmployeeBOImpl()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeDetails", "root", "1234");

		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public int save(Employee e) 
	{
		try 
		{
			PreparedStatement statement = con.prepareStatement(INSERT_QUERY);
			statement.setInt(1, e.getId());
			statement.setString(2, e.getName());
			statement.setString(3, e.getEmail());
			statement.setInt(4, e.getSalary());
			statement.setString(5, e.getDesignation());

			return statement.executeUpdate();
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		return 0;
	}

	public int update(Employee e) 
	{
		try 
		{
			PreparedStatement statement = con.prepareStatement(UPDATE_EMAIL);
			statement.setString(1, e.getEmail());
			statement.setInt(2, e.getId());

			return statement.executeUpdate();
		} 
		catch (SQLException f) 
		{
			f.printStackTrace();
		}

		return 0;
	}

	@Override
	public int updateSalary(int salary, String designation) 
	{
		try 
		{
			PreparedStatement statement = con.prepareStatement(UPDATE_SALARY);
			statement.setInt(1, salary);
			statement.setString(2, designation);

			return statement.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int delete(int id) 
	{
		try 
		{
			PreparedStatement statement =  con.prepareStatement(DELETE);
			statement.setInt(1, id);

			return statement.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public Employee get(int id) 
	{
		try 
		{
			PreparedStatement statement = con.prepareStatement(GET);
			statement.setInt(1, id);

			ResultSet res = statement.executeQuery();
            res.next();
			Employee e = new Employee(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getString(5));
			return e;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Employee> getAll() 
	{
		List<Employee> list = new ArrayList<Employee>();
		try 
		{
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(GET_ALL);
			
			while(result.next())
			{
				int id = result.getInt(1);
				String name = result.getString(2);
				String email = result.getString(3);
				int salary = result.getInt(4);
                String designation =result.getString(5);
                
                Employee e = new Employee(id,name,email,salary,designation);
                list.add(e);
			}
			return list;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return null;
	}

}
