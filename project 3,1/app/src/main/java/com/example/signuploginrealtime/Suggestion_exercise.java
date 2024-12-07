package com.example.signuploginrealtime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Suggestion_exercise extends AppCompatActivity {

    private TextView exerciseDescription;

    // Array of exercise names and their descriptions
    private final String[] exerciseDescriptions = {
            "Mountain Climber: Great for cardio and full-body strengthening.",
            "Sit Up 01: Ideal for strengthening the abdominal muscles.",
            "Basic Crunches: Effective for targeting the abdominal area.",
            "Bicycle Crunches: Targets the core and enhances endurance.",
            "Leg Raise: Excellent for strengthening the lower abdominal muscles.",
            "Alternative Heel Touch: Good for engaging the side of the abdominal muscles.",
            "Leg Up Crunches: Great for working the lower abs and toning your core.",
            "Sit Up: A classic exercise for toning the core and building endurance.",
            "Alternative V Ups: Excellent for overall core strengthening.",
            "Russian Twists: Great for building obliques and improving rotational movement.",
            "Flutter Kicks: Helps build core strength and leg endurance.",
            "Plank: A full-body exercise that targets core stability.",
            "Side Plank: Focuses on the obliques and overall core stability.",
            "Lunges: Effective for building leg strength and stability.",
            "Push Ups: A classic upper body exercise that strengthens arms, chest, and shoulders."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_exercise);

        // Initialize the TextView for displaying the exercise description
        exerciseDescription = findViewById(R.id.exercise_description);

        // Initialize buttons and set onClickListeners for each exercise
        Button mountainClimberButton = findViewById(R.id.mountain_climber_button);
        Button sitUpButton = findViewById(R.id.sit_up_button);
        Button basicCrunchesButton = findViewById(R.id.basic_crunches_button);
        Button bicycleCrunchesButton = findViewById(R.id.bicycle_crunches_button);
        Button legRaiseButton = findViewById(R.id.leg_raise_button);
        Button heelTouchButton = findViewById(R.id.heel_touch_button);
        Button legUpCrunchesButton = findViewById(R.id.leg_up_crunches_button);
        Button sitUp2Button = findViewById(R.id.sit_up2_button);
        Button vUpsButton = findViewById(R.id.v_ups_button);
        Button russianTwistsButton = findViewById(R.id.russian_twists_button);
        Button flutterKicksButton = findViewById(R.id.flutter_kicks_button);
        Button plankButton = findViewById(R.id.plank_button);
        Button sidePlankButton = findViewById(R.id.side_plank_button);
        Button lungesButton = findViewById(R.id.lunges_button);
        Button pushUpsButton = findViewById(R.id.push_ups_button);

        // Set the onClickListeners for the buttons
        mountainClimberButton.setOnClickListener(v -> showExerciseDescription(0));
        sitUpButton.setOnClickListener(v -> showExerciseDescription(1));
        basicCrunchesButton.setOnClickListener(v -> showExerciseDescription(2));
        bicycleCrunchesButton.setOnClickListener(v -> showExerciseDescription(3));
        legRaiseButton.setOnClickListener(v -> showExerciseDescription(4));
        heelTouchButton.setOnClickListener(v -> showExerciseDescription(5));
        legUpCrunchesButton.setOnClickListener(v -> showExerciseDescription(6));
        sitUp2Button.setOnClickListener(v -> showExerciseDescription(7));
        vUpsButton.setOnClickListener(v -> showExerciseDescription(8));
        russianTwistsButton.setOnClickListener(v -> showExerciseDescription(9));
        flutterKicksButton.setOnClickListener(v -> showExerciseDescription(10));
        plankButton.setOnClickListener(v -> showExerciseDescription(11));
        sidePlankButton.setOnClickListener(v -> showExerciseDescription(12));
        lungesButton.setOnClickListener(v -> showExerciseDescription(13));
        pushUpsButton.setOnClickListener(v -> showExerciseDescription(14));
    }

    // Method to display exercise description
    private void showExerciseDescription(int index) {
        exerciseDescription.setText(exerciseDescriptions[index]);
    }
}