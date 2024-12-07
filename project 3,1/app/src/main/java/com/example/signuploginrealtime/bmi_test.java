package com.example.signuploginrealtime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class bmi_test extends AppCompatActivity {

    EditText inputFeet, inputInches, inputWeight;
    Button btnCalculate;
    TextView resultBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_test);

        // Initialize UI elements
        inputFeet = findViewById(R.id.inputFeet);
        inputInches = findViewById(R.id.inputInches);
        inputWeight = findViewById(R.id.inputWeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        resultBMI = findViewById(R.id.resultBMI);

        // Set button click listener
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        // Get the input values
        String feetStr = inputFeet.getText().toString();
        String inchesStr = inputInches.getText().toString();
        String weightStr = inputWeight.getText().toString();

        // Check if inputs are not empty
        if (feetStr.isEmpty() || inchesStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert input values to numbers
        int feet = Integer.parseInt(feetStr);
        int inches = Integer.parseInt(inchesStr);
        double weightKg = Double.parseDouble(weightStr);

        // Convert height to meters
        double heightMeters = ((feet * 12) + inches) * 0.0254;

        // Calculate BMI
        double bmi = weightKg / (heightMeters * heightMeters);

        // Display the BMI and message based on BMI range
        String message;
        if (bmi < 18.5) {
            message = "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            message = "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            message = "Overweight";
        } else {
            message = "Obese";
        }

        // Show BMI and corresponding message
        String result = String.format("Your BMI is: %.2f\nYou are %s", bmi, message);
        resultBMI.setText(result);
    }
}