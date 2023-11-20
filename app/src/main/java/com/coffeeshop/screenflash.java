package com.coffeeshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.coffeeshop.ui.home.HomeFragment;

public class screenflash extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_TIME = 2500;

    ImageView logo;
    String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screenflash);
        logo = findViewById(R.id.logo);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("my preferences", MODE_PRIVATE);
                cid = sharedPreferences.getString("cid","");
                if (cid.isEmpty()) {
                    Intent intent1 = new Intent(screenflash.this, login.class);
                    startActivity(intent1);
                } else {
                    Intent intent2 = new Intent(screenflash.this, MainActivity.class);
                    startActivity(intent2);
                }

//                Intent intent = new Intent();
//                intent.setClass(screenflash.this,
//                        login.class);
                 Intent intent1 = new Intent();
                screenflash.this.startActivity(intent1);
                screenflash.this.finish();

                Intent intent2 = new Intent();
                screenflash.this.startActivity(intent2);
                screenflash.this.finish();

            }
        }, SPLASH_DISPLAY_TIME);
    }
}

