package model;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SemesterTest {
    private Semester semester1;
    private Course course1 = new Course("MATH100", 91);
    private Course course2 = new Course("CHEM100", 85);

    @BeforeEach
    void runBefore() {
        semester1 = new Semester();
        semester1.addCourse("MATH100", 91);
        semester1.addCourse("CHEM100", 85);
    }

    @Test
    void testConstructor() {
        List<Course> course = semester1.getCourse();
        assertEquals(2, course.size());
    }

    @Test
    void addCourse() {
        List<Course> course = semester1.addCourse("STAT 200", 82);
        assertEquals(3, course.size());
    }

    @Test
    void deleteCourse() {
        List<Course> course = semester1.deleteCourse("MATH100", 91);
        assertEquals(2, course.size());
    }

    @Test
    void testCalculateGPA() {
        assertEquals(3.61555, semester1.calculateGPA());
    }
}
