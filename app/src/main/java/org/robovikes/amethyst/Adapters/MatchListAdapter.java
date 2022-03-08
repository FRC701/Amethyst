package org.robovikes.amethyst.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.robovikes.amethyst.R;

import java.util.ArrayList;
import java.util.Random;

public class MatchListAdapter extends ArrayAdapter {

    public Activity context;
    private ArrayList<String> countryNames;
    private ArrayList<String> capitalNames;

    public MatchListAdapter(Activity context, ArrayList countryNames, ArrayList capitalNames) {
        super(context, R.layout.match_layout, countryNames);
        this.context = context;
        this.countryNames = countryNames;
        this.capitalNames = capitalNames;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            row = inflater.inflate(R.layout.match_layout, null, true);
            TextView blue = row.findViewById(R.id.blue1);
            TextView red = row.findViewById(R.id.red1);
            TextView score = row.findViewById(R.id.score);
            TextView match = row.findViewById(R.id.match);
            ProgressBar imageFlag = row.findViewById(R.id.progressBar);
            int blueScore;
            int redScore;
            double totalScore;
            double bluePercent;
            int finalScore;
            blueScore = new Random().nextInt(100) + 1;
            redScore = new Random().nextInt(100) + 1;
            totalScore = redScore + blueScore + 0.0;
            bluePercent = blueScore / totalScore;
            finalScore = (int) (bluePercent * 1000);
            score.setText(blueScore + " - " + redScore);
            imageFlag.setProgress(finalScore);
            blue.setText(countryNames.get(position));
            red.setText(capitalNames.get(position));
            match.setText("Match " + (position + 1));
        }
        return row;
    }
}
