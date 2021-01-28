package com.ttwcalc.houseestimationandcosting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class GroundActivity extends AppCompatActivity {
    private CardView earth,pcc,foundation,lintel2,slab,Stair, paint, wall ,pc, floor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ground);
        getSupportActionBar().setTitle("Estimation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        foundation = findViewById(R.id.foundation);
        earth = findViewById(R.id.earth);
        pcc = findViewById(R.id.pcc);
        lintel2 = findViewById(R.id.lintel2);
        slab = findViewById(R.id.slab);
        Stair = findViewById(R.id.Stair);
        paint  = findViewById(R.id.paint);
        wall = findViewById(R.id.wall);
        pc = findViewById(R.id.floor);
        floor = findViewById(R.id.pc);

        earth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroundActivity.this, EarthActivity.class);
                startActivity(i);

            }
        });

        pcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroundActivity.this, PccActivity.class);
                startActivity(i);
            }
        });

        foundation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent id = new Intent(GroundActivity.this, FoundationActivity.class);
                startActivity(id);
            }
        });

        lintel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroundActivity.this,LintelActivity.class);
                startActivity(i);

            }
        });

        slab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroundActivity.this,SlabActivity.class);
                startActivity(i);
            }
        });

        Stair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroundActivity.this,StairActivity.class);
                startActivity(i);
            }
        });
        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroundActivity.this, PaintActivity.class);
                startActivity(i);
            }
        });

        wall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroundActivity.this,WallActivity.class);
                startActivity(i);
            }
        });

        pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroundActivity.this, PlastActivity.class);
                startActivity(i);
            }
        });
        floor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroundActivity.this, Floring.class);
                startActivity(i);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.show){
            Intent i = new Intent(GroundActivity.this, ProjectActivity.class);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GroundActivity.this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}