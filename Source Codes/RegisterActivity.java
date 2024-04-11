package com.example.healthcareapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class RegisterActivity extends AppCompatActivity {
EditText edusername,edmail,edpassword,edconfpassword;
Button btn;
TextView tv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        edusername=findViewById(R.id.fullname);
        edmail=findViewById(R.id.addr);
        edpassword=findViewById(R.id.pincode);
        edconfpassword=findViewById(R.id.mobile);
        btn=findViewById(R.id.bookmedicine);
        tv=findViewById(R.id.textView2);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edusername.getText().toString();
                String email=edmail.getText().toString();
                String password=edpassword.getText().toString();
                String confpass=edconfpassword.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(username.length()==0 || password.length()==0 || email.length()==0 || confpass.length()==0){
                    Toast.makeText(RegisterActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                    Log.d("Registration","Registration Unsuccessful");
                }
                else{
                    if(password.compareTo(confpass)==0){
                        if(is_Valid_Password(password)){
                            db.register(username,email,password);
                            Toast.makeText(RegisterActivity.this, "Registration Successful !! ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "password must have at least eight characters .\n"+ "password consists of only letters and digits.\n" +
                                    " password must contain at least two digits.", Toast.LENGTH_SHORT).show();
                        }
                    }
//                else{
//                    Toast.makeText(RegisterActivity.this, "Registration Successful !!", Toast.LENGTH_SHORT).show();
//            }
                }
            }

        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textViewexistinguser), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    public static boolean is_Valid_Password(String password) {

        if (password.length() < 8) return false;

        int charCount = 0;
        int numCount = 0;
        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            if (is_Numeric(ch)) numCount++;
            else if (is_Letter(ch)) charCount++;
            else return false;
        }


        return (charCount >= 2 && numCount >= 2);
    }

    public static boolean is_Letter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }


    public static boolean is_Numeric(char ch) {

        return (ch >= '0' && ch <= '9');
    }
}