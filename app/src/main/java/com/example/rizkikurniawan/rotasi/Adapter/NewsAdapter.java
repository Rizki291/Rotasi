package com.example.rizkikurniawan.rotasi.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.rizkikurniawan.rotasi.Api.News_data;
import com.example.rizkikurniawan.rotasi.R;

import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsAdapter extends ArrayAdapter<News_data> {
    private Context mContext;
    int mResource;

    public NewsAdapter(Context context, int resource, ArrayList<News_data> obj) {
        super(context, resource, obj);
        this.mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String news1 = getItem(position).getId();
        String news2 = getItem(position).getTitle();
        String news3 = getItem(position).getImage();
        String news4 = getItem(position).getPublishDate();
        News_data stf = new News_data(news1,news2,news3,news4);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);
        TextView trxt1 = (TextView) convertView.findViewById(R.id.txtTitle);
        TextView trxt2 = (TextView) convertView.findViewById(R.id.txtDate);
        ImageView irmg1 = (ImageView) convertView.findViewById(R.id.ImgNews);
        trxt1.setText(news2);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formater.parse(news4);
            trxt2.setText(formater.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RequestOptions rq = new RequestOptions();
        rq.placeholder(R.drawable.giphy);
        Glide.with(mContext)
                .setDefaultRequestOptions(rq)
                .load("http://spim.bpjk.info/rotasi/media/news/"+news3)
                .into(irmg1);
        return convertView;

    }
}
