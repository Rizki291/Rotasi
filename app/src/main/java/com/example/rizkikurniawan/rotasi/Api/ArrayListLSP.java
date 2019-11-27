package com.example.rizkikurniawan.rotasi.Api;

public class ArrayListLSP {
    private String Name;
    private String LSPTypeName;
    private String Address;
    private String ProvinceName;
    private String TelephoneNumber;
    private String Logo;
    private Integer StatusId;
    private Integer Id;
    private String CreationDate;

    public ArrayListLSP(String name, String LSPTypeName, String address, String provinceName, String telephoneNumber, String logo, Integer statusId, Integer id, String creationDate) {
        Name = name;
        this.LSPTypeName = LSPTypeName;
        Address = address;
        ProvinceName = provinceName;
        TelephoneNumber = telephoneNumber;
        Logo = logo;
        StatusId = statusId;
        Id = id;
        CreationDate = creationDate;
    }

    public String getName() {
        return Name;
    }

    public String getLSPTypeName() {
        return LSPTypeName;
    }

    public String getAddress() {
        return Address;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public String getTelephoneNumber() {
        return TelephoneNumber;
    }

    public String getLogo() {
        return Logo;
    }

    public Integer getStatusId() {
        return StatusId;
    }

    public Integer getId() {
        return Id;
    }

    public String getCreationDate() {
        return CreationDate;
    }
}
