/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.BarangTransaksi;

/**
 *
 * @author wildan
 */
public interface InterfaceBarangTransaksi {
    public void insertBarangTransaksi(BarangTransaksi barang);
    public void updateBarangTransaksi(int id);
    public void deleteBarangTransaksi(int id);
    public List<BarangTransaksi> getListTransaksi(int id_transaksi);
}
