package com.example.educativebuddies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.educativebuddies.MainActivity;
import com.example.educativebuddies.course;

public class AddCourseActivity extends AppCompatActivity {

    private EditText courseNameEditText;
    private EditText courseDescriptionEditText;
    private EditText courseCreditsEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        courseNameEditText = findViewById(R.id.course_name);
        courseDescriptionEditText = findViewById(R.id.course_description);
        courseCreditsEditText = findViewById(R.id.course_credits);
        addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = courseNameEditText.getText().toString().trim();
                String description = courseDescriptionEditText.getText().toString().trim();
                String creditsString = courseCreditsEditText.getText().toString().trim();

                if (name.isEmpty() || description.isEmpty() || creditsString.isEmpty()) {
                    Toast.makeText(AddCourseActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int credits = Integer.parseInt(creditsString);

                // Create a new Course object with the entered data
                course course = new course(name, description, credits);

                // Perform your desired action with the created course object (e.g., add it to a list, save it to a database, etc.)

                // Show a success message
                Toast.makeText(AddCourseActivity.this, "Course added successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddCourseActivity.this, MainActivity.class);
                intent.putExtra("courseName", name);
                intent.putExtra("courseDescription", description);
                intent.putExtra("courseCredits", creditsString);
                startActivity(intent);

                // Clear the input fields
                courseNameEditText.setText("");
                courseDescriptionEditText.setText("");
                courseCreditsEditText.setText("");
            }
        });
    }
}
