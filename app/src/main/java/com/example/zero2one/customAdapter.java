package com.example.zero2one;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class customAdapter extends RecyclerView.Adapter<customAdapter.customViewHolder>
{
    Context ctx;
    List<WateringData> wateringData;
    public customAdapter(Context context, ArrayList<WateringData> data) {
        ctx = context;
        wateringData = data;
    }

    @NonNull
    @Override
    public customViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View customView = inflater.inflate(R.layout.history_each_item_layout,parent,false);
        return new customViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull customAdapter.customViewHolder holder, int position) {
        holder.t1.setText(wateringData.get(position).date_day);
        holder.t2.setText("ON TIME : "+wateringData.get(position).on_time);
        holder.t3.setText("OFF TIME : "+wateringData.get(position).off_time);
    }

    @Override
    public int getItemCount() {
        return wateringData.size();
    }

    public class customViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3;
        public customViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = (TextView)itemView.findViewById(R.id.header_text_history_activity);
            t2 = (TextView)itemView.findViewById(R.id.on_time_text_view);
            t3 = (TextView)itemView.findViewById(R.id.off_time_text_view);
        }
    }
}
