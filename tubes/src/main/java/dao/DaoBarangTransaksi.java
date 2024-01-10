 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import controller.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.BarangTransaksi;

/**
 *
 * @author wildan
 */
public class DaoBarangTransaksi implements InterfaceBarangTransaksi{

    Connection conn;
    
    private static final String INSERT = "INSERT INTO barang_transaksi (id_barang, id_transaksi, nama_barang) VALUES (?,?,?)";
    private static final String DELETE = "DELETE FROM barang_transaksi WHERE id=?";
    private static final String SELECT = "SELECT * FROM barang_transaksi WHERE id_transaksi=?";
    private static final String UPDATE = "UPDATE barang_transaksi SET id_transaksi=? WHERE id_transaksi=-1";
    
    public DaoBarangTransaksi() {
        conn = koneksi.connect();
    }
    
    @Override
    public void insertBarangTransaksi(BarangTransaksi barang) {
        try (PreparedStatement statement = conn.prepareStatement(INSERT)){
            statement.setInt(1, barang.getId_barang());
            statement.setInt(2, barang.getId_transaksi());
            statement.setString(3, barang.getNama_barang());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Barang Berhasil Ditambahkan");
        } catch (SQLException e) {   
        }
    }

    @Override
    public void deleteBarangTransaksi(int id) {
        try (PreparedStatement statement = conn.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Barang Transaksi Terhapus");
        } catch (SQLException e) {   
        }
    }

    @Override
    public List<BarangTransaksi> getListTransaksi(int id_transaksi) {
        List<BarangTransaksi> list_barang_transaksi = null;
        try {
            list_barang_transaksi = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(SELECT);
            statement.setInt(1, id_transaksi);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                BarangTransaksi barang = new BarangTransaksi();
                barang.setId(res.getInt("id"));
                barang.setNama_barang(res.getString("nama_barang"));
                list_barang_transaksi.add(barang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list_barang_transaksi;
    }

    @Override
    public void updateBarangTransaksi(int id) {
        try (PreparedStatement statement = conn.prepareStatement(UPDATE)){
            statement.setInt(1,id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Barang Transaksi Ter-update");
        } catch (SQLException e) {   
        }
    }
    
}
