package com.example.nontoninaja.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nontoninaja.Adapter.AdapterMyInvetoryTicket;
import com.example.nontoninaja.Adapter.AdapterMyTicket;
import com.example.nontoninaja.Model.MyTicketInventory;
import com.example.nontoninaja.R;


public class MyTicketFragment extends Fragment {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_ticket, container, false);
        recyclerView = view.findViewById(R.id.id_rv_fragment_my_ticket_yourConcert);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        AdapterMyInvetoryTicket adapterMyTicket = new AdapterMyInvetoryTicket(getContext(), MyTicketInventory.myTicketInventory);
        recyclerView.setAdapter(adapterMyTicket);
        return view;
    }
}