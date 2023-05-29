package com.arman.uts_10120061;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    Button edit,hapus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv = findViewById(R.id.b_nav);
        bukaFrag(new FragmentHome());
        // jika botton navigasi di klik
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home){
                    bukaFrag(new FragmentHome());
                    return true;
                }
                if(item.getItemId() == R.id.profil){
                    bukaFrag(new FragmentProfil());
                    return true;
                }
                if(item.getItemId() == R.id.info){
                    bukaFrag(new FragmentInfo());
                    return true;
                }
                return false;
            }
        });


    }


    // method untuk membuka fragment / replace viewpager dengan fragment
    boolean bukaFrag(Fragment f){
        if(f!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.content, f).commit();
            return true;
        }
        return false;
    }



}