package com.example.signuploginrealtime;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buttons
        Button btnBmi = findViewById(R.id.btn_bmi);
        Button btnbmitest = findViewById(R.id.btn_bmi_test);
        Button btnDietControl = findViewById(R.id.btn_diet_control);
        Button btnBloodPressure = findViewById(R.id.btn_blood_pressure);
        Button btnBloodPressure2 = findViewById(R.id.btn_blood_pressure_2);
        Button btnExercises = findViewById(R.id.btn_exercises);
        Button btnStepCounter2 = findViewById(R.id.btn_step_counter_2);
        Button btnExercise = findViewById(R.id.Exercise_suggestion);
        Button btnSensorbpm = findViewById(R.id.Sensor_BPM);

        // Start SignupActivity when MainActivity is opened
        //startActivity(new Intent(MainActivity.this, SignupActivity.class));

        // Button click listeners to navigate to respective activities
        btnBmi.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Bmi.class)));
        btnbmitest.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, bmi_test.class)));
        btnDietControl.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Diet_controlling.class)));
        btnBloodPressure.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Blood_Pressure_Counter.class)));
        btnBloodPressure2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Test_bpfirst.class)));
        btnExercises.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Gif_Exercise.class)));
        btnStepCounter2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Step_Counter.class)));
        btnExercise.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Suggestion_exercise.class)));
        btnSensorbpm.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Sensor_BPM.class)));
    }
}