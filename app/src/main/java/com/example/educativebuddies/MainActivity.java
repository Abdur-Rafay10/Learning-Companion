package com.example.educativebuddies;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.educativebuddies.CourseAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText searchBox;
    private CourseAdapter adapter;
    private ArrayList<course> courses;
    Button b1;
    private static final int REQUEST_CODE_ADD_COURSE = 1;

    String n;
    String d;
    String c;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course);
        try {
        n=getIntent().getStringExtra("courseName");
        d=getIntent().getStringExtra("courseDescription");
        c=getIntent().getStringExtra("courseCredits");
        }
        catch(Exception e)
        {

        }
        recyclerView = findViewById(R.id.courseListView);
        searchBox = findViewById(R.id.searchBox);

        courses = new ArrayList<>();
        if(n!=null && d!=null && c!=null)
        {
            courses.add(new course(n,d,Integer.parseInt(c)));

        }
        courses.add(new course("Programing", "c or c++", 3));
        courses.add(new course("Math", "ALgebra", 3));
        courses.add(new course("English", "Lets learn it", 3));
        courses.add(new course("Urdu", "Ao seekhay", 3));
        courses.add(new course("Physics", "F=ma Einstein BABA", 3));
        courses.add(new course("Chemistry", "Na + Cl = NaCl", 3));
        // Add more courses as needed


        adapter = new CourseAdapter(this, courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_COURSE && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("course")) {
                course newCourse = (course) data.getSerializableExtra("course");
                if (newCourse != null) {
                    courses.add(newCourse);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
