package com.example.nontoninaja.Adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nontoninaja.Model.MyTicket;
import com.example.nontoninaja.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

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
        View view = layoutInflater.inflate(R.layout.custom_view_rv_ticket,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMyTicket.MyViewHolder holder, int position) {
        final MyTicket movie = myTicketArrayList.get(position);
        holder.tv_title.setText(movie.getTxtTitle());
//        Picasso.with(mContext)
//                .load(movie.getMoviePoster())
//                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return myTicketArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imgView_content, imgView_date, imgView_time, imgView_type;
        TextView tv_title, tv_content, tv_date, tv_time, tv_type;
        ConstraintLayout view_container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView_content = itemView.findViewById(R.id.id_img_customview_content);
            imgView_date = itemView.findViewById(R.id.id_img_customview_date);
            imgView_time = itemView.findViewById(R.id.id_img_customview_time);
            imgView_type = itemView.findViewById(R.id.id_img_customview_type);

            tv_title = itemView.findViewById(R.id.id_tv_customview_title);
            tv_content = itemView.findViewById(R.id.id_tv_customview_content);
            tv_date = itemView.findViewById(R.id.id_tv_customview_date);
            tv_time = itemView.findViewById(R.id.id_tv_customview_time);
            tv_type = itemView.findViewById(R.id.id_tv_customview_type);

            //in-case kalau pake constarint
            view_container = itemView.findViewById(R.id.id_constraintLayout_myticket);
        }
    }
}
