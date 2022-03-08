package org.robovikes.amethyst.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.robovikes.amethyst.R;

import java.util.ArrayList;

public class EventListAdapter extends ArrayAdapter {

    public Activity context;

    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> start = new ArrayList<>();;
    private ArrayList<String> end = new ArrayList<>();;
    public EventListAdapter(Activity context, ArrayList<String> name, ArrayList<String> start, ArrayList<String> end) {
        super(context, R.layout.events_layout, name);
        this.context = context;
        this.name = name;
        this.start = start;
        this.end = end;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null) {
            row = inflater.inflate(R.layout.events_layout, null, true);
            TextView startText =  row.findViewById(R.id.eventStart);
            TextView teamsText =  row.findViewById(R.id.eventTeams);
            TextView endText =  row.findViewById(R.id.eventEnd);
            TextView nameText =  row.findViewById(R.id.eventName);

            startText.setText(start.get(position));
            endText.setText(end.get(position));
            nameText.setText(name.get(position));
            teamsText.setText(String.valueOf(15));
        }
        return  row;
    }
}
