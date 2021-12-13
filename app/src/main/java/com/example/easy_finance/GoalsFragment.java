package com.example.easy_finance;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class GoalsFragment extends Fragment {


    public GoalsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.fragment_goals, container, false);

        Button button = (Button) view.findViewById(R.id.enter);
        EditText myTextField = (EditText) view.findViewById(R.id.userInput);
        String str = myTextField.getText().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment listGoals = new ListGoalsFragment();
                FragmentManager manager = getParentFragmentManager();
                manager.beginTransaction().replace(R.id.container, listGoals).commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}