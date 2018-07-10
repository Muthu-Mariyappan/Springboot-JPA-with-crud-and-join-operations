package com.gmm.entities;

//Author: Muthu Mariyappan G

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

// Entity class Represents the record in department table

@Entity
@Table(name="department")
public class Department implements Serializable{ // serializable is optional but good practice to keep it
	
	private static final long serialVersionUID = 1L;
		
	private Integer id;
	private String name;
	private Set<Student> students = new HashSet<Student>(0); // set since many students
	
	// These constructors are needed only if there are gonna be used in @Query("new Department() like....")
	public Department() {}
	
	public Department(String name) {
		this.name = name;
	}
	
	public Department(String name,Set<Student> students) {
		this.name = name;
		this.students = students;
	}	
	
	@Id //specified the primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //uses the database identity column
	@Column(name="id",unique = true,nullable = false) //name is optional is variable name matches table field name
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="name",nullable = false) // nullable checks whether null accepted, before db throws error
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	//Onetomany -- One department may contain many studentes
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "department") //FetchType.Lazy loads the entities only when necessary good when dealing with lots of records
	public Set<Student> getStudents(){
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
}