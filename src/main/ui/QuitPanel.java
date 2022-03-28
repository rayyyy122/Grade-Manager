package ui;

import javax.swing.*;
import java.awt.*;

public class QuitPanel extends JPanel {


    public QuitPanel() {
        super(new BorderLayout());
    }

    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Quit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "quit successful!");


        //Create and set up the content pane.
        QuitPanel newContentPane = new QuitPanel();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(false);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


}

