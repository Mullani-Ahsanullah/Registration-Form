package com.employee.dao;

import java.util.List;

import com.employee.model.Employee;

public class Demo 
{
	public static void main(String[] args) 
	{
       Employee e = new Employee(2,"Irfan","irfan@gmail.com",50000,"Developer");
       EmployeeBOImpl obj = new EmployeeBOImpl();
       List<Employee> list = obj.getAll();
       for(Employee empl : list)
       {
    	   System.out.println(empl);
       }
	}

}
