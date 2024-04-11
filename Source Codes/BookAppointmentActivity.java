package com.example.healthcareapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4;
TextView tv;
private DatePickerDialog datepicker;
private TimePickerDialog timepicker;
    Button buttondate,buttontime,back,bookappoint;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_appointment);
        tv = findViewById(R.id.textViewapptitle);
        ed1 = findViewById(R.id.fullname);
        ed2 = findViewById(R.id.addr);
        ed3 = findViewById(R.id.pincode);
        ed4 = findViewById(R.id.mobile);
        buttondate=findViewById(R.id.buttondate);
        buttontime=findViewById(R.id.buttontime);
        bookappoint=findViewById(R.id.bookmedicine);
        back=findViewById(R.id.back);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String tit = it.getStringExtra("text1");
        String name = it.getStringExtra("text2");
        String addr = it.getStringExtra("text3");
        String cont = it.getStringExtra("text4");
        String fee = it.getStringExtra("text5");

        tv.setText(tit);
        ed1.setText(name);
        ed2.setText(addr);
        ed3.setText(cont);
        ed4.setText("Consultation Fee: " + fee + "/-");

        initDatePicker();
        buttondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker.show();
            }
        });
        initTimePicker();
        buttontime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timepicker.show();
            }
        });
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(BookAppointmentActivity.this, DoctorDetailActivity.class));
           }
       });
       bookappoint.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Database db=new Database(getApplicationContext(),"healthcare",null,1);
               SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs",MODE_PRIVATE);
               String username=sharedPreferences.getString("username","").toString();
               if(db.checkAppointmentExists(username,tit+" => "+name,addr,cont,buttondate.getText().toString(),buttontime.getText().toString())==1){
                   Toast.makeText(BookAppointmentActivity.this, "Appointment already booked", Toast.LENGTH_SHORT).show();
               }
               else{
                   db.addOrder(username,tit+" => "+name,addr,cont,0,buttondate.getText().toString(),buttontime.getText().toString(),Float.parseFloat(fee),"appointment");
                   Toast.makeText(BookAppointmentActivity.this, "Your appointment is made successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));
               }
           }
       });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textViewexistinguser), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

        private void initDatePicker () {
            DatePickerDialog.OnDateSetListener datesetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    dayOfMonth = dayOfMonth + 1;
                    buttondate.setText(dayOfMonth+"/"+month+"/"+year);
                }
            };
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int style = AlertDialog.THEME_HOLO_DARK;
            datepicker = new DatePickerDialog(this, style, datesetListener, year, month, day);
            datepicker.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
        }

        private void initTimePicker () {
            TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                   buttontime.setText(hourOfDay+":"+minute);
                }
            };
            Calendar cal = Calendar.getInstance();
            int hr = cal.get(Calendar.HOUR);
            int min = cal.get(Calendar.MINUTE);
            int style = AlertDialog.THEME_HOLO_DARK;
            timepicker = new TimePickerDialog(this, style, timeSetListener, hr, min, true);

        }


}