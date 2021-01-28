package com.ttwcalc.houseestimationandcosting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DemoActivity extends AppCompatActivity {
    Button button;
    EditText name;
    EditText pass, passWorld;
    String myName, Pass ,demo;
    TextView textView;
    Double passw, Text;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        button = findViewById(R.id.save);
        name = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        passWorld = findViewById(R.id.passworld);
        textView = findViewById(R.id.text);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        uid = firebaseAuth.getInstance().getUid();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myName = name.getText().toString();
                Pass = pass.getText().toString();
                passw = Double.parseDouble(passWorld.getText().toString());
                //demo = passWorld.getText().toString();

                firebaseDatabase.getReference().child(Pass).setValue(myName);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Text = Double.parseDouble(dataSnapshot.child(Pass).getKey().toString());
                        String result;
                        result = "calcu" + Text;

                        if (passw == Text){
                            textView.setText(result);
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}