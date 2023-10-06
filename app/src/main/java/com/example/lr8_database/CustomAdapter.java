package com.example.lr8_database;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList concert_id, concert_title, concert_band, concert_date, concert_place;

    CustomAdapter(Activity activity, Context context, ArrayList concert_id, ArrayList concert_title, ArrayList concert_band,
                  ArrayList concert_date, ArrayList concert_place){
        this.activity = activity;
        this.context = context;
        this.concert_id = concert_id;
        this.concert_title = concert_title;
        this.concert_band = concert_band;
        this.concert_date = concert_date;
        this.concert_place = concert_place;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.concert_id_txt.setText(String.valueOf(concert_id.get(position)));
        holder.concert_title_txt.setText(String.valueOf(concert_title.get(position)));
        holder.concert_band_txt.setText(String.valueOf(concert_band.get(position)));
        holder.concert_place_txt.setText(String.valueOf(concert_place.get(position)));
        holder.concert_date_txt.setText(String.valueOf(concert_date.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(concert_id.get(position)));
                intent.putExtra("title", String.valueOf(concert_title.get(position)));
                intent.putExtra("band", String.valueOf(concert_band.get(position)));
                intent.putExtra("place", String.valueOf(concert_place.get(position)));
                intent.putExtra("date", String.valueOf(concert_date.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return concert_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView concert_id_txt, concert_title_txt, concert_band_txt, concert_place_txt, concert_date_txt;
        LinearLayout mainLayout;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            concert_id_txt = itemView.findViewById(R.id.concert_id_txt);
            concert_title_txt = itemView.findViewById(R.id.concert_title_txt);
            concert_band_txt = itemView.findViewById(R.id.concert_band_txt);
            concert_place_txt = itemView.findViewById(R.id.concert_place_txt);
            concert_date_txt = itemView.findViewById(R.id.concert_date_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
