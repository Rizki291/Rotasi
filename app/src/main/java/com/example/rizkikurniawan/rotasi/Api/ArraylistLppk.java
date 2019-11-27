package com.example.rizkikurniawan.rotasi.Api;

public class ArraylistLppk {
     String Name;
     String ProvinceName;
     String BuildingImage;
     String OwnerImage;
     String Address;
     String TelephoneNumber;
     String AccountEmail;
     int StatusId;
     int Id;
     String CreationDate;

    public ArraylistLppk(String name, String provinceName, String buildingImage, String ownerImage, String address, String telephoneNumber, String accountEmail, int statusId, int id, String creationDate) {
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

    public void setAddress(String address) {
        Address = address;
    }

    public String getTelephoneNumber() {
        return TelephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        TelephoneNumber = telephoneNumber;
    }

    public String getAccountEmail() {
        return AccountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        AccountEmail = accountEmail;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }

    public String getBuildingImage() {
        return BuildingImage;
    }

    public void setBuildingImage(String buildingImage) {
        BuildingImage = buildingImage;
    }

    public String getOwnerImage() {
        return OwnerImage;
    }

    public void setOwnerImage(String ownerImage) {
        OwnerImage = ownerImage;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(String creationDate) {
        CreationDate = creationDate;
    }
}
