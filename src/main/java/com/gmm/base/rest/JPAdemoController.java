package com.gmm.base.rest;

//Author: Muthu Mariyappan G

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gmm.entities.*;
import com.gmm.services.*;

@RestController //is a convenience annotation that adds @Controller and @ResponseBody annotations
public class JPAdemoController {
	
	@Autowired //tells the spring that, injection need to occur here, then spring searches for that StudentService component and injects it here
	StudentService studentService; // remember @Service is also sub type of @Component
	
	@Autowired
	DepartmentService departmentService;
	
	// This prints the joined view of Student and Department tables
	@RequestMapping(value="/jointable")  
	public List<StudentJoin> displayJoinTable(){
		return this.studentService.join();     
	}
	
	// Finds and prints the topper - corresponding to cgpa
	@RequestMapping(value="/student/topper")
	public StudentJoin getTopper(){
		return this.studentService.getTopper(); 
	}
	
	//Finds Student details for the given id 
	@RequestMapping(value="/student/find/{id}")
	public Map<String,String> find(@PathVariable Integer id){
		//@PathVariable takes the part of url as value her {id} taken as id value
		Map<String,String> message = new LinkedHashMap<>(); // to store student details
		Student foundStudent;
		//this.studentService.find(id) returns Optional<> whose methods are isPresent and get()
		if(this.studentService.find(id).isPresent()) { // if the given student id is present
			foundStudent = this.studentService.find(id).get(); //fetches the student record from the database
			//Getting student detail with help of getter methods
			message.put("ID", foundStudent.getId().toString());
			message.put("Name", foundStudent.getName());
			message.put("CGPA", foundStudent.getCgpa().toString());
		}
		else { 
			message.put("Error","Cannot find student with id "+id);
		}
		return message;
	}
	
	@RequestMapping(value="/student/findall")// Lists all the student record in the student table
	public List<Map<String,String>> findAll(){
		Map<String,String> message ;
		List<Map<String,String>> listOfMessages = new LinkedList<>();
		Student foundStudent;
		Iterator<Student> iterator = this.studentService.findAll().iterator(); //this.studentService.findAll() return Iterator<>
		if(iterator.hasNext()) { // if table is not empty
			while(iterator.hasNext()) {
				foundStudent = iterator.next(); // fetch record one by one
				message = new LinkedHashMap<>(); // to store invidual's info
				message.put("ID", foundStudent.getId().toString());
				message.put("Name", foundStudent.getName());
				message.put("CGPA", foundStudent.getCgpa().toString());
				message.put("Department", foundStudent.getDepartment().getName());
				listOfMessages.add(message); // list of individual's info
			}
		}
		else { 
			message = new LinkedHashMap<>();
			message.put("Error","No students found!");
			listOfMessages.add(message);
		}
		return listOfMessages;
	}
	
	
	@RequestMapping(value="/student/deleteAll") // truncates the student table
	public List<Map<String,String>> deleteAll(){
		Map<String,String> message ;
		List<Map<String,String>> listOfMessages = new LinkedList<>();
		Student foundStudent;
		Iterator<Student> iterator = this.studentService.findAll().iterator();
		if(iterator.hasNext()) {
			message = new LinkedHashMap<>(); //LinkedHashMap to preserve the message order
			message.put("Success", "List of deleted student list");
			listOfMessages.add(message);
			while(iterator.hasNext()) {
				foundStudent = iterator.next();
				message = new LinkedHashMap<>();
				message.put("ID", foundStudent.getId().toString());
				message.put("Name", foundStudent.getName());
				message.put("CGPA", foundStudent.getCgpa().toString());
				message.put("Department", foundStudent.getDepartment().getName());
				listOfMessages.add(message);
			}
			this.studentService.deleteAll();
		}
		else { 
			message = new LinkedHashMap<>();
			message.put("Error","No students found!");
			listOfMessages.add(message);
		}
		return listOfMessages;
	}
	
	
	@RequestMapping(value="/student/insert")//inserts new student record into the table
	public Map<String,String> insertStudent(@RequestParam("name") String name,@RequestParam("cgpa") double cgpa,@RequestParam("deptid") int deptid){
		//RequestParam gets the value from url, eg: ?name=harry -- here harry mapped to String name
		Map<String,String> message = new LinkedHashMap<>();
		Student newStudent;
		if(this.departmentService.find(deptid).isPresent()) {
			newStudent = new Student();
			newStudent.setName(name);
			newStudent.setCgpa(cgpa);
			newStudent.setDepartment(this.departmentService.find(deptid).get());
			
			if(this.studentService.insert(newStudent)!=null) {
				message.put("Success", "New student successfully added!");
				message.put("ID",newStudent.getId().toString());
				message.put("Name", name);
				message.put("CGPA", cgpa+"");
				message.put("Department", this.departmentService.find(deptid).get().getName());
			}
			else
				message.put("Error", "Error cannot add new student");
		}
		else {
			message.put("Error", "Department id "+deptid+" is not found");
		}
		return message;
	}
	
	@RequestMapping(value="/student/update") //Updates student table
	public Map<String,String> updateStudent(@RequestParam("id") Integer id,@RequestParam("name") String name,@RequestParam("cgpa") double cgpa,@RequestParam("deptid") int deptid){
		System.out.println("\nrunning update");
		Map<String,String> message = new LinkedHashMap<>();
		Student newStudent;
		if(this.studentService.find(id).isPresent()) {
			if(this.departmentService.find(deptid).isPresent()) {
				newStudent = this.studentService.find(id).get();
				newStudent.setName(name);
				newStudent.setCgpa(cgpa);
				newStudent.setDepartment(this.departmentService.find(deptid).get());
				
				if(this.studentService.update(newStudent)!=null) {
					message.put("Success", "Student details successfully updated");
					message.put("ID",newStudent.getId().toString());
					message.put("Name", name);
					message.put("CGPA", cgpa+"");
					message.put("Department", this.departmentService.find(deptid).get().getName());
				}
				else
					message.put("Error", "Error cannot update student");
			}
			else 
				message.put("Error", "Department id "+deptid+" is not found");
		}
		else
			message.put("Error", "Student id "+id+" is not found");
		
		return message;
	}
	
	
	@RequestMapping(value="/student/delete/{id}")//deletes record from student table for the given id
	public Map<String,String> deleteStudent(@PathVariable("id") Integer id){
		System.out.println("\nrunning delete");
		Map<String,String> message = new LinkedHashMap<>();
		Student oldStudent;
		if(this.studentService.find(id).isPresent()) {	
			oldStudent = this.studentService.find(id).get();
			
			message.put("ID", oldStudent.getId().toString());
			message.put("Name", oldStudent.getName());
			message.put("CGPA", oldStudent.getCgpa().toString());
			message.put("Department", oldStudent.getDepartment().getName());
			
			if(this.studentService.delete(oldStudent)) 
				message.put("Success", "Student successfully removed");
			else {
				message.clear();
				message.put("Error", "Error cannot delete student");
			}
		}
		else
			message.put("Error", "Student id "+id+" is not found");
		return message;
	}
}