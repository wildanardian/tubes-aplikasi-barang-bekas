/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author wildan
 */
public class BarangTransaksi {
    private int id;
    private int id_barang;
    private int id_transaksi;
    private String nama_barang;

    public BarangTransaksi() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id_barang_transaksi) {
        this.id = id_barang_transaksi;
    }
    
    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }
    
    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }
    
}
