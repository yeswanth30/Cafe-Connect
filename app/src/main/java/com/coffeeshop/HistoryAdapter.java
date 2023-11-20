package com.coffeeshop;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coffeeshop.DBHandler.DBHandler;
import com.coffeeshop.models.history;
import com.coffeeshop.models.home;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    List<history> hist;
    Context context;
    DBHandler dbHandler;

    public HistoryAdapter(List<history> hist, Context context) {
        this.hist = hist;
        this.context = context;
        dbHandler = new DBHandler(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.history_items,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final history historys = hist.get(position);



//        holder.itemname.setText(historys.getItemname());
//        holder.price.setText(historys.getPrice());

    }
    @Override
    public int getItemCount() {

        return hist.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemname;
        TextView price;
        TextView amount;
        TextView date;
        TextView userid;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname = itemView.findViewById(R.id.itemname);
            price = itemView.findViewById(R.id.price);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.date);
            userid = itemView.findViewById(R.id.userid);

//            itemphoto = itemView.findViewById(R.id.itemphoto);
        }
    }
}
