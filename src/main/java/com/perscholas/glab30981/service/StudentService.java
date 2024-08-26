package com.perscholas.glab30981.service;
import com.perscholas.glab30981.dto.StudentDTO;
import com.perscholas.glab30981.model.Student;

import java.util.List;

public interface StudentService {
    void saveStudent(StudentDTO studentDto);
    Student findStudentByEmail(String email);
    List<StudentDTO> findAllStudents();
}

