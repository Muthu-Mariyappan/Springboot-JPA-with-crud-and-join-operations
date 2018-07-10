package com.gmm.services;

//Author: Muthu Mariyappan G

import java.util.List;
import java.util.Optional;

import com.gmm.entities.Student;
import com.gmm.entities.StudentJoin;

//Usage of interface for easier and flexible future developments

public interface StudentService {
	public List<StudentJoin>  join();
	public StudentJoin getTopper();
	public Optional<Student> find(Integer id);
	public Student insert(Student student);
	public Student update(Student student);
	public boolean delete(Student student);
	public Iterable<Student> findAll();
	public void deleteAll();
}
