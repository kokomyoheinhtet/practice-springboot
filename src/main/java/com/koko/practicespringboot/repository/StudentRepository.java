package com.koko.practicespringboot.repository;

import com.koko.practicespringboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<List<Student>> findByName(String name);

    List<Student> findByNameContaining(String name);

    List<Student> findByNameIsNotNull();

    List<Student> findByGuardianName(String guardianName);
}
