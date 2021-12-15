package com.example.easy_finance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NavigationBarView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SharedPreferences for storing Cash, Investments, and Credit Cards values
        //Net worth can be calculated as: cash + investments - credit cards
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.easy_finance", Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("cash", 6000).apply();
        sharedPreferences.edit().putInt("investments", 10235).apply();
        sharedPreferences.edit().putInt("creditCards", 1485).apply();

        List<String> textList = new ArrayList<String>();

        Gson gson = new Gson();
        String jsonText = gson.toJson(textList);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("history", jsonText);
        editor.commit();

        Button button1 = (Button) findViewById(R.id.login);

        // Function to send user to the Internet
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                EditText usernameEntry = (EditText) findViewById(R.id.username);
                String username = usernameEntry.getText().toString();

                EditText myTextField = (EditText)findViewById(R.id.password);
                String password = myTextField.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", username);
                editor.putString("password", password);

                editor.commit();

                bottomNavigationView = findViewById(R.id.bottomNav);
                bottomNavigationView.setOnItemSelectedListener(bottomNavMethod);
            }
        });

    }

    private NavigationBarView.OnItemSelectedListener bottomNavMethod = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    fragment = new HomeFragment();
                    break;
                case R.id.budget:
                    fragment = new BudgetFragment();
                    break;
                case R.id.invest:
                    fragment = new InvestFragment();
                    break;
                case R.id.importer:
                    fragment = new ImporterFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            return true;
        }
    };


}