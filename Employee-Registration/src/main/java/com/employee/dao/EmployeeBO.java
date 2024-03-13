package com.employee.dao;

import java.util.List;

import com.employee.model.Employee;

public interface EmployeeBO 
{
	int save(Employee e);
	int updateSalary(int salary , String designation);
	int delete(int id);
	Employee get(int id);
	List<Employee> getAll();
	int update(Employee e);
}
