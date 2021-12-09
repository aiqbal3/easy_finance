package com.example.easy_finance;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ImporterFragment extends Fragment {

    public ImporterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.fragment_importer, container, false);

        Button button = (Button) view.findViewById(R.id.manualInput);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment manualFragment = new ManualFragment();
                FragmentManager manager = getParentFragmentManager();
                manager.beginTransaction().replace(R.id.container, manualFragment).commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    //@Override
    public void onClick() {

    }


}