package com.example.signuploginrealtime;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Diet_controlling extends AppCompatActivity {

    private EditText editTextAge, editTextWeight, editTextFeet, editTextInches;
    private TextView textViewDietPlan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_controlling);

        editTextAge = findViewById(R.id.editTextAge);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextFeet = findViewById(R.id.editTextFeet);
        editTextInches = findViewById(R.id.editTextInches);
        Button buttonGeneratePlan = findViewById(R.id.buttonGeneratePlan);
        textViewDietPlan = findViewById(R.id.textViewDietPlan);

        buttonGeneratePlan.setOnClickListener(v -> generateDietPlan());
    }

    @SuppressLint("SetTextI18n")
    private void generateDietPlan() {
        String ageText = editTextAge.getText().toString();
        String weightText = editTextWeight.getText().toString();
        String feetText = editTextFeet.getText().toString();
        String inchesText = editTextInches.getText().toString();

        if (ageText.isEmpty() || weightText.isEmpty() || feetText.isEmpty() || inchesText.isEmpty()) {
            textViewDietPlan.setText("Please enter your age, weight, and height.");
            return;
        }

        int age = Integer.parseInt(ageText);
        double weight = Double.parseDouble(weightText);
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);

        // Convert height to centimeters
        double heightInCm = convertToCentimeters(feet, inches);

        String dietPlan = generateDietPlanBasedOnProfile(age, weight, heightInCm);
        textViewDietPlan.setText(dietPlan);
    }

    private double convertToCentimeters(int feet, int inches) {
        // 1 foot = 30.48 cm and 1 inch = 2.54 cm
        return (feet * 30.48) + (inches * 2.54);
    }

    private String generateDietPlanBasedOnProfile(int age, double weight, double height) {
        // Calculate BMI
        double bmi = calculateBMI(weight, height);

        // Generate diet plan based on BMI and age
        if (bmi < 18.5) {
            return "Your customized diet plan:\n" +
                    "- Breakfast: Avocado toast\n" +
                    "- Lunch: Quinoa salad with chickpeas\n" +
                    "- Dinner: Grilled salmon with steamed vegetables\n" +
                    "Remember to consult a nutritionist for personalized advice.";
        } else if (bmi >= 18.5 && bmi < 25) {
            if (age < 30) {
                return "Your customized diet plan:\n" +
                        "- Breakfast: Greek yogurt with berries\n" +
                        "- Lunch: Grilled chicken Caesar salad\n" +
                        "- Dinner: Stir-fried tofu with brown rice\n" +
                        "Remember to consult a nutritionist for personalized advice.";
            } else {
                return "Your customized diet plan:\n" +
                        "- Breakfast: Whole grain toast with peanut butter\n" +
                        "- Lunch: Quinoa and black bean burrito bowl\n" +
                        "- Dinner: Baked cod with roasted sweet potatoes\n" +
                        "Remember to consult a nutritionist for personalized advice.";
            }
        } else {
            return "Your customized diet plan:\n" +
                    "- Breakfast: Spinach and feta omelette\n" +
                    "- Lunch: Lentil soup with whole grain bread\n" +
                    "- Dinner: Grilled steak with roasted vegetables\n" +
                    "Remember to consult a nutritionist for personalized advice.";
        }
    }

    private double calculateBMI(double weight, double height) {
        return weight / ((height / 100) * (height / 100));
    }
}