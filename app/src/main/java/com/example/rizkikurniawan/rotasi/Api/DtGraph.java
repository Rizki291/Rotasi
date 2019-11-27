package com.example.rizkikurniawan.rotasi.Api;

public class DtGraph {
    private String Propinsi;
    private String TK;
    private String TKS;
    private String GAP;

    public DtGraph(String propinsi, String TK, String TKS, String GAP) {
        Propinsi = propinsi;
        this.TK = TK;
        this.TKS = TKS;
        this.GAP = GAP;
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

    public String getGAP() {
        return GAP;
    }

    public void setGAP(String GAP) {
        this.GAP = GAP;
    }
}
