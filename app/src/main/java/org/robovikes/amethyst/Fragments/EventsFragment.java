package org.robovikes.amethyst.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.robovikes.amethyst.Adapters.EventListAdapter;
import org.robovikes.amethyst.DataManager;
import org.robovikes.amethyst.R;
import org.robovikes.amethyst.databinding.FragmentEventsBinding;

import java.util.ArrayList;

public class EventsFragment extends Fragment {

    private FragmentEventsBinding binding;
    private ArrayList<String> eventStart  = new ArrayList<>();
    private ArrayList<String> eventEnd  = new ArrayList<>();
    private ArrayList<String> events  = new ArrayList<>();
    private ArrayList<Integer> eventTeams  = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentEventsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ListView listView = root.findViewById(R.id.eventsList);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference("events");
        DataManager DataManager = new DataManager();

        DataManager.setCurrentEvent("gjykyjkyky");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                EventListAdapter eventListAdapter = new EventListAdapter(getActivity(), events, eventStart, eventEnd);

                events.clear();
                eventStart.clear();
                eventEnd.clear();
                eventTeams.clear();

                final Handler handler = new Handler(Looper.getMainLooper());
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String eventName = snapshot.getKey();
                    String start = String.valueOf(snapshot.child("start").getValue());
                    String end = String.valueOf(snapshot.child("end").getValue());
                    events.add(eventName);
                    eventStart.add(start);
                    eventEnd.add(end);
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (DataManager.getCurrentEvent() != null) {
                            String currentEvent = DataManager.getCurrentEvent();
                            DataSnapshot Teams = dataSnapshot.child(currentEvent).child("Teams");
                            for (DataSnapshot team : Teams.getChildren()) {
                                int totalPoints = 0;
                                int playedMatches = 0;
                                DataSnapshot points = team.child("points");
                                DataSnapshot matches = team.child("matches");
                                for (DataSnapshot Matches : matches.getChildren()) {
                                    String match = Matches.getKey();
                                    if (match != null) {
                                        int matchPoints = Integer.parseInt(String.valueOf(Matches.child("points").getValue()));
                                        totalPoints = totalPoints + matchPoints;
                                        playedMatches++;
                                    }
                                }
                                double pointAverage = (totalPoints + 0.0) / playedMatches;
                                points.getRef().setValue(totalPoints);
                                team.getRef().child("pointAverage").setValue(pointAverage);
                            }
                        }
                        listView.setAdapter(eventListAdapter);
                    }
                }, 10);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DataManager.setCurrentEvent(events.get(position));
                System.out.println(DataManager.getCurrentEvent());

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}