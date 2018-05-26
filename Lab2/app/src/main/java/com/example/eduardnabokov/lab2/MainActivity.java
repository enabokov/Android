package com.example.eduardnabokov.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addSubmitButtonListener();
    }

    private void countriesCheckBoxes() {
        Button button = findViewById(R.id.checkbox_china);
        System.out.println("China button is " + button.isSelected());
    }

    private void addSubmitButtonListener() {
        final Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                countriesCheckBoxes();
                Toast.makeText(MainActivity.this, "Submit", Toast.LENGTH_LONG).show();
            }
        });
    }
}
