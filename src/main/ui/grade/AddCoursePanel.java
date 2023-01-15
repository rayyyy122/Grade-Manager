package ui.grade;

import model.Course;
import model.Semester;
import ui.GradeManagerAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddCoursePanel extends JPanel implements ActionListener {
    protected JTextField textField;
    protected JTextField textField1;
    protected JTextField textField2;
    protected JLabel label = new JLabel("enter the semester you want to change");
    protected JLabel label1 = new JLabel("enter the name of the course you want to add");
    protected JLabel label2 = new JLabel("enter the grade of the course you want to add");
    private String semesterName;
    private String courseName;
    private int courseGrade;
    private Course course;
    private JButton button = new JButton("back");
    public static final JFrame frame = new JFrame("add a course");


    public AddCoursePanel() {
        super(new GridBagLayout());
        textField = new JTextField(40);
        textField.addActionListener(this);
        textField1 = new JTextField(40);
        textField1.addActionListener(this);
        textField2 = new JTextField(40);
        textField2.addActionListener(this);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(label, c);
        add(textField, c);
        add(label1, c);
        add(textField1, c);
        add(label2, c);
        add(textField2, c);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.addActionListener(this);
        add(button);
    }

    // EFFECTS: create and show the graphical user interface
    public static void createAndShowPanel() {
        //Add contents to the window.
        frame.add(new AddCoursePanel());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowPanel();

            }
        });

    }

    // EFFECTS: justify whether the account contain the input semester
    private boolean containSemester(String name) {
        ArrayList<String> semesterNames = new ArrayList<>();
        ArrayList<Semester> semesters = GradeManagerAppGUI.account.getSemester();
        for (Semester value : semesters) {
            String name1 = value.getSemesterName();
            semesterNames.add(name1);
        }
        return semesterNames.contains(name);
    }

    // EFFECTS: calls each sub-panel when click corresponding button
    @Override
    public void actionPerformed(ActionEvent e) {
        semesterName = textField.getText();
        courseName = textField1.getText();
        String gradeString = textField2.getText();
        try {
            if (e.getActionCommand().equals("back")) {
                frame.setVisible(false);
            } else {
                courseGrade = Integer.parseInt(gradeString);
                course = new Course(courseName, courseGrade);
                if (courseGrade < 0 || courseGrade > 100) {
                    JOptionPane.showMessageDialog(frame, "grade should not be negative or exceed 100!");
                } else if (!containSemester(semesterName)) {
                    JOptionPane.showMessageDialog(frame, "there isn't such a semester!");
                } else {
                    Semester semester = GradeManagerAppGUI.account.findSemester(semesterName);
                    semester.addCourse(course);
                    JOptionPane.showMessageDialog(frame, "add successful!");
                }
            }
        } catch (NumberFormatException e1) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "please enter a proper form of grade!");
        }


    }
}
