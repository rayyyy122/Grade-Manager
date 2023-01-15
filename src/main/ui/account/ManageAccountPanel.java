package ui.account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageAccountPanel extends JPanel implements ActionListener {
    JButton add = new JButton("add a semester");
    JButton delete = new JButton("delete a semester");
    private JButton button = new JButton("back");
    public static final JFrame frame = new JFrame("manage my account");

    public ManageAccountPanel() {
        add.setVerticalTextPosition(AbstractButton.CENTER);
        add.setHorizontalTextPosition(AbstractButton.CENTER);
        add.addActionListener(this);
        add(add);
        delete.setVerticalTextPosition(AbstractButton.CENTER);
        delete.setHorizontalTextPosition(AbstractButton.CENTER);
        delete.addActionListener(this);
        add(delete);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.addActionListener(this);
        add(button);
    }

    // EFFECTS: create and show the graphical user interface
    public static void createAndShowPanel() {
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

    // EFFECTS: calls each sub-panel when click corresponding button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("back")) {
            frame.setVisible(false);
        } else if (e.getActionCommand().equals("add a semester")) {
            AddSemesterPanel.createAndShowPanel();
        } else {
            DeleteSemesterPanel.createAndShowPanel();
        }
    }
}
