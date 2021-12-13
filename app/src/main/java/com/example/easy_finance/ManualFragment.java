package com.example.easy_finance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManualFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ManualFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText enterAmount;
    private EditText receiptInfo;
    SharedPreferences sharedPreferences;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ManualFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManualInput.
     */
    // TODO: Rename and change types and number of parameters
    public static ManualFragment newInstance(String param1, String param2) {
        ManualFragment fragment = new ManualFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = (View)inflater.inflate(R.layout.fragment_manual, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences("com.example.easy_finance", Context.MODE_PRIVATE);
        Button button1 = (Button) view.findViewById(R.id.clickButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterAmount = view.findViewById(R.id.myTextField);
                receiptInfo = view.findViewById(R.id.myTextField2);

                String amount = enterAmount.getText().toString();
                String receipt = receiptInfo.getText().toString();
                //Toast.makeText(ManualFragment.this, enterAmount.getText().toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), amount,
                        Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), receipt,
                        Toast.LENGTH_LONG).show();

                //SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("com.example.easy_finance", Context.MODE_PRIVATE);
                String jsonText = sharedPreferences.getString("history", "Hello World");

                Gson gson = new Gson();
                String[] text = gson.fromJson(jsonText, String[].class);

                List<String> textList = new ArrayList<String>();

                if (text.length > 0) {
                    for (int i = 0; i < text.length; i++) {
                        textList.add(text[i]);
                    }
                }

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String todayDate = formatter.format(date);

                textList.add("Date: " + todayDate + " | Receipt: " + receipt + " | Amount: $" + amount);

                String jsonText2 = gson.toJson(textList);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("history", jsonText2);
                editor.commit();


            }
        });
        return view;
    }
}