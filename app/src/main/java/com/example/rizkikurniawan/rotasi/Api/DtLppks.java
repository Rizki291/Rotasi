package com.example.rizkikurniawan.rotasi.Api;

import com.google.gson.annotations.SerializedName;

public class DtLppks {
    private String Name;
    private String ProvinceName;
    private String BuildingImage;

    @SerializedName("OwnerName")
    private String OwnerImage;

    private String Address;
    private String TelephoneNumber;
    private String AccountEmail;
    private int StatusId;
    private int Id;
    private String CreationDate;

    public DtLppks(String name, String provinceName, String buildingImage, String ownerImage, String address, String telephoneNumber, String accountEmail, int statusId, int id, String creationDate) {
        Name = name;
        ProvinceName = provinceName;
        BuildingImage = buildingImage;
        OwnerImage = ownerImage;
        Address = address;
        TelephoneNumber = telephoneNumber;
        AccountEmail = accountEmail;
        StatusId = statusId;
        Id = id;
        CreationDate = creationDate;
    }

    public String getAddress() {
        return Address;
    }

    public String getTelephoneNumber() {
        return TelephoneNumber;
    }

    public String getAccountEmail() {
        return AccountEmail;
    }

    public String getName() {
        return Name;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public String getBuildingImage() {
        return BuildingImage;
    }

    public String getOwnerImage() {
        return OwnerImage;
    }

    public int getStatusId() {
        return StatusId;
    }

    public int getId() {
        return Id;
    }

    public String getCreationDate() {
        return CreationDate;
    }

}
