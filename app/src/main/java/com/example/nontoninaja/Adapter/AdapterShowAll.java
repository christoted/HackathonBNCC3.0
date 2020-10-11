package com.example.nontoninaja.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nontoninaja.Model.MyTicket;
import com.example.nontoninaja.R;
import com.example.nontoninaja.TicketDetailActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterShowAll extends RecyclerView.Adapter<AdapterShowAll.MyViewHolder> {
    private Context mcontext;
    private ArrayList<MyTicket> mlistTicket;
    public AdapterShowAll(Context context, ArrayList<MyTicket> listTicket){
        this.mcontext = context;
        this.mlistTicket = listTicket;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        View view = layoutInflater.inflate(R.layout.item_show_all_tickets,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final MyTicket myTicket = mlistTicket.get(position);
        holder.tvEventDate.setText(myTicket.getTxtDate());
        holder.tvEventName.setText(myTicket.getTxtTitle());
        holder.btnSeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, TicketDetailActivity.class);
                intent.putExtra("myTicket",myTicket);
                mcontext.startActivity(intent);
            }
        });

        Glide.with(mcontext)
                .load(myTicket.getImgEvent())
                .into(holder.imgEvent);
    }

    @Override
    public int getItemCount() {
        return mlistTicket.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvEventName;
        TextView tvEventDate;
        Button btnSeeDetails;
        ImageView imgEvent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEventName = itemView.findViewById(R.id.id_tv_ShowAllItem_eventName);
            tvEventDate = itemView.findViewById(R.id.id_tv_ShowAllItem_eventDate);
            btnSeeDetails = itemView.findViewById(R.id.id_btn_ShowAllItem_btnSeeDetails);
            imgEvent = itemView.findViewById(R.id.id_img_ShowAllItem_eventImg);
        }
    }
}
