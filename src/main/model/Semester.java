package model;


import java.util.ArrayList;
import java.util.List;

//represent a semester having a list of courses with corresponding grades
public class Semester {
    public static final double TotalGPA = 4.33;
    private String name;
    private ArrayList<Course> courses;

    public Semester(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }

    //REQUIRES: 0 =< finalGrade <= 100
    //MODIFIES: this
    //EFFECTS: adds a new course to the lists of courses in a semester
    public ArrayList<Course> addCourse(Course course) {
        this.courses.add(new Course(course.getName(), course.getGrade()));
        return courses;
    }

    //REQUIRES: the list is not empty and must enter the name that exist in the list
    //MODIFIES: this
    //EFFECTS: delete the course with the given name
    public ArrayList<Course> deleteCourse(String courseName) {
        for (int position = 0; position < courses.size(); position++) {
            if (courses.get(position).getName().equals(courseName)) {
                courses.remove(position);
            }
        }
        return courses;
    }

    public ArrayList<Course> getCourse() {
        return courses;
    }

    //REQUIRES: the course list is not empty
    //EFFECTS: calculate the overall GPA of a semester
    public double calculateGPA() {
        double a = 0;
        for (int position = 0; position < courses.size(); position++) {
            a = courses.get(position).getGrade() + a;
        }
        return a * TotalGPA / (courses.size() * 100);
    }

    public String getSemesterName() {
        return name;
    }


    // delete or rename this class!
}
