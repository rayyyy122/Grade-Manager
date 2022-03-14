package ui;

import model.Account;
import model.Semester;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class SaveOptionsPanel extends JPanel implements ActionListener {
    protected JButton save;
    protected JButton load;
    private static final String JSON_SOURCE = "./data/account.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_SOURCE);
    private JsonReader jsonReader = new JsonReader(JSON_SOURCE);
    private Account account;

    public SaveOptionsPanel() {
        save = new JButton("save to file");
        save.setVerticalTextPosition(AbstractButton.CENTER);
        save.setHorizontalTextPosition(AbstractButton.CENTER);
        save.addActionListener(this);
        add(save);
        load = new JButton("load from file");
        load.setVerticalTextPosition(AbstractButton.CENTER);
        load.setHorizontalTextPosition(AbstractButton.CENTER);
        load.addActionListener(this);
        add(load);
    }

    public static void createAndShowPanel() {
        JFrame frame = new JFrame("save options");


        //Create and set up the content pane.
        SaveOptionsPanel newContentPane = new SaveOptionsPanel();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);


        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: saves the workroom to file
    private void saveAccount() {
        try {
            jsonWriter.open();
            jsonWriter.write(GradeManagerAppGUI.account);
            jsonWriter.close();
            System.out.println("Saved Success");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_SOURCE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadAccount() {
        try {
            account = jsonReader.read();
            System.out.println("Loaded Success");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_SOURCE);
        }
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
        if (e.getActionCommand().equals("save to file")) {
            saveAccount();
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "save successful!");
        } else {
            loadAccount();
            ArrayList<Semester> semesters = account.getSemester();
            for (int i = 0; i < semesters.size(); i++) {
                GradeManagerAppGUI.account.addSemester(semesters.get(i));
            }
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "load successful!");


        }

    }
}
