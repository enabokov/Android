package com.example.eduardnabokov.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addSubmitButtonListener();
    }

    private StringBuilder countriesCheckBoxes() {
        List<CheckBox> boxes = new ArrayList<>();
        boxes.add((CheckBox) findViewById(R.id.checkbox_china));
        boxes.add((CheckBox) findViewById(R.id.checkbox_france));
        boxes.add((CheckBox) findViewById(R.id.checkbox_italy));
        boxes.add((CheckBox) findViewById(R.id.checkbox_netherlands));
        boxes.add((CheckBox) findViewById(R.id.checkbox_russia));
        boxes.add((CheckBox) findViewById(R.id.checkbox_spain));
        boxes.add((CheckBox) findViewById(R.id.checkbox_ukraine));
        boxes.add((CheckBox) findViewById(R.id.checkbox_usa));

        StringBuilder result = new StringBuilder();

        result.append("Countries: ");
        for(CheckBox box : boxes) {
            if (box.isChecked())
                result.append(box.getText() + " ");
        }

        result.append("\n");

        return result;
    }

    private StringBuilder pricesCheckBoxes() {
        List<CheckBox> boxes = new ArrayList<>();
        boxes.add((CheckBox) findViewById(R.id.checkbox_500));
        boxes.add((CheckBox) findViewById(R.id.checkbox_500_1000));
        boxes.add((CheckBox) findViewById(R.id.checkbox_1000));

        StringBuilder result = new StringBuilder();

        result.append("Prices: ");
        for(CheckBox box: boxes) {
            if (box.isChecked())
                result.append(box.getText() + " ");
        }

        result.append("\n");

        return result;
    }

    private void addSubmitButtonListener() {
        final Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StringBuilder res = new StringBuilder();
                StringBuilder res1 = countriesCheckBoxes();
                StringBuilder res2 = pricesCheckBoxes();

                res.append(res1);
                res.append(res2);

                Toast.makeText(MainActivity.this, res, Toast.LENGTH_LONG).show();
            }
        });
    }
}
