package com.example.signuploginrealtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Step_Counter extends AppCompatActivity implements SensorEventListener {

    private TextView stepCountTextView;
    private TextView distanceTextView; // New TextView for distance
    private SensorManager sensorManager;
    private boolean isSensorRegistered = false;
    private int stepCount = 0;
    private boolean isStepDetected = false;

    private static final float STEP_THRESHOLD = 10f;
    private static final long STEP_DELAY_THRESHOLD = 500;
    private static final float STEP_LENGTH = 0.78f; // Average step length in meters
    private int walkingGoal = 0; // User's walking goal

    private long lastStepTime = 0;
    private boolean goalReached = false;

    private static final int PERMISSION_REQUEST_SEND_SMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        stepCountTextView = findViewById(R.id.stepCountTextView);
        distanceTextView = findViewById(R.id.distanceTextView); // Initialize distanceTextView
        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);
        EditText goalEditText = findViewById(R.id.goalEditText);
        Button setGoalButton = findViewById(R.id.setGoalButton);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStepCounting();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopStepCounting();
            }
        });

        setGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String goalText = goalEditText.getText().toString();
                if (!goalText.isEmpty()) {
                    walkingGoal = Integer.parseInt(goalText);
                    Toast.makeText(Step_Counter.this, "Goal set to " + walkingGoal + " steps", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Step_Counter.this, "Please enter a valid goal", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Request SMS permission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_SEND_SMS);
        }
    }

    private void startStepCounting() {
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            isSensorRegistered = true;
        }
    }

    private void stopStepCounting() {
        if (isSensorRegistered) {
            sensorManager.unregisterListener(this);
            isSensorRegistered = false;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float acceleration = (float) Math.sqrt(x * x + z * z);

        long currentTime = System.currentTimeMillis();

        if (!isStepDetected && acceleration > STEP_THRESHOLD && (currentTime - lastStepTime) > STEP_DELAY_THRESHOLD) {
            stepCount++;
            stepCountTextView.setText("Steps: " + stepCount);

            // Calculate distance based on step count
            float distance = stepCount * STEP_LENGTH; // in meters
            distanceTextView.setText("Distance: " + distance + " m"); // Display distance

            lastStepTime = currentTime;
            isStepDetected = true;

            if (stepCount >= walkingGoal && !goalReached && walkingGoal != 0) {
                goalReached = true;
                sendSMS("01795514265", "Congratulations! You've achieved your walking goal of " + walkingGoal + " steps!");
            }
        } else if (acceleration < STEP_THRESHOLD) {
            isStepDetected = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void sendSMS(String phoneNumber, String message) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            if (phoneNumber.length() == 11) { // Check if the phone number is 11 digits long
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                Toast.makeText(this, "SMS sent!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "SMS permission required", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "SMS permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}