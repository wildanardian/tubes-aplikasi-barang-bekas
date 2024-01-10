/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.Barang;
import model.Transaksi;

/**
 *
 * @author wildan
 */
public interface InterfaceTransaksi {
    public Transaksi getTransaksi(int id);
    public int insert(Transaksi transaksi);
    public void update(Transaksi transaksi);
    public List<Barang> getListBarangTransaksi(int id);
}
