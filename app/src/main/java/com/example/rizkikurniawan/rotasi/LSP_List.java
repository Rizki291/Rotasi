package com.example.rizkikurniawan.rotasi;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rizkikurniawan.rotasi.Adapter.LSPAdapter;
import com.example.rizkikurniawan.rotasi.Api.ArrayListLSP;
import com.example.rizkikurniawan.rotasi.Api.ArraylistLppk;
import com.example.rizkikurniawan.rotasi.Api.DtLppks;
import com.example.rizkikurniawan.rotasi.Api.LSPData;
import com.example.rizkikurniawan.rotasi.Service.RetrofitClient;
import com.example.rizkikurniawan.rotasi.Service.RotasiApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LSP_List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsp__list);
        final Context context = LSP_List.this;
        final ArrayList<ArrayListLSP> lsps = new ArrayList<>();
        final ListView lv = findViewById(R.id.LspsList);
        final Spinner sp = findViewById(R.id.spinner2);
        final List<String> list = new ArrayList<String>();
        final ArrayList<ArrayListLSP> lpk2 = new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        RotasiApi rotasiApi = RetrofitClient.getClient().create(RotasiApi.class);
        Call<List<LSPData>> call = rotasiApi.getlsp();
        call.enqueue(new Callback<List<LSPData>>() {
            @Override
            public void onResponse(Call<List<LSPData>> call, Response<List<LSPData>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LSP_List.this, response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                List<LSPData> lspData = response.body();
                list.add("ALL");
                for (LSPData ldata : lspData){
                    ArrayListLSP arrayListLSP = new ArrayListLSP(ldata.getName(),ldata.getLSPTypeName(),ldata.getAddress(),
                            ldata.getProvinceName(),ldata.getTelephoneNumber(),ldata.getLogo(),ldata.getStatusId(),ldata.getId(),ldata.getCreationDate());
                    if(list.contains(arrayListLSP.getProvinceName())){
                    }else{ list.add(arrayListLSP.getProvinceName()); }
                    lsps.add(arrayListLSP);
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(LSP_List.this, android.R.layout.simple_spinner_item, list);
                sp.setAdapter(dataAdapter);
                LSPAdapter adapter = new LSPAdapter(LSP_List.this, R.layout.listview_lsp, lsps);
                lv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<LSPData>> call, Throwable t) {
                Toast.makeText(LSP_List.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sp.getSelectedItem().equals("ALL")){
                    LSPAdapter adapter = new LSPAdapter(LSP_List.this, R.layout.listview_lsp, lsps);
                    lv.setAdapter(adapter);
                }else {
                    for (int i = 0; i<=lsps.size()-1; i++) {
                        if (lsps.get(i).getProvinceName().equals(list.get(position))) {
                            ArrayListLSP tbl1 = new ArrayListLSP(lsps.get(i).getName(), lsps.get(i).getLSPTypeName(), lsps.get(i).getAddress(),
                                    lsps.get(i).getProvinceName(), lsps.get(i).getTelephoneNumber(), lsps.get(i).getLogo(), lsps.get(i).getStatusId(),
                                    lsps.get(i).getId(), lsps.get(i).getCreationDate());
                            lpk2.add(tbl1);
                        }
                    }
                    LSPAdapter adapter = new LSPAdapter(LSP_List.this, R.layout.listview_lsp, lpk2);
                    lv.setAdapter(adapter);
                    Toast.makeText(LSP_List.this, list.get(position),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.detail_lsp);
                dialog.setTitle("This Is Title");
                TextView text = (TextView) dialog.findViewById(R.id.TxtName);
                TextView text2 = (TextView) dialog.findViewById(R.id.textView36);
                TextView text3 = (TextView) dialog.findViewById(R.id.textView42);
                TextView text4 = (TextView) dialog.findViewById(R.id.textView45);
                String sut1 = String.valueOf(lsps.get(position).getName());
                String sut2 = String.valueOf(lsps.get(position).getAddress());
                String sut3 = String.valueOf(lsps.get(position).getProvinceName());
                String sut4 = String.valueOf(lsps.get(position).getTelephoneNumber());
                text.setText(sut1);
                text2.setText(sut2);
                text3.setText(sut3);
                text4.setText(sut4);
                ImageView image = (ImageView) dialog.findViewById(R.id.ImgVLSP);
                image.setImageResource(R.drawable.ic_menu_camera);
                Glide.with(LSP_List.this).load("http://111.67.75.212/rotasi/media/"+lsps.get(position).getLogo()).into(image);
                Button dialogButton = (Button) dialog.findViewById(R.id.btnTututp);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
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
