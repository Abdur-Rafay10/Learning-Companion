package com.example.educativebuddies;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    TextView titleTextView;
    TextView descriptionTextView;
    TextView creatorTextView;
    TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        creatorTextView = findViewById(R.id.creatorTextView);
        dateTextView = findViewById(R.id.dateTextView);

        // Now we can set the text of these TextViews
        titleTextView.setText("Title: Learning Companion");
        descriptionTextView.setText("Description: A platform to foster learning.");
        creatorTextView.setText("Created by: Rafay.Hassan and Asad");
        dateTextView.setText("Date: Dec 10, 2023");
    }
}
