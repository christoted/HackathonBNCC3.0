package com.example.nontoninaja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.nontoninaja.Fragment.HomeFragment;
import com.example.nontoninaja.Fragment.MyTicketFragment;
import com.gauravk.bubblenavigation.IBubbleNavigation;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public IBubbleNavigation iBubbleNavigation;
    public FragmentManager fragmentManager;

    public void init(){
        iBubbleNavigation = findViewById(R.id.id_activity_navigation_view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        getSupportFragmentManager().beginTransaction().replace(R.id.id_fragment_container, new HomeFragment()).commit();

        iBubbleNavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int id) {
                Fragment fragment = null;

                switch (id){
                    case 0:
                        fragment = new HomeFragment();
                        break;
                    case 1:
                        fragment = new MyTicketFragment();
                        break;
                }

                if(fragment != null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.id_fragment_container, fragment).commit();
                }
                else {
                    Log.e(TAG, "Error Creating Fragment");
                }

            }
        });


    }
}