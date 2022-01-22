package org.robovikes.amethyst.Fragments;

import android.os.Bundle;
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
import org.robovikes.amethyst.EventManager;
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

        EventManager EventManager = new EventManager();
        EventListAdapter eventListAdapter = new EventListAdapter(getActivity(), events, eventStart, eventEnd);
        ListView listView = root.findViewById(R.id.eventsList);
        listView.setAdapter(eventListAdapter);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference("events");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                events.clear();
                eventStart.clear();
                eventEnd.clear();
                eventTeams.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String eventName = snapshot.getKey();
                    String start = String.valueOf(snapshot.child("start").getValue());
                    String end = String.valueOf(snapshot.child("end").getValue());
                    events.add(eventName);
                    eventStart.add(start);
                    eventEnd.add(end);
                }
                EventManager.setCurrentEvent("gjykyjkyky");
                if (EventManager.getCurrentEvent() != null) {
                    String currentEvent = EventManager.getCurrentEvent();
                    DataSnapshot Teams = dataSnapshot.child(currentEvent).child("Teams");
                    for(DataSnapshot team : Teams.getChildren()) {
                        int totalPoints = 0;
                        int playedMatches = 0;
                        DataSnapshot points = team.child("points");
                        DataSnapshot matches = team.child("matches");
                        for(DataSnapshot Matches : matches.getChildren()) {
                            String match = Matches.getKey();
                            if (match != null) {
                                int matchPoints = Integer.parseInt(String.valueOf(Matches.child("points").getValue()));
                                totalPoints = totalPoints + matchPoints;
                                playedMatches++;
                            }
                        }
                        System.out.println(totalPoints);
                        double pointAverage = (totalPoints + 0.0) / playedMatches;
                        points.getRef().setValue(totalPoints);
                        team.getRef().child("pointAverage").setValue(pointAverage);
                    }
                }
                System.out.println(events);
                eventListAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            EventManager.setCurrentEvent(events.get(position));
            System.out.println(EventManager.getCurrentEvent());

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