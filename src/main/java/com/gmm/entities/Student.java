package com.gmm.entities;

//Author: Muthu Mariyappan G

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//Entity class Represents the record in student table

@Entity
@Table(name="student")
public class Student implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String name;
	private Double cgpa;
	private Department department; 
	
	public Student() {}
	
	public Student(String name,Double cgpa) {
		this.name = name;
		this.cgpa = cgpa;
	}
	
	public Student(Integer id,String name,Double cgpa,Department department) {
		this.id = id;
		this.name = name;
		this.cgpa = cgpa;
		this.department = department;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",unique = true,nullable = false)
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="name",nullable = false)
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	@Column(name="cgpa",nullable = false)
	public Double getCgpa() {
		return this.cgpa;
	}
	
	public void setCgpa(Double cgpa) {
		this.cgpa = cgpa;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)//Manytoone since many students belong to one department
	@JoinColumn(name="deptid",nullable = false)
	public Department getDepartment() {
		
		return this.department;
	}
	
	public void setDepartment(Department department) {
		this.department= department;
	}
}