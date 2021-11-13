package com.example.projectbncc;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<Webinar> webinarList;

    public MyAdapter(Context context, ArrayList<Webinar> webinarList) {
        this.context = context;
        this.webinarList = webinarList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.webinar_card,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String webinarUrl = webinarList.get(position).getWebinarImageUrl();
        Bundle bundle = new Bundle();
        String webinarDesc = webinarList.get(position).getWebinarDesc();
        Glide.with(context).load(webinarUrl).into(holder.webinarImage);
        bundle.putString("description",webinarDesc);
        holder.webinarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                manager
                        .setFragmentResult("descReq",bundle);
                manager
                        .beginTransaction()
                        .replace(R.id.main_container, new DescriptionFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return webinarList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView webinarImage;
        TextView longDesc;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            webinarImage = itemView.findViewById(R.id.webinar_image);
            longDesc = itemView.findViewById(R.id.desc_long);
        }
    }

}
