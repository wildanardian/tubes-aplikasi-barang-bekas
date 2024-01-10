/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import controller.koneksi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Barang;
import model.Pembayaran;
import model.Pembeli;
import model.Penjual;
import model.Transaksi;

/**
 *
 * @author wildan
 */
public class DaoTransaksi implements InterfaceTransaksi {

    Connection conn;
    private static final String INSERT = "INSERT INTO transaksi (tanggal_transaksi, penjual, pembeli) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE transaksi SET pembeli=?, penjual=?, metode_pembayaran=? WHERE id=?";
    private static final String SELECT = "SELECT * FROM transaksi WHERE id=?";

    private static final String SELECTBARANG = "SELECT * FROM barang_transaksi WHERE id_transaksi=?";
    private static final String SELECTBARANGDETAIL = "SELECT * FROM barang WHERE id=?";
    private static final String UPDATEBARANGTRANSAKSI = "UPDATE barang_transaksi SET id_transaksi=? WHERE id_transaksi IS NULL";

    public int key = -1;

    public DaoTransaksi() {
        this.conn = koneksi.connect();
    }

    @Override
    public Transaksi getTransaksi(int id) {
        Transaksi transaksi = null;
        try (PreparedStatement statement = conn.prepareStatement(SELECT)) {
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                transaksi = new Transaksi();
//                Date tanggalDb = res.getDate("tanggal_transaksi");
//                LocalDateTime tanggal = tanggalDb.toInstant().atZone(ZoneId.of("Asia/Jakarta")).toLocalDateTime();
//                transaksi.setTanggalTransaksi(tanggal);
                transaksi.setPenjual(new Penjual(res.getString("penjual")));
                transaksi.setPembeli(new Pembeli(res.getString("pembeli")));
            } else {
                JOptionPane.showMessageDialog(null, "Data dengan ID " + id + " tidak ditemukan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mendapatkan data" + e.getMessage());
        }
        return transaksi;
    }

    public void showKey() {
        JOptionPane.showMessageDialog(null, "Id Transaksi: " + key);
    }

    @Override
    public int insert(Transaksi transaksi) {
        int keyId = -1;
        try (PreparedStatement insertState = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            Date tanggalTransaksi = Date.valueOf(transaksi.getTanggalTransaksi().toLocalDate());
            insertState.setDate(1, tanggalTransaksi);
            insertState.setString(2, transaksi.getPenjual().getNama());
            insertState.setString(3, transaksi.getPembeli().getNama());
            int rowsAffected = insertState.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKey = insertState.getGeneratedKeys()) {
                    if (generatedKey.next()) {
                        keyId = generatedKey.getInt(1);
                        int idTransaksi = generatedKey.getInt(1);
                        try (PreparedStatement updateState = conn.prepareStatement(UPDATEBARANGTRANSAKSI)) {
                            updateState.setInt(1, idTransaksi);
                            int rows = updateState.executeUpdate();
                            if (rows > 0) {
                                JOptionPane.showMessageDialog(null, "Berhasil merubah data transaksi");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Gagal mengubah data transaksi barang, " + e.getMessage());
                        }
                        return keyId;
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Gagal menambahkan transaksi2, " + e.getMessage());

                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menambahkan transaksi" + e.getMessage());
        }
        return keyId;
    }

    @Override
    public void update(Transaksi transaksi) {
        try (PreparedStatement statement = conn.prepareStatement(UPDATE)) {
            statement.setString(1, transaksi.getPembeli().getNama());
            statement.setString(2, transaksi.getPenjual().getNama());
            statement.setString(3, transaksi.getMetodePembayaran().getNamaMetode());
            statement.setInt(4, transaksi.getIdTransaksi());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Pembayaran Berhasil");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal update data transaksi, " + e.getMessage());
        }
    }

    @Override
    public List<Barang> getListBarangTransaksi(int id) {
        List<Barang> list_bt = null;
        try (PreparedStatement statement = conn.prepareStatement(SELECTBARANG)) {
            list_bt = new ArrayList<>();
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                int idBarang = res.getInt("id_barang");

                try (PreparedStatement detailState = conn.prepareStatement(SELECTBARANGDETAIL)) {
                    detailState.setInt(1, idBarang);

                    try (ResultSet resDetail = detailState.executeQuery()) {
                        if (resDetail.next()) {
                            Barang barang = new Barang();
                            barang.setIdBarang(resDetail.getInt("id"));
                            barang.setNamaBarang(resDetail.getString("nama"));
                            barang.setHarga(resDetail.getDouble("harga"));
                            barang.setStok(resDetail.getInt("stok"));
                            list_bt.add(barang);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mendapatkan data");
        }
        return list_bt;
    }

}
