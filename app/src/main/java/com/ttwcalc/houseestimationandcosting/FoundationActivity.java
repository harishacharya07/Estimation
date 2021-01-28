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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FoundationActivity extends AppCompatActivity {

    EditText w_f, w_s, w_t, center_line, result, hs, hf, ht;
    Button btn_calculate;
    TextView tvResult;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation);
        getSupportActionBar().setTitle("Masonary Foundation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        w_f = findViewById(R.id.w_f);
        w_s = findViewById(R.id.w_s);
        w_t = findViewById(R.id.w_t);

        tvResult = findViewById(R.id.tv_res);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        userId = firebaseAuth.getCurrentUser().getUid();


        btn_calculate = findViewById(R.id.btn_calculate);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (w_f.getText().toString().isEmpty() || w_s.getText().toString().isEmpty() || w_t.getText().toString().isEmpty()
                       ){
                    Toast.makeText(FoundationActivity.this,"please enter all the details", Toast.LENGTH_SHORT).show();
                }else {

                    SharedPreferences sharedPreferences = getSharedPreferences("savedata", Context.MODE_PRIVATE);
                    double r = Double.parseDouble(sharedPreferences.getString("value", "0"));
                    double tb = Double.parseDouble(sharedPreferences.getString("res", "0"));


                    double c1 = Double.parseDouble(w_f.getText().toString().trim());
                    double c2 = Double.parseDouble(w_s.getText().toString().trim());
                    double c3 = Double.parseDouble(w_t.getText().toString().trim());



                    double l1 = r - (c1 / 2.0) * tb;
                    double l2 = r - (c2 / 2.0) * tb;
                    double l3 = r - (c3 / 2.0) * tb;

                    double q1 = l1 * c1 * 0.20;
                    double q2 = l2 * c2 * 0.20;
                    double q3 = l3 * c3 * 0.20;

                    double resf = Math.round(q1 + q2 + q3);
                    String res = "The calculated quantity is " + resf + "m cube";
                    tvResult.setText(res);

                    firebaseDatabase.getReference().child("foundation").child(userId).setValue(r);
                }
            }
        });
    }
}