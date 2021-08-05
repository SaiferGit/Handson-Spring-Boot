package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll(); // returns all the list of student
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional =  studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email Taken");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        // if id not found throw exception
        if(!exists){
            throw new IllegalStateException(
                    "student with id " + studentId + "does not exists"
            );
        }
        // if id found, delete it from db
        studentRepository.deleteById(studentId);
    }


    // transactional means entity goes an managing state
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        // finding students by id or throwing exceptions
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with Id " + studentId + "does not exists"
                ));
        // updating name, name that is provided for updating, not 0 or null or is not equal to existing
        if(name != null && name.length()> 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null && email.length()> 0
                && !Objects.equals(student.getEmail(), email)){
            // check if given email is not taken
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }
    }
}
