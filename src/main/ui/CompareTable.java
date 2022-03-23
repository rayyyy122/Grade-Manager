package ui;

import model.Account;
import model.Course;
import model.Semester;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class CompareTable extends JPanel implements ActionListener {
    private Account account = GradeManagerAppGUI.account;
    String[] columnNames = {"semester name",
            "course name",
            "grade",
            "GPA"};
    public static final JFrame frame = new JFrame("compare my grade" + " ( total GPA: " + Semester.TotalGPA + " )");

    public CompareTable(String semesterName1, String semesterName2) {
        super(new GridLayout(1, 0));
        Semester s1 = account.findSemester(semesterName1);
        Semester s2 = account.findSemester(semesterName2);
        Double g1 = s1.calculateGPA();
        Double g2 = s2.calculateGPA();
        ArrayList<Course> courses1 = s1.getCourse();
        ArrayList<Course> courses2 = s2.getCourse();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        JTable table = new JTable(model);
        addFirstRow(courses1, semesterName1, model, g1);
        addLeftRow(courses1, semesterName1, model, g1);
        addFirstRow(courses2, semesterName2, model, g2);
        addLeftRow(courses2, semesterName2, model, g2);
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

    private void addLeftRow(ArrayList<Course> courses, String semesterName, DefaultTableModel model, Double g) {
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
    }

    public static void createAndShowTable(String name1, String name2) {
        //Create and set up the window.


        //Create and set up the content pane.
        CompareTable newContentPane = new CompareTable(name1, name2);
        newContentPane.setOpaque(true);//content panes must be opaque
        frame.setContentPane(newContentPane);


        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
