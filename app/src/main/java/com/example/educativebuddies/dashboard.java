package com.example.educativebuddies;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class dashboard extends AppCompatActivity {

        private Button button;
        private Button button2;
        private Button button3;

        FirebaseAuth auth;
        Button b3;
        TextView textView;

        FirebaseUser user;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dashboard);
            auth = FirebaseAuth.getInstance();
            b3=findViewById(R.id.button4);
            textView=findViewById(R.id.course_name);
            user = auth.getCurrentUser();
            Log.d("DashboardActivity", "onCreate called");
            if(user==null)
            {
                Intent intent =new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                textView.setText(user.getEmail());
                //FirebaseAuth.getInstance().signOut();

            }
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("DashboardActivity", "Logout button clicked");
                    FirebaseAuth.getInstance().signOut();
                    Log.d("DashboardActivity", "signOut called");
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user == null) {
                        Log.d("DashboardActivity", "User is null after signOut");
                    } else {
                        Log.d("DashboardActivity", "User is not null after signOut");
                    }

                    Intent intent = new Intent(dashboard.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            button = findViewById(R.id.button);
            button2 = findViewById(R.id.button2);
            button3 = findViewById(R.id.button3);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(dashboard.this,MainActivity.class);
                    startActivity(intent);
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(dashboard.this,AddCourseActivity.class);
                    startActivity(intent);
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(dashboard.this,DetailsActivity.class);
                    startActivity(intent);
                }
            });
        }
    }


