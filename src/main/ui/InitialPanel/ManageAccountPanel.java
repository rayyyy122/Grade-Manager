package ui.InitialPanel;

import ui.AddSemesterPanel;
import ui.DeleteSemesterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageAccountPanel extends JPanel implements ActionListener {
    JButton add = new JButton("add a semester");
    JButton delete = new JButton("delete a semester");

    public ManageAccountPanel() {
        add.setVerticalTextPosition(AbstractButton.CENTER);
        add.setHorizontalTextPosition(AbstractButton.CENTER);
        add.addActionListener(this);
        add(add);
        delete.setVerticalTextPosition(AbstractButton.CENTER);
        delete.setHorizontalTextPosition(AbstractButton.CENTER);
        delete.addActionListener(this);
        add(delete);
    }

    public static void createAndShowPanel() {
        JFrame frame = new JFrame("manage my account");


        //Create and set up the content pane.
        ManageAccountPanel newContentPane = new ManageAccountPanel();
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
        if (e.getActionCommand().equals("add a semester")) {
            AddSemesterPanel.createAndShowPanel();
        } else {
            DeleteSemesterPanel.createAndShowPanel();
        }
    }
}
