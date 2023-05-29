package com.arman.uts_10120061;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentTambah extends Fragment {
    Button simpan;
    EditText judul, kategori, isi;
    ListCatatan table;
    Context c;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah, container, false);
        simpan = view.findViewById(R.id.simpan);

        simpan.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//
                try {
                    judul = view.findViewById(R.id.judul);
                    kategori = view.findViewById(R.id.kategori);
                    isi = view.findViewById(R.id.isi);
                    table = new ListCatatan(getActivity().getApplicationContext());
                    table.simpanData(judul.getText().toString(), kategori.getText().toString(), isi.getText().toString());

                    Toast.makeText(getActivity().getApplicationContext(), "Berhasil Tambah Data", Toast.LENGTH_SHORT).show();
                    bukaFrag(new FragmentHome());
                }catch (SQLiteException e){
                    Toast.makeText(getActivity().getApplicationContext(), "GAG AL Tambah Data", Toast.LENGTH_SHORT).show();
                    Log.d("error", e.toString());
                }

//                bukaFrag(new FragmentHome());
            }
        });
        return view;
    }

    boolean bukaFrag(Fragment f){
        if(f!=null){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, f).commit();
            return true;
        }
        return false;
    }

}
