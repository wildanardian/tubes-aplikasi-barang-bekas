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
public class TableModelBarang extends AbstractTableModel {

    List<Barang> list_barang;

    public TableModelBarang(List<Barang> list_barang) {
        this.list_barang = list_barang;
    }
    
    @Override
    public int getRowCount() {
        return list_barang.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list_barang.get(rowIndex).getIdBarang();
            case 1:
                return list_barang.get(rowIndex).getNamaBarang();
            case 2: 
                return list_barang.get(rowIndex).getKategori();
            case 3:
                return list_barang.get(rowIndex).getHarga();
            case 4:
                return list_barang.get(rowIndex).getStok();
            default:
                return null;
        }
    }
    
}
