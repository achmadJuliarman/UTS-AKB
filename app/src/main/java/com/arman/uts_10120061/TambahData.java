package com.arman.uts_10120061;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahData extends AppCompatActivity {
    EditText judul, isi, kategori;
    Button simpan;
    ListCatatan table;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tambah);
        judul = findViewById(R.id.judul);
        kategori = findViewById(R.id.kategori);
        isi = findViewById(R.id.isi);
        simpan = findViewById(R.id.simpan);

        table = new ListCatatan(getApplicationContext());

        simpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    simpan_data();
                    Toast.makeText(getApplicationContext(), "Berhasil Tambah Data", Toast.LENGTH_SHORT).show();
                }catch (SQLiteException e) {
                    Toast.makeText(getApplicationContext(), "Gagal Tambah Data", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    void simpan_data()
    {
        table.simpanData(judul.getText().toString(), kategori.getText().toString(), isi.getText().toString());
    }
}