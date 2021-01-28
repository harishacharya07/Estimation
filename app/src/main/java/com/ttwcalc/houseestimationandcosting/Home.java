package com.ttwcalc.houseestimationandcosting;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class Home extends Fragment {
    private int[] mImages = new int[]{R.drawable.img12,R.drawable.img2, R.drawable.img3
    };
    private CardView share, Feed, Projects, more;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String userId;



    CarouselView cview;
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);


        cview = view.findViewById(R.id.cview);
        cview.setPageCount(mImages.length);
        share = view.findViewById(R.id.share);
        more = view.findViewById(R.id.more);
        Projects = view.findViewById(R.id.project);
        Feed = view.findViewById(R.id.s);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        userId = firebaseAuth.getCurrentUser().getUid();
        cview.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);

            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(
                        "https://play.google.com/store/apps/details?id=com.ttwcalc.compass"));
                intent.setPackage("com.android.vending");
                startActivity(intent);
            }
        });

       Feed.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent sendIntent = new Intent();
               sendIntent.setAction(Intent.ACTION_SEND);
               sendIntent.putExtra(Intent.EXTRA_TEXT,
                       "House Estimo app: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
               sendIntent.setType("text/plain");
               Intent shareIntent = Intent.createChooser(sendIntent, null);
               startActivity(shareIntent);
           }
       });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DemoActivity.class);
                startActivity(i);

            }
        });

       Projects.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               firebaseDatabase.getReference().child("earthwork").child(userId).setValue(0);
               firebaseDatabase.getReference().child("pcc").child(userId).setValue(0);
               firebaseDatabase.getReference().child("foundation").child(userId).setValue(0);
               firebaseDatabase.getReference().child("Wall").child(userId).setValue(0);
               firebaseDatabase.getReference().child("lintel").child(userId).setValue(0);
               firebaseDatabase.getReference().child("Slab").child(userId).setValue(0);
               firebaseDatabase.getReference().child("Plast").child(userId).setValue(0);
               firebaseDatabase.getReference().child("Stair").child(userId).setValue(0);
               firebaseDatabase.getReference().child("Paint").child(userId).setValue(0);
               firebaseDatabase.getReference().child("flor").child(userId).setValue(0);
               firebaseDatabase.getReference().child("steel").child(userId).setValue(0);


               Intent i = new Intent(getContext(),GenaralDiscription.class);
               startActivity(i);
           }
       });
        return view;


    }


}
