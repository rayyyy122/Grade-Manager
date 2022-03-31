package ui.account;

import model.Semester;
import ui.GradeManagerAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteSemesterPanel extends JPanel implements ActionListener {
    protected JTextField textField;
    protected JLabel label;
    private JButton button = new JButton("back");
    public static final JFrame frame = new JFrame("delete a semester");


    public DeleteSemesterPanel() {
        super(new GridBagLayout());

        textField = new JTextField(40);
        textField.addActionListener(this);

        label = new JLabel("enter the name of the semester you want to delete");

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(label, c);
        add(textField, c);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.addActionListener(this);
        add(button);
    }

    public static void createAndShowPanel() {
        //Create and set up the window.


        //Add contents to the window.
        frame.add(new DeleteSemesterPanel());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String semesterName = textField.getText();
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
            GradeManagerAppGUI.account.deleteSemester(semesterName);
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "delete successful!");
        }


    }
}
