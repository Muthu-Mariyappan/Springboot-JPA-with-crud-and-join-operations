package com.gmm.repositories;

//Author: Muthu Mariyappan G

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gmm.entities.Department;


@Repository("departmentRepository") // sets role - marks this as DAO -- Data access object
public interface DepartmentRepository extends CrudRepository<Department,Integer>{

}