package com.coffeeshop.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coffeeshop.DBHandler.DBHandler;
import com.coffeeshop.ProductAdapter;
import com.coffeeshop.R;
import com.coffeeshop.databinding.FragmentHomeBinding;
import com.coffeeshop.models.home;
import com.coffeeshop.mycart;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    DBHandler dbHandler;
    TextView name;
    String cid;
    TextView price;
    Button cartpage;
    private FragmentHomeBinding binding;

    private LinearLayout contentLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        contentLayout = view.findViewById(R.id.layout1);

        DBHandler dbHandler = new DBHandler(getContext());
        name = view.findViewById(R.id.name);
        price = view.findViewById(R.id.price);
        cartpage = view.findViewById(R.id.cartpage);
        dbHandler = new DBHandler(getContext());

        cartpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), mycart.class);
                startActivity(intent);
                //Toast.makeText(getContext(), "Welcome to Cart Page", Toast.LENGTH_LONG).show();
            }
        });

        List<home> home = dbHandler.gethomeList();

        if (home.size() > 0){
            ProductAdapter home1 = new ProductAdapter(home,getContext());
            recyclerView.setAdapter(home1);
        }else {
            Toast.makeText(getContext(), "There is no product in the database", Toast.LENGTH_SHORT).show();
        }

//        Cursor res = dbHandler.gethome();
//
//
//        while (res.moveToNext()) {
//            String name = res.getString(1);
//            String price = res.getString(2);
//            home.add(new home(name, price));
//        }
//        while (res.moveToNext()) {
//            name.setText(res.getString(1));
//            price.setText(res.getString(2));

//            buffer.append("ProductName "+res.getString(0)+"\n");
//            buffer.append("LastName "+res.getString(1)+"\n");
//            buffer.append("Email "+res.getString(2)+"\n");
//            buffer.append("Mobile "+res.getString(3)+"\n");
        //}
        return view;

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//}
    }
}
