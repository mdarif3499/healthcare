package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Emergency extends AppCompatActivity {
    private String[][] health_details =
            {
                    {"National Emergency", "All over Bangladesh", "999", "", "Click to call", "24/7"},
                    {"DMCH", "Dhaka City", "999", "", "Click to call", "24/7"},
                    {"Apollo Hospitals Dhaka", "Dhaka City", "10678", "", "Click to call", "24/7"},
                    {"Square Hospitals Limited", "Dhaka City", "10616", "", "Click to call", "24/7"},
                    {"United Hospital", "Dhaka City", "10666", "", "Click to call", "24/7"},
                    {"Labaid Specialized Hospital", "Dhaka City", "10606", "", "Click to call", "24/7"}
            };
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnBack;
    ListView lst;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        btnBack = findViewById(R.id.backE);
        lst = findViewById(R.id.listViewHA);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Emergency.this, HomeActivity.class));
            }
        });
        list = new ArrayList();
        for (int i = 0; i < health_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", health_details[i][0]);
            item.put("line2", health_details[i][1]);
            item.put("line3", "Contact Number: " + health_details[i][2]);
            item.put("line4", health_details[i][3]);
            item.put("line5", "Available time: " + health_details[i][5]);
            item.put("line6", health_details[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line6", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e, R.id.line_n});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String phoneNumber = health_details[i][2]; // Replace with the desired phone number
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(dialIntent);
            }
        });
    }
}