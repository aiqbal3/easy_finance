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

        Button button1 = (Button) view.findViewById(R.id.scanReceipt);
        Button button2 = (Button) view.findViewById(R.id.importFromFile);
        Button button3 = (Button) view.findViewById(R.id.manualInput);
        Button button4 = (Button) view.findViewById(R.id.history);

        //Function to send user to the ScanFragment
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment scanFragment = new ScanFragment();
                FragmentManager manager = getParentFragmentManager();
                manager.beginTransaction().replace(R.id.container, scanFragment).commit();
            }
        });
        //Function to send user to the ImportFromFileFragment
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment importFromFileFragment = new ImportFromFileFragment();
                FragmentManager manager = getParentFragmentManager();
                manager.beginTransaction().replace(R.id.container, importFromFileFragment).commit();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment manualFragment = new ManualFragment();
                FragmentManager manager = getParentFragmentManager();
                manager.beginTransaction().replace(R.id.container, manualFragment).commit();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment historyFragment = new HistoryFragment();
                FragmentManager manager = getParentFragmentManager();
                manager.beginTransaction().replace(R.id.container, historyFragment).commit();
            }
        });



        // Inflate the layout for this fragment
        return view;
    }



}