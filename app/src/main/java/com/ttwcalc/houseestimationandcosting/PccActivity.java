package com.ttwcalc.houseestimationandcosting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PccActivity extends AppCompatActivity {
    TextView res;
    Button sub;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcc);
        getSupportActionBar().setTitle("Pcc Bed");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        res = findViewById(R.id.res);
        sub = findViewById(R.id.sub);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("savedata", Context.MODE_PRIVATE);
                double r = Double.parseDouble(sharedPreferences.getString("value", "0"));
                double tb = Double.parseDouble(sharedPreferences.getString("res", "0"));

                double resb = r - (0.127/2)*tb;

                double qunt = Math.round(resb*5*0.127);

                String result = "The calculated quantity is " + qunt + "m cube";

                res.setText(result);

                firebaseDatabase.getReference().child("pcc").child(userId).setValue(qunt);
            }
        });
    }
}