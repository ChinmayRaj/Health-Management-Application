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

public class BuyMedicineActivity extends AppCompatActivity {
private String[][] packages={
        {"Paracetamol","","","","50"},
        {"Ibuprofen","","","","400"},
        {" Chlorphenamine (Antihistamine)","","","","650"},
        {"Hydrocortisone Cream or Ointment","","","","25"},
        {"Pepto-Bismol","","","","170"},
        {"Zantac","","","","450"},
        {"Amphotericin B*","","","","320"},
        {"Diclofenac","","","","120"},
        {"Dettol Antiseptic Cream","","","","100"},
        {"OraSore Gel","","","","245"}

};
private String[] packages_details={
    "Paracetamol is a common pain killer.\n"+" You can take them for aches and pains. \n"+"It is also commonly used for reducing high temperature (fever).",
        "Ibuprofen is a non-steroidal anti-inflammatory drug (NSAID)\n"+" It reduces inflammation, hence helps to decrease swelling, pain, or fever\n"+
                "Ibuprofen is used to relieve pain from \n"+"various conditions such as headache, dental pain,"+" period pain, muscle aches, or arthritis.",
        "Antihistamines are medicines often used to relieve symptoms of allergies,\n"+" such as hay fever, hives, conjunctivitis and reactions to insect bites or stings.\n",
        "Hydrocortisone creams, ointments – also known as steroid. \n"+"They are used on the skin to treat swelling, itching and irritation – \n"+"eczema, psoriasis, contact dermatitis, prickly heat rash, insect bites and stings etc.\n",
        "Most of the time Diarrhoea doesn’t require treatment and usually lasts only a couple of days. \n"+"But medicine can help you feel better. It especially helps if you also have cramping\n" +"or stomach pain.\n",
        "You can take indigestion remedies if you suffer from heartburn– a \n"+"painful burning feeling in the chest, often after eating or you are feeling full and bloated,\n"+" feeling sick, belching and passing wind or bringing up food \n"+"or bitter tasting fluids.\n"
         ,"Used in place for all kind of Antifungal medicines.\n","This is helpful for muscular aches and sprains and can be used to relieve period pain.\n"+"These are painkillers which also reduce inflammation.\n",
        "If you use this on minor scrapes, cuts and bites,\n"+" they are less likely to become infected. ","If anyone in your family is prone to mouth soreness or mouth ulcers \n"+"it is a good idea to keep something to help with "



};
HashMap<String,String>item;;
ArrayList list;
SimpleAdapter sa;
Button btnback,btngotocart;
ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine);
        lst=findViewById(R.id.articledetail);
        btnback=findViewById(R.id.backarticle);
        btngotocart=findViewById(R.id.checkoutmedicine);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });

        btngotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","total Cost: "+packages[i][4]+"/-");
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
                Intent it=new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",packages_details[i]);
                it.putExtra("text3",packages[i][4]);
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