package com.example.educativebuddies;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    EditText e , p ;
    Button b;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
 @Override
 protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.sign_up);
     mAuth=FirebaseAuth.getInstance();
     e=findViewById(R.id.Phone);
     p=findViewById(R.id.Password);
     b=findViewById(R.id.Signup);
     progressBar=findViewById(R.id.progress_bar1);
     b.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             progressBar.setVisibility(View.VISIBLE);
             String email,password;
             email=String.valueOf(e .getText());
             password=String.valueOf(p.getText().toString());

             if(TextUtils.isEmpty(email))
             {
                 Toast.makeText(SignUpActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                 return;
             }
             if(TextUtils.isEmpty(password))
             {
                 Toast.makeText(SignUpActivity.this, "Enter passcode", Toast.LENGTH_SHORT).show();
                 return;
             }
             mAuth.createUserWithEmailAndPassword(email, password)
                     .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             progressBar.setVisibility(View.GONE);
                             if (task.isSuccessful()) {

                                 Toast.makeText(SignUpActivity.this, "Account created.",
                                         Toast.LENGTH_SHORT).show();
                                 Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                                 startActivity(intent);
                                 finish();


                             } else {
                                 // If sign in fails, display a message to the user.
                                 Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                         Toast.LENGTH_SHORT).show();

                             }
                         }
                     });
         }
     });
 }

}
