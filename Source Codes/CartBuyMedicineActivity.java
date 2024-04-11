package com.example.healthcareapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuyMedicineActivity extends AppCompatActivity {
HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    TextView tvtotal;
    private DatePickerDialog datepicker;
    private TimePickerDialog timepicker;
    private Button buttondate,buttontime,btncheckout,btnback;
    private String[][] packages={};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_buy_medicine);
        buttondate=findViewById(R.id.buttondate);
        btncheckout=findViewById(R.id.checkoutmedicine);
        btnback=findViewById(R.id.backarticle);
        tvtotal=findViewById(R.id.totalmedcost);
        lst=findViewById(R.id.articledetail);

        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs",MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();

        Database db=new Database(getApplicationContext(),"healthcare",null,1);

        float totalamount=0;
        ArrayList dbData=db.getCartData(username,"medicine");
        Toast.makeText(this, ""+dbData, Toast.LENGTH_SHORT).show();

        packages=new String[dbData.size()][];
        for(int i=0;i<packages.length;i++) {
            packages[i] = new String[5];
        }
        for(int i=0;i<dbData.size();i++){
            String arrData=dbData.get(i).toString();
            String[] strData=arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strData[0];
            packages[i][4]="Cost : "+strData[1]+"/-";
            totalamount=totalamount+Float.parseFloat(strData[1]);
        }
        tvtotal.setText("Total Cost :"+totalamount);

        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5",packages[i][4]);
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        lst=findViewById(R.id.articledetail);
        lst.setAdapter(sa);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartBuyMedicineActivity.this, BuyMedicineActivity.class));
            }
        });
        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CartBuyMedicineActivity.this, BuyMedicineBookActivity.class);
                it.putExtra("price",tvtotal.getText());
                it.putExtra("date",buttondate.getText());
                startActivity(it);
            }
        });
        initDatePicker();
        buttondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker.show();
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
}