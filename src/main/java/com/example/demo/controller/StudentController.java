
package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1. Register student
    @PostMapping
    public ResponseEntity<Student> registerStudent(
            @Valid @RequestBody Student student) {

        Student savedStudent = studentService.registerStudent(student);

        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // 2. View all students
    @GetMapping("/getallStudents")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // 3. Find student by ID
    @GetMapping("/getbyid/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // 4. Update student
    @PutMapping("/updatestudent/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {

        return studentService.updateStudent(id, student);
    }

    // 5. Delete student
    @DeleteMapping("/deletestudent/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    // 6. Filter students by course
    @GetMapping("/course/{courseName}")
    public List<Student> getStudentsByCourse(@PathVariable String courseName) {
        return studentService.getStudentsByCourse(courseName);
    }

    // 7. Sort students by name
    @GetMapping("/sort/name")
    public List<Student> sortStudentsByName() {
        return studentService.sortStudentsByName();
    }

    // 8. Sort students by age
    @GetMapping("/sort/age")
    public List<Student> sortStudentsByAge() {
        return studentService.sortStudentsByAge();
    }

}
