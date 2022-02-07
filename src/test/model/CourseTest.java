package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    private Course testCourse;

    @BeforeEach
    void runBefore() {
        testCourse = new Course("MATH100", 87);
    }

    @Test
    void testConstructor() {
        assertEquals("MATH100", testCourse.getName());
        assertEquals(87, testCourse.getGrade());
    }
}