package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @NotNull(message = "Student ID is required")
    @Min(value = 1, message = "Student ID must be greater than zero")
    private Long studentId;

    @NotBlank(message = "Student name cannot be blank")
    @Size(max = 100, message = "Student name cannot exceed 100 characters")
    private String studentName;

    @NotBlank(message = "Course name cannot be blank")
    @Size(max = 100, message = "Course name cannot exceed 100 characters")
    private String courseName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Student age must be at least 18")
    private Integer age;

}
