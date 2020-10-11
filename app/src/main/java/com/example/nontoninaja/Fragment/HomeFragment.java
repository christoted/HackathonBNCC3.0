package com.example.nontoninaja.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Adapter;
import android.widget.TextView;

import android.widget.ImageButton;
import android.widget.Toast;


import com.example.nontoninaja.Activity.LoginActivity;
import com.example.nontoninaja.Activity.MainActivity;
import com.example.nontoninaja.Activity.ProfileDetailActivity;
import com.example.nontoninaja.Adapter.AdapterMyTicket;
import com.example.nontoninaja.Adapter.AdapterRecommendForYou;
import com.example.nontoninaja.Database.CloudFireStore;
import com.example.nontoninaja.Model.MyTicket;
import com.example.nontoninaja.R;
import com.example.nontoninaja.ShowAllTicketsActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;

import javax.annotation.Nullable;

import static com.example.nontoninaja.Database.CloudFireStore.ticketArrayList;


public class HomeFragment extends Fragment {

    FirebaseFirestore db;
    RecyclerView recyclerView, recyclerView2;
    ImageButton imageButton;
    AdapterMyTicket adapterMyTicket;
    AdapterRecommendForYou adapterRecommendForYou;
    ArrayList<MyTicket> ticketArrayLists;
    TextView txtShowAll, txtUser, txtSaldo;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    String profileGoogle, emailGoogle;
    private void initView(View view)  {
        recyclerView = view.findViewById(R.id.id_rv_fragment_home_yourConcert);
        recyclerView2 = view.findViewById(R.id.id_rv_fragment_home_comingSoon);
        txtShowAll = view.findViewById(R.id.id_btn_fragment_home_showAll);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        imageButton = view.findViewById(R.id.id_imgBtn_fragment_home_profilePicture);
        txtUser = view.findViewById(R.id.id_tv_fragment_home_username);
        txtSaldo = view.findViewById(R.id.id_tv_fragment_home_saldo);
    }

    private void getText(){
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
        if (acct != null) {
            profileGoogle = acct.getDisplayName();
            emailGoogle = acct.getEmail();

            Log.d("DISPLAYED", "Name: " + profileGoogle);
            Log.d("DISPLAYED", "EMAIL: " + emailGoogle);
        }
        String userID = firebaseAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        DocumentReference documentReference = firebaseFirestore.collection("users")
                .document(userID);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.getString("name") != null){
                    txtUser.setText(documentSnapshot.getString("name"));
                    if(documentSnapshot.getString("saldo") == null){
                        txtSaldo.setText("0");
                    }else{
                        txtSaldo.setText(documentSnapshot.getString("saldo"));
                    }

                }else{
                    txtUser.setText(profileGoogle);
                    txtSaldo.setText("0");
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_home, container, false);
       initView(view);
       getText();
       initializeView();

        txtShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShowAllTicketsActivity.class);
                intent.putParcelableArrayListExtra("listTickets",ticketArrayLists);
                startActivity(intent);
            }
        });

     //  db.readConcert(getActivity());

       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
       recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
       Log.d("12345", "onCreateView: " + recyclerView);

       imageButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(v.getContext(), ProfileDetailActivity.class));
           }
       });

       return view;
    }

    public void initializeView()   {
        ticketArrayLists = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        CollectionReference concertsCollectionRef = db.collection("concerts");

            Query concertsQuery = concertsCollectionRef;

            concertsQuery
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
//                                ticketArrayList.clear();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    MyTicket myTicket = document.toObject(MyTicket.class);

                                    myTicket.setTxtTitle(document.getData().get("title").toString());
                                    myTicket.setTxtTime(document.getData().get("time").toString());
                                    myTicket.setTxtLocation(document.getData().get("location").toString());
                                    myTicket.setTxtPriceReguler(document.getData().get("priceReguler").toString());
                                    myTicket.setTxtPriceVIP(document.getData().get("priceVIP").toString());
                                    myTicket.setTxtPriceVVIP(document.getData().get("priceVVIP").toString());
                                    myTicket.setLikes(document.getData().get("likes").toString());
//                                    Log.d("12345", "onComplete: Likes" + document.getData().get("likes").toString());
                                    myTicket.setTxtDate(document.getData().get("date").toString());
                                    myTicket.setImgEvent(document.getData().get("image").toString());
                                    myTicket.setTxtCategory(document.getData().get("category").toString());
                                    myTicket.setId(document.getData().get("id").toString());

                                    myTicket.setTxtDescription(document.getData().get("description").toString());



                                    ticketArrayLists.add(myTicket);
//                                    Log.d("555", "onComplete: ini Home Fragment" + myTicket.getId());
                                }
                                adapterMyTicket = new AdapterMyTicket(getActivity(), ticketArrayLists);
                                adapterRecommendForYou = new AdapterRecommendForYou(getActivity(), ticketArrayLists);
                                recyclerView.setAdapter(adapterMyTicket);
                                recyclerView2.setAdapter(adapterRecommendForYou);
                            } else {
                                Log.w("error", "Error getting documents.", task.getException());
                            }
                        }
                    });

    }
}