package com.yatin.student_management.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yatin.student_management.model.Student;
import com.yatin.student_management.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    @Autowired
private JdbcTemplate jdbcTemplate;

    public List<Student> getAllStudents(){
        return repo.findAll();
    }

    public Student saveStudent(Student student){
        return repo.save(student); 
    }

    public Student getStudentById(Long id){
    return repo.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
}
public Student updateStudent(Long id, Student student){

    Student existing = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found"));

    existing.setName(student.getName());
    existing.setCourse(student.getCourse());
    existing.setEmail(student.getEmail());

    return repo.save(existing);
}
public void deleteStudentJdbc(Long id){

    String sql = "DELETE FROM students WHERE id = ?";
System.out.println(sql);
    jdbcTemplate.update(sql, id);
}
}