package com.coffeeshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.coffeeshop.DBHandler.DBHandler;

public class signup extends AppCompatActivity {
    EditText name;
    EditText phone;
    EditText dob;
    EditText address;
    EditText email;
    EditText password;
    Button signup;
    TextView message;
    private AppBarConfiguration appBarConfiguration;

 DBHandler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        dob = findViewById(R.id.dob);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        message = findViewById(R.id.message);
        dbhandler = new DBHandler(signup.this);

        EditText name = findViewById(R.id.name);
        EditText phone = findViewById(R.id.phone);

        String namePattern = "^[A-Za-z]+$";
        String phonePattern = "^[0-9]+$";

//        String name = name.getText().toString();
        String phoneNumber = phone.getText().toString();

//        if (!name.matches(namePattern)) {
//            name.setError("Please enter a valid name with only letters.");
//        }

        if (!phoneNumber.matches(phonePattern)) {
            phone.setError("Please enter a valid phone number with only numbers.");
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(signup.this, MainActivity.class);
                // startActivity(intent);

dbhandler.signup(
        name.getText().toString(),
        phone.getText().toString(),
        email.getText().toString(),
        password.getText().toString()
);
                Toast.makeText(signup.this, "data submitted", Toast.LENGTH_LONG).show();
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
            }
        });
    }
}

