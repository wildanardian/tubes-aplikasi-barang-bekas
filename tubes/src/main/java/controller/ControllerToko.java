/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DaoBarang;
import dao.DaoKategori;
import dao.DaoToko;
import dao.InterfaceBarang;
import dao.InterfaceKategori;
import dao.InterfaceToko;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import model.Barang;
import model.Kategori;
import model.TableModelBarang;
import view.ViewKategori;
import view.ViewLogin;
import view.ViewToko;

/**
 *
 * @author wildan
 */
public class ControllerToko {

    ViewToko frame;
    InterfaceToko interfaceToko;

    InterfaceBarang interfaceBarang;
    List<Barang> list_barang;

    InterfaceKategori interfaceKategori;
    List<Kategori> list_kategori;

    public ControllerToko(ViewToko frame) {
        this.frame = frame;
        interfaceToko = new DaoToko();
        interfaceBarang = new DaoBarang();
        interfaceKategori = new DaoKategori();
        list_barang = interfaceBarang.getData();
    }

    public void getToko(String username) {
        interfaceToko.searchToko(username);
    }

    public void getKategori() {
        list_kategori = interfaceKategori.getKategori();
        if (!list_kategori.isEmpty()) {
            for (Kategori k : list_kategori) {
                frame.getCb_kategori().addItem(k.getNama());
            }
        }
    }

    public void getTabelBarang(JTable tabel_barang) {
        list_barang = interfaceBarang.getData();
        TableModelBarang tmb = new TableModelBarang(list_barang);
        tabel_barang.setModel(tmb);

        String[] stringHeader = {"Id", "Nama Barang", "Kategori", "Harga", "Stok"};
        JTableHeader header = frame.getTb_barang().getTableHeader();
        TableColumn column;
        for (int i = 0; i < stringHeader.length; i++) {
            column = header.getColumnModel().getColumn(i);
            column.setHeaderValue(stringHeader[i]);
        }
        header.repaint();
    }

    public void insertBarang() {
        Barang barang = new Barang();
        barang.setNamaBarang(frame.getTxt_nama_barang().getText());
        Kategori kategori = new Kategori(frame.getCb_kategori().getSelectedItem().toString());
        barang.setKategori(kategori);
        barang.setHarga(Double.parseDouble(frame.getTxt_harga().getText()));
        barang.setStok(Integer.parseInt(frame.getTxt_stok().getText()));
        interfaceBarang.insert(barang);
        getTabelBarang(frame.getTb_barang());
        resetInput();
    }

    public void deleteBarang() {
        if (frame.getTb_barang().getModel().getRowCount() != 0) {
            int selectedBarangTransaksi = frame.getTb_barang().getSelectedRow();
            if (selectedBarangTransaksi != -1) {
                Object id = frame.getTb_barang().getValueAt(selectedBarangTransaksi, 0);
                int id_barang = (int) id;
                interfaceBarang.delete(id_barang);
                getTabelBarang(frame.getTb_barang());
                resetInput();
            } else {
                JOptionPane.showMessageDialog(null, "Tidak ada barang yang dipilih");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data kosong. Tidak bisa dihapus");
        }
    }

    public void showBarang() {
        Barang barang;
        if (frame.getTb_barang().getModel().getRowCount() != 0) {
            int selectedBarangTransaksi = frame.getTb_barang().getSelectedRow();
            if (selectedBarangTransaksi != -1) {
                Object id = frame.getTb_barang().getValueAt(selectedBarangTransaksi, 0);
                int id_barang = (int) id;
                barang = interfaceBarang.showBarang(id_barang);
                setTextBarang(barang);
            } else {
                JOptionPane.showMessageDialog(null, "Tidak ada barang yang dipilih");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data kosong. Tidak bisa dihapus");
        }
    }

    public void setTextBarang(Barang barang) {
        frame.getTxt_id().setText(String.valueOf(barang.getIdBarang()));
        frame.getTxt_nama_barang().setText(barang.getNamaBarang());
//        frame.getCb_kategori().setSelectedItem(barang.getKategori());
        frame.getTxt_harga().setText(String.valueOf(barang.getHarga()));
        frame.getTxt_stok().setText(String.valueOf(barang.getStok()));
        frame.getTxt_id().setText(String.valueOf(barang.getIdBarang()));
    }

    public void updateBarang(int id_barang) {
        Barang barang = new Barang();
        barang.setNamaBarang(frame.getTxt_nama_barang().getText());
        Kategori kategori = new Kategori(frame.getCb_kategori().getSelectedItem().toString());
        barang.setKategori(kategori);
        barang.setHarga(Double.parseDouble(frame.getTxt_harga().getText()));
        barang.setStok(Integer.parseInt(frame.getTxt_stok().getText()));
        barang.setIdBarang(id_barang);

        interfaceBarang.update(barang);
        getTabelBarang(frame.getTb_barang());
        resetInput();
    }

    public void tambahKategori() {
        ViewKategori kategori = new ViewKategori();
        kategori.setVisible(true);
        frame.setVisible(false);
    }

    public void logout() {
        frame.setVisible(false);
        ViewLogin login = new ViewLogin();
        login.setVisible(true);
    }

    public void resetInput(){
        frame.getTxt_id().setText("");
        frame.getTxt_nama_barang().setText("");
        frame.getTxt_harga().setText("");
        frame.getTxt_stok().setText("");
    }
    
}
