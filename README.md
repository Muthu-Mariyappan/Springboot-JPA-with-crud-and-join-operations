## Springboot-JPA-with-crud-and-join-operations

 This is a sample project in springboot and jpa(hibernate) and mysql
 It performs basic database operations and join operation in student and department tables
 
 ### Student table
 ---------------
 
	>* id primary key
	>* name not null
	>* cgpa not null
	>* deptid foreign key references department table
	
 ### Department table
 ------------------
	>id
	>name
	
> 	
	* using 'MYSQL' database to store the tables 
	
	##Implemented operations and sample url requests
	------------------------------------------------
	1. Find student by id
		
		> http://localhost:8080/student/find/1
		
	1. Display all student information
		
		> http://localhost:8080/student/findall
		
	1. Add new student entry to the table
	
		> http://localhost:8080/student/insert?name=kai&cgpa=9.87&deptid=100
		
	1. Update student entry
		
		> http://localhost:8080/student/update?id=7&name=sadie&cgpa=8.17&deptid=101
		
	1. Delete student entry
		
		> http://localhost:8080/student/delete/8
		
	1. Truncate the student table
		
		> http://localhost:8080/student/deleteAll
		
	1. Join student and department table
	
		> http://localhost:8080/jointable
		
	1. Find the college topper with respect to cgpa
	
		> http://localhost:8080/student/topper
	
		
	
  ### Tips
  -------
  * use Annotations such as @Autowired over context.xml files
  * use application.properties to store datasource information
  * Follow same naming convention
  * Try to use same field name as table field name
  * Double check the component names
  
  ### Useful tutorial links
  ------------------------
  
	* [Detailed and simple tutorial for springboot and jpa](https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-introduction-to-query-methods/)
	* Learn Spring Data JPA with Real Apps -- followed this app's project structure with changes to springboot
	
 
 
 
