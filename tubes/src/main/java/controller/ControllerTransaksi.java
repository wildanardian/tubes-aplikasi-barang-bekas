/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DaoBarang;
import dao.DaoBarangTransaksi;
import dao.DaoTransaksi;
import dao.InterfaceBarang;
import dao.InterfaceBarangTransaksi;
import dao.InterfaceTransaksi;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import model.Barang;
import model.BarangTransaksi;
import model.Pembayaran;
import model.Pembeli;
import model.Penjual;
import model.Transaksi;
import view.ViewBarang;
import view.ViewLogin;
import view.ViewTransaksi;

/**
 *
 * @author wildan
 */
public class ControllerTransaksi {

    ViewTransaksi frame;
    InterfaceTransaksi interfaceTransaksi;
    List<Barang> list_barang_transaksi;

    InterfaceBarangTransaksi interfaceBarangTransaksi;
    List<BarangTransaksi> list_barang_t;
    
    InterfaceBarang interfaceBarang;

    public ControllerTransaksi(ViewTransaksi frame) {
        this.frame = frame;
        interfaceTransaksi = new DaoTransaksi();
        interfaceBarangTransaksi = new DaoBarangTransaksi();
        //id untuk masing-masing transaksi
        interfaceBarang = new DaoBarang();
    }

    public void getTransaksi(int id) {
        Transaksi transaksi = interfaceTransaksi.getTransaksi(id);
//        if (transaksi != null && transaksi.getTanggalTransaksi() != null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//            String tanggal = transaksi.getTanggalTransaksi().format(formatter);
//            frame.getTgl_value().setText(tanggal);
//            frame.getPembeli_value().setText(transaksi.getPembeli().getNama());
//            frame.getPenjual_value().setText(transaksi.getPenjual().getNama());
//        } else {
//            frame.getTgl_value().setText("Data tidak tersedia");
//        }

        frame.getPembeli_value().setText(transaksi.getPembeli().getNama());
        frame.getPenjual_value().setText(transaksi.getPenjual().getNama());
    }

    public void getListBarang(int id) {
        double totalHarga = 0.0;
        list_barang_transaksi = interfaceTransaksi.getListBarangTransaksi(id);
        //tampilkan data di dalam sebuah Jlist
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Barang barang : list_barang_transaksi) {
            listModel.addElement(barang.getNamaBarang());
            totalHarga += barang.getHarga();
        }
        frame.getList_barang().setModel(listModel);
        frame.getHarga_value().setText("Rp." + totalHarga);

    }

    public void bayarTransaksi(int id) {
        String metode_pembayaran = frame.getMetode_bayar().getSelectedItem().toString();
        Transaksi transaksi = new Transaksi();
        Pembayaran pembayaran = new Pembayaran();
        pembayaran.setNamaMetode(metode_pembayaran);

        transaksi.setPenjual(new Penjual(frame.getPenjual_value().getText()));
        transaksi.setPembeli(new Pembeli(frame.getPembeli_value().getText()));
        transaksi.setIdTransaksi(id);
        transaksi.setMetodePembayaran(pembayaran);
        interfaceTransaksi.update(transaksi);
        
        list_barang_transaksi = interfaceTransaksi.getListBarangTransaksi(id);
        int stokBaru;
        for(Barang barang : list_barang_transaksi){
            stokBaru = barang.getStok() - 1;
            interfaceBarang.updateStokBarang(stokBaru, barang.getIdBarang());
        }

        int response = JOptionPane.showOptionDialog(null,
                "Apa yang ingin Anda lakukan selanjutnya?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, new Object[]{"Kembali Belanja", "Keluar dari Aplikasi"},
                "Kembali Belanja");

        if (response == JOptionPane.YES_OPTION) {
            ViewBarang barang = new ViewBarang(frame.getPembeli_value().getText());
            barang.setVisible(true);
            frame.setVisible(false);
        } else if (response == JOptionPane.NO_OPTION) {
            ViewLogin login = new ViewLogin();
            login.setVisible(true);
            frame.setVisible(false);
        }
    }

}
