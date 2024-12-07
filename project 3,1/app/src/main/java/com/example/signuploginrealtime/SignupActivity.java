package com.example.signuploginrealtime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText signupName, signupEmail, signupUsername, signupPassword;
    private TextView loginRedirectText;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize UI elements
        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);

        // Set onClickListener for signupButton
        signupButton.setOnClickListener(v -> {
            String name = signupName.getText().toString().trim();
            String email = signupEmail.getText().toString().trim();
            String username = signupUsername.getText().toString().trim();
            String password = signupPassword.getText().toString().trim();

            if (validateInput(name, email, username, password)) {
                // Store data in SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", name);
                editor.putString("email", email);
                editor.putString("username", username);
                editor.putString("password", password);
                editor.apply();

                Toast.makeText(SignupActivity.this, "Data saved successfully!", Toast.LENGTH_SHORT).show();

                // Navigate to OTP verification activity
                Intent intent = new Intent(SignupActivity.this, OtpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Set onClickListener for loginRedirectText
        loginRedirectText.setOnClickListener(v -> {
            // Navigate to login activity
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    // Input validation method
    private boolean validateInput(String name, String email, String username, String password) {
        if (name.isEmpty()) {
            signupName.setError("Name is required");
            signupName.requestFocus();
            return false;
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signupEmail.setError("Valid email is required");
            signupEmail.requestFocus();
            return false;
        }
        if (username.isEmpty()) {
            signupUsername.setError("Username is required");
            signupUsername.requestFocus();
            return false;
        }
        if (password.isEmpty() || password.length() < 6) {
            signupPassword.setError("Password must be at least 6 characters");
            signupPassword.requestFocus();
            return false;
        }
        return true;
    }
}
