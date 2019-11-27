package com.example.rizkikurniawan.rotasi;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
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
import com.example.rizkikurniawan.rotasi.Adapter.LppkAdapter;
import com.example.rizkikurniawan.rotasi.Api.ArraylistLppk;
import com.example.rizkikurniawan.rotasi.Api.DtLppks;
import com.example.rizkikurniawan.rotasi.Service.RetrofitClient;
import com.example.rizkikurniawan.rotasi.Service.RotasiApi;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LPPK extends AppCompatActivity {

    private Button btnprev,btnnext;
    private ArrayList<String> dt1;
    private int LastPage, currentPage =0;
    final Context context = LPPK.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lppk);
        final ArrayList<ArraylistLppk> lpk = new ArrayList<>();
        final ArrayList<ArraylistLppk> lpk2 = new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final ProgressDialog progressDoalog;
        final Spinner spinner = findViewById(R.id.spinner);
        final List<String> list = new ArrayList<String>();
        progressDoalog = new ProgressDialog(LPPK.this);
        progressDoalog.setMax(100);
        progressDoalog.setTitle("Loading");
        progressDoalog.getWindow().setGravity(Gravity.CENTER);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
        final TextView txt = findViewById(R.id.textView14);
        final ListView lv = findViewById(R.id.ListViewLppk);
        RotasiApi rotasiApi = RetrofitClient.getClient().create(RotasiApi.class);
        Call<List<DtLppks>> call = rotasiApi.getlppk();
        call.enqueue(new Callback<List<DtLppks>>() {
            @Override
            public void onResponse(Call<List<DtLppks>> call, Response<List<DtLppks>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LPPK.this, response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                List<DtLppks> dtLppks = response.body();

                    list.add("ALL");
                    for(DtLppks post : dtLppks){
                        ArraylistLppk tbl = new ArraylistLppk(post.getName(),post.getProvinceName(),post.getBuildingImage(),post.getOwnerImage(),post.getAddress(),post.getTelephoneNumber(),post.getAccountEmail(),post.getStatusId(),
                                post.getId(),post.getCreationDate());
                        if(list.contains(post.getProvinceName())){
                        }else{ list.add(post.getProvinceName()); }
                       lpk.add(tbl);
            }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(LPPK.this, android.R.layout.simple_spinner_item, list);
                spinner.setAdapter(dataAdapter);
                LppkAdapter adapter = new LppkAdapter(LPPK.this, R.layout.listview_lppk, lpk);
                lv.setAdapter(adapter);
                progressDoalog.dismiss();
            }

            @Override
            public void onFailure(Call<List<DtLppks>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(LPPK.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner.getSelectedItem().equals("ALL")){
                    LppkAdapter adapter = new LppkAdapter(LPPK.this, R.layout.listview_lppk, lpk);
                    lv.setAdapter(adapter);
                }else {
                    for (int i = 0; i<=lpk.size()-1; i++) {
                        if (lpk.get(i).getProvinceName().equals(list.get(position))) {
                            ArraylistLppk tbl1 = new ArraylistLppk(lpk.get(i).getName(), lpk.get(i).getProvinceName(), lpk.get(i).getBuildingImage(), lpk.get(i).getOwnerImage(), lpk.get(i).getAddress(), lpk.get(i).getTelephoneNumber(), lpk.get(i).getAccountEmail(), lpk.get(i).getStatusId(),
                                    lpk.get(i).getId(), lpk.get(i).getCreationDate());
                            lpk2.add(tbl1);
                        }
                    }
                    LppkAdapter adapter = new LppkAdapter(LPPK.this, R.layout.listview_lppk, lpk2);
                    lv.setAdapter(adapter);
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
                dialog.setContentView(R.layout.detail_lppks);
                dialog.setTitle("This Is Title");
                TextView text = (TextView) dialog.findViewById(R.id.textView18);
                TextView text2 = (TextView) dialog.findViewById(R.id.textView24);
                TextView text3 = (TextView) dialog.findViewById(R.id.textView25);
                TextView text4 = (TextView) dialog.findViewById(R.id.textView26);
                TextView text5 = (TextView) dialog.findViewById(R.id.textView27);
                String sut1 = String.valueOf(lpk.get(position).getName());
                String sut2 = String.valueOf(lpk.get(position).getOwnerImage());
                String sut3 = String.valueOf(lpk.get(position).getAddress());
                String sut4 = String.valueOf(lpk.get(position).getTelephoneNumber());
                String sut5 = String.valueOf(lpk.get(position).getAccountEmail());
                text.setText(sut1);
                text2.setText(sut2);
                text3.setText(sut3);
                text4.setText(sut4);
                text5.setText(sut5);
                ImageView image = (ImageView) dialog.findViewById(R.id.imageView5);
                image.setImageResource(R.drawable.ic_menu_camera);
                Glide.with(LPPK.this).load("http://111.67.75.212/rotasi/media/"+lpk.get(position).getBuildingImage()).into(image);
                Button dialogButton = (Button) dialog.findViewById(R.id.button);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                //String selectedItem = String.valueOf(lpk.get(position).getId());
                //txt.setText(selectedItem);
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
