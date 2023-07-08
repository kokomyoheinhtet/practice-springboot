package com.koko.practicespringboot.repository;

import com.koko.practicespringboot.entity.Course;
import com.koko.practicespringboot.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    public void printCourses() {
        List<Course> all = repository.findAll();
        System.out.println(all);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .name("sumyatthu").build();

        Course course = Course.builder()
                .title("pyton")
                .credit(6)
                .teacher(teacher)
                .build();

        repository.save(course);
    }

}