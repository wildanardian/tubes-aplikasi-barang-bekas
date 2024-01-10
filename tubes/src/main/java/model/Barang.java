/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author wildan
 */
    public class Barang {
    private int idBarang;
    private String namaBarang;
    private Kategori kategori;
    private double harga;
    private ArrayList<Barang> listBarang = new ArrayList<>();
    private int stok;

    public void Barang(){
        
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public double getHarga() {
        return harga;
    }
    
    public String getKategori(){
        return kategori.getNama();
    }

    public int getStok() {
        return stok;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setListBarang(ArrayList<Barang> listBarang) {
        this.listBarang = listBarang;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
    
    
    public void tampilkanBarang(){
        System.out.println("Barang: " + namaBarang 
                + " dengan kategori " + kategori 
                + " stok: " + stok);
    }
    
    public ArrayList<Barang> searchBarangByKategori(Barang barang){
        ArrayList<Barang> listKategoriBarang = new ArrayList<>();
        for(Barang b : listBarang){
            if(b.getKategori().equals(barang.getKategori())){
                listKategoriBarang.add(b);
            }
        }
        return listKategoriBarang;
    }
}
