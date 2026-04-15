/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author ofacu
 */
public class NotificadorEmail implements Observer {

    private String usuario;

    public NotificadorEmail(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("Email a " + usuario + ": " + mensaje);
    }
}