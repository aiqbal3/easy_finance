package com.example.easy_finance;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GoalsFragment extends Fragment {


    public GoalsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.fragment_goals, container, false);

        Button button = (Button) view.findViewById(R.id.enter);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView userGoal = (TextView) view.findViewById(R.id.userGoal);
                EditText myTextField = (EditText) view.findViewById(R.id.userInput);
                String str = myTextField.getText().toString();
                userGoal.setText("Goal: " + str);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}