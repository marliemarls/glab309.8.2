package com.perscholas.glab30981.impl;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.perscholas.glab30981.dto.StudentDTO;
import com.perscholas.glab30981.model.Role;
import com.perscholas.glab30981.model.Student;
import com.perscholas.glab30981.repository.RoleRepository;
import com.perscholas.glab30981.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.perscholas.glab30981.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository, RoleRepository roleRepository,
                              PasswordEncoder passwordEncoder) {
        super();
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveStudent(StudentDTO studentDto) {
        Student student = new Student();

        student.setName(studentDto.getFirstName() + " " +    studentDto.getLastName());
        student.setEmail(studentDto.getEmail());

        // Encrypt the password using Spring Security
        student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        student.setRoles(Arrays.asList(role));
        studentRepository.save(student);
    }
    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public Student findStudentByEmail(String email) {

        return studentRepository.findByEmail(email);
    }
    @Override
    public List<StudentDTO> findAllStudents() {
        List<Student>students = studentRepository.findAll();

        return students.stream()
                .map((student) -> mapToStudentDto(student))
                .collect(Collectors.toList());
    }
    private StudentDTO mapToStudentDto(Student student) {
        StudentDTO studentDto = new StudentDTO();

        String[] str = student.getName().split(" ");
        studentDto.setFirstName(str[0]);
        studentDto.setLastName(str[1]);
        studentDto.setEmail(student.getEmail());
        return studentDto;
    }
}
