/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.Kategori;

/**
 *
 * @author wildan
 */
public interface InterfaceKategori {
    public void insert(Kategori kategori);
    public List<Kategori> getKategori();
}
