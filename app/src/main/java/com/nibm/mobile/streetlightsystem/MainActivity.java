package com.nibm.mobile.streetlightsystem;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnLetMeIn;
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize Firebase
        FirebaseApp.initializeApp(this);

        email=findViewById(R.id.txtEditEmail);
        password=findViewById(R.id.txtEditPassword);
        btnLetMeIn=findViewById(R.id.btnLetMeIn);

        btnLetMeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.length() == 0 || password.length() == 0) {
                    email.setError("This field is required");
                    password.setError("This field is required");
                }
                else if (password.length() < 8) {
                    password.setError("Password must be minimum 8 characters");
                }
                else {
                    // If the input is valid, navigate to the StreetLightActivity
                    Intent intent = new Intent(MainActivity.this, BulbControl.class);
                    startActivity(intent);
                }
            }
        });
        }
    }

