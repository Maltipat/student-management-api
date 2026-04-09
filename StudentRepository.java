package com.example.student_api.repository;

import com.example.student_api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // GET
    List<Student> findByAgeLessThan(int age);

    // UPDATE
    @Modifying
    @Query("UPDATE Student s SET s.age = s.age + :increment WHERE s.age < :maxAge")
    void updateAgeLessThan(@Param("maxAge") int maxAge, @Param("increment") int increment);

    @Query("SELECT s FROM Student s WHERE UPPER(s.name) LIKE UPPER(CONCAT(:prefix, '%'))")
    List<Student> findByNameStartingWithCaseInsensitive(@Param("prefix") String prefix);

    // DELETE MULTIPLE
    void deleteAllByIdInBatch(Iterable<Long> ids);
    // COURSE se filter
    List<Student> findByCourse(String course);
    // Age exactly match
    List<Student> findByAge(int age);
}