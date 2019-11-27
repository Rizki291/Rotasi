package com.example.rizkikurniawan.rotasi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rizkikurniawan.rotasi.Adapter.MainSliderAdapter;
import com.example.rizkikurniawan.rotasi.Api.DtTenaga_kerja;
import com.example.rizkikurniawan.rotasi.Api.News;
import com.example.rizkikurniawan.rotasi.Api.News_data;
import com.example.rizkikurniawan.rotasi.Service.RetrofitClient;
import com.example.rizkikurniawan.rotasi.Service.RotasiApi;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main_Menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String[] myarrays=new String[5];
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    String[] title = new String[5];
    String[] Ids = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RotasiApi rotasiApi = RetrofitClient.getClient().create(RotasiApi.class);
        Call<News> call = rotasiApi.gethotnews();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Main_Menu.this, response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                ArrayList<News_data> mtable = new ArrayList<>();
                News news = response.body();
                String js = news.getData();
                //TextView txt1 = findViewById(R.id.textView23);
                try{
                    JSONArray obj = new JSONArray(js);
                    for(int i=0;i<obj.length();i++){
                        JSONObject jsonData = obj.getJSONObject(i);
                        myarrays[i]="http://spim.bpjk.info//rotasi/media/news/"+jsonData.getString("Image");
                        Ids[i] = jsonData.getString("Id");
                        title[i] = jsonData.getString("Title");
                    }
//                    txt1.setText(myarrays[0]);
                }catch (Exception ex){
                    Toast.makeText(Main_Menu.this,ex.getMessage(),Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(Main_Menu.this,"404 Not Found",Toast.LENGTH_LONG);
            }
        });
        //init banner
        init();

        //init button
        Button btn3 = (Button) findViewById(R.id.button3);Button btn4 = (Button) findViewById(R.id.button4);Button btn5 = (Button) findViewById(R.id.button5);
        Button btn6 = (Button) findViewById(R.id.button6);Button btn7 = (Button) findViewById(R.id.button7);Button btn9 = (Button) findViewById(R.id.button9);
        //button press
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_Menu.this, Tenaga_Kerja.class));
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_Menu.this, News_Board.class));
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_Menu.this, JadwalPelatihanV.class));
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_Menu.this, LPPK.class));
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_Menu.this, LSP_List.class));
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_Menu.this, QrCodeReader.class));
            }
        });
    }

    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MainSliderAdapter(Main_Menu.this,myarrays,title,Ids));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(5 * density);

        NUM_PAGES = myarrays.length;

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main__menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Tenaga_kerja) {
            startActivity(new Intent(Main_Menu.this, Tenaga_Kerja.class));
        } else if (id == R.id.nav_Sertifikat) {
            startActivity(new Intent(Main_Menu.this, Sertifikat.class));
        } else if (id == R.id.nav_LSP) {
            startActivity(new Intent(Main_Menu.this, LSP_List.class));
        } else if (id == R.id.nav_LPPK) {
            startActivity(new Intent(Main_Menu.this, LPPK.class));
        } else if(id == R.id.nav_Tenaga_Graf){
            startActivity(new Intent(Main_Menu.this, TK_Graph.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
