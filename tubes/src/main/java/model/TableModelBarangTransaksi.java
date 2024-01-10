/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author wildan
 */
public class TableModelBarangTransaksi extends AbstractTableModel{

    List<BarangTransaksi> list_barang_transaksi;

    public TableModelBarangTransaksi(List<BarangTransaksi> list_barang_transaksi) {
        this.list_barang_transaksi = list_barang_transaksi;
    }
    
    @Override
    public int getRowCount() {
        return list_barang_transaksi.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return list_barang_transaksi.get(rowIndex).getNama_barang();
            default:
                return null;
        }
    }
    
}
