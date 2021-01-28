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

public class SlabActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    Button btnsubmit;
    TextView tv3;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slab);
        getSupportActionBar().setTitle("Slab");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ed3 = findViewById(R.id.ed3);
        btnsubmit = findViewById(R.id.btnsubmit);
        tv3 = findViewById(R.id.tv3);

        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed3.getText().toString().isEmpty()){
                    Toast.makeText(SlabActivity.this,"please enter thickness of the slab",Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences sharedPreferences = getSharedPreferences("savedata", Context.MODE_PRIVATE);
                    double lb = Double.parseDouble(sharedPreferences.getString("area", String.valueOf(0)));

                    float t = Float.parseFloat(ed3.getText().toString().trim());

                    float vo = Math.round(lb*t);
                    String res = "Calculated quantity is " + vo + "m cube";
                    tv3.setText(res);

                    firebaseDatabase.getReference().child("Slab").child(userId).setValue(vo);
                }
            }
        });

    }
}