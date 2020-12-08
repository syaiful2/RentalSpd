package com.example.rentalspd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import id.zelory.compressor.Compressor;

public class EditSepeda extends AppCompatActivity implements IPickResult {

    private EditText edtmerk,edtkode,edthargasewa,edtjenis,edtwarna;
    private ImageView imggambar;
    private Button add, tambah;
    private ImageView back;
    SharedPreferences sharedPreferences;

    private Bitmap mSelectedImage;
    private String mSelectedImagePath;
    File mSelectedFileBanner;

    private SepedaModel model;
    private String U_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sepeda);
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);

        model = getIntent().getExtras().getParcelable("extra_sepeda");
        if(/*bundle*/ model != null) {
            U_ID = model.getId();
            Log.i("TAG", "onCreate: "+U_ID);

            edtkode.setText(model.getKode());
            edtmerk.setText(model.getMerk());
            edtjenis.setText(model.getJenis());
            edtwarna.setText(model.getWarna());
            edthargasewa.setText(model.getHargasewa());
            Picasso.get()
                    .load(BaseURL.url_foto + model.getGambar())
                    .into(imggambar);

        }

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditSepeda.this,SepedaActivity.class);
                startActivity(i);
            }
        });
        edtmerk = findViewById(R.id.merk);
        add = findViewById(R.id.btnadd);
        edtkode = findViewById(R.id.kode);
        edthargasewa = findViewById(R.id.hargasewa);
        edtjenis = findViewById(R.id.jenis);
        imggambar = findViewById(R.id.image);
        tambah = findViewById(R.id.btnimage);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickImageDialog.build(new PickSetup()).show(EditSepeda.this);
                new PickSetup().setCameraButtonText("Gallery");
            }
        });
        edtwarna = findViewById(R.id.warna);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String kode = edtkode.getText().toString();
                final String warna = edtwarna.getText().toString();
                final String merk = edtmerk.getText().toString();
                final String hargasewa = edthargasewa.getText().toString();
                final String jenis = edtjenis.getText().toString();

                if (kode.isEmpty()){
                    Toast.makeText(EditSepeda.this,"Kode tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }

                if (merk.isEmpty()){
                    Toast.makeText(EditSepeda.this,"Merk tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }

                if (warna.isEmpty()){
                    Toast.makeText(EditSepeda.this,"Warna tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }

                if (hargasewa.isEmpty()){
                    Toast.makeText(EditSepeda.this,"Harga Sewa tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }

                if (jenis.isEmpty()){
                    Toast.makeText(EditSepeda.this,"Jenis tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }
                HashMap<String, String> body = new HashMap<>();
                body.put("id", U_ID);
                body.put("kode", kode);
                body.put("merk", merk);
                body.put("warna", warna);
                body.put("hargasewa", hargasewa);
                body.put("jenis", jenis);
                AndroidNetworking.upload(BaseURL.url+"updatesepeda.php")
                        .addMultipartFile("gambar",mSelectedFileBanner)
                        .addMultipartParameter(body)
                        .setPriority(Priority.MEDIUM)
                        .setOkHttpClient(((initial) getApplication()).getOkHttpClient())
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("hasil", "onResponse: ");
                                JSONObject hasil = response.optJSONObject("hasil");
                                String respon = hasil.optString("respon");
                                Log.d("STATUS", "onResponse: " + hasil);
                                if (respon.equals("sukses")) {
//                                        sharedPreferences.edit().putString("logged","customer").apply();
                                    Intent intent = new Intent(EditSepeda.this, HomeAdmin.class);
                                    startActivity(intent);
//                                        finish();
//                                        progressDialog.dismiss();
                                } else {
                                    Toast.makeText(EditSepeda.this, "gagal", Toast.LENGTH_SHORT).show();
//                                        progressDialog.dismiss();

                                }

                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.d("TAG", "onError: " + anError.getErrorDetail());
                                Log.d("TAG", "onError: " + anError.getErrorBody());
                                Log.d("TAG", "onError: " + anError.getErrorCode());
                                Log.d("TAG", "onError: " + anError.getResponse());

                                Toast.makeText(EditSepeda.this, "Add Data Gagal", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
    }

    @Override
    public void onPickResult(PickResult r) {
        if(r.getError() == null){
            try {
                File fileku = new Compressor(this)
                        .setQuality(50)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToFile(new File(r.getPath()));
                mSelectedImagePath = fileku.getAbsolutePath();
                mSelectedFileBanner = new File(mSelectedImagePath);
                mSelectedImage=r.getBitmap();
                imggambar.setImageBitmap(mSelectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(EditSepeda.this, r.getError().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}