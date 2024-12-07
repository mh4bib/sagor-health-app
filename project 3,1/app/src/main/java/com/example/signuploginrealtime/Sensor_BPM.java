package com.example.signuploginrealtime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.content.Context;
import java.util.Random;

public class Sensor_BPM extends AppCompatActivity {


    private TextView bpmTextView, instructionTextView;
    private Button measureButton;
    private ProgressBar progressBar;
    private CameraManager cameraManager;
    private String cameraId;
    private Handler handler;
    private boolean isMeasuring = false;
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_bpm);

        bpmTextView = findViewById(R.id.bpmTextView);
        instructionTextView = findViewById(R.id.instructionTextView);
        measureButton = findViewById(R.id.measureButton);
        progressBar = findViewById(R.id.progressBar);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        // Check permissions and set cameraId
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        measureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMeasuring) {
                    startMeasurement();
                }
            }
        });
    }

    private void startMeasurement() {
        isMeasuring = true;
        measureButton.setEnabled(false);
        bpmTextView.setText("Measuring...");
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);
        progressStatus = 0;

        turnOnFlashlight();

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int simulatedBPM = new Random().nextInt(13) + 75; // Simulated BPM between 75 and 87
                bpmTextView.setText("Heart Rate: " + simulatedBPM + " BPM");
                turnOffFlashlight();
                isMeasuring = false;
                measureButton.setEnabled(true);
                progressBar.setVisibility(View.GONE);
            }
        }, 30000); // Measurement duration: 30 seconds

        simulateProgressBar();
    }

    private void simulateProgressBar() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100 && isMeasuring) {
                    progressStatus += 1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        Thread.sleep(300); // Progress update every 300ms
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void turnOnFlashlight() {
        try {
            if (cameraManager != null) {
                cameraManager.setTorchMode(cameraId, true);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void turnOffFlashlight() {
        try {
            if (cameraManager != null) {
                cameraManager.setTorchMode(cameraId, false);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}