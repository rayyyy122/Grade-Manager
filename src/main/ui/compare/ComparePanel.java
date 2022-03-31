package ui.compare;

import model.Semester;
import ui.GradeManagerAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ComparePanel extends JPanel implements ActionListener {
    protected JTextField textField1;
    protected JTextField textField2;
    protected JLabel label1;
    protected JLabel label2;
    private JButton button = new JButton("back");
    public static final JFrame frame = new JFrame("compare my grade");




    public ComparePanel() {
        super(new GridBagLayout());

        textField1 = new JTextField(40);
        textField1.addActionListener(this);

        textField2 = new JTextField(40);
        textField2.addActionListener(this);

        label1 = new JLabel("Enter the name of the first semester");
        label2 = new JLabel("Enter the name of the second semester");



        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(label1, c);
        add(textField1, c);
        add(label2, c);
        add(textField2, c);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.addActionListener(this);
        add(button);


    }

    public static void createAndShowPanel() {
        //Create and set up the window.



        //Add contents to the window.
        frame.add(new ComparePanel());

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
        String name1 = textField1.getText();
        String name2 = textField2.getText();
        ArrayList<String> semesterNames = new ArrayList<>();
        ArrayList<Semester> semesters = GradeManagerAppGUI.account.getSemester();
        for (int i = 0; i < semesters.size(); i++) {
            String name = semesters.get(i).getSemesterName();
            semesterNames.add(name);
        }
        if (e.getActionCommand().equals("back")) {
            frame.setVisible(false);
        } else if (!semesterNames.contains(name1) || !semesterNames.contains(name2)) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "there aren't such semesters!");
        } else {
            CompareTable.createAndShowTable(name1, name2);
        }

    }
}
