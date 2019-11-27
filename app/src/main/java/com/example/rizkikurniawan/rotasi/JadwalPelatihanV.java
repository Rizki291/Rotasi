package com.example.rizkikurniawan.rotasi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rizkikurniawan.rotasi.Adapter.NewsAdapter;
import com.example.rizkikurniawan.rotasi.Adapter.TenagaKerjaAdapter;
import com.example.rizkikurniawan.rotasi.Api.DTJadwalPelatihan;
import com.example.rizkikurniawan.rotasi.Api.Table;
import com.example.rizkikurniawan.rotasi.Api.TblJPelatihan;
import com.example.rizkikurniawan.rotasi.Service.RetrofitClient;
import com.example.rizkikurniawan.rotasi.Service.RotasiApi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalPelatihanV extends AppCompatActivity {
    int c = 1;
    int eu = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_pelatihan_v);
        final Button btn1 = findViewById(R.id.btnNixt);
        final Button btn2 = findViewById(R.id.btnPriv);
        final ListView mListView = (ListView) findViewById(R.id.LvJadwal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(JadwalPelatihanV.this);
        progressDoalog.setMax(100);
        progressDoalog.setTitle("Loading");
        progressDoalog.getWindow().setGravity(Gravity.CENTER);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
        final RotasiApi rotasiApi = RetrofitClient.getClient().create(RotasiApi.class);
        Call<DTJadwalPelatihan> call = rotasiApi.getJadwal(c,10);
        call.enqueue(new Callback<DTJadwalPelatihan>() {
            @Override
            public void onResponse(Call<DTJadwalPelatihan> call, Response<DTJadwalPelatihan> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(JadwalPelatihanV.this, response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                btn2.setEnabled(false);
                ArrayList<TblJPelatihan> mtable = new ArrayList<>();
                DTJadwalPelatihan news = response.body();
                String js = news.getData();
                try{
                    JSONArray obj = new JSONArray(js);
                    if(obj.length()<10){btn1.setEnabled(false);};
                    for(int i=0;i<obj.length();i++){
                        JSONObject jsonData = obj.getJSONObject(i);
                        TblJPelatihan tbl = new TblJPelatihan(jsonData.getString("Id"),jsonData.getString("NamaPelatihan"),jsonData.getString("ProgramPelatihan"),
                                jsonData.getString("TanggalPelaksana"),jsonData.getString("Provinsi"),jsonData.getString("BiayaPelatihan"),
                                jsonData.getString("NamaLPPK"),jsonData.getString("KontakLPPK"),jsonData.getString("Alamat"));
                        mtable.add(tbl);
                    }
                    TenagaKerjaAdapter adapter = new TenagaKerjaAdapter(JadwalPelatihanV.this,R.layout.listview_pelatihan,mtable);
                    mListView.setAdapter(adapter);
                    progressDoalog.dismiss();
                }catch (Exception ex){
                    Toast.makeText(JadwalPelatihanV.this,ex.getMessage(),Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<DTJadwalPelatihan> call, Throwable t) {

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDoalog.show();
                c+=1;
                Call<DTJadwalPelatihan> call = rotasiApi.getJadwal(c,10);
                call.enqueue(new Callback<DTJadwalPelatihan>() {
                    @Override
                    public void onResponse(Call<DTJadwalPelatihan> call, Response<DTJadwalPelatihan> response) {
                        ArrayList<TblJPelatihan> mtable = new ArrayList<>();
                        DTJadwalPelatihan news = response.body();
                        String js = news.getData();
                        try{
                            JSONArray obj = new JSONArray(js);
                            if(obj.length()<5){ btn2.setEnabled(true); btn1.setEnabled(false);
                            }else if(c>1){ btn2.setEnabled(true); btn1.setEnabled(true);
                            }else if(c<=1){ btn2.setEnabled(false); btn1.setEnabled(true);c=1; }
                            if(obj.length()<10){btn1.setEnabled(false);};
                            for(int i=0;i<obj.length();i++){
                                JSONObject jsonData = obj.getJSONObject(i);
                                TblJPelatihan tbl = new TblJPelatihan(jsonData.getString("Id"),jsonData.getString("NamaPelatihan"),jsonData.getString("ProgramPelatihan"),
                                        jsonData.getString("TanggalPelaksana"),jsonData.getString("Provinsi"),jsonData.getString("BiayaPelatihan"),
                                        jsonData.getString("NamaLPPK"),jsonData.getString("KontakLPPK"),jsonData.getString("Alamat"));
                                mtable.add(tbl);
                            }
                            TenagaKerjaAdapter adapter = new TenagaKerjaAdapter(JadwalPelatihanV.this,R.layout.listview_pelatihan,mtable);
                            mListView.setAdapter(adapter);
                            progressDoalog.dismiss();
                        }catch (Exception ex){
                            Toast.makeText(JadwalPelatihanV.this,ex.getMessage(),Toast.LENGTH_LONG);
                            progressDoalog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<DTJadwalPelatihan> call, Throwable t) {
                        progressDoalog.dismiss();
                        Toast.makeText(JadwalPelatihanV.this, "404 Not Found",Toast.LENGTH_LONG);

                    }
                });
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDoalog.show();
                c-=1;
                Call<DTJadwalPelatihan> call = rotasiApi.getJadwal(c,10);
                call.enqueue(new Callback<DTJadwalPelatihan>() {
                    @Override
                    public void onResponse(Call<DTJadwalPelatihan> call, Response<DTJadwalPelatihan> response) {
                        ArrayList<TblJPelatihan> mtable = new ArrayList<>();
                        DTJadwalPelatihan news = response.body();
                        String js = news.getData();
                        try{
                            JSONArray obj = new JSONArray(js);
                            if(obj.length()<5){ btn2.setEnabled(true); btn1.setEnabled(false);
                            }else if(c>1){ btn2.setEnabled(true); btn1.setEnabled(true);
                            }else if(c<=1){ btn2.setEnabled(false); btn1.setEnabled(true);c=1; }
                            if(obj.length()<10){btn1.setEnabled(false);};
                            for(int i=0;i<obj.length();i++){
                                JSONObject jsonData = obj.getJSONObject(i);
                                TblJPelatihan tbl = new TblJPelatihan(jsonData.getString("Id"),jsonData.getString("NamaPelatihan"),jsonData.getString("ProgramPelatihan"),
                                        jsonData.getString("TanggalPelaksana"),jsonData.getString("Provinsi"),jsonData.getString("BiayaPelatihan"),
                                        jsonData.getString("NamaLPPK"),jsonData.getString("KontakLPPK"),jsonData.getString("Alamat"));
                                mtable.add(tbl);
                            }
                            TenagaKerjaAdapter adapter = new TenagaKerjaAdapter(JadwalPelatihanV.this,R.layout.listview_pelatihan,mtable);
                            mListView.setAdapter(adapter);
                            progressDoalog.dismiss();
                        }catch (Exception ex){
                            progressDoalog.dismiss();
                            Toast.makeText(JadwalPelatihanV.this,ex.getMessage(),Toast.LENGTH_LONG);
                        }
                    }

                    @Override
                    public void onFailure(Call<DTJadwalPelatihan> call, Throwable t) {
                        Toast.makeText(JadwalPelatihanV.this, "404 Not Found",Toast.LENGTH_LONG);
                    }
                });
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
