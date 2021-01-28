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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EarthActivity extends AppCompatActivity {
    EditText w ,dept,dep,t;
    TextView res;
    Button sub;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth);

        getSupportActionBar().setTitle("Earth Work");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dep= findViewById(R.id.dep);
        res = findViewById(R.id.res);
        sub = findViewById(R.id.sub);


        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        userId = firebaseAuth.getCurrentUser().getUid();


        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(  dep.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(EarthActivity.this,"please enter the depth", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("savedata", Context.MODE_PRIVATE);
                    double r = Double.parseDouble(sharedPreferences.getString("value", "0"));
                    double tb = Double.parseDouble(sharedPreferences.getString("res", "0"));
                    float  deptb = Float.parseFloat(dep.getText().toString().trim());

                    double resb = r - (deptb/2) * tb;

                    double qunt = resb*deptb*5;

                    String result = "The calculated quantity is  " + qunt + "m cube";

                    res.setText(result);
                    firebaseDatabase.getReference().child("earthwork").child(userId).setValue(qunt);


                }
            }
        });

    }
}