/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DaoLogin;
import dao.InterfaceLogin;
import view.ViewBarang;
import view.ViewLogin;
import view.ViewToko;

/**
 *
 * @author wildan
 */
public class ControllerLogin {
    ViewLogin frame;
    InterfaceLogin interfaceLogin;

    public ControllerLogin(ViewLogin frame) {
        this.frame = frame;
        interfaceLogin = new DaoLogin();
    }
    
    public void login(){
        String username = frame.getTxt_username().getText();
        String password = frame.getTxt_password().getText();
        if(interfaceLogin.loginUser(username, password)){
            if("penjual".equals(username)){
                ViewToko toko = new ViewToko(username);
                toko.setVisible(true);
                frame.setVisible(false);
            }else {
                ViewBarang barang = new ViewBarang(username);
                barang.setVisible(true);
                frame.setVisible(false);
            }
        }
    }
    
    public void reset(){
        frame.getTxt_username().setText("");
        frame.getTxt_password().setText("");
    }
}
