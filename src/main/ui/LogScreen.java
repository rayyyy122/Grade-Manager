package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogScreen extends JPanel implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JTextArea textArea;
    private JButton button;


    public LogScreen() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        button = new JButton("close");
        button.addActionListener(this);
        setLayout(new BorderLayout());
        add(button, BorderLayout.SOUTH);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        setSize(WIDTH, HEIGHT);
        printLog(EventLog.getInstance());
    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            textArea.setText(textArea.getText() + next.toString() + "\n\n");
        }

        repaint();
    }

    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Event Log");


        //Create and set up the content pane.
        LogScreen newContentPane = new LogScreen();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("close")) {
            QuitPanel.createAndShowGUI();
            for (Event event: EventLog.getInstance()) {
                System.out.println(event.toString());
            }
            EventLog.getInstance().clear();
            System.exit(0);

        }



    }
}
