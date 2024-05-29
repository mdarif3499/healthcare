package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LabTestActivity extends AppCompatActivity {

//    private String[][] packages =
//            {
//                    {"Packages 1 : Full Body Checkup", "", "", "", "999"},
//                    {"Packages 2 : Blood Glucose Fasting", "", "", "", "299"},
//                    {"Packages 3 : COVID-19 Antibody - IgG", "", "", "", "899"},
//                    {"Packages 4 : Thyroid Check", "", "", "", "499"},
//                    {"Packages 5 : Immunity Check", "", "", "", "699"},
//            };

    private String[] package_details = {
            "Blood Glucose Fasting\n" +
                    "Complete Hemogram\n" +
                    "HbA1c\n" +
                    "Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "LDH Lactate Dehydrogenase, Serum\n" +
                    "Lipid Profile\n" +
                    "Liver Function Test",
            "Blood Glucose Fasting",
            "COVID-19 Antibody - IgG",
            "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)",
            "Complete Hemogram\n" +
                    "CRP (C Reactive Protein) Quantitative, Serum\n" +
                    "Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "Vitamin D Total-25 Hydroxy\n" +
                    "Liver Function Test\n" +
                    "Lipid Profile"

    };

    HashMap<String, String> item;
    HashMap<String, String> my_item;
    ArrayList list;
    List<String> mypackage_details;
    ArrayList list1;
    List<List<String>> package_name;
    private String[][] packages1 = new String[5][4];

    SimpleAdapter sa;
    Button btnGoToCard, btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        mypackage_details = new ArrayList<>();
        saveData();
        setContentView(R.layout.activity_lab_test);

        packages1[0] = new String[]{"Packages 1 : Full Body Checkup", "", "", "", "999"};

        btnGoToCard = findViewById(R.id.buttonLTGoToCard);
        btnBack = findViewById(R.id.buttonLTBack);
        listView = findViewById(R.id.listviewLT);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });

//        list = new ArrayList();
//        for (int i = 0; i < packages.length; i++) {
//            item = new HashMap<String, String>();
//            item.put("line1", packages[i][0]);
//            item.put("line2", packages[i][1]);
//            item.put("line3", packages[i][2]);
//            item.put("line4", packages[i][3]);
//            item.put("line5", "Total Cost" + packages[i][4] + "/-");
//            item.put("line6", "");
//            list.add(item);
//
//        }


    }


    public void saveData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("my_package").document("my_package1");

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    // Document found in the offline cache
                    DocumentSnapshot document = task.getResult();
                    PackegeModel model = document.toObject(PackegeModel.class);
                    package_name = new ArrayList<>();
                    package_name.clear();
                    package_name.add(model.line1);
                    package_name.add(model.line2);
                    package_name.add(model.line3);
                    package_name.add(model.line4);
                    package_name.add(model.line5);
                    Log.d("jshdfb", "" + model.getPackage_details());
                    mypackage_details = model.getPackage_details();
                    item = new HashMap<String, String>();
                    list1 = new ArrayList();
                    list1.clear();
                    for (int i = 0; i < package_name.size(); i++) {
                        my_item = new HashMap<String, String>();
                        my_item.put("line1", package_name.get(i).get(0));
                        my_item.put("line2", package_name.get(i).get(1));
                        my_item.put("line3", package_name.get(i).get(2));
                        my_item.put("line4", package_name.get(i).get(3));
                        my_item.put("line5", "Total Cost" + package_name.get(i).get(4) + "/-");
                        my_item.put("line6", "");
                        list1.add(my_item);
                    }

                } else {

                }


                sa = new SimpleAdapter(LabTestActivity.this, list1,
                        R.layout.multi_lines,
                        new String[]{"line1", "line2", "line3", "line4", "Line5", "line6"},
                        new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e, R.id.line_n});
                listView.setAdapter(sa);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                        saveData();
                        Intent it = new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                        it.putExtra("text1", package_name.get(i).get(0));
                        it.putExtra("text2", package_details[i]);
                        it.putExtra("text3", package_name.get(i).get(4));
                        startActivity(it);
                    }
                });

                btnGoToCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
                    }
                });


            }


        });

    }


}