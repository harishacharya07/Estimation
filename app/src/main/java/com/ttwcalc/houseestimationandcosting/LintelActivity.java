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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LintelActivity extends AppCompatActivity {
    EditText w ,dept,dep,t;
    TextView res;
    Button sub;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lintel);
        getSupportActionBar().setTitle("Lintel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        res = findViewById(R.id.res);
        sub = findViewById(R.id.sub);

        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();



        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("savedata", Context.MODE_PRIVATE);
                double r = Double.parseDouble(sharedPreferences.getString("value", "0"));
                double tb = Double.parseDouble(sharedPreferences.getString("res", "0"));

                    double resb = tb - (0.15/2)*r;

                    double qunt = resb* 0.15 * 0.23;

                    String result = "The calculated quantity is  " + qunt + "m cube";

                    res.setText(result);

                    firebaseDatabase.getReference().child("lintel").child(userId).setValue(qunt);


            }
        });
    }
}