/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DaoBarang;
import dao.DaoBarangTransaksi;
import dao.DaoToko;
import dao.DaoTransaksi;
import dao.InterfaceBarang;
import dao.InterfaceBarangTransaksi;
import dao.InterfaceToko;
import dao.InterfaceTransaksi;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import model.Barang;
import model.BarangTransaksi;
import model.Pembeli;
import model.Penjual;
import model.TableModelBarang;
import model.TableModelBarangTransaksi;
import model.Transaksi;
import view.ViewBarang;
import view.ViewLogin;
import view.ViewTransaksi;

/**
 *
 * @author wildan
 */
public class ControllerBarang {

    ViewBarang frame;
    ViewTransaksi frameTransaksi;
    InterfaceBarang interfaceBarang;
    List<Barang> list_barang;

    InterfaceBarangTransaksi interfaceBarangTransaksi;
    List<BarangTransaksi> list_barang_transaksi;
    
    InterfaceTransaksi interfaceTransaksi;
    InterfaceToko interfaceToko;

    public ControllerBarang(ViewBarang frame) {
        this.frame = frame;
        interfaceBarang = new DaoBarang();
        interfaceBarangTransaksi = new DaoBarangTransaksi();
        interfaceTransaksi = new DaoTransaksi();
        interfaceToko = new DaoToko();
        list_barang = interfaceBarang.getData();
        list_barang_transaksi = interfaceBarangTransaksi.getListTransaksi(-1);
    }
    
    public void tambahBarangTransaksi() {
        if (frame.getTb_barang_bekas().getModel().getRowCount() != 0) {
            int selectedBarang = frame.getTb_barang_bekas().getSelectedRow();
            if (selectedBarang != -1) {
                Object id = frame.getTb_barang_bekas().getValueAt(selectedBarang, 0);
                Object nama = frame.getTb_barang_bekas().getValueAt(selectedBarang, 1);

                int id_barang = (int) id;
                String nama_barang = (String) nama;
                BarangTransaksi bt = new BarangTransaksi();
                bt.setId_barang(id_barang);
                bt.setId_transaksi(-1);
                bt.setNama_barang(nama_barang);
                interfaceBarangTransaksi.insertBarangTransaksi(bt);
                loadDataTransaksi(frame.getTb_transaksi());
            } else {
                JOptionPane.showMessageDialog(null, "Tidak ada barang yang dipilih");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data kosong");
        }
    }
   
    public void deleteBarangTransaksi() {
        if (frame.getTb_transaksi().getModel().getRowCount() != 0) {
            int selectedBarangTransaksi = frame.getTb_transaksi().getSelectedRow();
            if (selectedBarangTransaksi != -1) {
                Object id = frame.getTb_transaksi().getValueAt(selectedBarangTransaksi, 1);
                int id_barang = (int)id;
                interfaceBarangTransaksi.deleteBarangTransaksi(id_barang);
                loadDataTransaksi(frame.getTb_transaksi());
            } else {
                JOptionPane.showMessageDialog(null, "Tidak ada barang yang dipilih");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data kosong. Tidak bisa dihapus");
        }
    }
    
    public void loadDataTransaksi(JTable tabel_transaksi){
        list_barang_transaksi = interfaceBarangTransaksi.getListTransaksi(-1);
        TableModelBarangTransaksi tmb = new TableModelBarangTransaksi(list_barang_transaksi);
        tabel_transaksi.setModel(tmb);
        
        String[] stringHeader = {"No", "Nama Barang"};
        JTableHeader header = frame.getTb_transaksi().getTableHeader();
        TableColumn column;
        for (int i = 0; i < stringHeader.length; i++) {
            column = header.getColumnModel().getColumn(i);
            column.setHeaderValue(stringHeader[i]);
        }
        header.repaint();
    }

    public void getTabelBarang() {
        list_barang = interfaceBarang.getData();
        TableModelBarang tmb = new TableModelBarang(list_barang);
        frame.getTb_barang_bekas().setModel(tmb);

        String[] stringHeader = {"Id", "Nama Barang", "Kategori", "Harga", "Stok"};
        JTableHeader header = frame.getTb_barang_bekas().getTableHeader();
        TableColumn column;
        for (int i = 0; i < stringHeader.length; i++) {
            column = header.getColumnModel().getColumn(i);
            column.setHeaderValue(stringHeader[i]);
        }
        header.repaint();
    }
    
    public void checkoutBarang(String namaPembeli){
        Transaksi transaksi = new Transaksi();
        LocalDateTime tanggalTransaksi = LocalDateTime.now();
        
        Penjual penjual = new Penjual("penjual");
        Pembeli pembeli = new Pembeli(namaPembeli);
        
        transaksi.setTanggalTransaksi(tanggalTransaksi);
        transaksi.setPenjual(penjual);
        transaksi.setPembeli(pembeli);
        int id = interfaceTransaksi.insert(transaksi);
        
        interfaceBarangTransaksi.updateBarangTransaksi(id);
        frameTransaksi = new ViewTransaksi(id);
        frameTransaksi.setVisible(true);
        frame.setVisible(false);
    }
    
    public void logout(){
        frame.setVisible(false);
        ViewLogin login = new ViewLogin();
        login.setVisible(true);
    }
}
