package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RunTracer extends AppCompatActivity {
    private TextView Timer, Distance, Speed;
    private Button Start, Stop, Reset;
    private boolean isRunning;
    private long startTime, elapsedTime;
    private float distance, speed;

    private Handler handler;
    private Runnable timerRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_tracer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Timer = findViewById(R.id.Timer);
        Distance = findViewById(R.id.Distance);
        Speed = findViewById(R.id.Speed);
        Start = findViewById(R.id.Start);
        Stop = findViewById(R.id.Stop);
        Reset = findViewById(R.id.Reset);

        handler = new Handler();

        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    private void startTimer() {
        if (!isRunning) {
            isRunning = true;
            startTime = System.currentTimeMillis();

            timerRunnable = new Runnable() {
                @Override
                public void run() {
                    elapsedTime = System.currentTimeMillis() - startTime;
                    updateTimer(elapsedTime);
                    handler.postDelayed(this, 1000);
                }
            };

            handler.postDelayed(timerRunnable, 0);

            Start.setEnabled(false);
            Stop.setEnabled(true);
            Reset.setEnabled(false);
        }
    }

    private void stopTimer() {
        if (isRunning) {
            isRunning = false;
            handler.removeCallbacks(timerRunnable);

            Start.setEnabled(true);
            Stop.setEnabled(false);
            Reset.setEnabled(true);
        }
    }

    private void resetTimer() {
        stopTimer();
        elapsedTime = 0;
        distance = 0;
        speed = 0;

        Timer.setText("00:00:00");
        Distance.setText("Distance: 0 meters");
        Speed.setText("Speed: 0 m/s");
    }

    private void updateTimer(long time) {
        int seconds = (int) (time / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        int hours = minutes / 60;
        minutes = minutes % 60;

        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        Timer.setText(timeString);

        updateDistance();
        updateSpeed();
    }

    private void updateDistance() {
        // Here, you can implement the logic to calculate the distance covered by the user
        // For simplicity, let's assume distance increases by 1 meter every second
        distance = elapsedTime / 1000; // 1 meter per second
        Distance.setText("Distance: " + distance + " meters");
    }

    private void updateSpeed() {
        // Here, you can implement the logic to calculate the user's speed
        // For simplicity, let's assume speed is distance divided by time in seconds
        speed = distance / (elapsedTime / 1000);
        Speed.setText("Speed: " + speed + " m/s");
    }
}
