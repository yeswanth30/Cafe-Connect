package com.coffeeshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coffeeshop.DBHandler.DBHandler;
import com.coffeeshop.models.cart;
import com.coffeeshop.models.home;
import com.coffeeshop.models.payment;

import java.util.List;

public class mycart extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHandler dbHandler;
    TextView itemname;
    TextView price;
    String cid;
    EditText mode;
    EditText status;
    EditText date;
    Button paybutton;
    private AppBarConfiguration appBarConfiguration;
    String modes,statuss,dates;
    //private LinearLayout contentLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycart);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
//        contentLayout = findViewById(R.id.layout01);
        itemname = findViewById(R.id.itemname);
        price = findViewById(R.id.price);
        mode = findViewById(R.id.mode);
        status = findViewById(R.id.status);
        date = findViewById(R.id.date);
        paybutton = findViewById(R.id.paybutton);

        DBHandler dbHandler1 = new DBHandler(mycart.this);
        SharedPreferences sharedPreferences = getSharedPreferences("my preferences", MODE_PRIVATE);
        cid = sharedPreferences.getString("cid","");
        List<home> homes = dbHandler1.getallcart(cid);

        if (homes.size() > 0) {
            CartAdapter payment1 = new CartAdapter(homes, mycart.this);
            recyclerView.setAdapter(payment1);
        } else {
            Toast.makeText(mycart.this, "There is no product in the cart", Toast.LENGTH_SHORT).show();
        }
        paybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 modes = mode.getText().toString();
                 dates = date.getText().toString();
                 statuss = status.getText().toString();
                 double totalSum = 0.0;

                for (home payments : homes) {
                    totalSum += Double.parseDouble(payments.getPrice());
                }
                SharedPreferences sharedpreferences = getSharedPreferences("my preferences", MODE_PRIVATE);
                String cid = sharedpreferences.getString("cid","");
                Log.e("payment ka data",cid+totalSum+modes+dates+statuss);
                for (home payment : homes) {
                    dbHandler1.payment(cid,payment.getId(),totalSum,modes,dates,statuss);

                }
            }

        });



    }
}
