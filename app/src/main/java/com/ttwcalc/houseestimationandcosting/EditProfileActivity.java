package com.ttwcalc.houseestimationandcosting;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import static com.ttwcalc.houseestimationandcosting.R.id.emil;

public class EditProfileActivity extends AppCompatActivity {

    EditText username, emails, orgnames;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent data = getIntent();
        String fullname =data.getStringExtra("fullname");
        String email =data.getStringExtra("email");
        String orgname =data.getStringExtra("orgname");

        username = findViewById(R.id.username);
        emails = findViewById(R.id.email);
        orgnames = findViewById(R.id.orgname);

        username.setText(fullname);
        emails.setText(email);
        orgnames.setText(orgname);


    }
}