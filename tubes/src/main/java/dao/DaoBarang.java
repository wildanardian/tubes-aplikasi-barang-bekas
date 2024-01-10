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
import model.Barang;
import model.Kategori;

/**
 *
 * @author wildan
 */
public class DaoBarang implements InterfaceBarang {

    Connection conn;
    private static final String INSERT = "INSERT INTO barang (nama, kategori, harga, stok) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE barang SET nama=?, kategori=?, harga=?, stok=? WHERE id=?";
    private static final String DELETE = "DELETE FROM barang WHERE id=?";
    private static final String SELECT = "SELECT * FROM barang";
    private static final String SHOW = "SELECT * FROM barang WHERE id=?";
    private static final String SELECT_BARANG = "SELECT * FROM barang WHERE kategori=?";
    private static final String UPDATE_STOK = "UPDATE barang SET stok=? WHERE id=?";

    public DaoBarang() {
        this.conn = koneksi.connect();
    }

    @Override
    public void insert(Barang barang) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(INSERT);
            statement.setString(1, barang.getNamaBarang());
            statement.setString(2, barang.getKategori());
            statement.setDouble(3, barang.getHarga());
            statement.setInt(4, barang.getStok());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Barang Berhasil Ditambahkan");
        } catch (SQLException e) {

        }
    }

    @Override
    public void update(Barang barang) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(UPDATE);
            statement.setString(1, barang.getNamaBarang());
            statement.setString(2, barang.getKategori());
            statement.setDouble(3, barang.getHarga());
            statement.setInt(4, barang.getStok());
            statement.setInt(5, barang.getIdBarang());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (SQLException e) {
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(DELETE);
            statement.setInt(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    @Override
    public List<Barang> getData() {
        List<Barang> list_barang = null;
        try {
            list_barang = new ArrayList<>();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(SELECT);
            while (res.next()) {
                Barang barang = new Barang();
                barang.setIdBarang(res.getInt("id"));
                barang.setNamaBarang(res.getString("nama"));
                Kategori kategori = new Kategori(res.getString("kategori"));
                barang.setKategori(kategori);
                barang.setHarga(res.getDouble("harga"));
                barang.setStok(res.getInt("stok"));
                list_barang.add(barang);
            }
        } catch (SQLException e) {
        }

        return list_barang;
    }

    @Override
    public Barang showBarang(int id) {
        Barang barang = new Barang();
        try (PreparedStatement statement = conn.prepareStatement(SHOW)) {
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();

            if (res.next()) {
                barang.setIdBarang(res.getInt("id"));
                barang.setNamaBarang(res.getString("nama"));
                Kategori kategori = new Kategori(res.getString("kategori"));
                barang.setKategori(kategori);
                barang.setHarga(res.getDouble("harga"));
                barang.setStok(res.getInt("stok"));
            } else {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan: ");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return barang;
    }

    @Override
    public List<Barang> searchBarangByKategori(String namaBarang) {
        List<Barang> list_barang = null;
        try {
            list_barang = new ArrayList<>();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(SELECT_BARANG);
            while (res.next()) {
                Barang barang = new Barang();
                barang.setIdBarang(res.getInt("id"));
                barang.setNamaBarang(res.getString("nama"));
                Kategori kategori = new Kategori(res.getString("kategori"));
                barang.setKategori(kategori);
                barang.setHarga(res.getDouble("harga"));
                barang.setStok(res.getInt("stok"));
                list_barang.add(barang);
            }
        } catch (SQLException e) {
        }

        return list_barang;
    }

    @Override
    public void updateStokBarang( int stokBaru, int id) {
        try (PreparedStatement statement = conn.prepareStatement(UPDATE_STOK)) {
            statement.setInt(1, stokBaru);
            statement.setInt(2, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                
            } else {
                JOptionPane.showMessageDialog(null, "Gagal update stok barang");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

}
