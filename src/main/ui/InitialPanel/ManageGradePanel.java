package ui.InitialPanel;

import model.Account;
import ui.AddCoursePanel;
import ui.DeleteCoursePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageGradePanel extends JPanel implements ActionListener {
    protected JButton add;
    protected JButton delete;


    public ManageGradePanel() {
        add = new JButton("add a course");
        add.setVerticalTextPosition(AbstractButton.CENTER);
        add.setHorizontalTextPosition(AbstractButton.CENTER);
        add.addActionListener(this);
        add(add);
        delete = new JButton("delete a course");
        delete.setVerticalTextPosition(AbstractButton.CENTER);
        delete.setHorizontalTextPosition(AbstractButton.CENTER);
        delete.addActionListener(this);
        add(delete);
    }

    public static void createAndShowPanel() {
        JFrame frame = new JFrame("manage my grade");


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
        if (e.getActionCommand().equals("add a course")) {
            AddCoursePanel.createAndShowPanel();
        } else {
            DeleteCoursePanel.createAndShowPanel();
        }
    }
}
