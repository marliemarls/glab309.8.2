package com.perscholas.glab30981.repository;
import com.perscholas.glab30981.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>
{

    Student findByEmail(String email);
}

