package com.koko.practicespringboot.repository;

import com.koko.practicespringboot.entity.Course;
import com.koko.practicespringboot.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository repository;

    @Test
    public void saveTeacher() {
        Course courseDBA = Course.builder().title("DBA").credit(5).build();
        Course courseArch = Course.builder().title("Archi").credit(6).build();
        Teacher teacher = Teacher.builder()
                .name("Sumaytthu")
//                .courses(List.of(courseDBA, courseArch))
                .build();
        repository.save(teacher);
    }

    @Test
    public void printTeacher() {
        List<Teacher> teachers = repository.findAll();

        System.out.println(teachers);
    }

}