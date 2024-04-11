package com.example.healthcareapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPreferences=getSharedPreferences("shared pref", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();
        Toast.makeText(this, "Welcome "+username, Toast.LENGTH_SHORT).show();
        CardView exit=findViewById(R.id.Logout);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));

            }
        });
           CardView findDoctor=findViewById(R.id.findDoctor);
           findDoctor.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(HomeActivity.this, FindDoctor.class));
               }
           });
           CardView labtest=findViewById(R.id.cardLabTest);
           labtest.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(HomeActivity.this,LabTestActivity.class));
               }
           });
           CardView orderdetail=findViewById(R.id.Order);
           orderdetail.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(HomeActivity.this,OrderDetailActivity.class));
               }
           });
           CardView buymedicine=findViewById(R.id.cardMedicine);
           buymedicine.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(HomeActivity.this,BuyMedicineActivity.class));
               }
           });
          CardView medarticles=findViewById(R.id.HealthArticles);
        medarticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,HealthArticlesActivity.class));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textViewexistinguser), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}