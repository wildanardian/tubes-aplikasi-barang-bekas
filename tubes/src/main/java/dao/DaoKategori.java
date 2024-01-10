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
import model.Kategori;

/**
 *
 * @author wildan
 */
public class DaoKategori implements InterfaceKategori {

    Connection conn;
    static final String INSERT = "INSERT INTO kategori (nama) VALUES (?)";
    static final String SELECT = "SELECT * FROM kategori";

    public DaoKategori() {
        this.conn = koneksi.connect();
    }
    
    @Override
    public void insert(Kategori kategori) {
        try (PreparedStatement statement = conn.prepareStatement(INSERT)){
            statement.setString(1, kategori.getNama());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kategori Berhasil Ditambahkan");
        } catch (SQLException e) {   
            JOptionPane.showMessageDialog(null, "Kategori Gagal Ditambahkan, " + e.getMessage());
        }
    }

    @Override
    public List<Kategori> getKategori() {
        List<Kategori> list_kategori = null;
        try {
            list_kategori = new ArrayList<>();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(SELECT);
            while(res.next()){
                Kategori kategori = new Kategori(res.getString("nama"));
                list_kategori.add(kategori);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mendapatkan kategori, " + e.getMessage());
        }
        return list_kategori;
    }
    
}
