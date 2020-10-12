package com.example.nontoninaja.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nontoninaja.Model.MyTicket;
import com.example.nontoninaja.R;
import com.example.nontoninaja.TicketDetailActivity;

import java.util.ArrayList;

public class AdapterRecommendForYou extends RecyclerView.Adapter<AdapterRecommendForYou.MyViewHolder>{

    private ArrayList<MyTicket> myTicketArrayList;
    private Context mContext;

    public AdapterRecommendForYou(Context mContext, ArrayList<MyTicket> myTicketArrayList){
        this.mContext = mContext;
        this.myTicketArrayList = myTicketArrayList;
    }

    @NonNull
    @Override
    public AdapterRecommendForYou.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_home_coming_soon,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final MyTicket myTicket = myTicketArrayList.get(position);
        holder.tv_title.setText(myTicket.getTxtTitle());
        holder.tv_date.setText(myTicket.getTxtDate());
        holder.tv_likes.setText(myTicket.getLikes());
        Glide.with(mContext).load(myTicket.getImgEvent()).into(holder.imgView_content);
    }

    @Override
    public int getItemCount() {
        return myTicketArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView_content;
        TextView tv_title, tv_date, tv_likes;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            imgView_content = itemView.findViewById(R.id.id_img_home_coming_soon_img);
            tv_title = itemView.findViewById(R.id.id_item_home_coming_soon_title);
            tv_date = itemView.findViewById(R.id.id_item_home_coming_soon_date);
            tv_likes = itemView.findViewById(R.id.id_item_home_coming_soon_likes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), TicketDetailActivity.class);
                    intent.putExtra("myTicket",myTicketArrayList.get(getLayoutPosition()));
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
