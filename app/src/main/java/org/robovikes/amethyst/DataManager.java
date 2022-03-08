package org.robovikes.amethyst;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DataManager {

    public DataManager() {

    }

    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String currentEvent;
    private ArrayList<Integer> Teams;

    public void createEvent(String name, String startDate, String endDate) {
        DatabaseReference events = database.getReference("events");
        events.child(name).child("start").setValue(startDate);
        events.child(name).child("end").setValue(endDate);
    }
    public void setTeams(ArrayList<Integer> teams) {
        Teams = teams;
    }
    public ArrayList<Integer> getTeams() {
        return Teams;
    }
    public String getCurrentEvent() {
        return currentEvent;
    }
    public void setCurrentEvent(String currentEvent) {
        this.currentEvent = currentEvent;
    }

}
