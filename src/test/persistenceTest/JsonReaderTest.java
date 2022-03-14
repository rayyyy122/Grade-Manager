package persistenceTest;

import model.Account;
import model.Semester;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Account a = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAccount() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAccount.json");
        try {
            Account a = reader.read();
            ArrayList<Semester> semesters = a.getSemester();
            assertEquals(0, semesters.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAccount() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAccount.json");
        try {
            Account a = reader.read();
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
            fail("Couldn't read from file");
        }
    }
}
