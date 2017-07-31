package com.banana.oa.entity;

import java.io.Serializable;

public class Employee implements Serializable {
	
	private static final long serialVersionUID = 7005514166677991202L;
	
	private int id;
	private String name;
	private short age;
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	public Employee() {
	}
	
	public Employee(String name, short age) {
		this.name = name;
		this.age = age;
	}

	public Employee(int id, String name, short age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getAge() {
		return age;
	}

	public void setAge(short age) {
		this.age = age;
	}

}
