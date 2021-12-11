package com.example.easy_finance;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class PredictorFragment extends Fragment {

    int radioPicker = 1;
    int fundNumber = 1;
    double customReturn = 0.0;
    int year = 2021;

    public PredictorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.fragment_predictor, container, false);

        //Get Current Investment Balance
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("com.example.easy_finance", Context.MODE_PRIVATE);
        int investments = sharedPreferences.getInt("investments", 0);

        TextView investmentBal = (TextView) view.findViewById(R.id.investmentBal);
        String investText = "$" + String.valueOf(investments);
        investmentBal.setText(investText);

        //Finding out what radio button user selected
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup2);

        RadioButton radioButton1 = (RadioButton) view.findViewById(R.id.index_fund);
        RadioButton radioButton2 = (RadioButton) view.findViewById(R.id.custom_return);

        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                if (radioId == 2131231209) {
                    radioPicker = 1;
                }
                //Toast.makeText(getActivity(), "hi: " + radioId, Toast.LENGTH_SHORT).show();
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                if (radioId == 2131231208) {
                    radioPicker = 2;
                }
                //Toast.makeText(getActivity(), "hi: " + radioId, Toast.LENGTH_SHORT).show();
            }
        });

        //Drop down menu for selecting Index Fund
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.funds, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int fund = position;
                fundNumber  = fund;
                //Toast.makeText(getActivity(), "hi: " + fund, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Get Custom Return Rate
        EditText returnRate = (EditText) view.findViewById(R.id.returnRate);
        returnRate.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (returnRate.getText().toString().isEmpty() == false) {
                    customReturn = Double.parseDouble(returnRate.getText().toString());
                }
                //Toast.makeText(getActivity(), "hi: " + customReturn, Toast.LENGTH_SHORT).show();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        //Get year user wants to predict value at
        NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.numberPicker);
        numberPicker.setMinValue(2022);
        numberPicker.setMaxValue(2099);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                year = i1;
                //Toast.makeText(getActivity(), "hi: " + year, Toast.LENGTH_SHORT).show();
            }
        });

        //Calculate future predicted balance on button click
        Button predictButton = (Button) view.findViewById(R.id.predictButton);
        TextView predictValue = (TextView) view.findViewById(R.id.predictValue);
        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double value = 0.0;
                double returnIndexPer = 0.0;
                //See if index fund or custom return rate was selected
                if (radioPicker == 1) {
                    //see which fund was selected
                    if (fundNumber == 0) {
                        returnIndexPer = 15.7;
                    }
                    else if (fundNumber == 1) {
                        returnIndexPer = 15.8;
                    }
                    else if (fundNumber == 2) {
                        returnIndexPer = 17.14;
                    }
                    else if (fundNumber == 3) {
                        returnIndexPer = 15.7;
                    }
                    else if (fundNumber == 4) {
                        returnIndexPer = 15.7;
                    }

                    //value has current year set as 2021
                    value = investments * Math.pow(1 + ((returnIndexPer/100) / 1), 1 * (year-2021));
                    //Toast.makeText(getActivity(), "hi: " + value, Toast.LENGTH_SHORT).show();
                }
                else {
                    value = investments * Math.pow(1 + ((customReturn/100) / 1), 1 * (year-2021));
                }
                final DecimalFormat df = new DecimalFormat("0.00");
                predictValue.setText("$" + df.format(value));
            }
        });

        return view;
    }
}