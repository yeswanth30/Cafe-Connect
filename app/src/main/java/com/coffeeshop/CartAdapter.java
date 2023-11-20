package com.coffeeshop;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coffeeshop.DBHandler.DBHandler;
import com.coffeeshop.models.home;
import com.coffeeshop.models.payment;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    List<home> cart;
    Context context;
    DBHandler dbHandler;

    public CartAdapter(List<home> cart, Context context) {
        this.cart = cart;
        this.context = context;
        dbHandler = new DBHandler(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart_items,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final home payments = cart.get(position);

        holder.itemname.setText(payments.getItemname());
        holder.price.setText(payments.getPrice());
      //  Toast.makeText(context,"data"+payments.getItemname(),Toast.LENGTH_LONG).show();
//        Log.e("datafrom adapter",payments.getItemname());
//        Log.e("datafrom adapter fro price",payments.getPrice());



    }
    @Override
    public int getItemCount() {

        return cart.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemname;
        TextView price;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname = itemView.findViewById(R.id.itemname);
            price = itemView.findViewById(R.id.price);

//            itemphoto = itemView.findViewById(R.id.itemphoto);
        }
    }
}