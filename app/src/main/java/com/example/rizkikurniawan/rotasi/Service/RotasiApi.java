package com.example.rizkikurniawan.rotasi.Service;

import com.example.rizkikurniawan.rotasi.Api.DSTK;
import com.example.rizkikurniawan.rotasi.Api.DTJadwalPelatihan;
import com.example.rizkikurniawan.rotasi.Api.DataSertifikat;
import com.example.rizkikurniawan.rotasi.Api.DtLppks;
import com.example.rizkikurniawan.rotasi.Api.DtTenaga_kerja;
import com.example.rizkikurniawan.rotasi.Api.Dtlsp;
import com.example.rizkikurniawan.rotasi.Api.LSPData;
import com.example.rizkikurniawan.rotasi.Api.News;
import com.example.rizkikurniawan.rotasi.Api.NewsDetail;
import com.example.rizkikurniawan.rotasi.JadwalPelatihanV;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RotasiApi {

    //graph Tenaga Keja
    @GET("rotasi/api/charts/dashboardtenagakerja")
    Call<DSTK> getdashgraph();

    //Siki Table Api
    @GET("rotasi/api/charts/dashboardsiki")
    Call<DtTenaga_kerja> getsiki();

    //Lsp Table Api
    @GET("rotasi/api/charts/dashboardlsplist")
    Call<List<LSPData>> getlsp();

    //lppk image
    @GET("rotasi/api/charts/dashboardlppk")
    Call<List<DtLppks>> getlppk();

    //get hot news
    @GET("rotasi/api/news/getrow?from=1&to=5")
    Call<News> gethotnews();

    //get news paging
    @GET("rotasi/api/news/get")
    Call<News> getnewspaging(
            @Query("page") Integer stuff,
            @Query("paging") Integer stuff2
    );

    //get news detail
    @GET("rotasi/api/news/details")
    Call<List<NewsDetail>> getnewsdetail(
            @Query("id") Integer stuff
    );
    @GET("rotasi/api/pelatihan/get")
    Call<DTJadwalPelatihan> getJadwal(
            @Query("page") Integer stuff,
            @Query("paging") Integer stuff2
    );
    //get news paging
    @GET("rotasi/api/sertifikat/get")
    Call<DataSertifikat> getDataSertifikat(
            @Query("id") String stuff
    );
}
