package com.example.rizkikurniawan.rotasi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rizkikurniawan.rotasi.Api.DtTenaga_kerja;
import com.example.rizkikurniawan.rotasi.Service.RotasiApi;
import com.example.rizkikurniawan.rotasi.Api.Table;
import com.example.rizkikurniawan.rotasi.Adapter.TblListAdapter;
import com.example.rizkikurniawan.rotasi.Service.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tenaga_Kerja extends AppCompatActivity {
    private TextView txtVresult;
    public  static final String Tag = "Tenaga_Kerja";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenaga__kerja);
        final ListView mListView = (ListView) findViewById(R.id.ListView1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(Tenaga_Kerja.this);
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
                    txtVresult.setText("Table : " + response.code());
                    return;
                }
                ArrayList<Table> mtable = new ArrayList<>();
                DtTenaga_kerja lsTK = response.body();
                //String content = "";
                //content += "Table : " + lsTK.getTable();
                String js = lsTK.getTable();
                try {
                    JSONArray obj = new JSONArray(js);
                    for(int i=0;i<obj.length();i++){
                        JSONObject jsonData = obj.getJSONObject(i);
                        Table tbl = new Table(jsonData.getString("Propinsi"),jsonData.getString("SKA1"),jsonData.getString("SKA2"),jsonData.getString("SKA3"),jsonData.getString("SKT1"),
                                jsonData.getString("SKT2"),jsonData.getString("SKT3"),jsonData.getString("SIKI"),jsonData.getString("DP"),jsonData.getString("DK"),jsonData.getString("DS"));
                        mtable.add(tbl);
                    }
                    TblListAdapter adapter = new TblListAdapter(Tenaga_Kerja.this, R.layout.listview_tk, mtable);
                    mListView.setAdapter(adapter);
                    progressDoalog.dismiss();
                }catch (Exception e){
                    progressDoalog.dismiss();
                    Toast.makeText(Tenaga_Kerja.this,e.getMessage(),Toast.LENGTH_LONG);
                }

            }
            @Override
            public void onFailure(Call<DtTenaga_kerja> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(Tenaga_Kerja.this,t.getMessage(),Toast.LENGTH_LONG);

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
