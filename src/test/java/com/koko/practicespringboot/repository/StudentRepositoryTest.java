package com.koko.practicespringboot.repository;

import com.koko.practicespringboot.entity.Guardian;
import com.koko.practicespringboot.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;


    @Test
    public void saveStudent() {
        Guardian guardian = Guardian.builder().name("Koko").mobile("999999999").build();
        Student student = Student.builder()
                .name("Boo")
                .email("boo@koko.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent() {
        List<Student> all = studentRepository.findAll();
        System.out.println("alll : " + all);
    }

    @Test
    public void printStudentByName() {
        Optional<List<Student>> students = studentRepository.findByName("koko");

        students.ifPresent(s -> {
            for (Student student : s) {
                System.out.println("email : " + student.getEmail());
            }
        });
        System.out.println(students);
    }

    @Test
    public void printStudentByNameContaining() {
        List<Student> students = studentRepository.findByNameContaining("b");

        System.out.println(students);
    }

    @Test
    public void printStudentByGuardianName() {
        List<Student> students = studentRepository.findByGuardianName("DAW SOe");

        System.out.println(students);
    }

    @Test
    public void printStudentByEmailJpql() {
        Optional<Student> studentByEmailAddress = studentRepository.getStudentByEmailAddress("koko1@koko.com");

        studentByEmailAddress.ifPresentOrElse(
                student -> {
                    System.out.println("It's found");
                    System.out.println(student);
                },
                () -> {
                    System.out.println("It's not found.");
                    System.out.println("Needa create");
                }
        );
    }

    @Test
    public void printStudentNameByEmailJpql() {
        Optional<String> studentNameByEmailAddress = studentRepository.getStudentNameByEmailAddress("koko@koko.com");

        studentNameByEmailAddress.ifPresentOrElse(System.out::println, () -> System.out.println("not found"));
    }

    @Test
    public void printStudentByEmailNativeJpql() {
        Optional<Student> studentByEmailAddressNative = studentRepository.getStudentByEmailAddressNative("koko1@koko.com");

        studentByEmailAddressNative.ifPresentOrElse(System.out::println, () -> System.out.println("not found"));
    }

    @Test
    public void printStudentByEmailNativeJpqlNamedParam() {
        Optional<Student> studentByEmailAddressNative = studentRepository.getStudentByEmailAddressNativeNamedParam("koko1@koko.com");

        studentByEmailAddressNative.ifPresentOrElse(System.out::println, () -> System.out.println("not found"));
    }

    @Test
    public void updateStudentNameByEmail() {
        int i = studentRepository.updateStudentNameByEmail("kkmhh", "koko1@koko.com");
        System.out.println(i);
    }
}