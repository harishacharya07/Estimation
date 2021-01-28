package com.ttwcalc.houseestimationandcosting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.graphics.pdf.PdfDocument.*;
import static com.ttwcalc.houseestimationandcosting.R.id.earth;
import static com.ttwcalc.houseestimationandcosting.R.id.paint;
import static com.ttwcalc.houseestimationandcosting.R.id.wall;

public class ProjectActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    Button pdf;
    final Context CONTEXT = this;
    int pageWidth = 1200;
    Date date;
    ProgressBar progressBar;
    DateFormat dateFormat;
    EditText name;
    String earthwork, pccBed, foundationwork, lintelwork, wallwork, slabWork, plasteringwork, paintwork, stairCaseWrk, Name, floor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        getSupportActionBar().setTitle("Project Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView earth, pcc, foundation, wall,lintel, slab,stair, plastering, paint, flooring;
        FirebaseDatabase firebaseDatabase;
        FirebaseAuth firebaseAuth;
        final String userId;


        earth = findViewById(R.id.earth);
        pcc = findViewById(R.id.pcc);
        foundation = findViewById(R.id.foundation);
        wall  = findViewById(R.id.wall);
        lintel = findViewById(R.id.slab);
        slab = findViewById(R.id.plastering);
        stair = findViewById(R.id.stair);
        paint = findViewById(R.id.paint);
        plastering = findViewById(R.id.textView50);
        flooring = findViewById(R.id.floor);

        name = findViewById(R.id.name);


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);



        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        userId = firebaseAuth.getCurrentUser().getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
                Sprite doubleBounce = new DoubleBounce();
                progressBar.setIndeterminateDrawable(doubleBounce);

                progressBar.setVisibility(View.VISIBLE);
                earthwork  = dataSnapshot.child("earthwork").child(userId).getKey().toString();
                pccBed = dataSnapshot.child("pcc").child(userId).getValue().toString();
                foundationwork = dataSnapshot.child("foundation").child(userId).getValue().toString();
                lintelwork = dataSnapshot.child("lintel").child(userId).getValue().toString();
                wallwork = dataSnapshot.child("Wall").child(userId).getValue().toString();
                slabWork = dataSnapshot.child("Slab").child(userId).getValue().toString();
                plasteringwork = dataSnapshot.child("Plast").child(userId).getValue().toString();
                paintwork = dataSnapshot.child("Paint").child(userId).getValue().toString();
                stairCaseWrk = dataSnapshot.child("Stair").child(userId).getValue().toString();
                floor = dataSnapshot.child("flor").child(userId).getValue().toString();

                pcc.setText(pccBed);
                earth.setText(earthwork);
                foundation.setText(foundationwork);
                wall.setText(wallwork);
                paint.setText(paintwork);
                stair.setText(stairCaseWrk);
                slab.setText(slabWork);
                lintel.setText(lintelwork);
                plastering.setText(plasteringwork);
                flooring.setText(floor);

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
                if(id == R.id.pdf){
                    if (name.getText().toString().isEmpty()){
                        Toast.makeText(ProjectActivity.this,"Please enter project name", Toast.LENGTH_SHORT).show();
                    }else {
                        createPdf();

                    }


                }
        return super.onOptionsItemSelected(item);
    }

    private void createPdf() {

        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();
        Paint titlePaint = new Paint();
        date = new Date();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Name = name.getText().toString();

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT.DEFAULT, Typeface.BOLD));
        titlePaint.setTextSize(70);
        canvas.drawText("House Estimo", pageWidth/2, 150, titlePaint);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        titlePaint.setTextSize(40);
        canvas.drawText("Invoice", pageWidth/2, 300, titlePaint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(35);
        paint.setColor(Color.BLACK);
        canvas.drawText("Project Name :" + Name,20 ,480, paint);

        paint.setTextAlign(Paint.Align.RIGHT);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        canvas.drawText("Date: " + dateFormat.format(date), pageWidth-20, 480, paint);

        dateFormat = new SimpleDateFormat("HH:mm:ss");
        canvas.drawText("time:" + dateFormat.format(date), pageWidth-20,520, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        canvas.drawRect(20,700,pageWidth-20,640, paint);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("SI No", 40, 680, paint);
        canvas.drawText("Discription", 200, 680, paint);
        canvas.drawText("Quantity", 800, 680, paint );

        canvas.drawText("1",40,760, paint);
        canvas.drawText("Earth work", 200, 740, paint);
        canvas.drawText(earthwork , 800, 740, paint );

        canvas.drawText("2",40,820, paint);
        canvas.drawText("Pcc Bed", 200, 820, paint);
        canvas.drawText(pccBed, 800, 820, paint );

        canvas.drawText("3",40,900, paint);
        canvas.drawText("Foundation", 200, 900, paint);
        canvas.drawText(foundationwork, 800, 900, paint );

        canvas.drawText("4",40,980, paint);
        canvas.drawText("Wall", 200, 980, paint);
        canvas.drawText(wallwork, 800, 980, paint );

        canvas.drawText("5",40,1060, paint);
        canvas.drawText("lintel", 200, 1060, paint);
        canvas.drawText(lintelwork, 800, 1060, paint );

        canvas.drawText("6",40,1140, paint);
        canvas.drawText("Plastering", 200, 1140, paint);
        canvas.drawText(plasteringwork, 800, 1140, paint );

        canvas.drawText("7",40,1240, paint);
        canvas.drawText("Stair Case", 200, 1240, paint);
        canvas.drawText(stairCaseWrk, 800, 1240, paint );

        canvas.drawText("8",40,1320, paint);
        canvas.drawText("Paint", 200, 1320, paint);
        canvas.drawText(paintwork, 800, 1320, paint );

        canvas.drawText("9",40,1400, paint);
        canvas.drawText("Flooring", 200, 1400, paint);
        canvas.drawText(floor, 800, 1400, paint );

        canvas.drawText("10",40,1480, paint);
        canvas.drawText("Slab", 200, 1480, paint);
        canvas.drawText(slabWork, 800, 1480, paint );

        double total = Double.parseDouble(earthwork) + Double.parseDouble(pccBed) + Double.parseDouble(foundationwork) +
                Double.parseDouble(wallwork) + Double.parseDouble(lintelwork) + Double.parseDouble(plasteringwork) + Double.parseDouble(stairCaseWrk) +
                Double.parseDouble(paintwork) + Double.parseDouble(slabWork) + Double.parseDouble(floor);

                paint.setTextSize(45);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT.DEFAULT, Typeface.BOLD));
        canvas.drawText("Total :",680,1560, paint);
        canvas.drawText(String.valueOf(total),800,1560, paint);

        titlePaint.setTextAlign(Paint.Align.RIGHT);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT.DEFAULT, Typeface.NORMAL));
        titlePaint.setTextSize(20);
        canvas.drawText("genarated with House Estimo", 1100, 1900, titlePaint);


        pdfDocument.finishPage(page);

        File file = new File(Environment.getExternalStorageDirectory(),  "/" + Name + ".pdf");
        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(ProjectActivity.this,"Pdf genarated", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
        }
        pdfDocument.close();

    }
}