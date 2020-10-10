package com.example.nontoninaja.Adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nontoninaja.R;

public class AdapterMyTicket extends RecyclerView.Adapter<AdapterMyTicket.MyViewHolder> {

    private Context mContex;

    //constructor
    public AdapterMyTicket(Context mContex){
        this.mContex = mContex;
    }

    @NonNull
    @Override
    public AdapterMyTicket.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMyTicket.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
