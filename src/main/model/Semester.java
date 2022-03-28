package model;


import exception.NotCourseInTheListException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.math.BigDecimal;
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
        EventLog.getInstance().logEvent(new Event("Add Course " + course.getName() + " with "
                + course.getGrade() + " to Semester " + name));
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
                EventLog.getInstance().logEvent(new Event("Delete Course " + courseName + " from semester " + name));
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
        Double d = a / courses.size();

        double d1 = (Math.round(d));
        Double d2 = convertPercentage(d1);
        return d2;
    }

    //REQUIRES: 0 <= the percentage <= 100
    public double convertPercentage(double d) {
        if (d >= 90.0) {
            return TotalGPA;
        } else if (d >= 50.0 && d <= 65.0) {
            return 1.00 + (d - 50) * 0.1;
        } else if (d >= 66.0 && d <= 69.0) {
            Double d1 = 2.55 + (d - 66) * 0.05;
            BigDecimal b = new BigDecimal(d1);
            return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else if (d == 70.0 || d == 71.0) {
            return 2.80 + (d - 70) * 0.1;
        } else if (d == 72.0) {
            return 2.95;
        } else if (d >= 73.0 && d <= 80.0) {
            return 3.00 + (d - 73) * 0.1;
        } else if (d >= 81.0 && d <= 85.0) {
            return 3.75 + (d - 81) * 0.05;
        } else {
            return extracted(d);
        }
    }

    private double extracted(double d) {
        if (d == 86.0) {
            return 4.00;
        } else if (d == 87.0) {
            return 4.10;
        } else if (d == 88.0) {
            return 4.20;
        } else if (d == 89.0) {
            return 4.30;
        } else {
            return 1.00;
        }
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
