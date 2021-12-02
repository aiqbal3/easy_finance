package com.example.easy_finance;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ImporterFragment extends Fragment {

    public ImporterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_importer, container, false);
    }

    //add onClick listener here?

    private void changeActivity() {
        Intent intent = new Intent(ImporterFragment.this.getActivity(), Scan.class);
        startActivity(intent);
    }


}