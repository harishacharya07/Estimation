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

public class PlastActivity extends AppCompatActivity {
    EditText lw, ww, hw, dw;
    TextView tvres;
    Button calulate;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plast);
        getSupportActionBar().setTitle("Plastering");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tvres = findViewById(R.id.tvres);

        calulate = findViewById(R.id.calculate);

        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();


        calulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("savedata", Context.MODE_PRIVATE);
                double lb = Double.parseDouble(sharedPreferences.getString("value", "0"));
                double tb = Double.parseDouble(sharedPreferences.getString("res", "0"));
                double f = Double.parseDouble(sharedPreferences.getString("length", "0"));
                double led = Double.parseDouble(sharedPreferences.getString("ld", "0"));
                double wed = Double.parseDouble(sharedPreferences.getString("wd", "0"));
                double nd = Double.parseDouble(sharedPreferences.getString("nd", "0"));
                double lw = Double.parseDouble(sharedPreferences.getString("lw", "0"));
                double ww = Double.parseDouble(sharedPreferences.getString("ww", "0"));
                double nw = Double.parseDouble(sharedPreferences.getString("nw", "0"));

                double value = (led*wed*nd) + (lw*ww*nw);

                double l = tb - (0.23/2) * lb;


                double res = Math.round((l * f * 0.23) * 2 - (value));

                String result = "Calculated quantity is " + res + "m cube";
                tvres.setText(result);

                firebaseDatabase.getReference().child("Plast").child(userId).setValue(res);


            }
        });
    }
}