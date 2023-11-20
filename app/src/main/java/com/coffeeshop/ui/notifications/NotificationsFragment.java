package com.coffeeshop.ui.notifications;

import static android.content.Context.MODE_PRIVATE;

import static com.coffeeshop.DBHandler.sharedpreference.getSharedPreferences;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coffeeshop.DBHandler.DBHandler;
import com.coffeeshop.HistoryAdapter;
import com.coffeeshop.ProductAdapter;
import com.coffeeshop.R;
import com.coffeeshop.databinding.FragmentHomeBinding;
import com.coffeeshop.databinding.FragmentNotificationsBinding;
import com.coffeeshop.models.history;
import com.coffeeshop.models.home;
import com.coffeeshop.mycart;

import org.w3c.dom.Text;

import java.util.List;

public class NotificationsFragment extends Fragment {

    RecyclerView recyclerView1;
    DBHandler dbHandler;
    TextView itemname;
    TextView amount;
    TextView date;
    TextView price;
    TextView userid;
    private FragmentHomeBinding binding;

    private LinearLayout contentLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        recyclerView1 = view.findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView1.setHasFixedSize(true);



        itemname = view.findViewById(R.id.itemname);
        amount = view.findViewById(R.id.amount);
        price = view.findViewById(R.id.price);
        userid = view.findViewById(R.id.userid);
        date = view.findViewById(R.id.date);
        dbHandler = new DBHandler(getContext());
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("my preferences", MODE_PRIVATE);
        String cid = sharedPreferences.getString("cid", "");

        List<history> history = dbHandler.getallhistory(cid);

        if (history.size() > 0){
            HistoryAdapter historys = new HistoryAdapter(history,getContext());
            recyclerView1.setAdapter(historys);
        }else {
            Toast.makeText(getContext(), "There is no product history", Toast.LENGTH_SHORT).show();
        }
        return view;
    }
}
