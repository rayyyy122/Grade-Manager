package model;

//represents a course with its grade
public class Course {
    private String courseName;
    private double finalGrade;

    public Course(String courseName, double finalGrade) {
        this.courseName = courseName;
        this.finalGrade = finalGrade;
    }

    public String getName() {
        return courseName;
    }

    public double getGrade() {
        return finalGrade;
    }
}
