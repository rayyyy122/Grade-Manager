package model;

import java.util.ArrayList;
import java.util.List;

//list of semesters
public class Account {
    private ArrayList<Semester> account;
    private String name;
    private Semester semester;

    public Account() {
        this.account = new ArrayList<>();
    }

    public boolean addSemester(String name) {
        this.name = name;
        semester = new Semester(name);
        if (this.account.add(semester)) {
            return true;
        }
        return false;
    }

    //REQUIRES: semester in the list
    //EFFECTS: delete the entered semester from the list
    public ArrayList<Semester> deleteSemester(String semesterName) {
        for (int position = 0; position < account.size(); position++) {
            if (account.get(position).getSemesterName().equals(semesterName)) {
                account.remove(position);
            }
        }
        return account;
    }

    public ArrayList<Semester> getSemester() {
        return account;
    }

    //REQUIRES: input semester in the Account
    //EFFECTS: compare two semester
    public ArrayList<Semester> compare(String semesterName1, String semesterName2) {
        ArrayList<Semester> compareList = new ArrayList<Semester>();
        for (int position = 0; position < account.size(); position++) {
            if (account.get(position).getSemesterName().equals(semesterName1)) {
                compareList.add(account.get(position));
            } else if (account.get(position).getSemesterName().equals(semesterName2)) {
                compareList.add(account.get(position));
            }
        }
        return compareList;
    }

    //REQUIRES:entered name lie in the account
    //EFFECTS:find a semester in an account by name
    public Semester findSemester(String semesterName) {
        for (int position = 0; position < account.size(); position++) {
            if (account.get(position).getSemesterName().equals(semesterName)) {
                Semester semester = account.get(position);
                return semester;
            }
        }
        return null;
    }
}
