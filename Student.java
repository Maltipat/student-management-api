package com.example.student_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty!")
    private String name;

    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Please enter a valid email!")
    private String email;

    @Min(value = 1, message = "Age must be at least 1!")
    @Max(value = 100, message = "Age cannot be more than 100!")
    private int age;

    // New fields
    @NotBlank(message = "Address cannot be empty!")
    private String address;

    @NotBlank(message = "Phone number cannot be empty!")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits!")
    private String phoneNumber;

    @NotBlank(message = "Course cannot be empty!")
    private String course;

    @NotBlank(message = "Gender cannot be empty!")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female or Other!")
    private String gender;

    @NotNull(message = "Date of birth cannot be empty!")
    private java.time.LocalDate dateOfBirth;
}