package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//list of semesters
public class Account implements Writable {
    private ArrayList<Semester> account;
    private String name;
    private Semester semester;

    //MODIFIES: this
    //construct a account contains a list of semesters
    public Account() {
        this.account = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add a semester with its name to the account
    public boolean addSemester(String name) {
        this.name = name;
        semester = new Semester(name);
        return this.account.add(semester);
    }

//    public boolean addSemester(String name) {
//        this.name = name;
//        semester = new Semester(name);
//        if (this.account.add(semester)) {
//            return true;
//        }
//        return false;
//    }

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

    public int length() {
        return account.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("semesters", semestersToJson());
        return json;
    }

    private JSONArray semestersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Semester s : account) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}
