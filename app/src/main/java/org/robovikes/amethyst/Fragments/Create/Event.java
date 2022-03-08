package org.robovikes.amethyst.Fragments.Create;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.robovikes.amethyst.DataManager;
import org.robovikes.amethyst.R;
import org.robovikes.amethyst.databinding.FragmentCreateEventBinding;

public class Event extends Fragment {

    private FragmentCreateEventBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCreateEventBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NavController navController = Navigation.findNavController(getActivity(), R.id.create_bar);
                BottomNavigationView scoutingBar = root.findViewById(R.id.create_bar);
                NavigationUI.setupWithNavController(scoutingBar, navController);
            }
        }, 10);

        Button createEvent = root.findViewById(R.id.createEvent);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView eventName = root.findViewById(R.id.eventName);
                TextView startDate = root.findViewById(R.id.eventStart);
                TextView endDate = root.findViewById(R.id.eventEnd);
                String name = eventName.getText().toString().trim();
                String start = startDate.getText().toString().trim();
                String end = endDate.getText().toString().trim();
                DataManager DataManager = new DataManager();
                if (!name.equals("") && !start.equals("") && !end.equals("")) {
                    if (!name.contains(".") && !name.contains("#") && !name.contains("$") && !name.contains("[") && !name.contains("]") && !start.contains(".") && !start.contains("#") && !start.contains("$") && !start.contains("[") && !start.contains("]") && !end.contains(".") && !end.contains("#") && !end.contains("$") && !end.contains("[") && !end.contains("]")) {
                        eventName.setError("This field is required");
                        System.out.println("NON");
                        DataManager.createEvent(name, start, end);
                        eventName.setText(null);
                        startDate.setText(null);
                        endDate.setText(null);
                        System.out.println(eventName.getText() + ":" + startDate.getText() + ":" + endDate.getText());
                    } else {
                        if (name.contains(".") || name.contains("#") || name.contains("$") || name.contains("[") || name.contains("]")) {
                            eventName.setError("May not contain " + ". # $ [ ]");
                        }
                        if (start.contains(".") || start.contains("#") || start.contains("$") || start.contains("[") || start.contains("]")) {
                            startDate.setError("May not contain " + ". # $ [ ]");
                        }
                        if (end.contains(".") || end.contains("#") || end.contains("$") || end.contains("[") || end.contains("]")) {
                            endDate.setError("May not contain " + ". # $ [ ]");
                        }
                    }
                } else {
                    if (name.equals("")) {
                        eventName.setError("This field is required");
                    }
                    if (start.equals("")) {
                        startDate.setError("This field is required");
                    }
                    if (end.equals("")) {
                        endDate.setError("This field is required");
                    }
                }
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