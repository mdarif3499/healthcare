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

public class OnlineDoctor extends AppCompatActivity {
    private String[][] health_details =
            {
                    {"Dr. Azad Hossain", "Apollo Hospital, Dhaka", "01723429868", "MBBS, FCPS (Medicine)", "Click to call", "00:00 - 06:00"},
                    {"Dr. Sabbir Ahmed", "BRB Hospital, Dhaka", "01941853038", "MBBS, DCH", "Click to call", "00:00 - 06:00"},
                    {"Dr. Salma Khan", "Square Hospitals Limited, Dhaka", "01678654321", "MBBS, MD (Internal Medicine)", "Click to call", "06:00 - 12:00"},
                    {"Dr. Kamal Ahmed", "United Hospital, Dhaka", "01899012345", "MBBS, MRCP (UK)", "Click to call", "06:00 - 12:00"},
                    {"Dr. Farida Rahman", "Labaid Specialized Hospital, Dhaka", "01587654321", "MBBS, FCPS (Gynecology)", "Click to call", "12:00 - 18:00"},
                    {"Dr. Nasir Uddin", "Bangladesh Medical College Hospital, Dhaka", "01711223344", "MBBS, FCPS (Surgery)", "Click to call", "12:00 - 18:00"},
                    {"Dr. Anika Islam", "Popular Medical College Hospital, Dhaka", "01995556666", "MBBS, FCPS (Pediatrics)", "Click to call", "18:00 - 24:00"},
                    {"Dr. Rafiqul Islam", "Green Life Medical College Hospital, Dhaka", "01667778888", "MBBS, MD (Cardiology)", "Click to call", "18:00 - 24:0"},
                    {"Dr. Tahmina Akhtar", "Medinova Medical Services Ltd., Dhaka", "01777665544", "MBBS, DGO", "Click to call", "06:00 - 12:00"},
                    {"Dr. Abul Hasan", "Central Hospital Limited, Dhaka", "01887776655", "MBBS, FCPS (Orthopedics)", "Click to call", "06:00 - 12:00"},
                    {"Dr. Ayesha Siddika", "Anwer Khan Modern Hospital, Dhaka", "01998887766", "MBBS, FCPS (Dermatology)", "Click to call", "18:00 - 24:00"},
                    {"Dr. Nusrat Khan", "Ibn Sina Medical College Hospital, Dhaka", "01766554433", "MBBS, MD (Pathology)", "Click to call", "06:00 - 12:00"},
                    {"Dr. Mahbub Rahman", "Holy Family Red Crescent Medical College Hospital, Dhaka", "01554332211", "MBBS, FCPS (Surgery)", "Click to call", "18:00 - 24:00"}
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
        setContentView(R.layout.activity_online_doctor);

        btnBack = findViewById(R.id.buttonB);
        lst = findViewById(R.id.listViewHA);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnlineDoctor.this, HomeActivity.class));
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
            item.put("line7", "");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line6", "line5", "line7"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_m, R.id.line_e, R.id.line_n, R.id.line_d});
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