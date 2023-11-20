package com.coffeeshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.coffeeshop.DBHandler.DBHandler;

public class paymentpage extends AppCompatActivity {
    Button s1;
    private AppBarConfiguration appBarConfiguration;

    DBHandler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentpage);
        s1 = findViewById(R.id.s1);

        dbhandler = new DBHandler(paymentpage.this);
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(paymentpage.this, MainActivity.class);
               // startActivity(intent);

              //  dbhandler.payment("1","1","1","2","3","1","1","2","1");
            }
        });
    }
}
