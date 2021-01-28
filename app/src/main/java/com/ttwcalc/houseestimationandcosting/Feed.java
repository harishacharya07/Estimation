package com.ttwcalc.houseestimationandcosting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feed extends AppCompatActivity {
    EditText name, adress;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Feed Back");

        name = findViewById(R.id.name);
        adress = findViewById(R.id.discription);
        btn =findViewById(R.id.submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("messeage/html");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"teamtechwarriors@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "feed back from app");
                i.putExtra(Intent.EXTRA_TEXT,"Name:" + name.getText().toString() + "\n Messeage :" + adress.getText().toString());
                try {
                    startActivity(Intent.createChooser(i, "please select email"));

                }
                catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(Feed.this, "no email client", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}