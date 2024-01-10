/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author wildan
 */
public class koneksi {
    
    static Connection conn;
    
    public static Connection connect(){
        if(conn == null){
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("barang_bekas");
            data.setUser("root");
            data.setPassword("");
            try {
                conn = data.getConnection();
            } catch (SQLException e) {
            }
        }
        return conn;
    }
}
