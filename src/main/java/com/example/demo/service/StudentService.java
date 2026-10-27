
package com.example.demo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.exception.DuplicateStudentException;

@Service
public class StudentService {

    // In-memory storage
    private final List<Student> students = new ArrayList<>();

    // 1. Register a new student
    public Student registerStudent(Student student) {

        boolean exists = students.stream()
                .anyMatch(s -> s.getStudentId()
                        .equals(student.getStudentId()));

        if (exists) {
            throw new DuplicateStudentException(
                    "Student ID already exists: "
                            + student.getStudentId());
        }

        students.add(student);
        return student;
    }

    // 2. View all students
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // 3. Find a student by ID
    public Student getStudentById(Long studentId) {

        return students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with ID: " + studentId));
    }

    // 4. Update student details
    public Student updateStudent(Long studentId, Student updatedStudent) {

        Student existingStudent = getStudentById(studentId);

        existingStudent.setStudentName(updatedStudent.getStudentName());
        existingStudent.setCourseName(updatedStudent.getCourseName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setAge(updatedStudent.getAge());

        return existingStudent;
    }

    // 5. Delete a student by ID
    public String deleteStudent(Long studentId) {

        Student student = getStudentById(studentId);
        students.remove(student);

        return "Student deleted successfully with ID: " + studentId;
    }

    // 6. Custom method: filter students by course name
    public List<Student> getStudentsByCourse(String courseName) {

        if (courseName == null || courseName.isBlank()) {
            throw new IllegalArgumentException(
                    "Course name cannot be blank");
        }

        return students.stream()
                .filter(s -> s.getCourseName()
                        .equalsIgnoreCase(courseName))
                .toList();
    }

    // 7. Custom method: sort students by name
    public List<Student> sortStudentsByName() {

        return students.stream()
                .sorted(Comparator.comparing(
                        Student::getStudentName,
                        String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    // 8. Custom method: sort students by age
    public List<Student> sortStudentsByAge() {

        return students.stream()
                .sorted(Comparator.comparing(Student::getAge))
                .toList();
    }

  
}
