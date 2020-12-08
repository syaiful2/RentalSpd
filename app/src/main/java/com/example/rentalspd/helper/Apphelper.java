package com.example.rentalspd.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.rentalspd.EditSepeda;
import com.example.rentalspd.SepedaModel;

public final class Apphelper {
    public static void goToSepedaAdminDetail(Context context, SepedaModel rowData) {
        Bundle bundle = new Bundle();

        bundle.putString("id", rowData.getId());
        bundle.putString("kode", rowData.getKode().toUpperCase());
        bundle.putString("jenis", rowData.getJenis().toUpperCase());
        bundle.putString("warna", rowData.getWarna());
        bundle.putString("gambar", rowData.getGambar());
        bundle.putString("hargasewa", rowData.getHargasewa().toUpperCase());
        bundle.putString("merk", rowData.getMerk().toUpperCase());

        Intent i = new Intent(context, EditSepeda.class);
        i.putExtra("extra_sepeda", rowData);
        context.startActivity(i);
    }
}
