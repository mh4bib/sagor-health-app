package com.example.signuploginrealtime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bmi extends AppCompatActivity {

    private EditText heightEditText, weightEditText;
    private TextView resultTextView;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        heightEditText = findViewById(R.id.heightEditText);
        weightEditText = findViewById(R.id.weightEditText);
        resultTextView = findViewById(R.id.resultTextView);
        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String heightInput = heightEditText.getText().toString();
        String weightInput = weightEditText.getText().toString();

        if (heightInput.isEmpty() || weightInput.isEmpty()) {
            Toast.makeText(Bmi.this, "Please enter valid height and weight", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert height to centimeters
        double heightInCm = Double.parseDouble(heightInput);

        // Convert weight to double
        double weight = Double.parseDouble(weightInput);

        // BMI calculation: weight (kg) / height (m)^2
        double heightInMeters = heightInCm / 100;
        double bmi = weight / (heightInMeters * heightInMeters);

        // Display result
        resultTextView.setText(String.format("Your BMI is: %.2f", bmi));

        // Determine BMI category and display it
        String bmiCategory = getBMICategory(bmi);
        resultTextView.append("\nCategory: " + bmiCategory);
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }
}