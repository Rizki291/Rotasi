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

import retrofit2.Callback;

public class TblListAdapter extends ArrayAdapter<Table> {
    private Context mContext;
    int mResource;
    int a; int b; int total, total2, total3, total4, total5, total6, total7, total8, total9, c;

    public TblListAdapter(Context context, int resource, ArrayList<Table> object) {
        super(context, resource, object);
        this.mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        a=0;b=0;c=0;total=0;total2=0;total3=0;total4=0;total5=0;total6=0;total7=0;total8=0;total9=0;
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
        TextView tvName1=(TextView) convertView.findViewById(R.id.txtProv);
        TextView tvName2=(TextView) convertView.findViewById(R.id.txtUtama);
        TextView tvName3=(TextView) convertView.findViewById(R.id.txtMadya);
        TextView tvName4=(TextView) convertView.findViewById(R.id.txtMuda);
        TextView tvName5=(TextView) convertView.findViewById(R.id.txtprice);
        TextView tvName6=(TextView) convertView.findViewById(R.id.txtkls1);
        TextView tvName7=(TextView) convertView.findViewById(R.id.txtkls2);
        TextView tvName8=(TextView) convertView.findViewById(R.id.txtkls3);
        TextView tvName9=(TextView) convertView.findViewById(R.id.txtkls4);
        TextView tvName10=(TextView) convertView.findViewById(R.id.TkTot);
        tvName1.setText(prov);
        tvName2.setText(ska1);
        tvName3.setText(ska2);
        tvName4.setText(ska3);
        a=Integer.valueOf(ska1)+Integer.valueOf(ska2)+Integer.valueOf(ska3);
        tvName5.setText(String.valueOf(a));
        tvName6.setText(skt1);
        tvName7.setText(skt2);
        tvName8.setText(skt3);
        b=Integer.valueOf(skt1)+Integer.valueOf(skt2)+Integer.valueOf(skt3);
        tvName9.setText(String.valueOf(b));
        tvName10.setText(Siki);
        return convertView;
    }
}
