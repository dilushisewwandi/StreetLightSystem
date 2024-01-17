package com.nibm.mobile.streetlightsystem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BulbControl extends AppCompatActivity{
    private DatabaseReference lightingModeRef;
    private DatabaseReference motionControlRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.strret_light_system);

        // Initialize Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        lightingModeRef = firebaseDatabase.getReference("automatic_lighting");
        motionControlRef = firebaseDatabase.getReference("motion_control");

        // Initialize UI elements
        Switch automaticLightingSwitch = findViewById(R.id.btnLED);
        Button motionControlButton1 = findViewById(R.id.btnMotion1);
        Button motionControlButton2 = findViewById(R.id.btnMotion2);
        Button motionControlButton3 = findViewById(R.id.btnMotion3);

        // Listen for changes in automatic lighting mode
        lightingModeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean automaticLighting = dataSnapshot.getValue(Boolean.class);
                automaticLightingSwitch.setChecked(automaticLighting != null && automaticLighting);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });

        // Set automatic lighting mode when the switch is toggled
        automaticLightingSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            lightingModeRef.setValue(isChecked);
        });

        // Control motion-activated lights
        motionControlButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Control the first motion-activated light
                motionControlRef.child("light1").setValue(true);
            }
        });

        motionControlButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Control the second motion-activated light
                motionControlRef.child("light2").setValue(true);
            }
        });

        motionControlButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Control the third motion-activated light
                motionControlRef.child("light3").setValue(true);
            }
        });
    }
}


