package model;

import java.util.ArrayList;
import java.util.List;

import exception.NotCourseInTheListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SemesterTest {
    private Semester semester1;
    private Course course1 = new Course("MATH100", 91);
    private Course course2 = new Course("CHEM100", 85);
    private Course course3 = new Course("STAT200", 82);
    private Course course4 = new Course("GEOG100", 86);
    private Course course5 = new Course("PHYS100", 77);

    @BeforeEach
    void runBefore() {
        semester1 = new Semester("2020");
        semester1.addCourse(course1);
        semester1.addCourse(course2);
    }

    @Test
    void testConstructor() {
        ArrayList<Course> course = semester1.getCourse();
        assertEquals(2, course.size());
    }

    @Test
    void addCourse() {
        ArrayList<Course> course = semester1.addCourse(course3);
        assertEquals(3, course.size());
    }

    @Test
    void deleteCourse() {
        ArrayList<Course> course = null;
        try {
            course = semester1.deleteCourse("MATH100");
        } catch (NotCourseInTheListException e) {
            //
        }
        assertEquals(1, course.size());
        semester1.addCourse(course4);
        ArrayList<Course> course2 = null;
        try {
            course2 = semester1.deleteCourse("GEOG100");
        } catch (NotCourseInTheListException e) {
            //
        }
        assertEquals(1, course2.size());
    }

    @Test
    void testCalculateGPA() {
        assertEquals(3.8104, semester1.calculateGPA());
        semester1.addCourse(course5);
        assertEquals(3.6516333333333333, semester1.calculateGPA());
    }
}
