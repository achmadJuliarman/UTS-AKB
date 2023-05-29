package com.arman.uts_10120061;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditData extends AppCompatActivity {
    EditText judul, kategori, isi;
    String t, j, k, i;
    int id;
    Button ubah;
    ListCatatan tabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit);
        judul = findViewById(R.id.judul);
        kategori = findViewById(R.id.kategori);
        isi = findViewById(R.id.isi);
        ubah = findViewById(R.id.ubah);

        judul.setText(getIntent().getStringExtra("judul"));
        kategori.setText(getIntent().getStringExtra("kategori"));
        isi.setText(getIntent().getStringExtra("isi"));

        t = String.valueOf(getIntent().getStringExtra("tanggal")).toString();
        j = getIntent().getStringExtra("judul");
        k = getIntent().getStringExtra("kategori");
        i = getIntent().getStringExtra("isi");

        tabel = new ListCatatan(getApplicationContext());
        ubah.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                    tabel.updateData(t, j, k, i);
                }catch (SQLiteException e){
                    Toast.makeText(getApplicationContext(), "Erorr" + e , Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });

    }
}
