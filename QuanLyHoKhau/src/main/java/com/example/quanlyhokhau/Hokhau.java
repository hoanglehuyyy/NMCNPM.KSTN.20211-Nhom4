package com.example.quanlyhokhau;

public class Hokhau {
    private int id_ho_khau;
    private String name_ho_khau;
    private String address_ho_khau;
    private String birth_ho_khau;
    private String cmt_ho_khau;

    public String getBirth_ho_khau() {
        return birth_ho_khau;
    }

    public void setBirth_ho_khau(String birth_ho_khau) {
        this.birth_ho_khau = birth_ho_khau;
    }

    public String getCmt_ho_khau() {
        return cmt_ho_khau;
    }

    public void setCmt_ho_khau(String cmt_ho_khau) {
        this.cmt_ho_khau = cmt_ho_khau;
    }

    public int getId_ho_khau() {
        return id_ho_khau;
    }

    public void setId_ho_khau(int id_ho_khau) {
        this.id_ho_khau = id_ho_khau;
    }

    public String getName_ho_khau() {
        return name_ho_khau;
    }

    public void setName_ho_khau(String name_ho_khau) {
        this.name_ho_khau = name_ho_khau;
    }

    public String getAddress_ho_khau() {
        return address_ho_khau;
    }

    public void setAddress_ho_khau(String address_ho_khau) {
        this.address_ho_khau = address_ho_khau;
    }

    public Hokhau(int id, String name, String address, String birth, String cmt){
        this.id_ho_khau = id;
        this.name_ho_khau = name;
        this.address_ho_khau = address;
        this.birth_ho_khau = birth;
        this.cmt_ho_khau = cmt;
    }
    public Hokhau(){

    }

    public void copy_hk(Hokhau a){
        this.setId_ho_khau(a.getId_ho_khau());
        this.setName_ho_khau(a.getName_ho_khau());
        this.setAddress_ho_khau(a.getAddress_ho_khau());
        this.setCmt_ho_khau(a.getCmt_ho_khau());
        this.setBirth_ho_khau(a.getBirth_ho_khau());
    }
}
