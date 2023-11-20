package com.coffeeshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.coffeeshop.DBHandler.DBHandler;

public class login extends AppCompatActivity {

    EditText email;
    EditText password;
    String email1;
    String password1;
    Button login;
    Button register;
    DBHandler dbHandler;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        dbHandler = new DBHandler(login.this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                email1 = email.getText().toString();
                password1 = password.getText().toString();
                if(email1.isEmpty() && password1.isEmpty()){
                    Toast.makeText(login.this, "Please Enter Values", Toast.LENGTH_LONG).show();
                } else {
                    String data = "";

                    SharedPreferences sharedPreferences = getSharedPreferences("my preferences", MODE_PRIVATE);
                    SharedPreferences.Editor myedit = sharedPreferences.edit();
                    data = dbHandler.login(email1, password1);
                    myedit.putString("cid", String.valueOf(data) );
                    myedit.commit();
                    Log.e("email", String.valueOf(data));
                    if (data.isEmpty()) {
                        Toast.makeText(login.this, "Email not found. Please sign up", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(login.this, "Successfully login", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(login.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });
}


}


