package com.example.rizkikurniawan.rotasi.Api;

public class DtTblLsp {
    private String Propinsi;
    private String TK;
    private String TKS;
    private String GAP;

    public DtTblLsp(String propinsi, String TK, String TKS, String gap) {
        Propinsi = propinsi;
        this.TK = TK;
        this.TKS = TKS;
        GAP = gap;
    }

    public String getPropinsi() {
        return Propinsi;
    }

    public void setPropinsi(String propinsi) {
        Propinsi = propinsi;
    }

    public String getTK() {
        return TK;
    }

    public void setTK(String TK) {
        this.TK = TK;
    }

    public String getTKS() {
        return TKS;
    }

    public void setTKS(String TKS) {
        this.TKS = TKS;
    }

    public String getGap() {
        return GAP;
    }

    public void setGap(String gap) {
        GAP = gap;
    }
}
