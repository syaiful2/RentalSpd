package com.example.rentalspd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentalspd.helper.Apphelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SepedaAdapter extends RecyclerView.Adapter<SepedaAdapter.SepedaViewHolder> {

    private List<SepedaModel> datalist;
    private SepedaModel model;

    public SepedaAdapter(List<SepedaModel> datalist) {
        this.datalist = datalist;
    }

    @Override
    public SepedaAdapter.SepedaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);

       return new SepedaAdapter.SepedaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SepedaAdapter.SepedaViewHolder holder, final int position) {

        holder.kode.setText(datalist.get(position).getKode());
        holder.merk.setText(datalist.get(position).getMerk());
        holder.hargasewa.setText(datalist.get(position).getHargasewa());
        holder.jenis.setText(datalist.get(position).getJenis());
        holder.warna.setText(datalist.get(position).getWarna());

        Picasso.get()
                .load(BaseURL.url_foto+datalist.get(position).getGambar())
                .into(holder.gambar);

        holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Intent in = new Intent(holder.itemView.getContext(), EditSepeda .class);
//                in.putExtra("id", datalist.get(position).getId());
//                in.putExtra("kode", datalist.get(position).getKode());
//                in.putExtra("merk", datalist.get(position).getMerk());
//                in.putExtra("hargasewa", datalist.get(position).getHargasewa());
//                in.putExtra("jenis", datalist.get(position).getJenis());
//                in.putExtra("warna", datalist.get(position).getWarna());
//                in.putExtra("gambar", datalist.get(position).getGambar());
//                holder.itemView.getContext().startActivity(in);
                Apphelper.goToSepedaAdminDetail(holder.itemView.getContext(),model);

            }
        });


    }

    @Override
    public int getItemCount() {
        return (datalist != null) ? datalist.size() : 0;
    }


    public class SepedaViewHolder extends RecyclerView.ViewHolder {
        private TextView kode, merk, jenis, hargasewa, warna ;
        CardView card;
        ImageView gambar;


        SepedaViewHolder(View itemView) {
            super(itemView);


            card = (CardView) itemView.findViewById(R.id.cardku);
            kode = (TextView) itemView.findViewById(R.id.kode);
            gambar = (ImageView) itemView.findViewById(R.id.gambar);
            merk = (TextView) itemView.findViewById(R.id.merk);
            jenis = (TextView) itemView.findViewById(R.id.jenis);
            hargasewa = (TextView) itemView.findViewById(R.id.hargasewa);
            warna = (TextView) itemView.findViewById(R.id.warna);

        }
    }
}
