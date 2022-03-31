package ui.info;

import model.Course;
import model.Semester;
import ui.GradeManagerAppGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class ViewSemesterNamePanel extends JPanel implements ActionListener {

    public ViewSemesterNamePanel() {
        super(new GridLayout(1, 0));
        String[] columnNames = {"semester name",
                "number of course"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        JTable table = new JTable(model);
        ArrayList<Semester> semesters = GradeManagerAppGUI.account.getSemester();
        for (int i = 0; i < semesters.size(); i++) {
            String semesterName = semesters.get(i).getSemesterName();
            ArrayList<Course> courses = semesters.get(i).getCourse();
            int coursesContained = courses.size();
            Vector vector = new Vector(2);
            vector.add(0, semesterName);
            vector.add(1, coursesContained);
            model.addRow(vector);
        }
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public static void createAndShowTable() {
        //Create and set up the window.
        JFrame frame = new JFrame("view all semester names");


        //Create and set up the content pane.
        ViewSemesterNamePanel newContentPane = new ViewSemesterNamePanel();
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
