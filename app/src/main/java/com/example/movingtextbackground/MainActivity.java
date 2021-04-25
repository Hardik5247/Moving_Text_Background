package com.example.movingtextbackground;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.MaskingLibrary;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    private Sensor sensor;
    private SensorManager mSensorManager;

    ImageView imageView;
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView4);

        textView = findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        if (null == sensor)
            finish();

        String text = "AWESOME";

        MaskingLibrary maskingLibrary = new MaskingLibrary();

        Bitmap textAsBitmap = maskingLibrary.textAsBitmap(text, 1000f);
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.mipmap.rainbownebula);

        imageView.setImageBitmap(maskingLibrary.MaskingProcess(textAsBitmap, image));

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float X = event.values[0];
        float Y = event.values[1];
        float Z = event.values[2];
        //String data = "X : " + String.valueOf(Math.abs(X)) + "\nY : " + String.valueOf(Math.abs(Y)) + "\nZ : " + String.valueOf(Math.abs(Z));
        //textView.setText(data);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
