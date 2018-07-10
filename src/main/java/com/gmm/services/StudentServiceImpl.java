package com.gmm.services;

//Author: Muthu Mariyappan G

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmm.entities.Student;
import com.gmm.entities.StudentJoin;
import com.gmm.repositories.StudentRepository;

@Transactional
@Service("studentService")
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public List<StudentJoin> join(){
		System.out.println("\nrunning service");
		return studentRepository.join();
	}

	@Override
	public StudentJoin getTopper() {
		return this.studentRepository.getTopper();
	}

	@Override
	public Optional<Student> find(Integer id) {
		
		return this.studentRepository.findById(id);
	}

	@Override
	public Student insert(Student student) {
		
		return this.studentRepository.save(student);
	}

	@Override
	public Student update(Student student) {

		return this.studentRepository.save(student);
	}

	@Override
	public boolean delete(Student student) {
		this.studentRepository.delete(student);
		return true;
	}

	@Override
	public Iterable<Student> findAll() {
		return this.studentRepository.findAll();
	}

	@Override
	public void deleteAll() {
		this.studentRepository.deleteAll();
	}
}
