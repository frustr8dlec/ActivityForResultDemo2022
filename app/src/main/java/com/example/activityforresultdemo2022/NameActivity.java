package com.example.activityforresultdemo2022;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activityforresultdemo2022.databinding.ActivityNameBinding;

public class NameActivity extends AppCompatActivity {

    private ActivityNameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_name);

        binding = ActivityNameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbarName);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        binding.buttonSubmit.setOnClickListener(view -> {
            Intent returnIntent = new Intent();
            if (!binding.textViewName.getText().toString().isEmpty()) {
                returnIntent.putExtra("nameData", binding.textViewName.getText().toString());
            }
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        });

        binding.buttonCancel.setOnClickListener(view -> {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }
}