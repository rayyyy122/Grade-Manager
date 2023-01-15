package ui.account;

import ui.GradeManagerAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSemesterPanel extends JPanel implements ActionListener {
    protected JTextField textField;
    protected JLabel label;
    private String semesterName;
    private JButton button = new JButton("back");
    public static final JFrame frame = new JFrame("add a semester");


    private static final String newline = "\n";

    public AddSemesterPanel() {
        super(new GridBagLayout());

        textField = new JTextField(40);
        textField.addActionListener(this);

        label = new JLabel("enter the name of the semester you want to add");

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

    // EFFECTS: create and show the graphical user interface
    public static void createAndShowPanel() {
        //Create and set up the window.



        //Add contents to the window.
        frame.add(new AddSemesterPanel());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: calls each sub-panel when click corresponding button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("back")) {
            frame.setVisible(false);
        } else {
            semesterName = textField.getText();
            GradeManagerAppGUI.account.addSemester(semesterName);
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "add successful!");
        }

    }
}
