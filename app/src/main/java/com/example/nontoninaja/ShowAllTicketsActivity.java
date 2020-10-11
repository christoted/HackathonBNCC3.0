package com.example.nontoninaja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ShowAllTicketsActivity extends AppCompatActivity {

    RecyclerView id_rv_ShowAllTickets_rvAllTickets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_tickets);

        id_rv_ShowAllTickets_rvAllTickets.findViewById(R.id.id_rv_ShowAllTickets_rvAllTickets);
    }
}