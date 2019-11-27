package com.example.rizkikurniawan.rotasi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rizkikurniawan.rotasi.Api.News_data;
import com.example.rizkikurniawan.rotasi.Api.TblJPelatihan;
import com.example.rizkikurniawan.rotasi.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TenagaKerjaAdapter extends ArrayAdapter<TblJPelatihan> {
    private Context mContext;
    int mResource;

    public TenagaKerjaAdapter(Context context, int resource, ArrayList<TblJPelatihan> obj) {
        super(context, resource, obj);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String skt1 = getItem(position).getId();
        String skt2 = getItem(position).getNamaPelatihan();
        String skt5 = getItem(position).getProgramPelatihan();
        String skt9 = getItem(position).getTanggalPelaksanaan();
        String skt3 = getItem(position).getProvinsi();
        String skt4 = getItem(position).getBiayaPelatihan();
        String skt6 = getItem(position).getNamaLPPK();
        String skt7 = getItem(position).getKontakLPPK();
        String skt8 = getItem(position).getAlamat();
        TblJPelatihan stf = new TblJPelatihan(skt1,skt2,skt5,skt9,skt3,skt4,skt6,skt7,skt8);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);
        TextView txt1 = convertView.findViewById(R.id.txtPPL);
        TextView txt2 = convertView.findViewById(R.id.txttglPelaksana);
        TextView txt3 = convertView.findViewById(R.id.txtprov);
        TextView txt4 = convertView.findViewById(R.id.txtbiayaPelatihan);
        TextView txt5 = convertView.findViewById(R.id.txtklsLPPK);
        TextView txt6 = convertView.findViewById(R.id.txtkontakLPPK);
        TextView txt7 = convertView.findViewById(R.id.txtalamat);
        txt1.setText(skt2);

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formater.parse(skt9);
            txt2.setText(formater.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txt3.setText(skt3);
        txt4.setText("Rp." + skt4);
        txt5.setText(skt6);
        txt6.setText(skt7);
        txt7.setText(skt8);
        return convertView;
    }
}
