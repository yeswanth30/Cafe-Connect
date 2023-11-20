package com.coffeeshop;

import static android.content.Context.MODE_PRIVATE;
import static com.coffeeshop.DBHandler.sharedpreference.getSharedPreferences;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coffeeshop.DBHandler.DBHandler;
import com.coffeeshop.models.home;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    List<home> product;
    Context context;
    Button cart;
    ImageView pimage;
    DBHandler dbHandler;

    public ProductAdapter(List<home> itemname, Context context) {
        this.product = itemname;
        this.context = context;
        this.cart = cart;
        this.pimage = pimage;
        dbHandler = new DBHandler(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.coffee_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final home homes = product.get(position);

        holder.itemname.setText(homes.getItemname());
        holder.price.setText(homes.getPrice());
        Bitmap imageBitmap = BitmapFactory.decodeFile(homes.getPhoto());
        holder.pimage.setImageBitmap(imageBitmap);

        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //homes pay = new home(homes.getId(),homes.getItemname(),homes.getPrice(),homes.getPhoto());

                SharedPreferences sharedPreferences = context.getSharedPreferences("my preferences", MODE_PRIVATE);
                String cid = sharedPreferences.getString("cid","");
              // Toast.makeText(context,"productadapter data"+cid, Toast.LENGTH_LONG).show();
                dbHandler.pay(homes.getId(),cid);

                notifyDataSetChanged();
//                Toast.makeText(addproduct.this, "product added", Toast.LENGTH_LONG).show();
            }
        });

        };
//
//       {
//            @Override
//            public void onClick(View v) {
//                String stringItemname = holder.itemname.getText().toString();
//                String stringPrice = holder.price.getText().toString();
//
//                dbHandler.home(new home(home.getItemname(),home.getPrice()));
//                notifyDataSetChanged();
//                ((Activity) context).finish();
//                context.startActivity(((Activity) context).getIntent());
//            }
//        });

    @Override
    public int getItemCount() {

        return product.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemname;
        TextView price;
        ImageView pimage;
        Button cart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname = itemView.findViewById(R.id.itemname);
            price = itemView.findViewById(R.id.price);
            cart = itemView.findViewById(R.id.cart);
            pimage = itemView.findViewById(R.id.pimage);

        }
            }
        }