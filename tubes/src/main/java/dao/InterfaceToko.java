/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.Barang;
import model.Toko;

/**
 *
 * @author wildan
 */
public interface InterfaceToko {
    public Toko getDetailToko(int id);
    public void searchToko(String namaPemilik);
    public String getNamaPemilikToko();
}
