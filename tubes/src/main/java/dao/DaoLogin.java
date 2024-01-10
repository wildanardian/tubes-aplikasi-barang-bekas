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

/**
 *
 * @author wildan
 */
public class DaoLogin implements InterfaceLogin{

    Connection conn;
    final String loginQuery = "SELECT * FROM user WHERE username=? AND password=?";

    public DaoLogin() {
        this.conn = koneksi.connect();
    }
    
    @Override
    public boolean loginUser(String username, String password) {
        try (PreparedStatement stmt = conn.prepareStatement(loginQuery)){         
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet res = stmt.executeQuery();
            return res.next();
        } catch (SQLException e) {
            e.printStackTrace();         
        } 
        return false;
    }
    
}
