package com.example.healthcareapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

/** @noinspection rawtypes, rawtypes */
public class DoctorDetailActivity extends AppCompatActivity {
    private final String[][] doctor_detail1= {
            {"Doctor Name: Ajit Kumar", "Hospital Address:  Subhash Nagar,Dehradun","Exp : 3 years", "Mobile No: 9412045005", "600"},
            {"Doctor Name: Sonia Gupta", "Hospital Address: Clement Town, Dehradun","Exp : 5 years", "Mobile No: 94120448405", "1000"},
            {"Doctor Name:Deepak Gulati","Hospital Address: Nanda Ki Chowki, Dehradun","Exp : 8 years", "Mobile No: 9412065005", "700"},
            {"Doctor Name: Sagar Negi", "Hospital Address: Prem Nagar,Dehradun","Exp : 1 years", "Mobile No: 7412045005", "500"},
            {"Doctor Name: Hardik Saxena","Hospital Address: Harrawala Road, Dehradun","Exp : 12 years", "Mobile No: 9412048009", "1200"}

    };
    private final String[][] doctor_detail2= {
            {"Doctor Name: Ajit Kumar", "Hospital Address:  Subhash Nagar,Dehradun","Exp : 3 years", "Mobile No: 9412045005", "600"},
            {"Doctor Name: Sonia Gupta", "Hospital Address: Clement Town, Dehradun","Exp : 5 years", "Mobile No: 94120448405", "1000"},
            {"Doctor Name:Deepak Gulati","Hospital Address: Nanda Ki Chowki, Dehradun","Exp : 8 years", "Mobile No: 9412065005", "700"},
            {"Doctor Name: Sagar Negi", "Hospital Address: Prem Nagar,Dehradun","Exp : 1 years", "Mobile No: 7412045005", "500"},
            {"Doctor Name: Hardik Saxena","Hospital Address: Harrawala Road, Dehradun","Exp : 12 years", "Mobile No: 9412048009", "1200"}
    };
    private final String[][] doctor_detail3= {
            {"Doctor Name: Ajit Kumar", "Hospital Address:  Subhash Nagar,Dehradun","Exp : 3 years", "Mobile No: 9412045005", "600"},
            {"Doctor Name: Sonia Gupta", "Hospital Address: Clement Town, Dehradun","Exp : 5 years", "Mobile No: 94120448405", "1000"},
            {"Doctor Name:Deepak Gulati","Hospital Address: Nanda Ki Chowki, Dehradun","Exp : 8 years", "Mobile No: 9412065005", "700"},
            {"Doctor Name: Sagar Negi", "Hospital Address: Prem Nagar,Dehradun","Exp : 1 years", "Mobile No: 7412045005", "500"},
            {"Doctor Name: Hardik Saxena","Hospital Address: Harrawala Road, Dehradun","Exp : 12 years", "Mobile No: 9412048009", "1200"}
    };
    private final String[][] doctor_detail4= {
            {"Doctor Name: Ajit Kumar", "Hospital Address:  Subhash Nagar,Dehradun","Exp : 3 years", "Mobile No: 9412045005", "600"},
            {"Doctor Name: Sonia Gupta", "Hospital Address: Clement Town, Dehradun","Exp : 5 years", "Mobile No: 94120448405", "1000"},
            {"Doctor Name:Deepak Gulati","Hospital Address: Nanda Ki Chowki, Dehradun","Exp : 8 years", "Mobile No: 9412065005", "700"},
            {"Doctor Name: Sagar Negi", "Hospital Address: Prem Nagar,Dehradun","Exp : 1 years", "Mobile No: 7412045005", "500"},
            {"Doctor Name: Hardik Saxena","Hospital Address: Harrawala Road, Dehradun","Exp : 12 years", "Mobile No: 9412048009", "1200"}
    };
    private final String[][] doctor_detail5= {
            {"Doctor Name: Ajit Kumar", "Hospital Address:  Subhash Nagar,Dehradun","Exp : 3 years", "Mobile No: 9412045005", "600"},
            {"Doctor Name: Sonia Gupta", "Hospital Address: Clement Town, Dehradun","Exp : 5 years", "Mobile No: 94120448405", "1000"},
            {"Doctor Name:Deepak Gulati","Hospital Address: Nanda Ki Chowki, Dehradun","Exp : 8 years", "Mobile No: 9412065005", "700"},
            {"Doctor Name: Sagar Negi", "Hospital Address: Prem Nagar,Dehradun","Exp : 1 years", "Mobile No: 7412045005", "500"},
            {"Doctor Name: Hardik Saxena","Hospital Address: Harrawala Road, Dehradun","Exp : 12 years", "Mobile No: 9412048009", "1200"}
    };

TextView tv;
Button btn;
String[][] doctor_details={};
ArrayList list;
SimpleAdapter sa;
    private HashMap<String, String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_detail);
        tv=findViewById(R.id.articletitle);
        Intent i=getIntent();
        String title=i.getStringExtra("title");
        tv.setText(title);
        if((title != null ? title.compareTo("Family Doctors") : 0) ==0)
            doctor_details=doctor_detail1;
       else if(title.compareTo("Dietician")==0)
            doctor_details=doctor_detail2;
        else if(title.compareTo("Dentist")==0)
            doctor_details=doctor_detail3;
        else if(title.compareTo("Surgeons")==0)
            doctor_details=doctor_detail4;
        else
            doctor_details=doctor_detail5;

        btn=findViewById(R.id.backarticle);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailActivity.this,FindDoctor.class));
            }
        });
        list= new ArrayList();
        for(int it=0;it<doctor_details.length;it++){
            
            item=new HashMap<String,String>();
            item.put("line1",doctor_details[it][0]);
            item.put("line2",doctor_details[it][1]);
            item.put("line3",doctor_details[it][2]);
            item.put("line4",doctor_details[it][3]);
            item.put("line5","Cons Fees:"+doctor_details[it][4]+"/-");
            list.add(item);

        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line 1","line2","line3","line","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        ListView lst=findViewById(R.id.articledetail);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int it, long id) {
                Intent i=new Intent(DoctorDetailActivity.this, BookAppointmentActivity.class);
                i.putExtra("text1",title);
                i.putExtra("text2",doctor_details[it][0]);
                i.putExtra("text3",doctor_details[it][1]);
                i.putExtra("text4",doctor_details[it][3]);
                i.putExtra("text5",doctor_details[it][4]);
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