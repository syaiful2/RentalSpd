package com.example.rentalspd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private EditText etemail;
    private EditText etpassword;
    private EditText etnama;

    private EditText etnohp;
    private EditText etalamat;
    private EditText etnoktp;

    private Button btn_regis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etemail = findViewById(R.id.email);
        etpassword = findViewById(R.id.password);
        etnama = findViewById(R.id.nama);
        etnohp = findViewById(R.id.nohp);
        etalamat = findViewById(R.id.alamat);
        etnoktp = findViewById(R.id.noktp);

        btn_regis = findViewById(R.id.btn_register);
        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etemail.getText().toString();
                String password = etpassword.getText().toString();
                String nama = etnama.getText().toString();
                String nohp = etnohp.getText().toString();
                String alamat = etalamat.getText().toString();
                String noktp = etnoktp.getText().toString();

                if (email.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "ini tidak boleh kosong", Toast.LENGTH_SHORT).show();

                }
                if (password.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "ini tidak boleh kosong", Toast.LENGTH_SHORT).show();

                }
                if (nama.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "ini tidak boleh kosong", Toast.LENGTH_SHORT).show();

                }
                if (nohp.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "ini tidak boleh kosong", Toast.LENGTH_SHORT).show();

                }
                if (alamat.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "ini tidak boleh kosong", Toast.LENGTH_SHORT).show();

                }
                if (noktp.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "ini tidak boleh kosong", Toast.LENGTH_SHORT).show();

                }

                AndroidNetworking.post(BaseURL.url+"register.php")
                        .addBodyParameter("email",email)
                        .addBodyParameter("password",password)
                        .addBodyParameter("nama",nama)
                        .addBodyParameter("nohp",nohp)
                        .addBodyParameter("alamat",alamat)
                        .addBodyParameter("noktp",noktp)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject hasil = response.getJSONObject("hasil");
                                    boolean sukses = hasil.getBoolean("respon");
                                    if (sukses){
                                        Log.d("0", "onResponse: "+sukses);
                                        Intent i = new Intent(RegisterActivity.this,ListdataActivity.class);
                                        startActivity(i);
                                        Toast.makeText(RegisterActivity.this, "Register Suskses", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Register Gagal", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (JSONException e){
                                    e.printStackTrace();
                                    System.out.println("pppp" + e.getMessage());
                                    Toast.makeText(RegisterActivity.this, "Register Gagal", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onError(ANError anError) {
                                System.out.println("ttttt" + anError);
                                System.out.println("ttttt" + anError.getErrorBody());
                                System.out.println("ttttt" + anError.getErrorDetail());
                                System.out.println("ttttt" + anError.getResponse());
                                System.out.println("ttttt" + anError.getErrorCode());

                                Toast.makeText(RegisterActivity.this, "Register Gagal", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
    }
}