package com.modeler.model;

import java.io.File;

public class User {
	
	private int id;
	private String name;
	private int age;
	private String password;
	private File file;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public File getFile() {
		return file;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

}
