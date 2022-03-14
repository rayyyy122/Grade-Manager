package persistenceTest;

import model.Account;
import model.Course;
import model.Semester;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Account a = new Account();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Account a = new Account();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccount.json");
            writer.open();
            writer.write(a);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccount.json");
            a = reader.read();
            ArrayList<Semester> semesters = a.getSemester();
            assertEquals(0, semesters.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Account a = new Account();
            a.addSemester("2020");
            a.addSemester("2021");
            Course course1 = new Course("math", 88);
            Course course2 = new Course("cpsc", 93);
            Course course3 = new Course("phys", 77);
            Course course4 = new Course("chem", 83);
            a.findSemester("2020").addCourse(course1);
            a.findSemester("2020").addCourse(course2);
            a.findSemester("2021").addCourse(course3);
            a.findSemester("2021").addCourse(course4);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAccount.json");
            writer.open();
            writer.write(a);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAccount.json");
            a = reader.read();
            ArrayList<Semester> semesters = a.getSemester();
            assertEquals(2, semesters.size());
            assertEquals("math",a.findSemester("2020").getCourse().get(0).getName());
            assertEquals(88,a.findSemester("2020").getCourse().get(0).getGrade());
            assertEquals("cpsc",a.findSemester("2020").getCourse().get(1).getName());
            assertEquals(93,a.findSemester("2020").getCourse().get(1).getGrade());
            assertEquals("phys",a.findSemester("2021").getCourse().get(0).getName());
            assertEquals(77,a.findSemester("2021").getCourse().get(0).getGrade());
            assertEquals("chem",a.findSemester("2021").getCourse().get(1).getName());
            assertEquals(83,a.findSemester("2021").getCourse().get(1).getGrade());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
