package com.yatin.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yatin.student_management.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}