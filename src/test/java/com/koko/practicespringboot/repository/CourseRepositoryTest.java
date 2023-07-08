package com.koko.practicespringboot.repository;

import com.koko.practicespringboot.entity.Course;
import com.koko.practicespringboot.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

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

    @Test
    public void findAllPagination() {
        PageRequest firstPageWithThreeRecords = PageRequest.of(0, 3);

        PageRequest secondPageWithTwoRecords = PageRequest.of(1, 2);

        List<Course> threeRecords = repository.findAll(secondPageWithTwoRecords).getContent();
        long totalElements = repository.findAll(secondPageWithTwoRecords).getTotalElements();
        long totalPages = repository.findAll(secondPageWithTwoRecords).getTotalPages();

        System.out.println("totalElements :" + totalElements);
        System.out.println("totalPages :" + totalPages);

        System.out.println(threeRecords);
    }

    @Test
    public void findAllSorting() {
        PageRequest sortByTitle = PageRequest.of(0, 2, Sort.by("title"));

        PageRequest creditDsc = PageRequest.of(0, 2, Sort.by("credit").descending());

        PageRequest sortByTitleAndCreditDsc = PageRequest.of(0, 2, Sort.by("title").descending().and(Sort.by("credit")));

        List<Course> records = repository.findAll(sortByTitle).getContent();

        for (Course record : records) {
            System.out.println(record);
        }
    }

    @Test
    public void printFindByTitleContaining() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Course> courses = repository.findByTitleContaining("d", pageRequest);

        for (Course course : courses.getContent()) {
            System.out.println(course);
        }
    }

}