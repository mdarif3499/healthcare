package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_lab_test_book);

        edname = findViewById(R.id.editTextLTBFullName);
        edaddress = findViewById(R.id.editTextLTBAddress);
        edcontact = findViewById(R.id.editTextLTBContact);
        edpincode = findViewById(R.id.editTextLTBPinCode);
        btnBooking = findViewById(R.id.buttonLTBBooking);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        //String time = intent.getStringExtra("time");

        String odnum = generateOrderNumber();

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();

                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                db.insertOrderDetails(odnum, "Unpaid"); // Insert a paid order


                db.addOrder(username,
                        edname.getText().toString(),
                        edaddress.getText().toString(),
                        edcontact.getText().toString(),
                        Integer.parseInt(edpincode.getText().toString()),
                        date.toString(),
                        odnum.toString(),
                        Float.parseFloat(price[1].toString()), "lab");
                db.removeCart(username, "lab");
                Toast.makeText(getApplicationContext(), "Your booking is done successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));

            }
        });
    }

    private String generateOrderNumber() {
        Random random = new Random();
        int orderNumber = random.nextInt(10000) + 9000; // Generate a random number between 10000 and 99999
        return String.valueOf(orderNumber);
    }

}