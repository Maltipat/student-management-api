package com.example.student_api.controller;

import com.example.student_api.model.Student;
import com.example.student_api.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public List<Student> getAll() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Student create(@Valid @RequestBody Student s) {
        return service.addStudent(s);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @Valid @RequestBody Student s) {
        return service.updateStudent(id, s);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteStudent(id);
        return "Student deleted successfully!";
    }

    @GetMapping("/age/{age}")
    public List<Student> getByAge(@PathVariable int age) {
        return service.getStudentsBelowAge(age);
    }

    @PutMapping("/age/increment")
    public String updateAge(@RequestBody Map<String, Integer> payload) {
        int maxAge = payload.get("maxAge");
        int increment = payload.get("increment");
        service.updateAge(maxAge, increment);
        return "Ages updated successfully";
    }

    @DeleteMapping("/delete-multiple")
    public String deleteMultiple(@RequestBody List<Long> ids) {
        return service.deleteMultipleStudents(ids);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });
        return ResponseEntity.badRequest().body(errors);
    }
    // Course se students dhundo
    @GetMapping("/course/{course}")
    public List<Student> getByCourse(@PathVariable String course) {
        return service.getStudentsByCourse(course);
    }

    // Age exactly match
    @GetMapping("/age/exact/{age}")
    public List<Student> getByExactAge(@PathVariable int age) {
        return service.getStudentsByAge(age);
    }
}