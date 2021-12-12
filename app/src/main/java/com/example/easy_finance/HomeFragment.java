package com.example.easy_finance;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
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
        int creditCards = sharedPreferences.getInt("creditCards", 0);
        int cash = sharedPreferences.getInt("cash", 0);
        int net_worth = investments + cash - creditCards;

        // Investment balance
        TextView investment_home = (TextView) view.findViewById(R.id.invest_num);
        String investText = "$" + String.valueOf(investments);
        investment_home.setText(investText);

        // Credit card balance
        TextView credit_cards = (TextView) view.findViewById(R.id.credit_card_bal);
        String creditText = "$" + String.valueOf(creditCards);
        credit_cards.setText(creditText);

        // Cash Balance
        TextView cash_home = (TextView) view.findViewById(R.id.cash_balance);
        String cashText = "$" + String.valueOf(cash);
        cash_home.setText(cashText);

        // Net worth balance
        TextView net_worth_home = (TextView) view.findViewById(R.id.net_worth_bal);
        String netWorthText = "$" + String.valueOf(net_worth);
        net_worth_home.setText(netWorthText);

        if (investments < 0) {
            investment_home.setTextColor(Color.RED);
        } else {
            investment_home.setTextColor(Color.GREEN);
        }
        if (creditCards < 0) {
            credit_cards.setTextColor(Color.RED);
        } else {
            credit_cards.setTextColor(Color.GREEN);
        }
        if (cash < 0) {
            cash_home.setTextColor(Color.RED);
        } else {
            cash_home.setTextColor(Color.GREEN);
        }
        if (net_worth < 0) {
            net_worth_home.setTextColor(Color.RED);
        } else {
            net_worth_home.setTextColor(Color.GREEN);
        }

        // Inflate the layout for this fragment
        return view;
    }
}