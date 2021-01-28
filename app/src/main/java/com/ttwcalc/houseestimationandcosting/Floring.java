package com.ttwcalc.houseestimationandcosting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Floring extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    Button btnsubmit;
    TextView tv3;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floring);

        getSupportActionBar().setTitle("Flooring");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnsubmit = findViewById(R.id.btnsubmit);
        tv3 = findViewById(R.id.tv3);

        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("savedata", Context.MODE_PRIVATE);
                double lb = Double.parseDouble(sharedPreferences.getString("area", "0"));

                String res = "Calculated quantity is " + lb + "sq meter";
                tv3.setText(res);

                firebaseDatabase.getReference().child("flor").child(userId).setValue(lb);
            }
        });
    }
}