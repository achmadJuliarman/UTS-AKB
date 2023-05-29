package com.arman.uts_10120061;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    ListView lv;
    Button tambah, edit, hapus;
    Context c;
    Cursor cursor;
    ListCatatan tabel;
    CustomeAdapter adapter;
    SQLiteDatabase database;
    ArrayList<ObjekCatatan> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        lv = view.findViewById(R.id.lv);
        tambah = view.findViewById(R.id.tambah);
        edit = view.findViewById(R.id.edit);
        hapus = view.findViewById(R.id.hapus);


        tabel = new ListCatatan(getActivity().getApplicationContext());
        ambil_data();

        // listener tambah data
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bukaFrag(new FragmentTambah());
            }
        });


        return view;
    }

    void ambil_data()
    {
        list = new ArrayList<ObjekCatatan>();
        cursor = tabel.TampilData();

        if (cursor != null && cursor.getCount()>0)
        {
            while(cursor.moveToNext())
            {
                String judul = cursor.getString(0);
                String kategori = cursor.getString(1);
                String isi = cursor.getString(2);
                String tanggal = cursor.getString(3);
                list.add(new ObjekCatatan(judul,kategori,isi,tanggal));
            }

            adapter = new CustomeAdapter(getActivity().getApplicationContext(), list);
            lv.setAdapter(adapter);



        }
    }


    boolean bukaFrag(Fragment f){
        if(f!=null){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, f).commit();

            return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        ambil_data();
    }
}

class CustomeAdapter extends BaseAdapter {

    Context c;
    LayoutInflater LI;
    ArrayList<ObjekCatatan> model;
    ListCatatan tabel;

    CustomeAdapter(Context context, ArrayList<ObjekCatatan> list) {
        this.c = context;
        this.model = list;
        this.LI = LayoutInflater.from(c);
        tabel = new ListCatatan(c);
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    TextView judul, isi, kategori, tanggal;
    Button edit, hapus;

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View viewCatatan = LI.inflate(R.layout.my_list_item, viewGroup, false);

        judul = viewCatatan.findViewById(R.id.judul);
        isi = viewCatatan.findViewById(R.id.isi);
        kategori = viewCatatan.findViewById(R.id.kategori);
        tanggal = viewCatatan.findViewById(R.id.tgl);

        judul.setText(model.get(position).getJudul());
        kategori.setText(model.get(position).getKategori());
        isi.setText(model.get(position).getIsi());
        tanggal.setText(model.get(position).getTanggal());

        edit = viewCatatan.findViewById(R.id.edit);
        hapus = viewCatatan.findViewById(R.id.hapus);
        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, EditData.class);
                intent.putExtra("judul", model.get(position).getJudul());
                intent.putExtra("judul", model.get(position).getJudul());
                intent.putExtra("kategori", model.get(position).getKategori());
                intent.putExtra("isi", model.get(position).getIsi());
                intent.putExtra("tanggal", model.get(position).getTanggal());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
//                    Toast.makeText(c.getApplicationContext(), "ini tombol edit", Toast.LENGTH_SHORT).show();
            }
        });
        hapus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tabel.delete(model.get(position).getTanggal());
            }
        });
        return viewCatatan;
    }
}



