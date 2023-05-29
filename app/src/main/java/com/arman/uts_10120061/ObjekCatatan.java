package com.arman.uts_10120061;

public class ObjekCatatan {
    String judul="", kategori="", isi="", tanggal="";
    ObjekCatatan(String judul, String kategori, String isi, String tanggal){
        this.judul = judul;
        this.kategori = kategori;
        this.isi = isi;
        this.tanggal = tanggal;

    }

    public String getJudul() {
        return judul;
    }

    public String getKategori() {
        return kategori;
    }

    public String getIsi() {
        return isi;
    }

    public String getTanggal() {
        return tanggal;
    }
}
