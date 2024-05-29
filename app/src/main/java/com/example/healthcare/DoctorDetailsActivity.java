package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 =
            {
                    {"Dr. Ahmed Khan", "Family Care Clinic, Dhaka", "Exp : 10yrs.", "01900000000", "500", "09:00 - 13:00", "MBBS (DU), FCPS"},
                    {"Dr. Ayesha Rahman", "Healthy Life Medical Center, Chittagong", "Exp : 7yrs.", "01700000000", "450", "10:00 - 14:00", "MBBS (DMC), MRCP (UK)"},
                    {"Dr. Abul Hasan", "Caring Medical Services, Sylhet", "Exp : 15yrs.", "01800000000", "800", "09:30 - 13:30", "MBBS (DU), DNB (Family Medicine)"},
                    {"Dr. Farid Ahmed", "Wellness Health Clinic, Rajshahi", "Exp : 12yrs.", "01300000000", "600", "11:00 - 15:00", "MBBS (DMC), FCPS"},
                    {"Dr. Samina Khan", "HealthFirst Clinic, Khulna", "Exp : 8yrs.", "Mobile No : 01600000000", "550", "10:00 - 14:00", "MBBS (DU), MD (Family Medicine)"}
            };


    private String[][] doctor_details2 =
            {
                    {"Dr. Nusrat Jahan", "Diet and Nutrition Center, Dhaka", "Exp : 6yrs.", "01900000000", "300", "09:00 - 12:00", "BSc in Nutrition and Dietetics"},
                    {"Dr. Farhana Ahmed", "Healthy Living Clinic, Chittagong", "Exp : 8yrs.", "01700000000", "400", "10:00 - 13:00", "MS in Dietetics and Nutrition"},
                    {"Dr. Zubair Khan", "Wellness Clinic, Sylhet", "Exp : 12yrs.", "01800000000", "800", "09:30 - 12:30", "Certified Dietician"},
                    {"Dr. Sadia Rahman", "Green Health Center, Rajshahi", "Exp : 5yrs.", "01300000000", "350", "11:00 - 14:00", "RD, LD"},
                    {"Dr. Sumaiya Haque", "Nutrition Plus Center, Khulna", "Exp : 10yrs.", "01600000000", "600", "09:00 - 12:00", "MSc in Nutrition Science"}
            };

    private String[][] doctor_details3 =
            {
                    {"Dr. Shahid Malik", "Perfect Smile Dental Clinic, Dhaka", "Exp : 12yrs.", "01900000000", "800", "10:00 - 18:00", "BDS (DU), MDS (Oral Surgery)"},
                    {"Dr. Farah Ahmed", "Healthy Teeth Dental Care, Chittagong", "Exp : 9yrs.", "01700000000", "700", "09:30 - 17:30", "BDS (DMC), FCPS (Dentistry)"},
                    {"Dr. Ahmed Rahman", "Bright Dental Center, Sylhet", "Exp : 15yrs.", "01800000000", "1000", "11:00 - 19:00", "BDS (DU), MOrth (Orthodontics)"},
                    {"Dr. Ayesha Khan", "Smile Haven Dental Clinic, Rajshahi", "Exp : 10yrs.", "01300000000", "850", "10:30 - 18:30", "BDS (DU), MFDS RCS (UK)"},
                    {"Dr. Samir Khan", "Dental Wellness Clinic, Khulna", "Exp : 7yrs.", "01600000000", "750", "11:30 - 19:30", "BDS (DU), MS (Oral Pathology)"}
            };


    private String[][] doctor_details4 =
            {
                    {"Dr. Fahim Rahman", "City Hospital, Dhaka", "Exp : 15yrs.", "01900000000", "1500", "08:00 - 16:00", "MBBS (DMC), FCPS (Surgery)"},
                    {"Dr. Nusrat Khan", "MediCorp Medical Center, Chittagong", "Exp : 12yrs.", "01700000000", "1300", "09:00 - 17:00", "MBBS (DMC), MS (General Surgery)"},
                    {"Dr. Shahin Ahmed", "Sunrise Hospital, Sylhet", "Exp : 20yrs.", "01800000000", "1800", "10:00 - 18:00", "MBBS (DU), MCh (Plastic Surgery)"},
                    {"Dr. Rehana Malik", "Royal Medical Complex, Rajshahi", "Exp : 18yrs.", "01300000000", "1600", "10:30 - 18:30", "MBBS (RU), FCPS (Surgery)"},
                    {"Dr. Imran Khan", "Greenview Hospital, Khulna", "Exp : 14yrs.", "01600000000", "1400", "11:00 - 19:00", "MBBS (KMC), FCPS (Surgery)"}
            };


    private String[][] doctor_details5 =
            {
                    {"Dr. Farah Khan", "HeartCare Clinic, Dhaka", "Exp : 12yrs.", "01900000000", "1200", "08:00 - 16:00", "MBBS (DMC), FCPS (Cardiology)"},
                    {"Dr. Ahsan Ahmed", "CardioMed Hospital, Chittagong", "Exp : 10yrs.", "01700000000", "1000", "09:00 - 17:00", "MBBS (CMC), MD (Cardiology)"},
                    {"Dr. Samiul Islam", "Heartbeat Hospital, Sylhet", "Exp : 15yrs.", "01800000000", "1500", "10:00 - 18:00", "MBBS (SU), DM (Cardiology)"},
                    {"Dr. Saba Akhtar", "CardioWell Clinic, Rajshahi", "Exp : 14yrs.", "01300000000", "1400", "10:30 - 18:30", "MBBS (RU), FCPS (Cardiology)"},
                    {"Dr. Imran Ali", "HeartHealth Hospital, Khulna", "Exp : 11yrs.", "01600000000", "1100", "11:00 - 19:00", "MBBS (KMC), MD (Cardiology)"}
            };


    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;

    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_doctor_details);

        btn = findViewById(R.id.buttonDDBack);
        tv = findViewById(R.id.textViewDDTitle);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Physicians") == 0)
            doctor_details = doctor_details1;
        else if (title.compareTo("Dietician") == 0)
            doctor_details = doctor_details2;
        else if (title.compareTo("Dentist") == 0)
            doctor_details = doctor_details3;
        else if (title.compareTo("Surgeon") == 0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < doctor_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons Fees:" + doctor_details[i][4] + "/-");
            item.put("line6", "Available Time: " + doctor_details[i][5]);
            item.put("line7", "Designation: " + doctor_details[i][6]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5", "line6", "line7"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e, R.id.line_n, R.id.line_m}
        );

        ListView lst = findViewById(R.id.listviewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5", doctor_details[i][4]);
                it.putExtra("text6", doctor_details[i][5]);
                startActivity(it);

            }
        });

    }
}