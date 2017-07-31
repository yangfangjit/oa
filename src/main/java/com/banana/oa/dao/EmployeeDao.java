package com.banana.oa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.banana.oa.entity.Employee;

public interface EmployeeDao {
	
	/**
	 * insert an employee record into db 
	 * @param name
	 * @param age
	 * @return 1 if success, otherwise 0
	 */
	int insertEmployee(@Param("name") String name, @Param("age") short age);
	
	/**
	 * batch insert employees into db
	 * @param employees, list of Employee
	 * @return 1 if success, otherwise 0
	 */
	int insertEmployees(List<Employee> employees);
	
	/**
	 * batch update employees
	 * @param employees, list of Employee need updated
	 * @return 1 if success, otherwise 0
	 */
	int updateEmployees(List<Employee> employees);
	
	/**
	 * query employee records 
	 * @param offset
	 * @param limit
	 * @return list of Employee
	 */
	List<Employee> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}
