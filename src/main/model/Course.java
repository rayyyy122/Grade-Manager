package model;

import org.json.JSONObject;
import persistence.Writable;

//represents a course with its grade
public class Course implements Writable {
    private String courseName;
    private double finalGrade;

    //MODIFIES: this
    //EFFECTS: construct a course with course name and its grade
    public Course(String courseName, double finalGrade) {
        this.courseName = courseName;
        this.finalGrade = finalGrade;
    }

    //EFFECTS: get the course name of the course
    public String getName() {
        return courseName;
    }


    //EFFECTS: get the grade of the course
    public double getGrade() {
        return finalGrade;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", courseName);
        json.put("grade", finalGrade);
        return json;
    }
}
