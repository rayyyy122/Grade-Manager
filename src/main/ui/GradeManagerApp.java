package ui;

import model.Account;
import model.Course;
import model.Semester;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.Semester.TotalGPA;

//grade manager app
public class GradeManagerApp {
    private Account account;
    private Scanner input;

    //EFFECTS: run the grade manager app
    public GradeManagerApp() {
        runGradeManager();
    }

    //MODIFIES: this
    //EFFECTS: process user input
    public void runGradeManager() {
        boolean keepGoing = true;
        String command = null;

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
        } else if (command.equals("g")) {
            doManageMyGrade();
        } else {
            System.out.println("input not valid");
        }
    }

    //MODIFIES: this
    //EFFECT initializes a account
    private void initial() {
        account = new Account();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
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
            List<Semester> twoSemesters = account.compare(semesterName1, semesterName2);
            for (int i = 0; i < twoSemesters.size(); i++) {
                printSemester(twoSemesters.get(i));
            }
        } else {
            System.out.println("cannot find such semesters");
        }
    }

    private void printSemester(Semester semester) {
        for (int i = 0; i < semester.getCourse().size(); i++) {
            System.out.println(semester.getSemesterName() + ":");
            System.out.println(semester.getCourse().get(i).getName());
            System.out.println(semester.getCourse().get(i).getGrade());
            System.out.println("GPA:" + semester.calculateGPA() + "/" + TotalGPA);
        }
    }

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
            account.deleteSemester(name);
            System.out.println("delete successfully!");
        }

    }

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
            doEditCourse(selection, semester);
        } else {
            System.out.println("there doesn't exist such a course!");
        }
    }

    private void doEditCourse(String selection, Semester semester) {
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
            semester.deleteCourse(input.next());
            System.out.println("delete successfully!");
        }
    }
}
