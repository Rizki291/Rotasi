package com.example.rizkikurniawan.rotasi.Api;

public class TblJPelatihan {
    private String Id;
    private String NamaPelatihan;
    private String ProgramPelatihan;
    private String TanggalPelaksanaan;
    private String Provinsi;
    private String BiayaPelatihan;
    private String NamaLPPK;
    private String KontakLPPK;
    private String Alamat;

    public TblJPelatihan(String id, String namaPelatihan, String programPelatihan, String tanggalPelaksanaan, String provinsi, String biayaPelatihan, String namaLPPK, String kontakLPPK, String alamat) {
        Id = id;
        NamaPelatihan = namaPelatihan;
        ProgramPelatihan = programPelatihan;
        TanggalPelaksanaan = tanggalPelaksanaan;
        Provinsi = provinsi;
        BiayaPelatihan = biayaPelatihan;
        NamaLPPK = namaLPPK;
        KontakLPPK = kontakLPPK;
        Alamat = alamat;
    }

    public String getId() {
        return Id;
    }

    public String getNamaPelatihan() {
        return NamaPelatihan;
    }

    public String getProgramPelatihan() {
        return ProgramPelatihan;
    }

    public String getTanggalPelaksanaan() {
        return TanggalPelaksanaan;
    }

    public String getProvinsi() {
        return Provinsi;
    }

    public String getBiayaPelatihan() {
        return BiayaPelatihan;
    }

    public String getNamaLPPK() {
        return NamaLPPK;
    }

    public String getKontakLPPK() {
        return KontakLPPK;
    }

    public String getAlamat() {
        return Alamat;
    }
}
