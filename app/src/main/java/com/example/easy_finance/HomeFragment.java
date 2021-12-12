package com.example.easy_finance;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = (View)inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("com.example.easy_finance", Context.MODE_PRIVATE);
        int investments = sharedPreferences.getInt("investments", 0);

        TextView investment_home = (TextView) view.findViewById(R.id.invest_num);
        // String investText = "$" + String.valueOf(investments);
        investment_home.setText("Hello World");


        // Inflate the layout for this fragment
        return view;
    }
}