package com.example.students.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.students.model.Student;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	private List<Student> students  = new ArrayList<>(Arrays.asList(
			new Student(1,"Chema", "chema@gmail.com", 23, "spanish"),
			new Student(2, "Pepe", "pepe@gmail.com", 6, "math"),
			new Student(3, "Jose", "jose@gmail.com", 8, "geology"),
			new Student(4, "Manuel", "manuel@gmail.com", 20, "physics")
			)); 
	
	@GetMapping
	public List<Student> getStudents () {
		return students;
	}
	
	@GetMapping("/{email}")
	public Student getStudentByEmail (@PathVariable String email) {
		for(Student s : students) {
			if(s.getEmail().equalsIgnoreCase(email)) {				
				return s;
			}
		}
		return null;
	}
	
	@PostMapping
	public Student postStudent (@RequestBody Student student) {
		students.add(student);
		return student;
	}
	@PutMapping
	public Student putStudent (@RequestBody Student student) {
		for(Student s : students) {
			if(s.getId() == student.getId()) {
				s.setName(student.getName());
				s.setEmail(student.getEmail());
				s.setAge(student.getAge());
				s.setCourse(student.getCourse());
				return s;
			}
		}
		return null;
	}
	
	@DeleteMapping("/{id}")
	public Student deleteStudent (@PathVariable int id) {
		for(Student s : students) {
			if(s.getId() == id) {
				students.remove(s);
				return s;
			}
		}
		return null;
	}
	
}
