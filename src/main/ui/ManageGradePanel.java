package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageGradePanel extends JPanel implements ActionListener {
    protected JButton add;
    protected JButton delete;
    protected JButton change = new JButton("change a grade");
    protected JButton button = new JButton("back");
    public static final JFrame frame = new JFrame("manage my grade");


    public ManageGradePanel() {
        add = new JButton("add a course");
        add.setVerticalTextPosition(AbstractButton.CENTER);
        add.setHorizontalTextPosition(AbstractButton.CENTER);
        add.addActionListener(this);
        add(add);
        change.setVerticalTextPosition(AbstractButton.CENTER);
        change.setHorizontalTextPosition(AbstractButton.CENTER);
        change.addActionListener(this);
        add(change);
        delete = new JButton("delete a course");
        delete.setVerticalTextPosition(AbstractButton.CENTER);
        delete.setHorizontalTextPosition(AbstractButton.CENTER);
        delete.addActionListener(this);
        add(delete);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.addActionListener(this);
        add(button);
    }

    public static void createAndShowPanel() {

        //Create and set up the content pane.
        ManageGradePanel newContentPane = new ManageGradePanel();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);


        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowPanel();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("back")) {
            frame.setVisible(false);
        } else if (e.getActionCommand().equals("add a course")) {
            AddCoursePanel.createAndShowPanel();
        } else {
            DeleteCoursePanel.createAndShowPanel();
        }
    }
}
