package com.example.healthcareapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FindDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_doctor);
        CardView exit=findViewById(R.id.cardBackButton);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctor.this,HomeActivity.class));
            }
        });
        CardView familydoc=findViewById(R.id.cardFDFamilyPhysician);
        familydoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FindDoctor.this,DoctorDetailActivity.class);
                i.putExtra("title","Family Doctors");
                startActivity(i);
            }
        });
        CardView diet=findViewById(R.id.cardFDDietician);
        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FindDoctor.this,DoctorDetailActivity.class);
                i.putExtra("title","Dietician");
                startActivity(i);
            }
        });
        CardView dent=findViewById(R.id.cardFDDentist);
        dent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FindDoctor.this,DoctorDetailActivity.class);
                i.putExtra("title","Dentist");
                startActivity(i);
            }
        });
        CardView surgeon=findViewById(R.id.cardFDSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FindDoctor.this,DoctorDetailActivity.class);
                i.putExtra("title","Surgeons");
                startActivity(i);
            }
        });
        CardView cardio=findViewById(R.id.cardFDCardiologist);
        cardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FindDoctor.this,DoctorDetailActivity.class);
                i.putExtra("title","Cardiologists");
                startActivity(i);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textViewexistinguser), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}