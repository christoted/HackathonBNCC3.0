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
import com.example.nontoninaja.ShowAllTicketsActivity;
import com.example.nontoninaja.TicketDetailActivity;

import java.util.ArrayList;

public class AdapterMyTicket extends RecyclerView.Adapter<AdapterMyTicket.MyViewHolder> {

    private ArrayList<MyTicket> myTicketArrayList;
    private Context mContext;

    //constructor
    public AdapterMyTicket(Context mContext, ArrayList<MyTicket> myTickets){
        this.mContext = mContext;
        this.myTicketArrayList = myTickets;
    }

    @NonNull
    @Override
    public AdapterMyTicket.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_home_concert,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMyTicket.MyViewHolder holder, int position) {
        final MyTicket myTicket = myTicketArrayList.get(position);
        holder.tv_title.setText(myTicket.getTxtTitle());
        holder.tv_time.setText(myTicket.getTxtTime());
        holder.tv_category.setText(myTicket.getTxtCategory());
        holder.tv_location.setText(myTicket.getTxtLocation());
        holder.tv_calendar.setText(myTicket.getTxtDate());

        Glide.with(mContext)
                .load(myTicket.getImgEvent())
                .into(holder.imgView_content);


    }

    @Override
    public int getItemCount() {
        return myTicketArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imgView_content;
        TextView tv_title, tv_content, tv_calendar, tv_time, tv_category,tv_location;


        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            imgView_content = itemView.findViewById(R.id.id_iv_home_imageContent);
            tv_title = itemView.findViewById(R.id.id_tv_item_home_concert_title);
            tv_category = itemView.findViewById(R.id.id_tv_item_home_concert_category);
            tv_time = itemView.findViewById(R.id.id_tv_item_home_concert_time);
            tv_location = itemView.findViewById(R.id.id_tv_item_home_location);
            tv_calendar = itemView.findViewById(R.id.id_tv_item_home_concert_calendar);

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
