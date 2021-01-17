package com.dedy.katalogsmartphone;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

public class Smartphone {



    public static final String Baru="Baru";
    public static final String Bekas="Bekas";

    private String id;
    private Date tanggal;
    private String Brand;
    private String Spesifikasi;
    private String harga;
    private String jenis;
    private String model;

    public Smartphone() {
        this.id = UUID.randomUUID().toString();
        this.tanggal = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getSpesifikasi() {
        return Spesifikasi;
    }

    public void setSpesifikasi(String spesifikasi) {
        this.Spesifikasi = spesifikasi;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        this.Brand = brand;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public static Smartphone fromJSONObject(JSONObject obj) {
        Smartphone tr = new Smartphone();
        try {
            tr.setId(obj.getString("id"));
            tr.setTanggal(new Date(obj.getLong("tanggal")));
            tr.setBrand(obj.getString("brand"));
            tr.setModel(obj.getString("model"));
            tr.setSpesifikasi(obj.getString("spesifikasi"));
            tr.setHarga(obj.getString("harga"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tr;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id",this.id);
            jsonObj.put("tanggal",this.tanggal.getTime());
            jsonObj.put("brand",this.Brand);
            jsonObj.put("spesifikasi",this.Spesifikasi);
            jsonObj.put("harga",this.harga);
            jsonObj.put("jenis",this.jenis);
            jsonObj.put("model",this.model);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}