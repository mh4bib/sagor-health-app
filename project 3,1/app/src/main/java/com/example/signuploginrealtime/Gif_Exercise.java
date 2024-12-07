package com.example.signuploginrealtime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Gif_Exercise extends AppCompatActivity {

    private ImageView gifImageView;
    private TextView exerciseNameTextView;
    private TextView timerTextView;
    private Button previousButton;
    private Button nextButton;
    private Button startButton;
    private Button stopButton;
    private int currentExerciseIndex = 0;

    private final int[] exerciseImages = {
            R.drawable.exersice_1, R.drawable.exersice_2, R.drawable.exersice_3,
            R.drawable.exersice_4, R.drawable.exersice_5, R.drawable.exersice_6,
            R.drawable.exersice_7, R.drawable.exersice_8, R.drawable.exersice_9,
            R.drawable.exersice_10, R.drawable.exersice_11, R.drawable.exersice_12,
            R.drawable.exersice_13, R.drawable.exersice_14, R.drawable.exersice_15
    };

    private final String[] exerciseNames = {
            "Mountain Climber (2 min, 2x)", "Sit Up 01 (2 min, 2x)", "Basic Crunches (2 min, 2x)",
            "Bicycle Crunches (2 min, 2x)", "Leg Raise (2 min, 2x)", "Alternative Heel Touch (2 min, 2x)",
            "Leg Up Crunches (2 min, 2x)", "Sit Up (2 min, 2x)", "Alternative V Ups (2 min, 2x)",
            "Russian Twists (1.5 min, 2x)", "Flutter Kicks (1.5 min, 2x)", "Plank (1 min, 3x)",
            "Side Plank (1 min, 2x each side)", "Lunges (2 min, 2x)", "Push Ups (2 min, 2x)"
    };

    private final long[] exerciseDurations = {
            2 * 60 * 1000, 2 * 60 * 1000, 2 * 60 * 1000,
            2 * 60 * 1000, 2 * 60 * 1000, 2 * 60 * 1000,
            2 * 60 * 1000, 2 * 60 * 1000, 2 * 60 * 1000,
            (long)(1.5 * 60 * 1000), (long)(1.5 * 60 * 1000), 1 * 60 * 1000,
            1 * 60 * 1000, 2 * 60 * 1000, 2 * 60 * 1000
    };

    private CountDownTimer exerciseTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_exercise);

        gifImageView = findViewById(R.id.gifImageView);
        previousButton = findViewById(R.id.previous_button);
        nextButton = findViewById(R.id.next_button);
        exerciseNameTextView = findViewById(R.id.exercise_name);
        timerTextView = findViewById(R.id.timer_text);
        startButton = findViewById(R.id.start_button);
        stopButton = findViewById(R.id.stop_button);

        updateExercise();

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentExerciseIndex > 0) {
                    currentExerciseIndex--;
                    updateExercise();
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentExerciseIndex < exerciseImages.length - 1) {
                    currentExerciseIndex++;
                    updateExercise();
                }
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });
    }

    private void updateExercise() {
        if (exerciseTimer != null) {
            exerciseTimer.cancel();
        }

        gifImageView.setImageResource(exerciseImages[currentExerciseIndex]);
        exerciseNameTextView.setText(exerciseNames[currentExerciseIndex]);
        timerTextView.setText(formatTime(exerciseDurations[currentExerciseIndex])); // Initial timer display
    }

    private void startTimer() {
        exerciseTimer = new CountDownTimer(exerciseDurations[currentExerciseIndex], 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(formatTime(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0:00");
            }
        }.start();
    }

    private void stopTimer() {
        if (exerciseTimer != null) {
            exerciseTimer.cancel();
            timerTextView.setText(formatTime(exerciseDurations[currentExerciseIndex]));
        }
    }

    private String formatTime(long millis) {
        long minutes = millis / 1000 / 60;
        long seconds = (millis / 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);

    }
}