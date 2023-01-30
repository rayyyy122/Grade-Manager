package ui;

import model.Account;
import model.Event;
import model.EventLog;
import ui.account.ManageAccountPanel;
import ui.compare.ComparePanel;
import ui.grade.ManageGradePanel;
import ui.info.ViewSemesterNamePanel;
import ui.view.ViewPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeManagerAppGUI extends JPanel implements ActionListener {
    JButton view = new JButton("view my grade");
    JButton viewInfo = new JButton("view information");
    JButton compare = new JButton("compare my grade");
    JButton manageAccount = new JButton("manage my account");
    JButton manageGrade = new JButton("manage my grade");
    JButton saveOptions = new JButton("save options");
    JButton quit = new JButton("quit");
    JDesktopPane desktop = new JDesktopPane();
    private static final String JSON_SOURCE = "./data/account.json";
    public static final Account account = new Account();


    public GradeManagerAppGUI() {
        view.setHorizontalTextPosition(AbstractButton.CENTER);
        view.addActionListener(this);
        viewInfo.setHorizontalTextPosition(AbstractButton.CENTER);
        viewInfo.addActionListener(this);
        compare.setHorizontalTextPosition(AbstractButton.CENTER);
        compare.addActionListener(this);
        manageAccount.setHorizontalTextPosition(AbstractButton.CENTER);
        manageAccount.addActionListener(this);
        manageGrade.setHorizontalTextPosition(AbstractButton.CENTER);
        manageGrade.addActionListener(this);
        saveOptions.setHorizontalTextPosition(AbstractButton.CENTER);
        saveOptions.addActionListener(this);
        quit.setHorizontalTextPosition(AbstractButton.CENTER);
        quit.addActionListener(this);
        add(view);
        add(viewInfo);
        add(compare);
        add(manageAccount);
        add(manageGrade);
        add(saveOptions);
        add(quit);

    }

    // EFFECTS: create and show the graphical user interface
    public static void createAndShowGui() {
        JFrame frame = new JFrame("Grade Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        GradeManagerAppGUI newContentPane = new GradeManagerAppGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }



    // EFFECTS: calls each sub-panel when click corresponding button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("view my grade")) {
            ViewPanel.createAndShowPanel();
        } else if (e.getActionCommand().equals("view information")) {
            if (account.getSemester().isEmpty()) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "your account is empty!");
            } else {
                ViewSemesterNamePanel.createAndShowTable();
            }
        } else if (e.getActionCommand().equals("compare my grade")) {
            ComparePanel.createAndShowPanel();
        } else if (e.getActionCommand().equals("manage my account")) {
            ManageAccountPanel.createAndShowPanel();
        } else if (e.getActionCommand().equals("manage my grade")) {
            ManageGradePanel.createAndShowPanel();
        } else if (e.getActionCommand().equals("save options")) {
            SaveOptionsPanel.createAndShowPanel();
        } else if (e.getActionCommand().equals("quit")) {
            LogScreen.createAndShowGUI();
        }
    }
}
