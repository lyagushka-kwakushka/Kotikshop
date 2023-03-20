package com.example.kotikshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText userNameEditText;
    int kolichestvo = 0;
    int b = 300;
    int c = 0;
    int count = 0;
    Spinner spinner;
    ArrayList spinnerArray;
    ArrayAdapter spinneradapr;
    HashMap tovari;
    String tovarname;
    TextView quantity;
    TextView cena;
    ImageView kartinkatovara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userNameEditText = findViewById(R.id.editTextTextPersonName);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArray = new ArrayList();
        tovari = new HashMap();
        quantity = findViewById(R.id.textView7);
        cena = findViewById(R.id.textView6);
        kartinkatovara = findViewById(R.id.imageView2);
        quantity.setText(String.format(Locale.ENGLISH ,"%d", kolichestvo));
        cena.setText(String.format(Locale.ENGLISH ,"%d", c));
        spinnerArray.add("Ptichka");
        spinnerArray.add("Myachik");
        spinnerArray.add("Palohka");
        spinnerArray.add("Travfka");
        tovari.put("Ptichka", 300);
        tovari.put("Myachik", 200);
        tovari.put("Palohka", 600);
        tovari.put("Travfka", 150);

        spinneradapr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArray);
        spinneradapr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinneradapr);
    }

    public void IncQuantity(View view) {
        kolichestvo += 1;
        quantity.setText(String.format(Locale.ENGLISH ,"%d", kolichestvo));
        c += b;
        cena.setText(String.format(Locale.ENGLISH ,"%d", c));
    }

    public void decQuantity(View view) {
        if(kolichestvo > 0)
        {
            kolichestvo -= 1;
            c -= b;
        }
        cena.setText(String.format(Locale.ENGLISH ,"%d", c));
        quantity.setText(String.format(Locale.ENGLISH ,"%d", kolichestvo));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tovarname = spinner.getSelectedItem().toString();
        switch (tovarname)
        {
            case("Ptichka"):
                kartinkatovara.setImageResource(R.drawable.ptichka);
                break;
            case("Myachik"):
                kartinkatovara.setImageResource(R.drawable.myachik);
                break;
            case("Palohka"):
                kartinkatovara.setImageResource(R.drawable.palochka);
                break;
            case("Travfka"):
                kartinkatovara.setImageResource(R.drawable.trafka);
                break;
        }
        b = (int) tovari.get(tovarname);
        TextView CenaTextView = findViewById(R.id.textView6);
        kolichestvo = 0;
        c = 0;
        quantity.setText(String.format(Locale.ENGLISH, "%d", count));
        cena.setText(String.format(Locale.ENGLISH, "%d", count));
        CenaTextView.setText("" + kolichestvo * c);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName=userNameEditText.getText().toString();
        Log.d("printUserName", order.userName);

        order.goodsName = tovarname;
        Log.d("goodsName", order.goodsName);

        order.quantity = kolichestvo;
        Log.d("quantity", ""+order.quantity);

        order.orderPrice = kolichestvo * c;
        Log.d("orderPrice", ""+order.orderPrice);

    }
}