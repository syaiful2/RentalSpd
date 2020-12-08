package com.example.rentalspd;

import android.os.Parcel;
import android.os.Parcelable;

public class SepedaModel implements Parcelable {
    private String kode;
    private String id;
    private String merk;
    private String warna;
    private String hargasewa;
    private String gambar;
    private String jenis;

    protected SepedaModel(Parcel in) {
        kode = in.readString();
        id = in.readString();
        merk = in.readString();
        warna = in.readString();
        hargasewa = in.readString();
        gambar = in.readString();
        jenis = in.readString();
    }

    public static final Creator<SepedaModel> CREATOR = new Creator<SepedaModel>() {
        @Override
        public SepedaModel createFromParcel(Parcel in) {
            return new SepedaModel(in);
        }

        @Override
        public SepedaModel[] newArray(int size) {
            return new SepedaModel[size];
        }
    };

    public SepedaModel() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getHargasewa() {
        return hargasewa;
    }

    public void setHargasewa(String hargasewa) {
        this.hargasewa = hargasewa;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public static Creator<SepedaModel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(kode);
        parcel.writeString(id);
        parcel.writeString(merk);
        parcel.writeString(warna);
        parcel.writeString(hargasewa);
        parcel.writeString(gambar);
        parcel.writeString(jenis);
    }
}
