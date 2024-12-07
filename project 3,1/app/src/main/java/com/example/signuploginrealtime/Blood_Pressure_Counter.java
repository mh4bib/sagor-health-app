package com.example.signuploginrealtime;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Blood_Pressure_Counter extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private TextView pressureTextView;
    private EditText systolicEditText;
    private EditText diastolicEditText;

    // Define threshold values for high and low pressure
    private static final float HIGH_PRESSURE_THRESHOLD = 140f; // in mmHg
    private static final float LOW_PRESSURE_THRESHOLD = 90f; // in mmHg

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure_counter);

        pressureTextView = findViewById(R.id.pressureTextView);
        systolicEditText = findViewById(R.id.systolicEditText);
        diastolicEditText = findViewById(R.id.diastolicEditText);
        Button checkPressureButton = findViewById(R.id.checkPressureButton);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        }

        checkPressureButton.setOnClickListener(v -> checkBloodPressure());
    }

    @SuppressLint("SetTextI18n")
    private void checkBloodPressure() {
        try {
            float systolic = Float.parseFloat(systolicEditText.getText().toString());
            float diastolic = Float.parseFloat(diastolicEditText.getText().toString());
            String pressureStatus = determineBloodPressureStatus(systolic, diastolic);
            pressureTextView.setText("Blood Pressure: " + systolic + "/" + diastolic + " mmHg\n" + pressureStatus);
        } catch (NumberFormatException e) {
            pressureTextView.setText("Please enter valid numbers");
        }
    }

    private String determineBloodPressureStatus(float systolic, float diastolic) {
        if (systolic >= HIGH_PRESSURE_THRESHOLD || diastolic >= HIGH_PRESSURE_THRESHOLD) {
            return "High Blood Pressure";
        } else if (systolic <= LOW_PRESSURE_THRESHOLD && diastolic <= LOW_PRESSURE_THRESHOLD) {
            return "Low Blood Pressure";
        } else {
            return "Normal Blood Pressure";
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pressureSensor != null) {
            sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (pressureSensor != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            float pressureValue = event.values[0];
            pressureTextView.setText("Current Pressure: " + pressureValue + " hPa");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }
}