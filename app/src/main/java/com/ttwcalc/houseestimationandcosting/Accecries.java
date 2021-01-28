package com.ttwcalc.houseestimationandcosting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class Accecries extends Fragment {
    CardView calculater, compass, store, updates, book, about_us;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.accecreies,container,false);
        calculater = view.findViewById(R.id.calculater);
        compass = view.findViewById(R.id.compass);
        store = view.findViewById(R.id.store);
        updates = view.findViewById(R.id.updates);
        book = view.findViewById(R.id.book);
        about_us = view.findViewById(R.id.about_us);
        //calculater = view.findViewById(R.id.calculater);

        calculater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), CalculatorActivity.class);
                startActivity(i);
            }
        });

        compass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), CompassActivity.class));
            }
        });


        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), StoreActivity.class));
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), BookActivity.class));
            }
        });
        updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), UpdatesActivity.class));
            }
        });
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AboutUs.class);
                startActivity(i);
            }
        });

        return view;
    }

}
