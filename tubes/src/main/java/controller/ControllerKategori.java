/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DaoKategori;
import dao.InterfaceKategori;
import java.util.List;
import javax.swing.DefaultListModel;
import model.Kategori;
import view.ViewKategori;

/**
 *
 * @author wildan
 */
public class ControllerKategori {
    ViewKategori frame;
    InterfaceKategori interfaceKategori;
    
    List<Kategori> list_kategori;

    public ControllerKategori(ViewKategori frame) {
        this.frame = frame;
        interfaceKategori = new DaoKategori();
    }
    
    public void getKategori(){
        list_kategori = interfaceKategori.getKategori();
        DefaultListModel<String> listModelKategori = new DefaultListModel<>();
        
        if(!list_kategori.isEmpty()){
            for(Kategori k: list_kategori){
                listModelKategori.addElement(k.getNama());
            }
            frame.getList_kategori().setModel(listModelKategori);
        }
    }
    
    public void tambahKategori(){
        String namaKategori = frame.getTf_nama_kategori().getText();
        Kategori kategori = new Kategori(namaKategori);
        interfaceKategori.insert(kategori);
    }
    
    
}
