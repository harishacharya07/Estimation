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

public class StairActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7,ed8,ed9;
    Button btn;
    TextView tv1;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stair);
        ed8 = findViewById(R.id.ed8);
        tv1 = findViewById(R.id.tvres);
        btn = findViewById(R.id.btn);


        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        getSupportActionBar().setTitle("Stair Case");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed8.getText().toString().isEmpty()){
                    Toast.makeText(StairActivity.this, "please enter width of the stair", Toast.LENGTH_SHORT).show();
                }else {


                    double width = Double.parseDouble(ed8.getText().toString().trim());

                    SharedPreferences sharedPreferences = getSharedPreferences("savedata", Context.MODE_PRIVATE);
                    double r = Double.parseDouble(sharedPreferences.getString("length", "0"));

                    double no_steps = (r / 0.15);

                    double horizontal = no_steps * 0.25;
                    double waist = Math.sqrt((horizontal * horizontal) + (r * r));
                    double volw = waist * width * 0.15;
                    double vol1 = (0.5) * 0.25 * 0.15 * width;
                    double vot = no_steps * vol1;
                    double totalv = Math.round(vot + volw);

                    String res = "The calculated quantity is " + totalv + " m cube";
                    tv1.setText(res);

                    firebaseDatabase.getReference().child("Stair").child(userId).setValue(totalv);

                }

            }
        });
    }
}