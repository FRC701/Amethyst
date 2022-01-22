package org.robovikes.amethyst;

import java.util.ArrayList;

public class EventManager {

    private String currentEvent;
    private ArrayList<Integer> Teams;

    public EventManager() {

    }
    public String getCurrentEvent() {
        return currentEvent;
    }
    public void setCurrentEvent(String currentEvent) {
        this.currentEvent = currentEvent;
    }

    public void setTeams(ArrayList<Integer> teams) {
        Teams = teams;
    }
    public ArrayList<Integer> getTeams() {
        return Teams;
    }
}
