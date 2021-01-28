package com.ttwcalc.houseestimationandcosting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GenaralDiscription extends AppCompatActivity {

    EditText center, tj, ftf, ld, wd, nw, lw, ww, hw, nd;
    Button save;

    public static final String SHARED = "sharedpref";
    public static final String TEXT = "text";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genaral_discription);

        getSupportActionBar().setTitle("General Descriptions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tj = findViewById(R.id.tjountion);
        center = findViewById(R.id.center_line);
        ftf = findViewById(R.id.ftf);
        ld = findViewById(R.id.ld);
        wd = findViewById(R.id.hd);
        nw = findViewById(R.id.nw);
        lw = findViewById(R.id.lw);
        ww = findViewById(R.id.ww);
        nd = findViewById(R.id.nd);
        hw = findViewById(R.id.area);

        save = findViewById(R.id.save);

        alertDialogue();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tj.getText().toString().isEmpty() || center.getText().toString().isEmpty() || ftf.getText().toString().isEmpty()
                || ld.getText().toString().isEmpty() || wd.getText().toString().isEmpty() || nd.getText().toString().isEmpty() ||
                lw.getText().toString().isEmpty() || ww.getText().toString().isEmpty() || nw.getText().toString().isEmpty()|| hw.getText().toString().isEmpty()){
                    Toast.makeText(GenaralDiscription.this, "please enter all the details", Toast.LENGTH_SHORT).show();
                }else {
                    sharedPreferences = getSharedPreferences("savedata", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("value", tj.getText().toString().trim());
                    editor.putString("res", center.getText().toString().trim());
                    editor.putString("length", ftf.getText().toString().trim());
                    editor.putString("ld", ld.getText().toString().trim());
                    editor.putString("wd", wd.getText().toString().trim());
                    editor.putString("nd", nd.getText().toString().trim());
                    editor.putString("lw", lw.getText().toString().trim());
                    editor.putString("ww", ww.getText().toString().trim());
                    editor.putString("nw", nw.getText().toString().trim());
                    editor.putString("area", hw.getText().toString().trim());


                    editor.apply();

                    Intent i = new Intent(GenaralDiscription.this, GroundActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    private void alertDialogue() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("All dimensions are in meter")
                .setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}