package com.example.healthcareapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class LabTestDetailActivity extends AppCompatActivity {
TextView packagename,totalcost;
EditText ed;
Button back,cart;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test_detail);
        packagename=findViewById(R.id.medicinecarttitle);
        totalcost=findViewById(R.id.totalcost);
        ed=findViewById(R.id.mulitilinepackdetail);
        back=findViewById(R.id.backarticle);
        cart=findViewById(R.id.checkoutmedicine);

        ed.setKeyListener(null);

        Intent intent=getIntent();
        packagename.setText(intent.getStringExtra("text1"));
         ed.setText(intent.getStringExtra("text2"));
         totalcost.setText("Total Cost :"+intent.getStringExtra("text3")+"/-");

         back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(LabTestDetailActivity.this, LabTestActivity.class));
             }
         });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username= sharedPreferences.getString("username","").toString();
                String product=packagename.getText().toString();
                float price=Float.parseFloat((Objects.requireNonNull(intent.getStringExtra("text3"))));
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(db.checkCart(username,product)==1){
                    Toast.makeText(LabTestDetailActivity.this, "Product Already Added", Toast.LENGTH_SHORT).show();

                }
                else{
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(LabTestDetailActivity.this, "Record inserted in the cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailActivity.this, LabTestActivity.class));

                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textViewexistinguser), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}