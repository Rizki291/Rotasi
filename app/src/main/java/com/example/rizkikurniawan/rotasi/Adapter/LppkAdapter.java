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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.rizkikurniawan.rotasi.Api.ArraylistLppk;
import com.example.rizkikurniawan.rotasi.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BitmapTransformation;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class LppkAdapter extends ArrayAdapter<ArraylistLppk> {
    private Context mContext;
    int mResource;

    public LppkAdapter(Context context, int resource, ArrayList<ArraylistLppk> obj) {
        super(context, resource, obj);
        this.mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        String stuf1 = getItem(position).getName();
        String stuf2 = getItem(position).getProvinceName();
        String stuf3 = getItem(position).getBuildingImage();
        String stuf4 = getItem(position).getOwnerImage();
        int stuf5 = getItem(position).getStatusId();
        int stuf6 = getItem(position).getId();
        String stuf7 = getItem(position).getCreationDate();
        String stuf8 = getItem(position).getAddress();
        String stuf9 = getItem(position).getTelephoneNumber();
        String stuf10 = getItem(position).getAccountEmail();
        ArraylistLppk tbl = new ArraylistLppk(stuf1,stuf2,stuf3,stuf4,stuf8,stuf9,stuf10,stuf5,stuf6,stuf7);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);
        TextView txt1 = (TextView) convertView.findViewById(R.id.textView16);
        TextView txt2 = (TextView) convertView.findViewById(R.id.textView17);
        TextView txt3 = (TextView) convertView.findViewById(R.id.textView15);
        ImageView img1 = (ImageView) convertView.findViewById(R.id.imageView4);
        Glide.with(mContext).load("http://spim.bpjk.info/rotasi/media/"+stuf3).apply(new RequestOptions().transform(new RoundedCorners(10))).into(img1);
        txt3.setText(stuf1);
        txt2.setText(stuf2);
        txt1.setText(stuf4);
        return convertView;
    }

}

