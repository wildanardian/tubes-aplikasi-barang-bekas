/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wildan
 */
public class Toko {
    private int idToko;
    private String namaToko;
    private String namaPemilik;
    private Barang listBarang[];

    public Toko() {
        
    }

    public Toko(int idToko, String namaToko, String namaPemilik) {
        this.idToko = idToko;
        this.namaToko = namaToko;
        this.namaPemilik = namaPemilik;
    }
    
    public int getIdToko() {
        return idToko;
    }

    public void setIdToko(int idToko) {
        this.idToko = idToko;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public Barang[] getListBarang() {
        return listBarang;
    }

    public void setListBarang(Barang[] listBarang) {
        this.listBarang = listBarang;
    }
    
    
}
