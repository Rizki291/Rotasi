package com.example.rizkikurniawan.rotasi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rizkikurniawan.rotasi.Adapter.NewsAdapter;
import com.example.rizkikurniawan.rotasi.Api.News;
import com.example.rizkikurniawan.rotasi.Api.News_data;
import com.example.rizkikurniawan.rotasi.Service.RetrofitClient;
import com.example.rizkikurniawan.rotasi.Service.RotasiApi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class News_Board extends AppCompatActivity {
    int c = 1;
    int eu = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__board);
        final Button btn1 = findViewById(R.id.button8);
        final Button btn2 = findViewById(R.id.button2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btn2.setEnabled(false);
        final RotasiApi rotasiApi = RetrofitClient.getClient().create(RotasiApi.class);
        final ArrayList<News_data> mtable = new ArrayList<>();
        final ListView mListView = (ListView) findViewById(R.id.ListViewNews);
        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(News_Board.this);
        progressDoalog.setMax(100);
        progressDoalog.setTitle("Loading");
        progressDoalog.getWindow().setGravity(Gravity.CENTER);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();

        final Call<News> call = rotasiApi.getnewspaging(c,5);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(News_Board.this, response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                News news = response.body();
                String js = news.getData();
                try{
                    JSONArray obj = new JSONArray(js);
                    if(obj.length()<5){btn1.setEnabled(false);};
                    for(int i=0;i<obj.length();i++){
                        JSONObject jsonData = obj.getJSONObject(i);
                        News_data tbl = new News_data(jsonData.getString("Id"),jsonData.getString("Title"),jsonData.getString("Image"),
                                jsonData.getString("PublishDate"));
                        mtable.add(tbl);
                    }
                    NewsAdapter adapter = new NewsAdapter(News_Board.this,R.layout.listview_news,mtable);
                    mListView.setAdapter(adapter);
                    progressDoalog.dismiss();
                }catch (Exception ex){
                    Toast.makeText(News_Board.this,ex.getMessage(),Toast.LENGTH_LONG);
                }
            }
            @Override
            public void onFailure(Call<News> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(News_Board.this,t.getMessage(),Toast.LENGTH_LONG);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDoalog.show();
                c+=1;
               Call<News> call = rotasiApi.getnewspaging(c,5);
                call.enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(News_Board.this, response.code(),
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        mtable.clear();
                        News news = response.body();
                        String js = news.getData();
                        try{
                            JSONArray obj = new JSONArray(js);
                            //eu = Integer.valueOf(news.getPaging());
                            if(obj.length()<5){ btn2.setEnabled(true); btn1.setEnabled(false);
                            }else if(c>1){ btn2.setEnabled(true); btn1.setEnabled(true);
                            }else if(c<=1){ btn2.setEnabled(false); btn1.setEnabled(true);c=1; }

                            for(int i=0;i<obj.length();i++){
                                JSONObject jsonData = obj.getJSONObject(i);
                                News_data tbl = new News_data(jsonData.getString("Id"),jsonData.getString("Title"),jsonData.getString("Image"),
                                        jsonData.getString("PublishDate"));
                                mtable.add(tbl);
                            }
                            NewsAdapter adapter = new NewsAdapter(News_Board.this,R.layout.listview_news,mtable);
                            mListView.setAdapter(adapter);
                            progressDoalog.dismiss();
                        }catch (Exception ex){
                            Toast.makeText(News_Board.this,ex.getMessage(),Toast.LENGTH_LONG);
                        }
                    }
                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        Toast.makeText(News_Board.this,t.getMessage(),Toast.LENGTH_LONG);
                    }
                });
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDoalog.show();
                c-=1;
                Call<News> call = rotasiApi.getnewspaging(c,5);
                call.enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(News_Board.this, response.code(),
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        mtable.clear();
                        News news = response.body();
                        String js = news.getData();
                        eu = Integer.valueOf(news.getTotal());
                        Toast.makeText(News_Board.this,"This is Next",Toast.LENGTH_LONG);
                        try{
                            JSONArray obj = new JSONArray(js);
                            if(obj.length()<5){ btn2.setEnabled(true); btn1.setEnabled(false);
                            }else if(c>1){ btn2.setEnabled(true); btn1.setEnabled(true);
                            }else if(c<=1){ btn2.setEnabled(false); btn1.setEnabled(true);c=1; }
                            for(int i=0;i<obj.length();i++){
                                JSONObject jsonData = obj.getJSONObject(i);
                                News_data tbl = new News_data(jsonData.getString("Id"),jsonData.getString("Title"),jsonData.getString("Image"),
                                        jsonData.getString("PublishDate"));
                                mtable.add(tbl);
                            }
                            NewsAdapter adapter = new NewsAdapter(News_Board.this,R.layout.listview_news,mtable);
                            mListView.setAdapter(adapter);
                            progressDoalog.dismiss();
                        }catch (Exception ex){
                            Toast.makeText(News_Board.this,ex.getMessage(),Toast.LENGTH_LONG);
                        }
                    }
                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        Toast.makeText(News_Board.this,t.getMessage(),Toast.LENGTH_LONG);
                    }
                });
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), News_detail.class);
                intent.putExtra("id", mtable.get(position).getId());
                startActivity(intent);
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
