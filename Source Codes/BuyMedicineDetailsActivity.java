package com.example.healthcareapplication;

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

public class BuyMedicineDetailsActivity extends AppCompatActivity {
TextView tvpackagename,tvtotal;
EditText eddetail;
Button btncartmed, btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine_details);

        tvpackagename=findViewById(R.id.medicinecarttitle);
        tvtotal=findViewById(R.id.totalcost);
        btncartmed=findViewById(R.id.checkoutmedicine);
        btnback=findViewById(R.id.backarticle);
        eddetail=findViewById(R.id.mulitilinepackdetail);
         eddetail.setKeyListener(null);

         Intent intent=getIntent();
         tvpackagename.setText(intent.getStringExtra("text1"));
         eddetail.setText(intent.getStringExtra("text2"));
         tvtotal.setText(intent.getStringExtra("Total Cost: "+intent.getStringExtra("text3")+"/-"));

         btnback.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
             }
         });
        btncartmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs",MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=tvpackagename.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkCart(username,product)==1){
                    Toast.makeText(BuyMedicineDetailsActivity.this, "Product already exist in the cart", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(BuyMedicineDetailsActivity.this, "Medicine Inserted to cart", Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
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