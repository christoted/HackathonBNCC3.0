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
import com.example.nontoninaja.Model.MyTicketWithQty;
import com.example.nontoninaja.R;
import com.example.nontoninaja.activity_myticket_details;

import java.util.ArrayList;

public class AdapterMyInvetoryTicket extends RecyclerView.Adapter<AdapterMyInvetoryTicket.MyViewHolder> {
    Context context;
    ArrayList<MyTicketWithQty> arrayList;

    public AdapterMyInvetoryTicket(Context context, ArrayList<MyTicketWithQty> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_home_concert,parent,false);

        return new AdapterMyInvetoryTicket.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final MyTicket myTicket = arrayList.get(position).getTicket();
        holder.tv_title.setText(myTicket.getTxtTitle());
        holder.tv_time.setText(myTicket.getTxtTime());
        holder.tv_category.setText(myTicket.getTxtCategory());
        holder.tv_location.setText(myTicket.getTxtLocation());
        holder.tv_calendar.setText(myTicket.getTxtDate());

        Glide.with(context)
                .load(myTicket.getImgEvent())
                .into(holder.imgView_content);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
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
                    Intent intent = new Intent(itemView.getContext(), activity_myticket_details.class);
                    intent.putExtra("myTicket",arrayList.get(getLayoutPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
