package org.robovikes.amethyst.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.robovikes.amethyst.R;
import org.robovikes.amethyst.databinding.FragmentMatchActiveBinding;

public class MatchActiveFragment extends Fragment {

    private int upper_score = 0;
    private int lower_score = 0;
    private FragmentMatchActiveBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMatchActiveBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button plus_button = root.findViewById(R.id.plusButton);
        Button minus_button = root.findViewById(R.id.minusButton);
        Button button_plus = root.findViewById(R.id.buttonPlus);
        Button button_minus = root.findViewById(R.id.buttonMinus);
        TextView upperScore = root.findViewById(R.id.upperScore);
        TextView lowerScore = root.findViewById(R.id.lowerScore);
        upperScore.setText(String.valueOf(upper_score));
        lowerScore.setText(String.valueOf(lower_score));

        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upper_score++;
                upperScore.setText(String.valueOf(upper_score));
            }
        });

        minus_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(upper_score > 0){
                    upper_score--;
                    upperScore.setText(String.valueOf(upper_score));
                }
            }
        });

        button_plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                lower_score++;
                lowerScore.setText((String.valueOf(lower_score)));
            }
        });

        button_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lower_score > 0){
                    lower_score--;
                    lowerScore.setText(String.valueOf(lower_score));
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
