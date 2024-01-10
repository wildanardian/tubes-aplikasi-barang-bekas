/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.InterfaceLogin;

/**
 *
 * @author wildan
 */
public class User {
    private int idUser;
    private String nama;
    private String email;
    private int no_hp;
    private String alamat;
    private String username;
    private String password;

    public User(String nama){
        this.nama = nama;
    }
    
    public User(int idUser, String nama, String email, int no_hp, String alamat) {
        this.idUser = idUser;
        this.nama = nama;
        this.email = email;
        this.no_hp = no_hp;
        this.alamat = alamat;
    }
    
    //Getter
    public int getIdUser() {
        return idUser;
    }
    
    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public int getNo_hp() {
        return no_hp;
    }

    public String getAlamat() {
        return alamat;
    }
    
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    
    public String tampilkanProfil(){
        return this.getNama();
    }
    
    
    //Setter

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNo_hp(int no_hp) {
        this.no_hp = no_hp;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
