package com.arman.uts_10120061;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import androidx.annotation.Nullable;

public class ListCatatan extends SQLiteOpenHelper {

    Context c;
    Cursor cursor;
    SQLiteDatabase db;

    public static String Db_name = "listCatatan";
    public static String tbl_name = "catatan";

    public ListCatatan(@Nullable Context context) {
        super(context, Db_name, null, 3);
        this.c = context;

        db = getReadableDatabase();
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // sqlite akan otomatis menambahkan rowid jadi tidak perlu menambahkan PK
        String query = "CREATE TABLE IF NOT EXISTS "+tbl_name+" (judul varchar(50), kategori varchar(50), isi varchar(500), tgl varchar(30));";
        db.execSQL(query);
    }

    void simpanData(String judul, String kategori, String isi)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String tgl = String.valueOf(dtf.format(now));

        String query = "INSERT INTO "+tbl_name+" values"+"('"+judul+"','"+kategori+"','"+isi+"','"+tgl+"')";

        db.execSQL(query); // db ini mengambil properti milik class listCatatan

    }

    void updateData(String tanggal, String judul, String kategori, String isi)
    {
        db.execSQL("UPDATE catatan SET judul='"+judul+"', kategori='"+kategori+"', isi='"+isi+"' WHERE tgl='"+tanggal+"' ");
        Toast.makeText(c, "Berhasil Update Data", Toast.LENGTH_SHORT).show();
    }

    void delete(String tanggal){
        db.execSQL("DELETE FROM catatan WHERE tgl='"+tanggal+"'; ");
    }

    // model tabel data
    Cursor TampilData(){
        Cursor cursor = db.rawQuery("SELECT * FROM "+tbl_name, null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
