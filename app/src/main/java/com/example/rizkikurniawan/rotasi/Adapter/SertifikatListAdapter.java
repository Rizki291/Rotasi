package com.example.rizkikurniawan.rotasi.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rizkikurniawan.rotasi.Api.Table;
import com.example.rizkikurniawan.rotasi.R;

import java.util.ArrayList;
import java.util.List;

public class SertifikatListAdapter extends ArrayAdapter<Table> {
    private Context mContext;
    int mResource;

    public SertifikatListAdapter(Context context, int resource, ArrayList<Table> object) {
        super(context, resource, object);
        this.mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String prov = getItem(position).getPropinsi();
        String ska1 = getItem(position).getSka1();
        String ska2 = getItem(position).getSka2();
        String ska3 = getItem(position).getSka3();
        String skt1 = getItem(position).getSkt1();
        String skt2 = getItem(position).getSkt2();
        String skt3 = getItem(position).getSkt3();
        String dp = getItem(position).getDP();
        String dk = getItem(position).getDK();
        String Siki = getItem(position).getSIKI();
        String ds = getItem(position).getDS();
        Table tbl = new Table(prov,ska1,ska2,ska3,skt1,skt2,skt3,dp,dk,Siki,ds);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);
        TextView textView1 = (TextView) convertView.findViewById(R.id.textView3);
        TextView textView2 = (TextView) convertView.findViewById(R.id.textView4);
        TextView textView3 = (TextView) convertView.findViewById(R.id.textView5);
        TextView textView4 = (TextView) convertView.findViewById(R.id.textView6);
        TextView textView5 = (TextView) convertView.findViewById(R.id.textView7);
        TextView textView6 = (TextView) convertView.findViewById(R.id.textView8);
        TextView textView7 = (TextView) convertView.findViewById(R.id.textView9);
        TextView textView8 = (TextView) convertView.findViewById(R.id.textView10);
        TextView textView9 = (TextView) convertView.findViewById(R.id.textView11);
        TextView textView10 = (TextView) convertView.findViewById(R.id.textView12);
        TextView textView11 = (TextView) convertView.findViewById(R.id.textView13);
        textView1.setText(prov);
        textView2.setText(ska1);
        textView3.setText(ska2);
        textView4.setText(ska3);
        textView5.setText(skt1);
        textView6.setText(skt2);
        textView7.setText(skt3);
        textView8.setText(Siki);
        textView9.setText(dp);
        textView10.setText(dk);
        textView11.setText(ds);
        return convertView;
    }
}
