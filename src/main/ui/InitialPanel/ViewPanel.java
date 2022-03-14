package ui.InitialPanel;

import model.Semester;
import ui.GradeManagerAppGUI;
import ui.ViewTable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.ArrayList;

public class ViewPanel extends JPanel implements ActionListener {
    protected JTextField textField;
    protected JLabel label;
    private String semesterName;


    private static final String newline = "\n";

    public ViewPanel() {
        super(new GridBagLayout());

        textField = new JTextField(40);
        textField.addActionListener((ActionListener) this);

        label = new JLabel("Enter the semester you want to view");

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(label, c);
        add(textField, c);

        InputMap im = textField.getInputMap();
        ActionMap am = textField.getActionMap();

    }

    public static void createAndShowPanel() {
        //Create and set up the window.
        JFrame frame = new JFrame("view my grade");


        //Add contents to the window.
        frame.add(new ViewPanel());

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


    @Override
    public void actionPerformed(ActionEvent e) {
        semesterName = textField.getText();
        ArrayList<String> semesterNames = new ArrayList<>();
        ArrayList<Semester> semesters = GradeManagerAppGUI.account.getSemester();
        for (int i = 0; i < semesters.size(); i++) {
            String name = semesters.get(i).getSemesterName();
            semesterNames.add(name);
        }
        Semester semester = GradeManagerAppGUI.account.findSemester(semesterName);
        if (!semesterNames.contains(semesterName)) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "there isn't such a semester!");
        } else if (semester.getCourse().isEmpty()) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "the semester is empty!");
        } else  {
            ViewTable.createAndShowTable(semesterName);
        }
    }
}
