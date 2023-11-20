package com.coffeeshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.coffeeshop.DBHandler.DBHandler;
import com.coffeeshop.models.home;

import java.util.List;

public class addproduct extends AppCompatActivity {
    EditText itemname;
    EditText price;
    EditText pid;
    Button BSelectImage;
    ImageView IVPreviewImage;
    private final int SELECT_PICTURE = 1000;
    Button submit;
    private Uri selectedImageUri;
    private AppBarConfiguration appBarConfiguration;

    DBHandler dbhandler;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct);
        itemname = findViewById(R.id.itemname);
        BSelectImage = findViewById(R.id.BSelectImage);
        IVPreviewImage = findViewById(R.id.IVPreviewImage);
        price = findViewById(R.id.price);
        pid = findViewById(R.id.pid);
        submit = findViewById(R.id.submit);
        dbhandler = new DBHandler(addproduct.this);

        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_PICTURE);
            }
        });

    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(addproduct.this, signup.class);
            startActivity(intent);
            String name = itemname.getText().toString();
            String prices = price.getText().toString();
            String imagePath = getRealPathFromURI(selectedImageUri);
            home homemodeldata =new home(name,prices,imagePath);
            dbhandler.home(homemodeldata);
            Toast.makeText(addproduct.this, "product added", Toast.LENGTH_LONG).show();
        }
    });
}

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                IVPreviewImage.setImageURI(data.getData());
            }
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                String filePath = cursor.getString(columnIndex);
                cursor.close();
                return filePath;
            }
            cursor.close();
        }

        // Handle cursor issues or no data found...
        return contentUri.getPath(); // Fall back to Uri's path
    }
}


