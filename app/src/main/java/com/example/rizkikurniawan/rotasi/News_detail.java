package com.example.rizkikurniawan.rotasi;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rizkikurniawan.rotasi.Api.News;
import com.example.rizkikurniawan.rotasi.Api.NewsDetail;
import com.example.rizkikurniawan.rotasi.Service.RetrofitClient;
import com.example.rizkikurniawan.rotasi.Service.RotasiApi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class News_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        RotasiApi rotasiApi = RetrofitClient.getClient().create(RotasiApi.class);
        final RetrofitClient rfc = new RetrofitClient();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        int Id = Integer.valueOf(getIntent().getStringExtra("id"));
        final TextView txt1 = findViewById(R.id.txtTitle);
        final TextView txt2 = findViewById(R.id.txtdescription);
        final TextView txt3 = findViewById(R.id.txtdate1);
        final ImageView img1 = findViewById(R.id.imgnews);
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(News_detail.this);

        progressDoalog.setMax(100);
        progressDoalog.setTitle("Loading");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
        Call<List<NewsDetail>> call = rotasiApi.getnewsdetail(Id);
        call.enqueue(new Callback<List<NewsDetail>>() {
            @Override
            public void onResponse(Call<List<NewsDetail>> call, Response<List<NewsDetail>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(News_detail.this, response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                List<NewsDetail> news = response.body();
                for(NewsDetail nw : news){
                    txt1.setText(rfc.stripHtml(nw.getTitle()));
                    txt2.setText(rfc.stripHtml(nw.getDescription()));
                    Glide.with(News_detail.this)
                            .load("http://111.67.75.212/rotasi/media/news/"+nw.getImage())
                            .into(img1);
                    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = formater.parse(nw.getPublishDate());
                        txt3.setText(formater.format(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //txt3.setText(nw.getPublishDate());
                }
                progressDoalog.dismiss();
            }

            @Override
            public void onFailure(Call<List<NewsDetail>> call, Throwable t) {
                Toast.makeText(News_detail.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
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
