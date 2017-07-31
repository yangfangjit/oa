package com.banana.oa.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.banana.oa.entity.Employee;

public class EmployeeServiceTest extends BaseTest {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceTest.class);
	
	@Autowired
	private EmployeeService employeeService;

	@Test
	@Rollback
	@Transactional
	public void testEmployeeService() {
		String name = "yangfang";
		short age = 26;
		Employee employee = new Employee(name, age);
		
		employeeService.insertEmployee(employee);
		
		List<Employee> list = employeeService.queryAll(0, 100);
		
		logger.info(list.get(0).toString());
		
		assertEquals(name, list.get(0).getName());
		assertEquals(age, list.get(0).getAge());
	}
	
	@Test
	@Rollback
	@Transactional
	public void testBatchInsertAndUpdateEmployees() {
		String name = "Unit Test";
		short age = 50;
		
		Employee employee1 = new Employee(name, age);
		List<Employee> list = new ArrayList<Employee>(2);
		list.add(employee1);
		
		employeeService.insertEmployees(list);
		
		List<Employee> results = employeeService.queryAll(0, 100);
		
		for(Employee employee : results) {
			logger.info(employee.toString());
			
			assertEquals(name, employee.getName());
			assertEquals(age, employee.getAge());
		}
		
		String updateName = "Test update";
		short updateAge = 10;
		
		for(Employee employee : results) {
			employee.setName(updateName);
			employee.setAge(updateAge);
		}
		
		employeeService.updateEmployees(results);
		
		List<Employee> resultsAfterUpdate = employeeService.queryAll(0, 100);
		
		for(Employee employee : resultsAfterUpdate) {
			logger.info(employee.toString());
			
			assertEquals(updateName, employee.getName());
			assertEquals(updateAge, employee.getAge());
		}
	}

}
