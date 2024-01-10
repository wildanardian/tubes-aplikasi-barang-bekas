/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.Barang;

/**
 *
 * @author wildan
 */
public interface InterfaceBarang {
    public void insert(Barang barang);
    public void update(Barang barang);
    public void delete(int id);
    public List<Barang> getData();
    public Barang showBarang(int id);
    public List<Barang> searchBarangByKategori(String namaBarang);
    public void updateStokBarang(int stokBaru, int id);
}
