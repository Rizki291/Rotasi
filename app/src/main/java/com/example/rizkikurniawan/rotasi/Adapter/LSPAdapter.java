package com.example.rizkikurniawan.rotasi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.rizkikurniawan.rotasi.Api.ArrayListLSP;
import com.example.rizkikurniawan.rotasi.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LSPAdapter extends ArrayAdapter<ArrayListLSP> {
    private Context mContext;
    int mResource;

    public LSPAdapter(Context context, int resource, ArrayList<ArrayListLSP> obj ) {
        super(context, resource, obj);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String po1 = getItem(position).getName();
        String po2 = getItem(position).getLSPTypeName();
        String po3 = getItem(position).getAddress();
        String po4 = getItem(position).getProvinceName();
        String po5 = getItem(position).getTelephoneNumber();
        String po6 = getItem(position).getLogo();
        int po7 = getItem(position).getStatusId();
        int po8 = getItem(position).getId();
        String po9 = getItem(position).getCreationDate();
        ArrayListLSP listLSP = new ArrayListLSP(po1,po2,po3,po4,po5,po6,po7,po8,po9);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);
        TextView txt1 = convertView.findViewById(R.id.TxtName1);
        TextView txt2 = convertView.findViewById(R.id.TxtProvinceName);
        TextView txt3 = convertView.findViewById(R.id.TxTCreationDate);
        ImageView img1 = convertView.findViewById(R.id.ImageLsp);
        RequestOptions rq = new RequestOptions();
        rq.placeholder(R.drawable.giphy);

        Glide.with(mContext)
                .setDefaultRequestOptions(rq)
                .load("http://spim.bpjk.info/rotasi/media/"+po6)
                .into(img1);
        txt1.setText(po1);
        txt2.setText(po4);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formater.parse(po9);
            txt3.setText(formater.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
