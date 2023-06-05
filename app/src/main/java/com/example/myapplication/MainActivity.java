package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EditText editTextWeight = findViewById(R.id.weight2);
        EditText editTextHeight = findViewById(R.id.height2);
        Button button = findViewById(R.id.btnSubmit);
        TextView textView = findViewById(R.id.result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float height = Float.parseFloat(String.valueOf(editTextHeight.getText())) / 100;
                float weight = Float.parseFloat(String.valueOf(editTextWeight.getText()));
                float bmi = weight / (height * height);
                textView.setText(String.valueOf(bmi));
            }
        });
    }
}