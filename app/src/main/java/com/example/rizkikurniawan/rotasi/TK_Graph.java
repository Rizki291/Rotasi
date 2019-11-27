package com.example.rizkikurniawan.rotasi;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rizkikurniawan.rotasi.Api.DSTK;
import com.example.rizkikurniawan.rotasi.Api.DtGraph;
import com.example.rizkikurniawan.rotasi.Api.DtTblLsp;
import com.example.rizkikurniawan.rotasi.Api.Dtlsp;
import com.example.rizkikurniawan.rotasi.Service.RetrofitClient;
import com.example.rizkikurniawan.rotasi.Service.RotasiApi;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TK_Graph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tk__graph);
        RotasiApi rotasiApi = RetrofitClient.getClient().create(RotasiApi.class);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(TK_Graph.this);
        progressDoalog.setMax(100);
        progressDoalog.setTitle("Loading");
        progressDoalog.getWindow().setGravity(Gravity.CENTER);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
        Call<DSTK> call = rotasiApi.getdashgraph();
        call.enqueue(new Callback<DSTK>() {
            @Override
            public void onResponse(Call<DSTK> call, Response<DSTK> response) {
                if(!response.isSuccessful()){
                    //txtVresult.setText("Table : " + response.code());
                    Toast.makeText(TK_Graph.this, response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                final ArrayList<DtGraph> mtable = new ArrayList<>();
                DSTK dstk = response.body();

                String aData = dstk.getData();
                try {
                    final JSONArray obj = new JSONArray(aData);
                    ArrayList<BarEntry> entries = new ArrayList<>();
                    ArrayList<BarEntry> entries2 = new ArrayList<>();
                    final ArrayList<String> labels = new ArrayList<String>();
                    for (int i = 0; i < obj.length(); i++) {
                        JSONObject jsonData = obj.getJSONObject(i);
                        entries.add(new BarEntry(i,Float.valueOf(jsonData.getString("TK"))));
                        entries2.add(new BarEntry(i,Float.valueOf(jsonData.getString("TKS"))));
                        labels.add(jsonData.getString("Propinsi"));
                    }
                    HorizontalBarChart chart = new HorizontalBarChart(TK_Graph.this);
                    /////
                    BarDataSet dataset = new BarDataSet(entries, "Tenaga Kerja");
                    dataset.setColor(Color.rgb(100,255,100));
                    dataset.setValueTextSize(6);
                    /////
                    BarDataSet dataset2 = new BarDataSet(entries2, "Tenaga Kerja Bersertifikat");
                    dataset2.setColor(Color.rgb(255,100,100));
                    dataset2.setValueTextSize(6);
                    dataset2.setValueTextColor(Color.rgb(150,150,150));
                    /////
                    ArrayList<IBarDataSet> dts = new ArrayList<IBarDataSet>();
                    dts.add(dataset);
                    dts.add(dataset2);
                    BarData data = new BarData(dts);
                    data.setBarWidth(0.5f);
                    /////
                    chart.setData(data);
                    chart.getAxisLeft().setDrawLabels(false);
                    chart.getAxisRight().setDrawLabels(false);
                    XAxis xAxis = chart.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
                    xAxis.setCenterAxisLabels(true);
                    xAxis.setGranularity(1f);
                    xAxis.setGranularityEnabled(true);
                    Description description = new Description();
                    description.setText("");
                    chart.setDescription(description);
                    chart.getXAxis().setAxisMinimum(0);
                    chart.getXAxis().setAxisMaximum(obj.length());

                    chart.groupBars(0, 0, 0); // perform the "explicit" grouping
                    chart.invalidate();

                    setContentView(chart);
                    chart.animateX(3000);

                    xAxis.setTextSize(10);

                    xAxis.setLabelCount(obj.length());

                    Legend l = chart.getLegend();
                    l.setFormSize(10f); // set the size of the legend forms/shapes
                    l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
                    l.setTextSize(12f);
                    l.setTextColor(Color.BLACK);
                    l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
                    l.setYEntrySpace(5f); // set the space between the legend entries on the y-axis
                    progressDoalog.dismiss();
                }catch (Exception ex){
                    Toast.makeText(TK_Graph.this, ex.getMessage(),Toast.LENGTH_LONG);
                    progressDoalog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<DSTK> call, Throwable t) {
                    Toast.makeText(TK_Graph.this, t.getMessage(),Toast.LENGTH_LONG);
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
