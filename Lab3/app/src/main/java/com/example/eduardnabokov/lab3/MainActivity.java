package com.example.eduardnabokov.lab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // expandable list view
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHashMap;
    private Integer selectedGroupPosition;
    private Integer selectedItemPosition;

    // checkboxes
    private CheckBox chkFrance, chkSpain, chkUSA, chkRussia, chkChina;
    private CheckBox chkLT500, chkB500_1000, chkGT1000;
    private Button btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ExpandableListView) findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHashMap);
        listView.setAdapter(listAdapter);

        addListenerOnChkFrance();
        addListenerOnButton();
        addListenerOnChildListView();
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();
        listDataHeader.add("Dishes");

        List<String> exdDev = new ArrayList<>();
        exdDev.add("Fork");
        exdDev.add("Spoon");
        exdDev.add("Teapot");
        exdDev.add("Knife");
        exdDev.add("Plate");

        listHashMap.put(listDataHeader.get(0), exdDev);
    }

    public void addListenerOnChkFrance() {

        chkFrance = (CheckBox) findViewById(R.id.chkFrance);

        chkFrance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(MainActivity.this,
                            "It's better Spain!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addListenerOnChildListView() {
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                v.setSelected(true);
                System.out.println("GroupPosition: " + groupPosition);
                System.out.println("ChildPosition: " + childPosition);
                System.out.println("ID: " + id);

                selectedGroupPosition = groupPosition;
                selectedItemPosition = childPosition;
                return false;
            }
        });
    };

    public void addListenerOnButton() {
        final List<CheckBox> checkBoxesProducers = new ArrayList<>();
        checkBoxesProducers.add((CheckBox) findViewById(R.id.chkFrance));
        checkBoxesProducers.add((CheckBox) findViewById(R.id.chkSpain));
        checkBoxesProducers.add((CheckBox) findViewById(R.id.chkUSA));
        checkBoxesProducers.add((CheckBox) findViewById(R.id.chkRussia));
        checkBoxesProducers.add((CheckBox) findViewById(R.id.chkChina));

        final List<CheckBox> checkBoxesPrices = new ArrayList<>();
        checkBoxesPrices.add((CheckBox) findViewById(R.id.chkLT500));
        checkBoxesPrices.add((CheckBox) findViewById(R.id.chkB500_1000));
        checkBoxesPrices.add((CheckBox) findViewById(R.id.chkGT1000));

        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            //Run when button is clicked
            @Override
            public void onClick(View v) {
                StringBuilder result = new StringBuilder();

                StringBuilder producers = new StringBuilder();
                for(CheckBox checkBox: checkBoxesProducers){
                    if (checkBox.isChecked()) {
                        producers.append(checkBox.getText() + " ");
                    }
                }

                if (producers.length() > 0) {
                    result.append("Producers: ");
                    result.append(producers);
                }

                StringBuilder prices = new StringBuilder();
                for(CheckBox checkBox: checkBoxesPrices){
                    if (checkBox.isChecked()) {
                        prices.append(checkBox.getText() + " ");
                    }
                }

                if (prices.length() > 0) {
                    result.append("\nPrices: ");
                    result.append(prices);
                }

                String itemName = null;
                try {
                    itemName = (String) listAdapter.getChild(selectedGroupPosition, selectedItemPosition);
                } catch (Exception exc) {
                    System.out.println(exc.getCause());
                }

                if (itemName != null) {
                    result.append("\nItem: ");
                    result.append(itemName);
                }

                Toast.makeText(MainActivity.this, result.toString(),
                        Toast.LENGTH_LONG).show();

            }
        });

    }
}
