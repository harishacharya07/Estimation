package com.ttwcalc.houseestimationandcosting;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager SensorManage;

    private ImageView compassimage;

    private float DegreeStart = 0f;
    TextView DegreeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        getSupportActionBar().setTitle("Compass");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        compassimage = (ImageView) findViewById(R.id.compass_image);

        DegreeTV = (TextView) findViewById(R.id.DegreeTV);

        SensorManage = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    @Override
    protected void onPause() {
        super.onPause();

        SensorManage.unregisterListener((SensorEventListener) this);
    }
    @Override
    protected void onResume() {
        super.onResume();

        SensorManage.registerListener((SensorEventListener) this, SensorManage.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

            float degree = Math.round(event.values[0]);
            DegreeTV.setText("Heading: " + Float.toString(degree) + " degrees");
            RotateAnimation ra = new RotateAnimation(
                    DegreeStart,
                    -degree,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            ra.setFillAfter(true);

            ra.setDuration(210);

            compassimage.startAnimation(ra);
            DegreeStart = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}