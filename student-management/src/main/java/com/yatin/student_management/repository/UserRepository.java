package com.yatin.student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yatin.student_management.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

    User findByUsername(String username);

}