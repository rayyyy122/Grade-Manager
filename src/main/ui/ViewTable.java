package ui;

import model.Account;
import model.Course;
import model.Semester;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class ViewTable extends JPanel {
    private Account account = GradeManagerAppGUI.account;
    String[] columnNames = {"semester name",
            "course name",
            "grade",
            "GPA"};



    public ViewTable(String semesterName) {
        super(new GridLayout(1, 0));
        Semester s = account.findSemester(semesterName);
        Double g = s.calculateGPA();
        ArrayList<Course> courses = s.getCourse();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        JTable table = new JTable(model);

        addFirstRow(courses, semesterName, model, g);

        for (int i = 1; i < courses.size(); i++) {
            String courseName = courses.get(i).getName();
            Double courseGrade = courses.get(i).getGrade();
            Vector vector2 = new Vector(4);
            vector2.add(0, "");
            vector2.add(1, courseName);
            vector2.add(2, courseGrade);
            vector2.add(3, "");
            model.addRow(vector2);
        }
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private void addFirstRow(ArrayList<Course> courses, String semesterName, DefaultTableModel model, Double g) {
        for (int i = 0; i < 1; i++) {
            String courseName = courses.get(i).getName();
            Double courseGrade = courses.get(i).getGrade();
            Vector vector1 = new Vector(4);
            vector1.add(0, semesterName);
            vector1.add(1, courseName);
            vector1.add(2, courseGrade);
            vector1.add(3, g);
            model.addRow(vector1);
        }
    }

    public static void createAndShowTable(String name) {
        //Create and set up the window.
        JFrame frame = new JFrame("view my grade" + " ( total GPA: " + Semester.TotalGPA + " )");


        //Create and set up the content pane.
        ViewTable newContentPane = new ViewTable(name);
        newContentPane.setOpaque(true);//content panes must be opaque
        frame.setContentPane(newContentPane);


        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


}
