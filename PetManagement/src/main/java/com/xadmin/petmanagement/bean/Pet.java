package com.xadmin.petmanagement.bean;

public class Pet {
	
	private int id;
	private String name;
	private String type;
	private String age;
	private String breed;
	
	
	public Pet(String name, String type, String age, String breed) {
		super();
		this.name = name;
		this.type = type;
		this.age = age;
		this.breed = breed;
	}
	public Pet(int id, String name, String type, String age, String breed) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.age = age;
		this.breed = breed;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}

}
