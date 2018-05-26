package com.example.eduardnabokov.lab3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addSubmitButtonListener();
        addCheckButtonListener();
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

    private StringBuilder dishSelected() {
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        StringBuilder res = new StringBuilder();
        res.append("Dish: " + (String) spinner.getSelectedItem());

        return res;
    }

    private void addSubmitButtonListener() {
        final Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StringBuilder res = new StringBuilder();

                StringBuilder res1 = countriesCheckBoxes();
                StringBuilder res2 = pricesCheckBoxes();
                StringBuilder res3 = dishSelected();

                res.append(res1);
                res.append(res2);
                res.append(res3);

                writeToFile(res.toString());

                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addCheckButtonListener() {
        final Button button = findViewById(R.id.button_read);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                readFromFile(v);
            }
        });
    }

    private void writeToFile(String text) {
        String filename = "saved_data.txt";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(View v) {
        TextView view = (TextView) findViewById(R.id.textview);

        try {
            FileInputStream fileIn=openFileInput("saved_data.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[100];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s += readstring;
            }
            InputRead.close();
            view.setText(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
