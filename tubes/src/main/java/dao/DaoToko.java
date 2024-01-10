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
import java.util.List;
import model.Barang;
import model.Toko;

/**
 *
 * @author wildan
 */
public class DaoToko implements InterfaceToko {

    Connection conn;
    static final String INSERT = "INSERT INTO toko (nama_toko, nama_pemilik) VALUES (?,?,?,?)";
    static final String UPDATE = "UPDATE toko SET nama_toko=?, nama_pemilik=? WHERE id=?";
    static final String SELECT = "SELECT nama_pemilik FROM toko";
    static final String SHOW = "SELECT * FROM toko WHERE id=?";
    static final String SEARCH_TOKO_ID = "SELECT id FROM toko WHERE nama_pemilik=?";

    public DaoToko() {
        this.conn = koneksi.connect();
    }
    
    @Override
    public Toko getDetailToko(int id) {
        Toko toko = new Toko();
        try (PreparedStatement stmt = conn.prepareStatement(SHOW)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                // Ambil nilai dari hasil query dan lakukan sesuai kebutuhan
                int tokoId = resultSet.getInt("id");
                String namaToko = resultSet.getString("nama");
                String namaPemilik = resultSet.getString("nama_pemilik");

                toko.setIdToko(tokoId);
                toko.setNamaToko(namaToko);
                toko.setNamaPemilik(namaPemilik);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Tangani kesalahan SQL
        }
        return toko;
    }

    @Override
    public void searchToko(String namaPemilik) {
        try (PreparedStatement stmt = conn.prepareStatement(SEARCH_TOKO_ID)) {
            stmt.setString(1, namaPemilik);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                // Ambil nilai ID dari hasil query SEARCH_TOKO_ID
                int tokoId = resultSet.getInt("id");

                // Panggil metode getDetailToko dengan ID yang ditemukan
                getDetailToko(tokoId);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Tangani kesalahan SQL
        }
    }

    @Override
    public String getNamaPemilikToko() {
        try (PreparedStatement stmt = conn.prepareStatement(SELECT); ResultSet resultSet = stmt.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getString("nama_pemilik");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Tangani kesalahan SQL
        }

        return null;
    }

}
