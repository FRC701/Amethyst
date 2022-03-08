package org.robovikes.amethyst.Fragments.Create;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.robovikes.amethyst.R;
import org.robovikes.amethyst.databinding.FragmentCreateTeamBinding;

public class Team extends Fragment {

    private FragmentCreateTeamBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCreateTeamBinding.inflate(inflater, container, false);
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
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}