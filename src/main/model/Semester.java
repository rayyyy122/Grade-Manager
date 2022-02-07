package model;


import java.util.ArrayList;
import java.util.List;

//represent a semester having a list of courses with corresponding grades
public class Semester {
    private List<Course> courses;

    public Semester() {
        this.courses = new ArrayList<>();
    }

    //REQUIRES: 0 =< finalGrade <= 100
    //MODIFIES: this
    //EFFECTS: adds a new course to the lists of courses in a semester
    public List<Course> addCourse(String courseName, double finalGrade) {
        this.courses.add(new Course(courseName, finalGrade));
        return null;
    }

    public List<Course> deleteCourse(String courseName, double finalGrade) {
        return null;
    }

    public List<Course> getCourse() {
        return courses;
    }

    public double calculateGPA() {
        return 0;
    }




    // delete or rename this class!
}
