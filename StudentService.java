package com.example.student_api.service;

import com.example.student_api.model.Student;
import com.example.student_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found!"));
    }

    public Student addStudent(Student s) {
        return repo.save(s);
    }

    public Student updateStudent(Long id, Student updated) {
        Student existing = getById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setAge(updated.getAge());
        return repo.save(existing);
    }

    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }

    public String deleteMultipleStudents(List<Long> ids) {
        repo.deleteAllByIdInBatch(ids);
        return "Students deleted successfully!";
    }

    // GET — age se kam wale students
    public List<Student> getStudentsBelowAge(int age) {
        return repo.findByAgeLessThan(age);
    }

    // UPDATE — age increment
    @Transactional
    public void updateAge(int maxAge, int increment) {
        repo.updateAgeLessThan(maxAge, increment);
    }
    // Course se students
    public List<Student> getStudentsByCourse(String course) {
        return repo.findByCourse(course);
    }
    // Age exactly match wale students
    public List<Student> getStudentsByAge(int age) {
        return repo.findByAge(age);
    }

}