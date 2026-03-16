package com.yatin.student_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.yatin.student_management.model.Student;
import com.yatin.student_management.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService service;

    private static final Logger logger =
        LoggerFactory.getLogger(StudentController.class);
    @GetMapping
    public List<Student> getStudents(){
         logger.info("Fetching all students");
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
public Student getStudentById(@PathVariable Long id){
    
    return service.getStudentById(id); 
}

//    @PostMapping
// @PreAuthorize("hasRole('ADMIN')")
// public Student addStudent(@RequestBody Student student){
//     return service.saveStudent(student);  
// }

@PostMapping
@PreAuthorize("hasRole('ADMIN')")
public Student addStudent(@Valid @RequestBody Student student){
     logger.info("Creating student: {}", student.getName());
    return service.saveStudent(student);
}
@PutMapping("/{id}")
@PreAuthorize("hasRole('ADMIN')")
public Student updateStudent(
        @PathVariable Long id,
        @Valid @RequestBody Student student){

    return service.updateStudent(id, student);
}

@DeleteMapping("/jdbc/{id}")
@PreAuthorize("hasRole('ADMIN')")
public String deleteStudentJdbc(@PathVariable Long id){

    service.deleteStudentJdbc(id);

    return "Student deleted using JDBC";
}
}