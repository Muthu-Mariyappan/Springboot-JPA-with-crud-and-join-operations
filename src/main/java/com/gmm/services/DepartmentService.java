package com.gmm.services;

//Author: Muthu Mariyappan G

import java.util.Optional;

import com.gmm.entities.Department;

//Usage of interface for easier and flexible future developments

//Defines the methods used to Controller to service the requests
public interface DepartmentService {
	public Optional<Department> find(Integer id);
}
