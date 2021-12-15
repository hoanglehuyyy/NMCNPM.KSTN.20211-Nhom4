package com.example.quanlyhokhau;

import java.util.Date;

public class Hokhau {
    private int id_ho_khau;
    private int id_chu_ho;
    private String address_ho_khau;
    private String thanhpho_ho_khau;
    private String quanhuyen_ho_khau;
    private String phuongxa_ho_khau;
    private String ngaytao_ho_khau;

    public int getId_ho_khau() {
        return id_ho_khau;
    }

    public void setId_ho_khau(int id_ho_khau) {
        this.id_ho_khau = id_ho_khau;
    }

    public int getId_chu_ho() {
        return id_chu_ho;
    }

    public void setId_chu_ho(int id_chu_ho) {
        this.id_chu_ho = id_chu_ho;
    }

    public String getAddress_ho_khau() {
        return address_ho_khau;
    }

    public void setAddress_ho_khau(String address_ho_khau) {
        this.address_ho_khau = address_ho_khau;
    }

    public String getThanhpho_ho_khau() {
        return thanhpho_ho_khau;
    }

    public void setThanhpho_ho_khau(String thanhpho_ho_khau) {
        this.thanhpho_ho_khau = thanhpho_ho_khau;
    }

    public String getQuanhuyen_ho_khau() {
        return quanhuyen_ho_khau;
    }

    public void setQuanhuyen_ho_khau(String quanhuyen_ho_khau) {
        this.quanhuyen_ho_khau = quanhuyen_ho_khau;
    }

    public String getPhuongxa_ho_khau() {
        return phuongxa_ho_khau;
    }

    public void setPhuongxa_ho_khau(String phuongxa_ho_khau) {
        this.phuongxa_ho_khau = phuongxa_ho_khau;
    }

    public String getNgaytao_ho_khau() {
        return ngaytao_ho_khau;
    }

    public void setNgaytao_ho_khau(String ngaytao_ho_khau) {
        this.ngaytao_ho_khau = ngaytao_ho_khau;
    }

    public Hokhau(){

    }

    public Hokhau(int id_ho_khau, int id_chu_ho, String address_ho_khau, String thanhpho_ho_khau, String quanhuyen_ho_khau, String phuongxa_ho_khau, String ngaytao_ho_khau) {
        this.id_ho_khau = id_ho_khau;
        this.id_chu_ho = id_chu_ho;
        this.address_ho_khau = address_ho_khau;
        this.thanhpho_ho_khau = thanhpho_ho_khau;
        this.quanhuyen_ho_khau = quanhuyen_ho_khau;
        this.phuongxa_ho_khau = phuongxa_ho_khau;
        this.ngaytao_ho_khau = ngaytao_ho_khau;
    }

    public Hokhau(int id_ho_khau, int id_chu_ho, String address_ho_khau, String thanhpho_ho_khau, String ngaytao_ho_khau) {
        this.id_ho_khau = id_ho_khau;
        this.id_chu_ho = id_chu_ho;
        this.address_ho_khau = address_ho_khau;
        this.thanhpho_ho_khau = thanhpho_ho_khau;
        this.ngaytao_ho_khau = ngaytao_ho_khau;
    }

    public void copy_hk(Hokhau a){
        this.id_ho_khau = a.getId_ho_khau();
        this.id_chu_ho = a.getId_chu_ho();
        this.address_ho_khau = a.getAddress_ho_khau();
        this.thanhpho_ho_khau = a.getThanhpho_ho_khau();
        this.quanhuyen_ho_khau = a.getQuanhuyen_ho_khau();
        this.phuongxa_ho_khau = a.getPhuongxa_ho_khau();
        this.ngaytao_ho_khau = a.getNgaytao_ho_khau();
    }
}
