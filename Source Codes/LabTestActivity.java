package com.example.healthcareapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {
private final String[][] packages={
        {"Package 1: Full Body Checkup","","","","1249"},
        {"Package 2: Blood Glucose Testing/ Fasting Testing","","","","1299"},
        {"Package 3: Covid-19 Anti-Body IG-16","","","","899"},
        {"Package 4: Thyroid Check","","","","999"},
        {"Package 5: Immunity Check","","","","799"}
};
private final String[] package_details={

        "Complete Hemogram\n"+
        "Diabetes\n"+
        "Electrolytes \n"+
        "Iron Deficiency \n"+
        "Lipid,Renal,Thyroid \n"+
        "Vitamin B12 & D\n",
    "Glucose Level before  and after fasting\n"+"Vitamin B check, Heamoglobin level and Blood count\n" ,
                " Covid-19 Check-up  Vitamin D3 25(OH) Total and Vitamin B12\n" ,
        "Thyroid Profile- T3 T4 TSH/n"+ "Vitamin D3 25 OH Total\n" ,
                "Vital Organs Testing -Thyroid, Heart, Liver, Kidney"
};
HashMap<String,String>item;
ArrayList list;
SimpleAdapter sa;
Button btnGotocart,btnback;
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test);
        btnback=findViewById(R.id.backarticle);
        btnGotocart=findViewById(R.id.checkoutmedicine);
        listView=findViewById(R.id.articledetail);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Cost is :"+packages[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int it, long id) {
                Intent i=new Intent(LabTestActivity.this,LabTestDetailActivity.class);
                i.putExtra("text1",packages[it][0]);
                i.putExtra("text2",package_details[it]);
                i.putExtra("text3",packages[it][4]);
                startActivity(i);
            }
        });
        btnGotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this,CartLabActivity.class));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textViewexistinguser), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}