package ui.grade;

import exception.NotCourseInTheListException;
import model.Semester;
import ui.GradeManagerAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteCoursePanel extends JPanel implements ActionListener {
    protected JTextField textField;
    protected JTextField textField1;
    protected JLabel label;
    protected JLabel label1;
    private JButton button = new JButton("back");
    public static final JFrame frame = new JFrame("delete a course");

    private static final String newline = "\n";

    public DeleteCoursePanel() {
        super(new GridBagLayout());

        textField = new JTextField(40);
        textField.addActionListener(this);

        textField1 = new JTextField(40);
        textField1.addActionListener(this);

        label = new JLabel("enter the name of the semester you want to change");

        label1 = new JLabel("enter the name of the course you want to delete");

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
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.addActionListener(this);
        add(button);
    }

    public static void createAndShowPanel() {
        //Create and set up the window.



        //Add contents to the window.
        frame.add(new DeleteCoursePanel());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String semesterName = textField.getText();
        String courseName = textField1.getText();
        ArrayList<String> semesterNames = new ArrayList<>();
        ArrayList<Semester> semesters = GradeManagerAppGUI.account.getSemester();
        for (int i = 0; i < semesters.size(); i++) {
            String name = semesters.get(i).getSemesterName();
            semesterNames.add(name);
        }
        if (e.getActionCommand().equals("back")) {
            frame.setVisible(false);
        } else if (!semesterNames.contains(semesterName)) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "there isn't such a semester!");
        } else {
            Semester s = GradeManagerAppGUI.account.findSemester(semesterName);
            try {
                s.deleteCourse(courseName);
                JOptionPane.showMessageDialog(frame, "delete successful!");
            } catch (NotCourseInTheListException ex) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "there isn't such a course in the input semester!");
            }
        }




    }
}
