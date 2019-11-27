package com.example.rizkikurniawan.rotasi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rizkikurniawan.rotasi.Api.DataSertifikat;
import com.example.rizkikurniawan.rotasi.Service.RetrofitClient;
import com.example.rizkikurniawan.rotasi.Service.RotasiApi;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrCodeReader extends AppCompatActivity implements View.OnClickListener{
    private Button buttonScan;
    private TextView txtno, textViewName, txttanggal;
    private ImageView logo;
    private IntentIntegrator qrScan;
    ProgressDialog progressDoalog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_reader);
        //View objects
        progressDoalog = new ProgressDialog(QrCodeReader.this);
        progressDoalog.setMax(100);
        progressDoalog.setTitle("Loading");
        progressDoalog.getWindow().setGravity(Gravity.CENTER);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        buttonScan = (Button) findViewById(R.id.buttonScan);
        txtno = (TextView) findViewById(R.id.txtNo);
        textViewName = (TextView) findViewById(R.id.textViewName);
        txttanggal = (TextView) findViewById(R.id.txtTanggal);
        logo = (ImageView) findViewById(R.id.LogoV);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //intializing scan object
        qrScan = new IntentIntegrator(this);
        //attaching onclick listener
        buttonScan.setOnClickListener(this);
    }
    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        progressDoalog.show();
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
                progressDoalog.dismiss();
            } else {
                //if qr contains data
                RotasiApi rotasiApi = RetrofitClient.getClient().create(RotasiApi.class);
                Call<DataSertifikat> call = rotasiApi.getDataSertifikat(result.getContents());
                call.enqueue(new Callback<DataSertifikat>() {
                    @Override
                    public void onResponse(Call<DataSertifikat> call, Response<DataSertifikat> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(QrCodeReader.this, response.code(),
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        DataSertifikat ds1 = response.body();
                        txtno.setText(ds1.getNo());
                        textViewName.setText(ds1.getNama());

                        //txttanggal.setText(ds1.getTanggal());
                        SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
                        try {
                            Date date = formater.parse(ds1.getTanggal());
                            txttanggal.setText(formater.format(date));
                        } catch (ParseException e) {
                            Toast.makeText(QrCodeReader.this,e.getMessage(),Toast.LENGTH_LONG);
                        }
                        Glide.with(QrCodeReader.this)
                                .load("http://111.67.75.212/rotasi/media/"+ds1.getLogo())
                                .into(logo);
                        progressDoalog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<DataSertifikat> call, Throwable t) {
                        Toast.makeText(QrCodeReader.this, "404 Not Found", Toast.LENGTH_LONG);
                        progressDoalog.dismiss();
                    }
                });
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
            progressDoalog.dismiss();
        }
    }
    @Override
    public void onClick(View view) {
        //initiating the qr code scan
        qrScan.initiateScan();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
