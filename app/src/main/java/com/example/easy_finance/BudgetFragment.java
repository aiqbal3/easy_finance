package com.example.easy_finance;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.w3c.dom.Text;

import android.widget.LinearLayout.LayoutParams;

public class BudgetFragment extends Fragment {

    View view;
    TextView tvHousing, tvFood, tvInsurance, tvPersonal, tvInvestments, tvMisc;
    PieChart pieChart;

    //String housing, food, insurance, personal, investments, misc = "100";

    double housing = 0;
    double food = 0;
    double insurance = 0;
    double personal = 0;
    double investments = 0;
    double misc = 0;

    String category;

    public BudgetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_budget, container, false);

        BottomNavigationView navBar = getActivity().findViewById(R.id.bottomNav);

        navBar.setVisibility(View.GONE);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Tap on Spending Percentages to add a new purchase to your budget\n\n" +
                        "To bring up navigation bar click menu");
        builder.setTitle("Info");

        //This will not allow to close dialogbox until user selects an option
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });


        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        //alert.setTitle("AlertDialogExample");
        alert.show();

        TextView tv = (TextView) view.findViewById(R.id.spending);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder categoryPicker = new AlertDialog.Builder(getActivity());

                categoryPicker.setTitle("Select Category for Purchase");
                // category.setView(edittext);

                final int[] checkedItem = {-1};
                final String[] listItems = new String[]{"Housing", "Food", "Insurance", "Personal", "Investments", "Misc"};

                categoryPicker.setSingleChoiceItems(listItems, checkedItem[0], new DialogInterface.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // update the selected item which is selected by the user
                        // so that it should be selected when user opens the dialog next time
                        // and pass the instance to setSingleChoiceItems method
                        checkedItem[0] = which;

                        // now also update the TextView which previews the selected item
                        category = listItems[which];

                        // when selected an item the dialog should be closed with the dismiss method
                        dialog.dismiss();

                        if (category != null) {

                            final EditText edittext = new EditText(getContext());

                            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                            builder.setTitle("Enter Amount of Last Purchase");
                            builder.setView(edittext);

                            //This will not allow to close dialogbox until user selects an option
                            builder.setCancelable(false);
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    String amount = edittext.getText().toString();
                                    switch (category) {
                                        case "Housing":
                                            housing = housing + Double.parseDouble(amount);
                                            break;
                                        case "Food":
                                            food = food + Double.parseDouble(amount);
                                            break;
                                        case "Insurance":
                                            insurance = insurance + Double.parseDouble(amount);
                                            break;
                                        case "Personal":
                                            personal = personal + Double.parseDouble(amount);
                                            break;
                                        case "Investments":
                                            investments = investments + Double.parseDouble(amount);
                                            break;
                                        case "Misc":
                                            misc = misc + Double.parseDouble(amount);
                                            break;
                                    }
                                    // housing = housing + Double.parseDouble(amount);
                                    setData();
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                }
                            });

                            //Creating dialog box
                            AlertDialog alert = builder.create();
                            //Setting the title manually
                            //alert.setTitle("AlertDialogExample");
                            alert.show();
                        }
                    }
                });

                //This will not allow to close dialogbox until user selects an option
                categoryPicker.setCancelable(false);

                categoryPicker.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                });

                AlertDialog alert1 = categoryPicker.create();
                //Setting the title manually
                //alert.setTitle("AlertDialogExample");
                alert1.show();


            }

        });

        TextView tv2 = (TextView) view.findViewById(R.id.menuAccess);

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navBar.setVisibility(View.VISIBLE);
            }

        });

        tvHousing = view.findViewById(R.id.tvHousing);
        tvFood = view.findViewById(R.id.tvFood);
        tvInsurance = view.findViewById(R.id.tvInsurance);
        tvPersonal = view.findViewById(R.id.tvPersonal);
        tvInvestments = view.findViewById(R.id.tvInvestments);
        tvMisc = view.findViewById(R.id.tvMisc);
        pieChart = view.findViewById(R.id.piechart);

        // Creating a method setData()
        // to set the text in text view and pie chart
        setData();

        return view;
    }


    private void setData()
    {
        pieChart.clearChart();

        // String housingText = housing;
        // Set the percentage of language used
        tvHousing.setText(String.valueOf(housing));
        tvFood.setText(String.valueOf(food));
        tvInsurance.setText(String.valueOf(insurance));
        tvPersonal.setText(String.valueOf(personal));
        tvInvestments.setText(String.valueOf(investments));
        tvMisc.setText(String.valueOf(misc));


        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Housing",
                        (float) Double.parseDouble(tvHousing.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Food",
                        (float) Double.parseDouble(tvFood.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Insurance",
                        (float) Double.parseDouble(tvInsurance.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Personal",
                        (float) Double.parseDouble(tvPersonal.getText().toString()),
                        Color.parseColor("#29B6F6")));
        pieChart.addPieSlice(
                new PieModel(
                        "Personal",
                        (float) Double.parseDouble(tvInvestments.getText().toString()),
                        Color.parseColor("#f542d4")));
        pieChart.addPieSlice(
                new PieModel(
                        "Personal",
                        (float) Double.parseDouble(tvMisc.getText().toString()),
                        Color.parseColor("#a503fc")));


        tvHousing.setText("$" + String.valueOf(housing));
        tvFood.setText("$" + String.valueOf(food));
        tvInsurance.setText("$" + String.valueOf(insurance));
        tvPersonal.setText("$" + String.valueOf(personal));
        tvInvestments.setText("$" + String.valueOf(investments));
        tvMisc.setText("$" + String.valueOf(misc));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}
