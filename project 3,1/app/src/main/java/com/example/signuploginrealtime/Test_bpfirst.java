package com.example.signuploginrealtime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Test_bpfirst extends AppCompatActivity {

    private EditText bpmInput;
    private TextView systolicOutput, diastolicOutput;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bpfirst);

        bpmInput = findViewById(R.id.bpm_input);
        systolicOutput = findViewById(R.id.systolic_output);
        diastolicOutput = findViewById(R.id.diastolic_output);
        calculateButton = findViewById(R.id.calculate_button);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bpmText = bpmInput.getText().toString();
                if (!bpmText.isEmpty()) {
                    int bpm = Integer.parseInt(bpmText);
                    if (bpm > 0) {
                        calculateBloodPressure(bpm);
                    } else {
                        Toast.makeText(Test_bpfirst .this, "Please enter a valid BPM", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Test_bpfirst .this, "BPM cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void calculateBloodPressure(int bpm) {
        // Arbitrary formula to estimate blood pressure based on BPM.
        // For demo purposes only, not medically accurate.
        int systolic = 90 + (bpm / 2);  // Simple formula to demonstrate
        int diastolic = 60 + (bpm / 3); // Simple formula to demonstrate

        // Display the results
        systolicOutput.setText("Systolic: " + systolic + " mmHg");
        diastolicOutput.setText("Diastolic: " + diastolic + " mmHg");

        systolicOutput.setVisibility(View.VISIBLE);
        diastolicOutput.setVisibility(View.VISIBLE);
    }
}