package com.example.rizkikurniawan.rotasi.Api;

public class Table {

    String propinsi;
    String Ska1;
    String Ska2;
    String Ska3;
    String Skt1;
    String Skt2;
    String Skt3;
    String SIKI;
    String DP;
    String DK;
    String DS;

    public Table(String propinsi, String ska1, String ska2, String ska3, String skt1, String skt2, String skt3, String SIKI, String DP, String DK, String DS) {
        this.propinsi = propinsi;
        Ska1 = ska1;
        Ska2 = ska2;
        Ska3 = ska3;
        Skt1 = skt1;
        Skt2 = skt2;
        Skt3 = skt3;
        this.SIKI = SIKI;
        this.DP = DP;
        this.DK = DK;
        this.DS = DS;
    }

    public String getPropinsi() {
        return propinsi;
    }

    public void setPropinsi(String propinsi) {
        this.propinsi = propinsi;
    }

    public String getSka1() {
        return Ska1;
    }

    public void setSka1(String ska1) {
        Ska1 = ska1;
    }

    public String getSka2() {
        return Ska2;
    }

    public void setSka2(String ska2) {
        Ska2 = ska2;
    }

    public String getSka3() {
        return Ska3;
    }

    public void setSka3(String ska3) {
        Ska3 = ska3;
    }

    public String getSkt1() {
        return Skt1;
    }

    public void setSkt1(String skt1) {
        Skt1 = skt1;
    }

    public String getSkt2() {
        return Skt2;
    }

    public void setSkt2(String skt2) {
        Skt2 = skt2;
    }

    public String getSkt3() {
        return Skt3;
    }

    public void setSkt3(String skt3) {
        Skt3 = skt3;
    }

    public String getSIKI() {
        return SIKI;
    }

    public void setSIKI(String SIKI) {
        this.SIKI = SIKI;
    }

    public String getDP() {
        return DP;
    }

    public void setDP(String DP) {
        this.DP = DP;
    }

    public String getDK() {
        return DK;
    }

    public void setDK(String DK) {
        this.DK = DK;
    }

    public String getDS() {
        return DS;
    }

    public void setDS(String DS) {
        this.DS = DS;
    }


}
