package com.example.rizkikurniawan.rotasi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rizkikurniawan.rotasi.Api.DtTenaga_kerja;
import com.example.rizkikurniawan.rotasi.Service.RotasiApi;
import com.example.rizkikurniawan.rotasi.Adapter.SertifikatListAdapter;
import com.example.rizkikurniawan.rotasi.Api.Table;
import com.example.rizkikurniawan.rotasi.Service.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sertifikat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sertifikat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final ListView mListView = (ListView) findViewById(R.id.ListView1);
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(Sertifikat.this);
        progressDoalog.setMax(100);
        progressDoalog.setTitle("Loading");
        progressDoalog.getWindow().setGravity(Gravity.CENTER);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
        RotasiApi rotasiApi = RetrofitClient.getClient().create(RotasiApi.class);
        Call<DtTenaga_kerja> call = rotasiApi.getsiki();
        call.enqueue(new Callback<DtTenaga_kerja>() {
            @Override
            public void onResponse(Call<DtTenaga_kerja> call, Response<DtTenaga_kerja> response) {
                if(!response.isSuccessful()){
                    //txtVresult.setText("Table : " + response.code());
                    Toast.makeText(Sertifikat.this, response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                ArrayList<Table> mtable = new ArrayList<>();
                DtTenaga_kerja lsTK = response.body();
                String js = lsTK.getTable();
                try {
                    JSONArray obj = new JSONArray(js);
                    for(int i=0;i<obj.length();i++){
                        JSONObject jsonData = obj.getJSONObject(i);
                        Table tbl = new Table(jsonData.getString("Propinsi"),jsonData.getString("SKA1"),jsonData.getString("SKA2"),jsonData.getString("SKA3"),jsonData.getString("SKT1"),
                                jsonData.getString("SKT2"),jsonData.getString("SKT3"),jsonData.getString("SIKI"),jsonData.getString("DP"),jsonData.getString("DK"),jsonData.getString("DS"));
                        mtable.add(tbl);
                    }
                    SertifikatListAdapter adapter = new SertifikatListAdapter(Sertifikat.this, R.layout.listview_sertifikat, mtable);
                    mListView.setAdapter(adapter);
                    progressDoalog.dismiss();
                }catch (Exception e){
                    Toast.makeText(Sertifikat.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DtTenaga_kerja> call, Throwable t) {
                Toast.makeText(Sertifikat.this, "No Connection", Toast.LENGTH_LONG).show();
                progressDoalog.dismiss();
            }
        });

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
