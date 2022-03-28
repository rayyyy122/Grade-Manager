package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    private Account account1;
    private Account account2;
    private Semester semester1;
    private Semester semester2;
    private Semester semester3;
    private Semester semester4;
    private Course course1 = new Course("MATH100", 91);
    private Course course2 = new Course("CHEM100", 85);
    private Course course3 = new Course("STAT200", 82);
    private Course course4 = new Course("GEOG100", 86);
    private Course course5 = new Course("PHYS100", 77);
    private Course course6 = new Course("BIOL100", 89);

    @BeforeEach
    void runBefore() {
        account1 = new Account();
        account2 = new Account();
        semester1 = new Semester("2020");
        semester2 = new Semester("2021");
        semester3 = new Semester("2022");
        semester4 = new Semester("2023");
        account1.addSemester("2020");
        account1.addSemester("2021");
        semester1.addCourse(course1);
        semester1.addCourse(course2);
        semester2.addCourse(course3);
        semester2.addCourse(course4);
        semester3.addCourse(course5);
        semester3.addCourse(course6);

    }

    @Test
    void testConstructor() {
        ArrayList<Semester> semesters = account1.getSemester();
        assertEquals(2, semesters.size());
    }

    @Test
    void testAddSemester() {
        assertTrue(account1.addSemester("2022"));

    }

    @Test
    void testDeleteSemester() {
        ArrayList<Semester> semesters = account1.deleteSemester("2021");
        assertEquals(1,semesters.size());
        account1.addSemester("2023");
        ArrayList<Semester> semesters2 = account1.deleteSemester("2023");
        assertEquals(1,semesters2.size());
    }

    @Test
    void testCompare() {
        account1.addSemester("2022");
        ArrayList<Semester> semesters = account1.compare("2020","2022");
        assertEquals(2,semesters.size());

    }

    @Test
    void testFindSemester() {
        Semester semester =account1.findSemester("2020");
        String name = semester.getSemesterName();
        assertEquals("2020",name);
        assertNull(account1.findSemester("1"));

    }




}
