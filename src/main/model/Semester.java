package model;


import exception.NotCourseInTheListException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//represent a semester having a list of courses with corresponding grades
public class Semester extends NotCourseInTheListException implements Writable {
    public static final double TotalGPA = 4.33;
    private String name;
    private ArrayList<Course> courses;

    //MODIFIES: this
    //EFFECTS: construct a semester containing a list of courses
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
    public ArrayList<Course> deleteCourse(String courseName) throws NotCourseInTheListException {
        int originalSize = courses.size();
        for (int position = 0; position < courses.size(); position++) {
            if (courses.get(position).getName().equals(courseName)) {
                courses.remove(position);
            }
        }
        if (courses.size() == originalSize) {
            throw new NotCourseInTheListException();
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Description", name);
        json.put("courses", semesterToJson());
        return json;
    }

    private JSONArray semesterToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : courses) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }


    // delete or rename this class!
}
