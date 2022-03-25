package model;

import java.util.ArrayList;
import java.util.List;

import exception.NotCourseInTheListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SemesterTest {
    private Semester semester1;
    private Semester semester2;
    private Course course1 = new Course("MATH100", 91);
    private Course course2 = new Course("CHEM100", 85);
    private Course course3 = new Course("STAT200", 82);
    private Course course4 = new Course("GEOG100", 86);
    private Course course5 = new Course("PHYS100", 77);
    private Course course6 = new Course("SOCI200", 2);
    private Course course7 = new Course("70", 70);
    private Course course8 = new Course("72", 72);

    @BeforeEach
    void runBefore() {
        semester2 = new Semester("2021");
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
        try {
            ArrayList<Course> course3 =semester1.deleteCourse("c");
        } catch (NotCourseInTheListException e) {
            System.out.println("cannot find such a course!");
        }
    }

    @Test
    void testCalculateGPA() {
        semester1.addCourse(course5);
        assertEquals(3.9, semester1.calculateGPA());
        semester2.addCourse(course1);
        assertEquals(4.33, semester2.calculateGPA());
        semester2.addCourse(course6);
        assertEquals(1.00, semester2.calculateGPA());
        semester2.addCourse(course1);
        assertEquals(2.10, semester2.calculateGPA());
        Semester semester = new Semester("67");
        Course course = new Course("67", 67);
        semester.addCourse(course);
        Double d = semester.calculateGPA();
        assertEquals(2.6, d);
        Semester semester89 = new Semester("89");
        Course course89 = new Course("89", 89);
        semester89.addCourse(course89);
        assertEquals(4.3, semester89.calculateGPA());
        Semester semester88 = new Semester("88");
        Course course88 = new Course("88", 88);
        semester88.addCourse(course88);
        assertEquals(4.2, semester88.calculateGPA());



    }

    @Test
    void testMoreCalculateGPA() {
        semester2.addCourse(course7);
        assertEquals(2.80, semester2.calculateGPA());
        Semester semester = new Semester("72");
        semester.addCourse(course8);
        Double d = semester.calculateGPA();
        assertEquals(2.95, d);
        Semester semester3 = new Semester("87");
        Course course = new Course("87", 87);
        semester3.addCourse(course);
        assertEquals(4.1, semester3.calculateGPA());
        Semester semester4 = new Semester("77");
        semester4.addCourse(course5);
        assertEquals(3.4, semester4.calculateGPA());
        Semester semester71 = new Semester("71");
        Course course71 = new Course("71", 71);
        semester71.addCourse(course71);
        assertEquals(2.9, semester71.calculateGPA());
        Semester semester86 = new Semester("86");
        semester86.addCourse(course4);
        assertEquals(4.0, semester86.calculateGPA());


    }
}
