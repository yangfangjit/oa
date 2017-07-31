package com.banana.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banana.oa.dao.EmployeeDao;
import com.banana.oa.entity.Employee;
import com.banana.oa.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public int insertEmployee(Employee employee) {
		return employeeDao.insertEmployee(employee.getName(), employee.getAge());
	}
	
	@Override
	public int insertEmployees(List<Employee> employees) {
		return employeeDao.insertEmployees(employees);
	}

	@Override
	public int updateEmployees(List<Employee> employees) {
		return employeeDao.updateEmployees(employees);
	}
	
	@Override
	public List<Employee> queryAll(int offset, int limit) {
		return employeeDao.queryAll(offset, limit);
	}

}
