package com.gmm.services;

//Author: Muthu Mariyappan G


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gmm.repositories.DepartmentRepository;
import com.gmm.entities.Department;


@Transactional //Spring dynamically creates a proxy that implements the same interface(s) as the class that gets annotated
@Service("departmentService") // to detected by @Autowired
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired //automatically injects the implementation of crudrepo
	private DepartmentRepository departmentRepository;
	
	@Override
	public Optional<Department> find(Integer id) {
		return this.departmentRepository.findById(id); //default crudrepo methods
	}
}