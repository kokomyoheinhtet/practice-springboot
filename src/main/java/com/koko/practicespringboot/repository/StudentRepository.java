package com.koko.practicespringboot.repository;

import com.koko.practicespringboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<List<Student>> findByName(String name);

    List<Student> findByNameContaining(String name);

    List<Student> findByNameIsNotNull();

    List<Student> findByGuardianName(String guardianName);

    //JPQL
    @Query("select s from Student s where s.email = ?1")
    Optional<Student> getStudentByEmailAddress(String email);

    //JPQL
    @Query("select s.name from Student s where s.email = ?1")
    Optional<String> getStudentNameByEmailAddress(String email);

    //native query
    @Query(nativeQuery = true,
            value = "select * from tbl_student s where s.email_address = ?1")
    Optional<Student> getStudentByEmailAddressNative(String email);

    //native query Named param
    @Query(nativeQuery = true,
            value = "select * from tbl_student s where s.email_address = :email")
    Optional<Student> getStudentByEmailAddressNativeNamedParam(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "update tbl_student set name = :name where email_address = :email")
    int updateStudentNameByEmail(String name, String email);
}
