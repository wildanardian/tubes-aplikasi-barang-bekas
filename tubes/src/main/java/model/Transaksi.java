/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author wildan
 */
public class Transaksi {
    private int idTransaksi;
    private User pembeli;
    private User penjual;
    private ArrayList<Barang> listBarang;
    private LocalDateTime tanggalTransaksi;
    private Pembayaran metodePembayaran;
    
    public Transaksi(){
        
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public User getPembeli() {
        return pembeli;
    }

    public void setPembeli(User pembeli) {
        this.pembeli = pembeli;
    }

    public User getPenjual() {
        return penjual;
    }

    public void setPenjual(User penjual) {
        this.penjual = penjual;
    }

    public ArrayList<Barang> getListBarang() {
        return listBarang;
    }

    public void setListBarang(ArrayList<Barang> listBarang) {
        this.listBarang = listBarang;
    }

    public LocalDateTime getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(LocalDateTime tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public Pembayaran getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(Pembayaran metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }
        
}
