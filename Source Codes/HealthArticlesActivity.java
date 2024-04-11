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

public class HealthArticlesActivity extends AppCompatActivity {
private String[][] health={
        {"Walking Daily","","","","Click for more Details"},
        {"Home care for COVID-19 patients","","","","Click for more Details"},
        {"Stop Smoking","","","","Click for more Details"},
        {"Menstrual Cramps","","","","Click for more Details"},
        {"Healthy Gut","","","","Click for more Details"}
    };
private int[] images={
        R.drawable.health1,
        R.drawable.health2,
        R.drawable.health3,
        R.drawable.health4,
        R.drawable.health5

};
HashMap<String,String>item;
ArrayList list;

SimpleAdapter sa;
    ListView lst;
Button btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_articles);
        lst=findViewById(R.id.articledetail);
        btnback=findViewById(R.id.backarticle);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesActivity.this, HomeActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i< health.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",health[i][0]);
            item.put("line2",health[i][1]);
            item.put("line3",health[i][2]);
            item.put("line4",health[i][3]);
            item.put("line5",health[i][4]);
            list.add(item);
        }

        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );

        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it=new Intent(HealthArticlesActivity.this, HealtArticleDetail.class);
                it.putExtra("text1",health[i][0]);
                it.putExtra("text2",images[i]);
                startActivity(it);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textViewexistinguser), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}