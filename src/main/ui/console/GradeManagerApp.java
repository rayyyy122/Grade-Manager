package ui.console;

import exception.NotCourseInTheListException;
import model.Account;
import model.Course;
import model.Semester;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static model.Semester.TotalGPA;

//grade manager app
public class GradeManagerApp {
    private static final String JSON_SOURCE = "./data/account.json";
    private Account account;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: run the grade manager app
    public GradeManagerApp() {
        runGradeManager();
    }

    //MODIFIES: this
    //EFFECTS: process user input
    public void runGradeManager() {
        boolean keepGoing = true;
        String command = null;
        jsonWriter = new JsonWriter(JSON_SOURCE);
        jsonReader = new JsonReader(JSON_SOURCE);

        initial();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye !");


    }

    private void processCommand(String command) {
        if (command.equals("v")) {
            doViewMyGrade();
        } else if (command.equals("c")) {
            doCompareMyGrade();
        } else if (command.equals("m")) {
            doManageMyAccount();
            saveAccount();
        } else if (command.equals("g")) {
            doManageMyGrade();
            saveAccount();
        } else {
            System.out.println("input not valid");
        }
    }

    //MODIFIES: this
    //EFFECT initializes a account
    private void initial() {
        account = new Account();
        Course testCourse = new Course("testCourse", 100);
        account.addSemester("testSemester");
        Semester semester = account.findSemester("testSemester");
        semester.addCourse(testCourse);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        loadAccount();
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome!");
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view my grade");
        System.out.println("\tc -> compare my grade");
        System.out.println("\tm -> manage my account");
        System.out.println("\tg -> manage my grade");
        System.out.println("\tq -> quit");
    }

    //EFFECTS: show a list off grades
    private void doViewMyGrade() {
        System.out.println("Enter the semester you want to view");
        String semesterName = input.next();

        Semester semester = account.findSemester(semesterName);
        if (semester != null) {
            ArrayList<Course> courses = semester.getCourse();
            for (int i = 0; i < courses.size(); i++) {
                System.out.println(courses.get(i).getName());
                System.out.println(courses.get(i).getGrade());
            }
            System.out.println("GPA:" + semester.calculateGPA() + "/" + TotalGPA);
        } else {
            System.out.println("cannot find the enter semester");
        }

    }

    //EFFECTS: show two lists of grades
    private void doCompareMyGrade() {
        System.out.println("Enter the name of the first semester");
        String semesterName1 = input.next();
        System.out.println("Enter the name of the second semester you want to compare with");
        String semesterName2 = input.next();
        if (account.findSemester(semesterName1) != null
                && account.findSemester(semesterName2) != null) {
            ArrayList<Semester> twoSemesters = account.compare(semesterName1, semesterName2);
            for (int i = 0; i < twoSemesters.size(); i++) {
                System.out.println(twoSemesters.get(i).getSemesterName() + ":");
                printSemester(twoSemesters.get(i));
            }
        } else {
            System.out.println("cannot find such semesters");
        }
    }

    //print a semester
    private void printSemester(Semester semester) {
        for (int i = 0; i < semester.getCourse().size(); i++) {
            System.out.println(semester.getCourse().get(i).getName());
            System.out.println(semester.getCourse().get(i).getGrade());
            System.out.println("GPA:" + semester.calculateGPA() + "/" + TotalGPA);
        }
    }

    // EFFECTS: manage my account by adding or deleting a semester
    private void doManageMyAccount() {
        String selection = "";

        while (!(selection.equals("c") || selection.equals("d"))) {
            System.out.println("c for create a semester");
            System.out.println("d for delete a semester");
            selection = input.next();
        }
        if (selection.equals("c")) {
            System.out.println("enter semester name you want to create");
            String name = input.next();
            account.addSemester(name);
            System.out.println("create successfully!");
        } else {
            System.out.println("enter semester name you want to delete");
            String name = input.next();
            if (account.findSemester(name) != null) {
                account.deleteSemester(name);
                System.out.println("delete successfully!");
            } else {
                System.out.println("cannot find such a semester!");
            }


        }

    }

    // EFFECTS: manage my grade by adding or deleting a course
    private void doManageMyGrade() {
        System.out.println("enter the name of semester that you want to change");
        Semester semester = account.findSemester(input.next());
        if (semester != null) {
            String selection = "";
            while (!(selection.equals("c") || selection.equals("d"))) {
                System.out.println("c for add a course with grade");
                System.out.println("d for delete a course with grade");
                selection = input.next();
            }
            try {
                doEditCourse(selection, semester);
            } catch (InputMismatchException e) {
                System.out.println("input is not valid! Please enter a integer!");
            }

        } else {
            System.out.println("there doesn't exist such a semester!");
        }
    }

    //EFFECTS: edit a course
    private void doEditCourse(String selection, Semester semester) throws InputMismatchException {
        if (selection.equals("c")) {
            System.out.println("enter the name of the course you want to add");
            String name = input.next();
            System.out.println("enter the grade of the course you want to add");
            int grade = input.nextInt();
            Course course = new Course(name, grade);
            semester.addCourse(course);
            System.out.println("add successfully!");
        } else {
            System.out.println("enter the name of the course you want to delete");
            try {
                semester.deleteCourse(input.next());
                System.out.println("delete successfully!");
            } catch (NotCourseInTheListException e) {
                System.out.println("cannot find such a course!");
            }
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveAccount() {
        try {
            jsonWriter.open();
            jsonWriter.write(account);
            jsonWriter.close();
            System.out.println("Saved Success");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_SOURCE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadAccount() {
        try {
            account = jsonReader.read();
            System.out.println("Loaded Success");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_SOURCE);
        }
    }
}
